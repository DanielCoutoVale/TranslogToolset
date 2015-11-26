package org.uppermodel.translog.typing;

import static org.junit.Assert.*;
import static org.uppermodel.translog.typing.TranslogTypingUtils.loadDocument;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.uppermodel.translog.typing.dto.WritingEvent;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TypingPauseEstimatorTest {

	@Test
	public void estimate() throws ParserConfigurationException, SAXException, IOException {
		Document document = loadDocument("/Users/DanielVale/Sciebo/Eye-tracking/Baseline Experiment/Analysis/Translogs/1_Participants/Data/1_A01.xml");
		Element eventsElm = (Element) document.getElementsByTagName("Events").item(0);
		NodeList eventNodes = eventsElm.getChildNodes();
		CharInsertClassifier classifier = new GermanCharInsertClassifier();
		WritingEventFactory factory = new WritingEventFactory(classifier);
		List<WritingEvent> writingEvents = factory.makeWritingEvents(eventNodes);

		// How to use the estimator
		TypingPauseEstimator estimator = new TypingPauseEstimator();
		estimator.estimate(writingEvents);
		
		// Assert results
		assertNull(writingEvents.get(0).prevPause.typingPause);
		assertNull(writingEvents.get(1).prevPause.typingPause);
		assertNull(writingEvents.get(2).prevPause.typingPause);
		assertNull(writingEvents.get(3).prevPause.typingPause);
		assertEquals(1, writingEvents.get(4).prevPause.typingPause.length);
		assertNull(writingEvents.get(5).prevPause.typingPause);
		assertNull(writingEvents.get(6).prevPause.typingPause);
		assertNull(writingEvents.get(7).prevPause.typingPause);
		assertNull(writingEvents.get(8).prevPause.typingPause);
		assertNull(writingEvents.get(9).prevPause.typingPause);
		assertEquals(0, writingEvents.get(10).prevPause.typingPause.length);
		assertNull(writingEvents.get(11).prevPause.typingPause);
		assertNull(writingEvents.get(12).prevPause.typingPause);
		assertNull(writingEvents.get(13).prevPause.typingPause);
		assertEquals(0, writingEvents.get(14).prevPause.typingPause.length);
		assertNull(writingEvents.get(15).prevPause.typingPause);
		assertNull(writingEvents.get(16).prevPause.typingPause);
		assertNull(writingEvents.get(17).prevPause.typingPause);
		assertNull(writingEvents.get(18).prevPause.typingPause);
		assertNull(writingEvents.get(19).prevPause.typingPause);
		assertEquals(2, writingEvents.get(20).prevPause.typingPause.length);
	}
}
