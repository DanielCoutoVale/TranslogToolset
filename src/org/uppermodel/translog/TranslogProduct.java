package org.uppermodel.translog;

/**
 * A product in the Translog® document.
 * 
 * @author Daniel Couto-Vale <danielvale@icloud.com>
 */
public class TranslogProduct {

	/**
	 * The dot
	 */
	private final int dot;

	/**
	 * The mark
	 */
	private final int mark;

	/**
	 * The text
	 */
	private final String text;

	/**
	 * Constructor
	 * 
	 * @param dot the dot specifying the caret position (and the beginning of a selection)
	 * @param mark the mark specifying the end of a selection
	 * @param text the text
	 */
	public TranslogProduct(int dot, int mark, String text) {
		this.dot = dot;
		this.mark = mark;
		this.text = text;
	}

	/**
	 * @return the dot
	 */
	public final int getDot() {
		return dot;
	}

	/**
	 * @return the mark
	 */
	public final int getMark() {
		return mark;
	}

	/**
	 * @return the text
	 */
	public final String getText() {
		return text;
	}

	@Override
	public final String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(dot);
		buffer.append("\t");
		buffer.append(mark);
		buffer.append("\t");
		buffer.append(text);
		return buffer.toString();
	}

}
