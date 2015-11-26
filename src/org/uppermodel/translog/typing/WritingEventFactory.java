package org.uppermodel.translog.typing;

import java.util.LinkedList;
import java.util.List;

import org.uppermodel.translog.typing.dto.CharInsertEvent;
import org.uppermodel.translog.typing.dto.WritingEvent;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * A factory of writing events.
 * 
 * @author Daniel Couto-Vale <daniel.couto-vale@ifaar.rwth-aachen.de>
 */
public class WritingEventFactory {

	/**
	 * The "Key" node name.
	 */
	private static final String KEY = "Key".intern();

	/**
	 * The char-insert classifier.
	 */
	private final CharInsertClassifier classifier;

	/**
	 * The writing events exporter.
	 * 
	 * @param classifier the classifier
	 */
	public WritingEventFactory(CharInsertClassifier classifier) {
		this.classifier = new GermanCharInsertClassifier();
	}

	/**
	 * Export a chart insert event
	 * 
	 * @param id the id of the event
	 * @param elm the element
	 * @return the chart insert event
	 */
	public final CharInsertEvent makeCharInsertEvent(int id, Element elm) {
		CharInsertEvent event = new CharInsertEvent();
		event.id = Integer.toString(id);
		event.time = Long.parseLong(elm.getAttribute("Time"));
		if (elm.getAttribute("Value").equals("")) {
			event.character = '`'; // Empirically based guess
		} else {
			event.character = elm.getAttribute("Value").charAt(0);
		}
		event.subclass = classifier.classify(event);
		return event;
	}

	/**
	 * Makes writing events.
	 * 
	 * @param eventNodes the event nodes.
	 * @return the writing events
	 */
	public final List<WritingEvent> makeWritingEvents(NodeList eventNodes) {
		List<WritingEvent> events = new LinkedList<WritingEvent>();
		for (int i = 0; i < eventNodes.getLength(); i++) {
			Node node = eventNodes.item(i);
			if (node.getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}
			Element elm = (Element) node;
			if (KEY == elm.getNodeName().intern()) {
				CharInsertEvent event = makeCharInsertEvent(i, elm);
				events.add(event);
			}
		}
		return events;
	}
}
