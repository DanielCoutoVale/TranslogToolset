package org.uppermodel.translog.v0v1v0;

import javax.xml.xpath.XPath;

import org.uppermodel.translog.TranslogProduct;
import org.w3c.dom.Element;

/**
 * A select event for Document v0.1.0.
 * 
 * @author Daniel Couto-Vale <danielvale@icloud.com>
 */
public class SelectEvent extends Event {

	/**
	 * Constructor
	 * 
	 * @param element the DOM element
	 * @param xPath the xPath
	 */
	public SelectEvent(Element element, XPath xPath) {
		super(element, xPath);
	}

	public final int getDot() {
		return Integer.parseInt(element.getAttribute("Dot"));
	}

	public final int getMark() {
		return Integer.parseInt(element.getAttribute("Mark"));
	}

	public final TranslogProduct newProduct(TranslogProduct product) {
		int dot = getDot();
		int mark = getMark();
		String text = product.getText();
		return new TranslogProduct(dot, mark, text);
	}

}
