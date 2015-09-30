package org.uppermodel.translog.v0v1v0;

import javax.xml.xpath.XPath;

import org.w3c.dom.Element;

/**
 * A system event for Document v0.1.0.
 * 
 * @author Daniel Couto-Vale <danielvale@icloud.com>
 */
public class SystemEvent extends Event {

	/**
	 * Constructor
	 * 
	 * @param element the DOM element
	 * @param xPath the xPath
	 */
	public SystemEvent(Element element, XPath xPath) {
		super(element, xPath);
	}

}
