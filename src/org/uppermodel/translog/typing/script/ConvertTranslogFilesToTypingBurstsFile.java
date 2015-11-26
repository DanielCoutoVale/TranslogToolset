package org.uppermodel.translog.typing.script;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.uppermodel.translog.typing.GermanCharInsertClassifier;
import org.uppermodel.translog.typing.TypingPauseCounter;
import org.uppermodel.translog.typing.WritingEventFactory;
import org.uppermodel.translog.typing.dto.CharInsertEvent;
import org.uppermodel.translog.typing.dto.WritingEvent;
import org.uppermodel.translog.typing.dto.WritingPause;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import static org.uppermodel.translog.typing.TranslogTypingUtils.*;

/**
 * Converts a set of translog files into a typing burst file.
 *
 * @author Daniel Couto-Vale <daniel.couto-vale@ifaar.rwth-aachen.de>
 */
public class ConvertTranslogFilesToTypingBurstsFile implements Runnable {

	private PrintWriter out;

	private long pauseA0;

	private long pauseB0;

	private long pauseC0;

	private long pauseA7;

	private long pauseB7;

	private long pauseC7;

	private long pauseA8;

	private long pauseB8;

	private long pauseC8;

	private long pauseA9;

	private long pauseB9;

	private long pauseC9;

	private long pauseAA;

	private long pauseBA;

	private long pauseCA;

	private long pauseAB;

	private long pauseBB;

	private long pauseCB;

	private long pauseAC;

	private long pauseBC;

	private long pauseCC;

	private long pauseAD;

	private long pauseBD;

	private long pauseCD;

	private long pauseAE;

	private long pauseBE;

	private long pauseCE;

	private long pauseAF;

	private long pauseBF;

	private long pauseCF;

	private long pauseXM;

	private List<WritingEvent> writingEvents;

	private String filePath;

	/**
	 * Constructor
	 * 
	 * @param filePath the xml file path
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public ConvertTranslogFilesToTypingBurstsFile(String filePath, PrintWriter out) throws ParserConfigurationException, SAXException, IOException {
		this.filePath = filePath;
		Document document = loadDocument(filePath);
		Element eventsElm = (Element) document.getElementsByTagName("Events").item(0);
		NodeList eventNodes = eventsElm.getChildNodes();
		GermanCharInsertClassifier classifier = new GermanCharInsertClassifier();
		WritingEventFactory factory = new WritingEventFactory(classifier);
		writingEvents = factory.makeWritingEvents(eventNodes);
		calcBases();
		this.out = out;
	}

	@Override
	public void run() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n");
		buffer.append(this.filePath + "\n");
		buffer.append("\n");
		try {
			StringBuffer pause0 = new StringBuffer("");
			StringBuffer pause7 = new StringBuffer("");
			StringBuffer pause8 = new StringBuffer("");
			StringBuffer pause9 = new StringBuffer("");
			StringBuffer pauseA = new StringBuffer("");
			StringBuffer pauseB = new StringBuffer("");
			StringBuffer pauseC = new StringBuffer("");
			StringBuffer pauseD = new StringBuffer("");
			StringBuffer pauseE = new StringBuffer("");
			StringBuffer pauseF = new StringBuffer("");
			StringBuffer pauseX = new StringBuffer("");
			for (WritingEvent writingEvent : writingEvents) {
				long pause = writingEvent.prevPause.length;
				WritingPause.Subclass deltaType = writingEvent.prevPause.subclass;
				char value = ((CharInsertEvent)writingEvent).character;
				if (deltaType == null) {
					pause0.append("˘" + value);
					pause7.append("˘" + value);
					pause8.append("˘" + value);
					pause9.append("˘" + value);
					pauseA.append("˘" + value);
					pauseB.append("˘" + value);
					pauseC.append("˘" + value);
					pauseD.append("˘" + value);
					pauseE.append("˘" + value);
					pauseF.append("˘" + value);
					pauseX.append("˘" + value);
					continue;
				}
				if (pause > pauseXM) { pauseX.append("•" + value); } else { pauseX.append("˘" + value); }
				switch (deltaType) {
				case AA:
					if (pause >= pauseA0) { pause0.append("•" + value); } else { pause0.append("˘" + value); }
					if (pause >= pauseA7) { pause7.append("•" + value); } else { pause7.append("˘" + value); }
					if (pause >= pauseA8) { pause8.append("•" + value); } else { pause8.append("˘" + value); }
					if (pause >= pauseA9) { pause9.append("•" + value); } else { pause9.append("˘" + value); }
					if (pause >= pauseAA) { pauseA.append("•" + value); } else { pauseA.append("˘" + value); }
					if (pause >= pauseAB) { pauseB.append("•" + value); } else { pauseB.append("˘" + value); }
					if (pause >= pauseAC) { pauseC.append("•" + value); } else { pauseC.append("˘" + value); }
					if (pause >= pauseAD) { pauseD.append("•" + value); } else { pauseD.append("˘" + value); }
					if (pause >= pauseAE) { pauseE.append("•" + value); } else { pauseE.append("˘" + value); }
					if (pause >= pauseAF) { pauseF.append("•" + value); } else { pauseF.append("˘" + value); }
					break;
				case AB:
					if (pause >= pauseB0) { pause0.append("•" + value); } else { pause0.append("˘" + value); }
					if (pause >= pauseB7) { pause7.append("•" + value); } else { pause7.append("˘" + value); }
					if (pause >= pauseB8) { pause8.append("•" + value); } else { pause8.append("˘" + value); }
					if (pause >= pauseB9) { pause9.append("•" + value); } else { pause9.append("˘" + value); }
					if (pause >= pauseBA) { pauseA.append("•" + value); } else { pauseA.append("˘" + value); }
					if (pause >= pauseBB) { pauseB.append("•" + value); } else { pauseB.append("˘" + value); }
					if (pause >= pauseBC) { pauseC.append("•" + value); } else { pauseC.append("˘" + value); }
					if (pause >= pauseBD) { pauseD.append("•" + value); } else { pauseD.append("˘" + value); }
					if (pause >= pauseBE) { pauseE.append("•" + value); } else { pauseE.append("˘" + value); }
					if (pause >= pauseBF) { pauseF.append("•" + value); } else { pauseF.append("˘" + value); }
					break;
				case BA:
					if (pause >= pauseC0) { pause0.append("•" + value); } else { pause0.append("˘" + value); }
					if (pause >= pauseC7) { pause7.append("•" + value); } else { pause7.append("˘" + value); }
					if (pause >= pauseC8) { pause8.append("•" + value); } else { pause8.append("˘" + value); }
					if (pause >= pauseC9) { pause9.append("•" + value); } else { pause9.append("˘" + value); }
					if (pause >= pauseCA) { pauseA.append("•" + value); } else { pauseA.append("˘" + value); }
					if (pause >= pauseCB) { pauseB.append("•" + value); } else { pauseB.append("˘" + value); }
					if (pause >= pauseCC) { pauseC.append("•" + value); } else { pauseC.append("˘" + value); }
					if (pause >= pauseCD) { pauseD.append("•" + value); } else { pauseD.append("˘" + value); }
					if (pause >= pauseCE) { pauseE.append("•" + value); } else { pauseE.append("˘" + value); }
					if (pause >= pauseCF) { pauseF.append("•" + value); } else { pauseF.append("˘" + value); }
					break;
				default:
					pause0.append("˘" + value);
					pause7.append("˘" + value);
					pause8.append("˘" + value);
					pause9.append("˘" + value);
					pauseA.append("˘" + value);
					pauseB.append("˘" + value);
					pauseC.append("˘" + value);
					pauseD.append("˘" + value);
					pauseE.append("˘" + value);
					pauseF.append("˘" + value);
				}
			}
			String pause0s = pause0.toString().replaceAll("\\[", "\u232B").replaceAll("\\]", "\u2326");
			String pause7s = pause7.toString().replaceAll("\\[", "\u232B").replaceAll("\\]", "\u2326");
			String pause8s = pause8.toString().replaceAll("\\[", "\u232B").replaceAll("\\]", "\u2326");
			String pause9s = pause9.toString().replaceAll("\\[", "\u232B").replaceAll("\\]", "\u2326");
			String pauseAs = pauseA.toString().replaceAll("\\[", "\u232B").replaceAll("\\]", "\u2326");
			String pauseBs = pauseB.toString().replaceAll("\\[", "\u232B").replaceAll("\\]", "\u2326");
			String pauseCs = pauseC.toString().replaceAll("\\[", "\u232B").replaceAll("\\]", "\u2326");
			String pauseDs = pauseD.toString().replaceAll("\\[", "\u232B").replaceAll("\\]", "\u2326");
			String pauseEs = pauseE.toString().replaceAll("\\[", "\u232B").replaceAll("\\]", "\u2326");
			String pauseFs = pauseF.toString().replaceAll("\\[", "\u232B").replaceAll("\\]", "\u2326");
			String pauseXs = pauseX.toString().replaceAll("\\[", "\u232B").replaceAll("\\]", "\u2326");
			buffer.append(pauseFs + "\n");
			buffer.append(pauseEs + "\n");
			buffer.append(pauseDs + "\n");
			buffer.append(pauseCs + "\n");
			buffer.append(pauseBs + "\n");
			buffer.append(pauseAs + "\n");
			buffer.append(pause9s + "\n");
			buffer.append(pause8s + "\n");
			buffer.append(pause7s + "\n");
			buffer.append(pause0s + "\n");
			int p0 = 0, p1 = 0, p2 = 0, p3 = 0, p4 = 0, p5 = 0, p6 = 0, p7 = 0, p8 = 0, p_ = 0;
			buffer.append("\n");
			for (int i = 0; i < pause0s.length(); i++) {
				char ch = pause0s.charAt(i);
				switch(ch) {
				case '•':
					if (pause7s.charAt(i) != '•') { buffer.append("¯"); p0++; break; }
					if (pause8s.charAt(i) != '•') { buffer.append("1"); p1++; break; }
					if (pause9s.charAt(i) != '•') { buffer.append("2"); p2++; break; }
					if (pauseAs.charAt(i) != '•') { buffer.append("3"); p3++; break; }
					if (pauseBs.charAt(i) != '•') { buffer.append("4"); p4++; break; }
					if (pauseCs.charAt(i) != '•') { buffer.append("5"); p5++; break; }
					if (pauseDs.charAt(i) != '•') { buffer.append("6"); p6++; break; }
					if (pauseEs.charAt(i) != '•') { buffer.append("7"); p7++; break; }
					if (pauseFs.charAt(i) != '•') { buffer.append("8"); p8++; break; }
					buffer.append("∞"); p_++; break;
				default:
					buffer.append(ch);
				}
			}
			buffer.append("\n");
			buffer.append(pauseXs + "\n");
			buffer.append("\n");
			buffer.append("¯:        0 =     0: " + p0 + "\n");
			buffer.append("1: 64 × 2^1 =   128: " + p1 + "\n");
			buffer.append("2: 64 × 2^2 =   256: " + p2 + "\n");
			buffer.append("3: 64 × 2^3 =   512: " + p3 + "\n");
			buffer.append("4: 64 × 2^4 =  1024: " + p4 + "\n");
			buffer.append("5: 64 × 2^5 =  2048: " + p5 + "\n");
			buffer.append("6: 64 × 2^6 =  4096: " + p6 + "\n");
			buffer.append("7: 64 × 2^7 =  8192: " + p7 + "\n");
			buffer.append("8: 64 × 2^8 = 16384: " + p8 + "\n");
			buffer.append("∞: 64 × 2^9 = 32768: " + p_ + "\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		printBuffer(buffer);
	}

	private void calcBases() {
		TypingPauseCounter c = new TypingPauseCounter(writingEvents);
		c.count();
		pauseA0 = c.data064a.getBasePause() * 2 + (long)Math.pow(2, 0);
		pauseB0 = c.data128b.getBasePause() * 2 + (long)Math.pow(2, 0);
		pauseC0 = c.data064c.getBasePause() * 2 + (long)Math.pow(2, 0);
		pauseA7 = c.data064a.getBasePause() * 2 + (long)Math.pow(2, 7);
		pauseB7 = c.data128b.getBasePause() * 2 + (long)Math.pow(2, 7);
		pauseC7 = c.data064c.getBasePause() * 2 + (long)Math.pow(2, 7);
		pauseA8 = c.data064a.getBasePause() * 2 + (long)Math.pow(2, 8);
		pauseB8 = c.data128b.getBasePause() * 2 + (long)Math.pow(2, 8);
		pauseC8 = c.data064c.getBasePause() * 2 + (long)Math.pow(2, 8);
		pauseA9 = c.data064a.getBasePause() * 2 + (long)Math.pow(2, 9);
		pauseB9 = c.data128b.getBasePause() * 2 + (long)Math.pow(2, 9);
		pauseC9 = c.data064c.getBasePause() * 2 + (long)Math.pow(2, 9);
		pauseAA = c.data064a.getBasePause() * 2 + (long)Math.pow(2, 10);
		pauseBA = c.data128b.getBasePause() * 2 + (long)Math.pow(2, 10);
		pauseCA = c.data064c.getBasePause() * 2 + (long)Math.pow(2, 10);
		pauseAB = c.data064a.getBasePause() * 2 + (long)Math.pow(2, 11);
		pauseBB = c.data128b.getBasePause() * 2 + (long)Math.pow(2, 11);
		pauseCB = c.data064c.getBasePause() * 2 + (long)Math.pow(2, 11);
		pauseAC = c.data064a.getBasePause() * 2 + (long)Math.pow(2, 12);
		pauseBC = c.data128b.getBasePause() * 2 + (long)Math.pow(2, 12);
		pauseCC = c.data064c.getBasePause() * 2 + (long)Math.pow(2, 12);
		pauseAD = c.data064a.getBasePause() * 2 + (long)Math.pow(2, 13);
		pauseBD = c.data128b.getBasePause() * 2 + (long)Math.pow(2, 13);
		pauseCD = (long)c.data064c.getBasePause() * 2L + (long)Math.pow(2, 13);
		pauseAE = (long)c.data064a.getBasePause() * 2L + (long)Math.pow(2, 14);
		pauseBE = (long)c.data128b.getBasePause() * 2L + (long)Math.pow(2, 14);
		pauseCE = (long)c.data064c.getBasePause() * 2L + (long)Math.pow(2, 14);
		pauseAF = (long)c.data064a.getBasePause() * 2L + (long)Math.pow(2, 15);
		pauseBF = (long)c.data128b.getBasePause() * 2L + (long)Math.pow(2, 15);
		pauseCF = (long)c.data064c.getBasePause() * 2L + (long)Math.pow(2, 15);
		pauseXM = calculateMean(writingEvents);
	}

	private final synchronized void printBuffer(StringBuffer buffer) {
		out.println(buffer.toString());
	}

	private final long calculateMean(List<WritingEvent> events) {
		List<Long> means = new ArrayList<Long>();
		int index = 0;
		int count = 0;
		means.add(0L);
		for (WritingEvent event : events) {
			long pauseLength = event.prevPause.length;
			if (pauseLength > 0) {
				means.set(index, means.get(index) + pauseLength);
				count++;
			}
			if (count == 100) {
				means.set(index, means.get(index) / count);
				index++;
				means.add(0L);
			}
		}
		means.set(index, means.get(index)/count);
		index++;
		long mean = 0;
		for (int i = 0; i < index; i++) {
			mean += means.get(i);
		}
		mean = mean / index;
		return mean;
	}

	/**
	 * Application
	 * 
	 * @param paths the process path
	 * @throws IOException 
	 */
	public static void main(String[] paths) throws IOException {
		PrintWriter out = new PrintWriter(System.out);
		int i = 0;
		if (paths[0].intern() == "-f".intern()) {
			out = new PrintWriter(new BufferedWriter(new FileWriter(new File(paths[1]))));
			i = 2;
		}
		List<Thread> threads = new LinkedList<Thread>();
		for (; i < paths.length; i++) {
			String path = paths[i];
			Runnable runnable = new Runnable() {@Override public void run() {}};
			try {
				runnable = new ConvertTranslogFilesToTypingBurstsFile(path, out);
			} catch (ParserConfigurationException | SAXException e) {
				e.printStackTrace();
			}
			Thread thread = new Thread(runnable);
			thread.run();
			threads.add(thread);
		}
		for (Thread thread : threads) {
			while (thread.isAlive()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		out.flush();
		out.close();
	}
}
