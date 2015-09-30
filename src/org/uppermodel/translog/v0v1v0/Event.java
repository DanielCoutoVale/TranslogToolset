package org.uppermodel.translog.v0v1v0;

import javax.xml.xpath.XPath;

import org.uppermodel.translog.TranslogEvent;
import org.uppermodel.translog.TranslogProduct;
import org.w3c.dom.Element;

/**
 * An event for Document v0.1.0.
 * 
 * @author Daniel Couto-Vale <danielvale@icloud.com>
 */
public class Event implements TranslogEvent {

	/**
	 * The DOM element
	 */
	protected Element element;

	/**
	 * The xPath
	 */
	protected XPath xPath;

	/**
	 * Constructor
	 * 
	 * @param element the DOM element
	 */
	public Event(Element element, XPath xPath) {
		this.element = element;
		this.xPath = xPath;
	}

	/**
	 * Gets time
	 * 
	 * @return the time
	 */
	public final int getTime() {
		return Integer.parseInt(element.getAttribute("Time"));
	}

	@Override
	public TranslogProduct newProduct(TranslogProduct product) {
		return product;
	}

	public static Event makeNode(Element element, XPath xPath) {
		if (element.getTagName().equals("System")) {
			return new SystemEvent(element, xPath);
		}
		if (element.getTagName().equals("Key")) {
			return new KeyEvent(element, xPath);
		}
		if (element.getTagName().equals("Mouse")) {
			return new MouseEvent(element, xPath);
		}
		if (element.getTagName().equals("Interface")) {
			return new InterfaceEvent(element, xPath);
		}
		if (element.getTagName().equals("Stimulus")) {
			return new StimulusEvent(element, xPath);
		}
		if (element.getTagName().equals("Select")) {
			return new SelectEvent(element, xPath);
		}
		return null;
	}

}
