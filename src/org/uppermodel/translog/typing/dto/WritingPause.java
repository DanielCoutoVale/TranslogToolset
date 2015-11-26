package org.uppermodel.translog.typing.dto;

/**
 * A writing pause.
 * 
 * @author Daniel Couto-Vale <daniel.couto-vale@ifaar.rwth-aachen.de>
 */
public class WritingPause {

	/**
	 * A subclass of pauses depending on which class of chart insert happens
	 * before and after it.
	 * 
	 * A => Standard
	 * B => ShiftKeyLow
	 * 
	 * @author Daniel Couto-Vale <daniel.couto-vale@ifaar.rwth-aachen.de>
	 *
	 */
	public enum Subclass {
		AA, AB, BA, Other;
	}

	/**
	 * The event prior to the writing pause
	 */
	public WritingEvent prevEvent;

	/**
	 * The event after the writing pause
	 */
	public WritingEvent nextEvent;

	/**
	 * The lenght of the writing pause
	 */
	public long length;

	/**
	 * The subclass of writing pause
	 */
	public Subclass subclass;

	/**
	 * The typing pause
	 */
	public TypingPause typingPause;

}
