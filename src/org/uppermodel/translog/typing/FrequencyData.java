package org.uppermodel.translog.typing;

/**
 * A typing pause frequency data.
 * 
 * @author Daniel Couto-Vale <daniel.couto-vale@ifaar.rwth-aachen.de>
 */
public class FrequencyData {

	/**
	 * The granularity of the measure
	 */
	private long granularity;

	/**
	 * The minimum measure
	 */
	private long minMeasure;

	/**
	 * The maxMeasure
	 */
	private long maxMeasure;

	/**
	 * The frequency of a measure band
	 */
	private long[] bandFreq;

	/**
	 * Constructor
	 * 
	 * @param granularity the granularity
	 * @param minMeasure the min measure
	 * @param maxMeasure the max measure
	 */
	public FrequencyData(long granularity, long minMeasure, long maxMeasure) {
		this.granularity = granularity;
		this.minMeasure = minMeasure;
		this.maxMeasure = maxMeasure;
		this.bandFreq = new long[(int) ((maxMeasure - minMeasure) / granularity)];
	}

	/**
	 * Add a measure.
	 * 
	 * @param measure the measure
	 */
	public final void addMeasure(long measure) {
		if (measure < minMeasure || measure > maxMeasure) {
			throw new MeasureOutOfBoundsException();
		}
		int band = (int) Math.floor((measure - minMeasure) / granularity);
		bandFreq[band]++;
	}

	/**
	 * Gets the most frequent band
	 * 
	 * @return the most frequent band
	 */
	public final int getMostFrequentBand() {
		int band = 0;
		long freq = 0;
		for (int i = 0; i < bandFreq.length; i++) {
			if (bandFreq[i] > freq) {
				freq = bandFreq[i];
				band = i;
			}
		}
		return band;
	}

	/**
	 * Gets the band frequency.
	 * 
	 * @param band the band
	 * @return the band frequency
	 */
	public final long getBandFrequency(int band) {
		return bandFreq[band];
	}

	/**
	 * The band boundaries.
	 * 
	 * @param band the band
	 * @return the band boundaries
	 */
	public final BandBoundaries getBandBoundaries(int band) {
		long minMeasure = band * granularity + this.minMeasure; 
		return new BandBoundaries(minMeasure, granularity);
	}

	/**
	 * Gets the number of bands.
	 * 
	 * @return the number of bands
	 */
	public final int getBandCount() {
		return bandFreq.length;
	}

	/**
	 * Gets the base pause (the maximum pause of the most frequent band)
	 * 
	 * @return the base pause
	 */
	public final long getBasePause() {
		return getBandBoundaries(getMostFrequentBand()).getMax();
	}

}
