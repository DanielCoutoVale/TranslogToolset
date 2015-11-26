package org.uppermodel.translog.typing.dto;

/**
 * A char insert event.
 * 
 * @author Daniel Couto-Vale <danielvale@icloud.com>
 */
public class CharInsertEvent extends WritingEvent {

	/**
	 * Subclasses of char insert event based on how many keys need to
	 * be low for that char insert.
	 * 
	 * @author Daniel Couto-Vale <danielvale@icloud.com>
	 */
	public enum Subclass {
		Standard, ShiftKeyLow, AltgrKeyLow, ShiftAltgrKeysLow
	}

	/**
	 * The subclass
	 */
	public Subclass subclass;

	/**
	 * The character
	 */
	public char character;

	@Override
	public final String toString() {
		return "I" + character;
	}

}
