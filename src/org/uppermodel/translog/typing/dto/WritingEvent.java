package org.uppermodel.translog.typing.dto;

/**
 * A writing event from the user.
 * 
 * @author Daniel Couto-Vale <danielvale@icloud.com>
 */
public class WritingEvent {

	/**
	 * The id
	 */
	public String id;

	/**
	 * The time
	 */
	public long time;

	/**
	 * The previous writing pause
	 */
	public WritingPause prevPause;

	/**
	 * The following writing pause
	 */
	public WritingPause nextPause;

}
