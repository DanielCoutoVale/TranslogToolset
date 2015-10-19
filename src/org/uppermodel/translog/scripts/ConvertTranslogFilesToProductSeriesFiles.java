package org.uppermodel.translog.scripts;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import org.uppermodel.translog.TranslogDocument;
import org.uppermodel.translog.TranslogDocumentLoader;
import org.uppermodel.translog.TranslogDocumentLoadingException;
import org.uppermodel.translog.TranslogEvent;
import org.uppermodel.translog.TranslogProduct;

public class ConvertTranslogFilesToProductSeriesFiles {

	public final static void main(String[] args) throws TranslogDocumentLoadingException, IOException {
		if (args.length != 2) {
			System.out.println("USAGE: convert [input-dir] [output-dir]");
			return;
		}
		File source = new File(args[0]);
		File target = new File(args[1]);
		TranslogDocumentLoader loader = new TranslogDocumentLoader();
		for (File sourceFile : source.listFiles()) {
			TranslogDocument document = loader.loadDocument(sourceFile);
			String name = sourceFile.getName();
			File targetFile = new File(target, name.substring(0, name.lastIndexOf(".")) + ".txt");
			FileWriter fw = new FileWriter(targetFile);
			convert(document, name, fw, new Callback() {

				@Override
				public void onSuccess() {
					System.out.println(name + "\tOK");
				}

				@Override
				public void onFailure(String textA, String textB) {
					System.out.println(name + "\tERROR");
					printDiff(textA, textB);
				}
				
			});
		}
	}

	public static interface Callback {
		public void onSuccess();
		public void onFailure(String textA, String textB);
	}

	private static void convert(TranslogDocument document, String name, Writer w, Callback callback)
			throws IOException {
		TranslogProduct product = new TranslogProduct(0, 0, "");
		String text = "";
		BufferedWriter bw = new BufferedWriter(w);
		PrintWriter pw = new PrintWriter(bw);
		for (TranslogEvent event : document.getEvents()) {
			product = event.newProduct(product);
			if (text.equals(product.getText())) {
				continue;
			}
			String line = event.getTime() + "\t" + product;
			pw.println(line);
			text = product.getText();
			pw.flush();
		}
		pw.close();
		String text2 = document.getFinalTargetText();
		boolean ok = text.equals(text2);
		if (ok) {
			callback.onSuccess();
		} else {
			callback.onFailure(text, text2);
		}
	}

	private static void printDiff(String text, String text2) {
		StringBuffer b1 = new StringBuffer();
		StringBuffer b2 = new StringBuffer();
		for (int i = 0; i < text.length(); i++) {
			if (i < text2.length()) {
				char ch1 = text.charAt(i);
				char ch2 = text2.charAt(i);
				if (ch1 != ch2) {
					b1.append(ch1);
					b2.append(ch2);
				} else {
					b1.append('-');
					b2.append('-');
				}
			} else {
				char ch1 = text.charAt(i);
				b1.append(ch1);
			}
		}
		System.out.println(text);
		System.out.println(b1.toString());
		System.out.println(b2.toString());
	}
}
