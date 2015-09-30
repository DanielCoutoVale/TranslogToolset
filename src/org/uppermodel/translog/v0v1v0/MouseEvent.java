package org.uppermodel.translog.v0v1v0;

import javax.xml.xpath.XPath;

import org.uppermodel.translog.TranslogProduct;
import org.w3c.dom.Element;

/**
 * A mouse event for Document v0.1.0.
 * 
 * @author Daniel Couto-Vale <danielvale@icloud.com>
 */
public class MouseEvent extends Event {

	/**
	 * Constructor
	 * 
	 * @param element the DOM element
	 * @param xPath the xPath
	 */
	public MouseEvent(Element element, XPath xPath) {
		super(element, xPath);
	}

	@Override
	public final TranslogProduct newProduct(TranslogProduct product) {
		if (isDown() || isUp()) {
			int dot = getCursor();
			String text = product.getText();
			dot = dot < text.length() ? dot : text.length();
			dot = dot > 0 ? dot : 0;
			int mark = dot + getBlock();
			return new TranslogProduct(dot, mark, text);
		}
		return product;
	}

	private final int getBlock() {
		if (element.hasAttribute("Block")) {
			return Integer.parseInt(element.getAttribute("Block"));
		} else {
			return 0;
		}
	}

	private final boolean isDown() {
		return "Down".equals(element.getAttribute("Value"));
	}

	private final boolean isUp() {
		return "Up".equals(element.getAttribute("Value"));
	}

	private final int getCursor() {
		return Integer.parseInt(element.getAttribute("Cursor"));
	}

}
