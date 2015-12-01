package org.uppermodel.translog.scripts;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.uppermodel.translog.TranslogDocument;
import org.uppermodel.translog.TranslogDocumentLoader;
import org.uppermodel.translog.TranslogDocumentLoadingException;
import org.uppermodel.translog.export.TextSeriesExporter;

public class ConvertToTextSeriesFiles {

	private static final String USAGE = "USAGE: 'convert directory' or 'convert file'";

	public final static void main(String[] args) {
		if (args.length == 0) {
			System.out.println(USAGE);
			return;
		}
		File argFile = new File(args[0]);
		if (argFile.isDirectory()) {
			for (File inputFile : argFile.listFiles()) {
				convert(inputFile);
			}
		} else if (argFile.isFile()) {
			convert(argFile);
		} else {
			System.out.println(USAGE);
			return;
		}
	}

	private static final boolean convert(File sourceFile) {
		try {
			return convertUnsafe(sourceFile);
		} catch (TranslogDocumentLoadingException | IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	private static final boolean convertUnsafe(File sourceFile) throws TranslogDocumentLoadingException,
			IOException {
		final String sourceFileName = sourceFile.getName();
		if (!sourceFileName.endsWith(".xml")) {
			return false;
		}
		String targetFileName = sourceFileName.substring(0, sourceFileName.length() - 4) + ".ts"; 
		File targetFile = new File(sourceFile.getParentFile(), targetFileName);
		TranslogDocumentLoader loader = new TranslogDocumentLoader();
		TranslogDocument document = loader.loadDocument(sourceFile);
		if (!document.isCompliant()) {
			return false;
		}
		FileWriter fw = new FileWriter(targetFile);
		TextSeriesExporter exporter = new TextSeriesExporter();
		exporter.exportTextSeries(document, fw, new TextSeriesExporter.Callback() {

			@Override
			public void onSuccess() {
				System.out.println(sourceFileName + "\tOK");
			}

			@Override
			public void onFailure(String textA, String textB) {
				System.out.println(sourceFileName + "\tERROR");
				printDiff(textA, textB);
			}
			
		});
		return true;
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
