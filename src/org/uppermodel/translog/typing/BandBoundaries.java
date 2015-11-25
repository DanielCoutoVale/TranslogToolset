package org.uppermodel.translog.typing;

/**
 * The measure band.
 * 
 * @author Daniel Couto-Vale <daniel.couto-vale@ifaar.rwth-aachen.de>
 */
public class BandBoundaries {

	/**
	 * The minimum measure
	 */
	private final long min;

	/**
	 * The band width, the granularity
	 */
	private final long granularity;

	/**
	 * Constructor
	 * 
	 * @param min the minimum measure
	 * @param granularity the granularity
	 */
	public BandBoundaries(long min, long granularity) {
		this.min = min;
		this.granularity = granularity;
	}

	/**
	 * @return the minimum measure
	 */
	public final long getMin() {
		return min;
	}

	/**
	 * @return the granularity
	 */
	public final long getGranularity() {
		return granularity;
	}

	/**
	 * @return the maximum measure
	 */
	public final long getMax() {
		return min + granularity;
	}

}
