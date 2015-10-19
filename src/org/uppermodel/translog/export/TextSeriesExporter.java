package org.uppermodel.translog.export;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.Writer;

import org.uppermodel.translog.TranslogDocument;
import org.uppermodel.translog.TranslogEvent;
import org.uppermodel.translog.TranslogProduct;

/**
 * An exporter of text series
 * 
 * @author Daniel Couto-Vale <daniel.couto-vale@ifaar.rwth-aachen.de>
 */
public class TextSeriesExporter {

	/**
	 * A callback to export the decision of what to do with successes and failures.
	 * 
	 * @author Daniel Couto-Vale <daniel.couto-vale@ifaar.rwth-aachen.de>
	 */
	public static interface Callback {
		public void onSuccess();
		public void onFailure(String textA, String textB);
	}

	/**
	 * Exports text series
	 * 
	 * @param document the document
	 * @param writer the writer
	 * @param callback the callback
	 */
	public void exportTextSeries(TranslogDocument document, Writer writer, Callback callback) {
		TranslogProduct product = new TranslogProduct(0, 0, "");
		String textA = "";
		BufferedWriter bw = new BufferedWriter(writer);
		PrintWriter pw = new PrintWriter(bw);
		for (TranslogEvent event : document.getEvents()) {
			product = event.newProduct(product);
			if (textA.equals(product.getText())) {
				continue;
			}
			String line = event.getTime() + "\t" + product;
			pw.println(line);
			textA = product.getText();
			pw.flush();
		}
		pw.close();
		String textB = document.getFinalTargetText();
		boolean ok = textA.equals(textB);
		if (ok) {
			callback.onSuccess();
		} else {
			callback.onFailure(textA, textB);
		}
	}

}
