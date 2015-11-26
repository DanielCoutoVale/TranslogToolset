package org.uppermodel.translog.typing.dto;

/**
 * A char delete event.
 * 
 * @author Daniel Couto-Vale <danielvale@icloud.com>
 */
public class CharDeleteEvent extends WritingEvent {

	@Override
	public final String toString() {
		return "D";
	}

}
