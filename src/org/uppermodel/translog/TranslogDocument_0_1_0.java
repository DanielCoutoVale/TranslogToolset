package org.uppermodel.translog;

import java.util.LinkedList;
import java.util.List;

import org.uppermodel.translog.v0v1v0.Event;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * Translog document v.0.1.0
 * 
 * @author Daniel Couto-Vale <danielvale@icloud.com>
 */
public class TranslogDocument_0_1_0 implements TranslogDocument {

	private static final String VERSION_EXP = "/LogFile/VersionString";

	private static final String TRANSLATOR_ID_EXP = "/LogFile/Subject";

	private static final String SOURCE_TEXT_EXP = "/LogFile/Project/Interface/Standard/Settings/SourceTextUTF8";
	
	private static final String TARGET_TEXT_EXP = "/LogFile/Project/Interface/Standard/Settings/TargetTextUTF8";
	
	private static final String FINAL_TARGET_TEXT_EXP = "/LogFile/FinalTextChar/CharPos";

	private static final String EVENTS_EXP = "/LogFile/Events/*";

	/**
	 * The DOM document
	 */
	private Document document;

	/**
	 * The xPath object
	 */
	private final XPath xPath;

	private XPathExpression versionExp;
	private XPathExpression translatorIdExp;
	private XPathExpression sourceTextExp;
	private XPathExpression targetTextExp;
	private XPathExpression finalTargetTextExp;
	private XPathExpression eventsExp;

	/**
	 * Constructor
	 * 
	 * @param document the DOM document
	 */
	public TranslogDocument_0_1_0(Document document) {
		this.document = document;
		this.xPath =  XPathFactory.newInstance().newXPath();
		try {
			this.versionExp = this.xPath.compile(VERSION_EXP);
			this.translatorIdExp = this.xPath.compile(TRANSLATOR_ID_EXP);
			this.sourceTextExp = this.xPath.compile(SOURCE_TEXT_EXP);
			this.targetTextExp = this.xPath.compile(TARGET_TEXT_EXP);
			this.finalTargetTextExp = this.xPath.compile(FINAL_TARGET_TEXT_EXP);
			this.eventsExp = this.xPath.compile(EVENTS_EXP);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public final String getVersion() {
		return extractString(versionExp);
	}

	@Override
	public final String getTranslatorId() {
		return extractString(translatorIdExp);
	}

	@Override
	public final String getSourceText() {
		return extractString(sourceTextExp);
	}

	@Override
	public final String getInitialTargetText() {
		return extractString(targetTextExp);
	}

	@Override
	public final String getFinalTargetText() {
		try {
			return getFinalTargetTextImpl();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
			return "";
		}
	}

	private final String getFinalTargetTextImpl() throws XPathExpressionException {
		StringBuffer buffer = new StringBuffer();
		NodeList nodeList = (NodeList) finalTargetTextExp.evaluate(document, XPathConstants.NODESET);
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element)nodeList.item(i);
			String value = element.getAttribute("Value");
			if (value.equals("\n")) {
				value = "␤";
			}
			buffer.append(value);
		}
		return buffer.toString();
	}

	@Override
	public final List<TranslogEvent> getEvents() {
		try {
			return getEventListImp();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
			return new LinkedList<TranslogEvent>();
		}
	}

	private final List<TranslogEvent> getEventListImp() throws XPathExpressionException {
		List<TranslogEvent> events = new LinkedList<TranslogEvent>();
		NodeList nodeList = (NodeList) eventsExp.evaluate(document, XPathConstants.NODESET);
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element)nodeList.item(i);
			events.add(Event.makeNode(element, xPath));
		}
		return events;
	}

	private final String extractString(XPathExpression exp) {
		try {
			return extractStringImp(exp);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
			return null;
		}
	}

	private final String extractStringImp(XPathExpression exp) throws XPathExpressionException {
		NodeList nodeList = (NodeList) exp.evaluate(document, XPathConstants.NODESET);
		Node node = nodeList.item(0).getFirstChild();
		return node == null ? "" : node.getNodeValue();
	}

	@Override
	public boolean isCompliant() {
		return document.getDocumentElement().getNodeName().equals("LogFile");
	}

}
