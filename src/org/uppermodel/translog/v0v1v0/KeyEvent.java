package org.uppermodel.translog.v0v1v0;

import java.util.LinkedList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;

import org.uppermodel.translog.TranslogProduct;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * A key event for Document v0.1.0.
 * 
 * @author Daniel Couto-Vale <danielvale@icloud.com>
 */
public class KeyEvent extends Event {

	private final static String FOLLOWING_SIBLING_EXP = "following-sibling::*";
	private XPathExpression followingSiblingExp;

	/**
	 * Constructor
	 * 
	 * @param element the DOM element
	 * @param xPath the xPath
	 */
	public KeyEvent(Element element, XPath xPath) {
		super(element, xPath);
		try {
			followingSiblingExp = xPath.compile(FOLLOWING_SIBLING_EXP);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public final TranslogProduct newProduct(TranslogProduct product) {
		if (isInsert()) {
			int dot = product.getDot();
			if (dot != getCursor()) {
				System.out.println(dot + " " + getCursor());
			}
			int size = getValue().length();
			int mark = product.getMark();
			int min = dot > mark ? mark : dot;
			int max = dot > mark ? dot : mark;
			String text = product.getText();
			String newText = text.substring(0, min) + getValue();
			if (max < text.length()) {
				newText += text.substring(max);
			}
			return new TranslogProduct(dot + size, dot + size, newText);
		}
		if (isDelete()) {
			if (isBackDelete()) {
				int block = getBlock();
				int dot = product.getDot();
				int mark = product.getMark();
				if (block == 1) {
					dot  = product.getDot() - 1;
					mark = product.getDot();
				}
				String text = product.getText();
				text = text.substring(0, dot) + text.substring(mark);
				return new TranslogProduct(dot, dot, text);
			}
			if (isForwardDelete()) {
				int block = getBlock();
				int dot = product.getDot();
				int mark = product.getMark();
				String text = product.getText();
				text = text.substring(0, dot) + text.substring(dot + block);
				return new TranslogProduct(dot, mark, text);
			}
		}
		if (isNavi()) {
			if (isLeftNavi()) {
				int dot = product.getDot();
				int mark = product.getMark();
				String text = product.getText();
				dot = dot > 0 ? dot - 1 : 0;
				mark = mark > 0 ? mark - 1 : 0;
				return new TranslogProduct(dot, mark, text);
			}
			if (isRightNavi()) {
				int dot = product.getDot();
				int mark = product.getMark();
				String text = product.getText();
				dot = dot < text.length() ? dot + 1 : text.length();
				mark = mark < text.length() ? mark + 1 : text.length();
				return new TranslogProduct(dot, mark, text);
			}
			if (isDownNavi() || isUpNavi()) {
				int dot = product.getDot();
				int mark = product.getMark();
				String text = product.getText();
				List<Event> events = getFollowingSiblings();
				if (events.size() > 0) {
					Event event = events.get(0);
					if (event instanceof KeyEvent) {
						KeyEvent keyEvent = (KeyEvent)event;
						if (keyEvent.isInsert() || keyEvent.isLeftNavi()) {
							dot = keyEvent.getCursor();
							mark = keyEvent.getCursor();
						}
						if (keyEvent.isBackDelete()) {
							dot = keyEvent.getCursor() + 1;
							mark = keyEvent.getCursor() + 1;
						}
					}
				}
				return new TranslogProduct(dot, mark, text);
			}
		}
		return product;
	}

	private final int getCursor() {
		return Integer.parseInt(element.getAttribute("Cursor"));
	}

	private final List<Event> getFollowingSiblings() {
		try {
			return getFollowingSiblingsImp();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
			return new LinkedList<Event>();
		}
	}

	private final List<Event> getFollowingSiblingsImp() throws XPathExpressionException {
		List<Event> events = new LinkedList<Event>();
		NodeList nodeList = (NodeList) followingSiblingExp.evaluate(element, XPathConstants.NODESET);
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);
			events.add(Event.makeNode(element, xPath));
		}
		return events;
	}

	private final boolean isLeftNavi() {
		return isNavi() && "[Left]".equals(getValue());
	}

	private final boolean isRightNavi() {
		return isNavi() && "[Right]".equals(getValue());
	}

	private final boolean isDownNavi() {
		return isNavi() && "[Down]".equals(getValue());
	}

	private final boolean isUpNavi() {
		return isNavi() && "[Up]".equals(getValue());
	}

	private final boolean isNavi() {
		return "navi".equals(getType());
	}

	private final boolean isReturn() {
		return "return".equals(getType());
	}

	private final int getBlock() {
		return Integer.parseInt(element.getAttribute("Block"));
	}

	private final boolean isForwardDelete() {
		return isDelete() && "[Delete]".equals(getValue());
	}

	private final boolean isBackDelete() {
		return isDelete() && "[Back]".equals(getValue());
	}

	private final boolean isDelete() {
		return "delete".equals(getType());
	}

	private final boolean isInsert() {
		return isReturn() || "insert".equals(getType());
	}

	private final String getType() {
		return element.getAttribute("Type");
	}

	private final String getValue() {
		if (isReturn()) {
			return "␤";
		}
		return element.getAttribute("Value");
	}

}
