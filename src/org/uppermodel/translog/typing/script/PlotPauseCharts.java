package org.uppermodel.translog.typing.script;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.xml.parsers.ParserConfigurationException;

import org.uppermodel.translog.typing.FrequencyData;
import org.uppermodel.translog.typing.PauseCounter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import static org.uppermodel.translog.typing.TranslogTypingUtils.*;

/**
 * Plots the pause charts for a set of translog files.
 * 
 * @author Daniel Couto-Vale <daniel.couto-vale@ifaar.rwth-aachen.de>
 */
public class PlotPauseCharts implements Runnable {


	/**
	 * The path of the xml file
	 */
	private final String filePath;

	/**
	 * Constructor
	 * 
	 * @param filePath the xml file path
	 */
	public PlotPauseCharts(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void run() {
		try {
			Document document = loadDocument(filePath);
			Element eventsElm = (Element) document.getElementsByTagName("Events").item(0);
			NodeList eventNodes = eventsElm.getChildNodes();
			PauseCounter c = new PauseCounter(eventNodes);
			c.count();
			plotPauseFrequencyGraph(c.data008, ".008.png");
			plotPauseFrequencyGraph(c.data016, ".016.png");
			plotPauseFrequencyGraph(c.data032, ".032.png");
			plotPauseFrequencyGraph(c.data064, ".064.png");
			plotPauseFrequencyGraph(c.data128, ".128.png");
			plotPauseFrequencyGraph(c.data008a, ".008a.png");
			plotPauseFrequencyGraph(c.data016a, ".016a.png");
			plotPauseFrequencyGraph(c.data032a, ".032a.png");
			plotPauseFrequencyGraph(c.data064a, ".064a.png");
			plotPauseFrequencyGraph(c.data128a, ".128a.png");
			plotPauseFrequencyGraph(c.data008b, ".008b.png");
			plotPauseFrequencyGraph(c.data016b, ".016b.png");
			plotPauseFrequencyGraph(c.data032b, ".032b.png");
			plotPauseFrequencyGraph(c.data064b, ".064b.png");
			plotPauseFrequencyGraph(c.data128b, ".128b.png");
			plotPauseFrequencyGraph(c.data008c, ".008c.png");
			plotPauseFrequencyGraph(c.data016c, ".016c.png");
			plotPauseFrequencyGraph(c.data032c, ".032c.png");
			plotPauseFrequencyGraph(c.data064c, ".064c.png");
			plotPauseFrequencyGraph(c.data128c, ".128c.png");
			plotPauseFrequencyGraph(c.data008d, ".008d.png");
			plotPauseFrequencyGraph(c.data016d, ".016d.png");
			plotPauseFrequencyGraph(c.data032d, ".032d.png");
			plotPauseFrequencyGraph(c.data064d, ".064d.png");
			plotPauseFrequencyGraph(c.data128d, ".128m.png");
			plotPauseFrequencyGraph(c.data008m, ".008m.png");
			plotPauseFrequencyGraph(c.data016m, ".016m.png");
			plotPauseFrequencyGraph(c.data032m, ".032m.png");
			plotPauseFrequencyGraph(c.data064m, ".064m.png");
			plotPauseFrequencyGraph(c.data128m, ".128m.png");
			plotPauseFrequencyGraph(c.data008n, ".008n.png");
			plotPauseFrequencyGraph(c.data016n, ".016n.png");
			plotPauseFrequencyGraph(c.data032n, ".032n.png");
			plotPauseFrequencyGraph(c.data064n, ".064n.png");
			plotPauseFrequencyGraph(c.data128n, ".128n.png");
			plotPauseDurationGraph(c.dtList, c.baseline, ".png");
			plotPauseDurationGraphBlock(c.dtList, c.baseline, ".block.png");
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	private final void plotPauseDurationGraphBlock(List<Long> dtList, long baseline, String ending) throws IOException {
		int width = dtList.size() * 2;
		int height = 600;
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D ig2 = bi.createGraphics();
		Font font = new Font("TimesRoman", Font.BOLD, 20);
		ig2.setFont(font);
		ig2.setPaint(Color.white);
		ig2.fillRect(0, 0, width, height);
		ig2.setPaint(Color.black);
		for (int x = 32; x < 300000; x*=2) {
			int y = (int)(Math.log(x) * 50.0);
			ig2.fillRect(0, (height - 1) - y, width, 1);
		}
		ig2.setPaint(Color.black);
		for (int x = 0; x < width / 2; x++) {
			double c = dtList.get(x) - baseline;
			int y = (int)(Math.log(c) * 50.0);
			for (int z = 32; x < 300000; z*=2) {
				int y2 = (int)(Math.log(z) * 50.0);
				if (y2 > y) {
					if (y2 > 32) {
						ig2.fillRect(x * 2, (height - 1) - y2, 1, y2);
					}
					break;
				}
			}
		}
		bi.flush();
		ImageIO.write(bi, "PNG", new File(filePath + ending));
	}

	private final void plotPauseDurationGraph(List<Long> dtList, long baseline, String ending) throws IOException {
		int width = dtList.size() * 2;
		int height = 600;
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D ig2 = bi.createGraphics();
		Font font = new Font("TimesRoman", Font.BOLD, 20);
		ig2.setFont(font);
		ig2.setPaint(Color.white);
		ig2.fillRect(0, 0, width, height);
		ig2.setPaint(Color.gray);
		for (int x = 32; x < 300000; x*=2) {
			int y = (int)(Math.log(x) * 50.0);
			ig2.fillRect(0, (height - 1) - y, width, 1);
		}
		ig2.setPaint(Color.black);
		for (int x = 0; x < width / 2; x++) {
			double c = dtList.get(x) - baseline;
			int y = (int)(Math.log(c) * 50.0);
			ig2.fillRect(x * 2, (height - 1) - y, 1, y);
		}
		bi.flush();
		ImageIO.write(bi, "PNG", new File(filePath + ending));
	}

	private final void plotPauseFrequencyGraph(FrequencyData data, String ending) throws IOException {
		int width = 800;
		int height = 600 + 8;
		int maxFreq = (int) data.getBandFrequency(data.getMostFrequentBand());
		double quot = 600.0 / ((double) maxFreq);
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D ig2 = bi.createGraphics();
		Font font = new Font("TimesRoman", Font.BOLD, 20);
		ig2.setFont(font);
		ig2.setPaint(Color.white);
		ig2.fillRect(0, 0, width, height);
		ig2.setPaint(Color.black);
		for (int x = 0; x < width / 8; x++) {
			int y = (int) data.getBandFrequency(x);
			y = (int) Math.floor(quot * ((double)y));
			ig2.drawOval(x * 8, height - y - 8, 8, 8);
		}
		bi.flush();
		ImageIO.write(bi, "PNG", new File(filePath + ending));
	}

	public static final void main(String[] paths) {
		for (String path : paths) {
			Runnable runnable = new PlotPauseCharts(path);
			Thread thread = new Thread(runnable);
			thread.run();
		}
	}

}
