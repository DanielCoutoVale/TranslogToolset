package org.uppermodel.translog;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Test;

public class TranslogDocumentLoaderTest {

	public final static String expectedSourceText = "Crumpling a sheet of paper seems simple and doesn't require much effort, but explaining the behaviour of the crumpled ball is another matter entirely. Once scrunched, a paper ball is more than 75 percent air. Yet the fact that the ball is able to display extraordinary strength and resistance to further compression has confounded physicists. A report in the journal Physical Review Letters describes one aspect of the behaviour of crumpled sheets: the changes in size of crumpled sheets in relation to the force they withstand.\nA crushed thin sheet is essentially a mass of conical points connected by curved ridges in which the energy is stored. During further compression of the sheet these ridges collapse and smaller ones form. The consequence is an increase of energy stored within the wad. Scientists at the University of Chicago modelled how the force required to compress the crumpled balls relates to their size. They compacted thin aluminized plastic foil and then placed it inside a cylinder equipped with a piston to crush the sheet. An unexpected finding was the continuous decrease of the height of the ball even three weeks after the researchers had applied the weight.\n";

	@Test
	public final void testLoad() throws TranslogDocumentLoadingException, IOException {
		TranslogDocumentLoader loader = new TranslogDocumentLoader();
		InputStream is1 = getClass().getResourceAsStream("i.xml");
		TranslogDocument document = loader.loadDocument(is1);
		String version = document.getVersion();
		assertEquals("0.1.0.189", version);
		String translatorId = document.getTranslatorId();
		assertEquals("A02", translatorId);
		String sourceText = document.getSourceText();
		assertEquals(expectedSourceText, sourceText);
		List<TranslogEvent> events = document.getEvents();
		assertEquals(0, events.get(0).getTime());
		assertEquals(1438408, events.get(events.size() - 1).getTime());
		TranslogProduct product = new TranslogProduct(0, 0, "");
		String text = "";
		InputStream is2 = getClass().getResourceAsStream("o.txt");
		InputStreamReader isr = new InputStreamReader(is2);
		BufferedReader br = new BufferedReader(isr);
		for (TranslogEvent event : events) {
			product = event.newProduct(product);
			if (text.equals(product.getText())) {
				continue;
			}
			String line = event.getTime() + "\t" + product;
			System.out.println(line);
			assertEquals(br.readLine(), line);
			text = product.getText();
		}
		br.close();
	} 
}
