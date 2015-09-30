package org.uppermodel.translog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * A loader of Translog® documents.
 * 
 * @author Daniel Couto-Vale <danielvale@icloud.com>
 */
public class TranslogDocumentLoader {

	/**
	 * Loads a Translog® document.
	 * 
	 * @param fileName the Translog® file name
	 * @return the Translog® document
	 * @throws TranslogDocumentLoadingException when loading fails 
	 */
	public final TranslogDocument loadDocument(String fileName) throws TranslogDocumentLoadingException {
		File file = new File(fileName);
		return loadDocument(file);
	}

	/**
	 * Loads a Translog® document.
	 * 
	 * @param file the Translog® file
	 * @return the Translog® document
	 * @throws TranslogDocumentLoadingException when loading fails
	 */
	public final TranslogDocument loadDocument(File file) throws TranslogDocumentLoadingException {
		try {
			return loadDocumentImp(file);
		} catch (FileNotFoundException e) {
			throw new TranslogDocumentLoadingException(e);
		}
	}

	private final TranslogDocument loadDocumentImp(File file) throws FileNotFoundException,
			TranslogDocumentLoadingException {
		FileInputStream fis = new FileInputStream(file);
		return loadDocument(fis);
	}

	/**
	 * Loads a Translog® document.
	 * 
	 * @param inputStream the Translog® inputStream
	 * @return the Translog® document
	 * @throws TranslogDocumentLoadingException when loading fails
	 */
	public final TranslogDocument loadDocument(InputStream inputStream) throws TranslogDocumentLoadingException {
		try {
			return loadDocumentImp(inputStream);
	    } catch (Exception e) {
	    	throw new TranslogDocumentLoadingException(e);
	    }    
	}

	private final TranslogDocument loadDocumentImp(InputStream inputStream)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(inputStream);
		return new TranslogDocument_0_1_0(document);
	}

}
