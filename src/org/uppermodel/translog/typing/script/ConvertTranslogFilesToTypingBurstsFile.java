package org.uppermodel.translog.typing.script;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.uppermodel.translog.typing.PauseCounter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import static org.uppermodel.translog.typing.TranslogTypingUtils.*;

/**
 * Converts a set of translog files into a typing burst file
 *
 * @author Daniel Couto-Vale <daniel.couto-vale@ifaar.rwth-aachen.de>
 */
public class ConvertTranslogFilesToTypingBurstsFile implements Runnable {

	private static final String KEY = "Key".intern();

	/**
	 * The path of the xml file
	 */
	private final String filePath;

	private PrintWriter out;

	/**
	 * Constructor
	 * 
	 * @param filePath the xml file path
	 */
	public ConvertTranslogFilesToTypingBurstsFile(String filePath, PrintWriter out) {
		this.filePath = filePath;
		this.out = out;
	}

	@Override
	public void run() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n");
		buffer.append(this.filePath + "\n");
		buffer.append("\n");
		try {
			Document document = loadDocument(filePath);
			Element eventsElm = (Element) document.getElementsByTagName("Events").item(0);
			NodeList eventNodes = eventsElm.getChildNodes();
			PauseCounter c = new PauseCounter(eventNodes);
			c.count();
			long pauseA0 = c.data064a.getBasePause() * 2 + (long)Math.pow(2, 0);
			long pauseB0 = c.data128b.getBasePause() * 2 + (long)Math.pow(2, 0);
			long pauseC0 = c.data064c.getBasePause() * 2 + (long)Math.pow(2, 0);
			long pauseA7 = c.data064a.getBasePause() * 2 + (long)Math.pow(2, 7);
			long pauseB7 = c.data128b.getBasePause() * 2 + (long)Math.pow(2, 7);
			long pauseC7 = c.data064c.getBasePause() * 2 + (long)Math.pow(2, 7);
			long pauseA8 = c.data064a.getBasePause() * 2 + (long)Math.pow(2, 8);
			long pauseB8 = c.data128b.getBasePause() * 2 + (long)Math.pow(2, 8);
			long pauseC8 = c.data064c.getBasePause() * 2 + (long)Math.pow(2, 8);
			long pauseA9 = c.data064a.getBasePause() * 2 + (long)Math.pow(2, 9);
			long pauseB9 = c.data128b.getBasePause() * 2 + (long)Math.pow(2, 9);
			long pauseC9 = c.data064c.getBasePause() * 2 + (long)Math.pow(2, 9);
			long pauseAA = c.data064a.getBasePause() * 2 + (long)Math.pow(2, 10);
			long pauseBA = c.data128b.getBasePause() * 2 + (long)Math.pow(2, 10);
			long pauseCA = c.data064c.getBasePause() * 2 + (long)Math.pow(2, 10);
			long pauseAB = c.data064a.getBasePause() * 2 + (long)Math.pow(2, 11);
			long pauseBB = c.data128b.getBasePause() * 2 + (long)Math.pow(2, 11);
			long pauseCB = c.data064c.getBasePause() * 2 + (long)Math.pow(2, 11);
			long pauseAC = c.data064a.getBasePause() * 2 + (long)Math.pow(2, 12);
			long pauseBC = c.data128b.getBasePause() * 2 + (long)Math.pow(2, 12);
			long pauseCC = c.data064c.getBasePause() * 2 + (long)Math.pow(2, 12);
			long pauseAD = c.data064a.getBasePause() * 2 + (long)Math.pow(2, 13);
			long pauseBD = c.data128b.getBasePause() * 2 + (long)Math.pow(2, 13);
			long pauseCD = (long)c.data064c.getBasePause() * 2L + (long)Math.pow(2, 13);
			long pauseAE = (long)c.data064a.getBasePause() * 2L + (long)Math.pow(2, 14);
			long pauseBE = (long)c.data128b.getBasePause() * 2L + (long)Math.pow(2, 14);
			long pauseCE = (long)c.data064c.getBasePause() * 2L + (long)Math.pow(2, 14);
			long pauseAF = (long)c.data064a.getBasePause() * 2L + (long)Math.pow(2, 15);
			long pauseBF = (long)c.data128b.getBasePause() * 2L + (long)Math.pow(2, 15);
			long pauseCF = (long)c.data064c.getBasePause() * 2L + (long)Math.pow(2, 15);
			long pauseXM = calculateMean(eventNodes);
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
			for (int i = 0; i < eventNodes.getLength(); i++) {
				Node node = eventNodes.item(i);
				if (node.getNodeType() != Node.ELEMENT_NODE) {
					continue;
				}
				Element elm = (Element) node;
				if (KEY == elm.getNodeName().intern()) {
					long pause = Long.parseLong(elm.getAttribute("DeltaTime"));
					String deltaType = elm.getAttribute("DeltaType");
					char value;
					if (elm.getAttribute("Value").equals("")) {
						value = '`'; // Empirically based guess
					} else {
						value = elm.getAttribute("Value").charAt(0);
					}
					if (deltaType == null || deltaType.length() == 0) {
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
					switch (deltaType.charAt(0)) {
					case 'A':
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
					case 'B':
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
					case 'C':
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

	private final synchronized void printBuffer(StringBuffer buffer) {
		out.println(buffer.toString());
	}

	private final long calculateMean(NodeList eventNodes) {
		List<Long> means = new ArrayList<Long>();
		int index = 0;
		int count = 0;
		means.add(0L);
		for (int i = 0; i < eventNodes.getLength(); i++) {
			Node node = eventNodes.item(i);
			if (node.getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}
			Element elm = (Element) node;
			if (KEY == elm.getNodeName().intern()) {
				long pause = Long.parseLong(elm.getAttribute("DeltaTime"));
				if (pause > 0) {
					means.set(index, means.get(index) + pause);
					count++;
				}
				if (count == 100) {
					means.set(index, means.get(index) / count);
					index++;
					means.add(0L);
				}
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
			Runnable runnable = new ConvertTranslogFilesToTypingBurstsFile(path, out);
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
