package org.uppermodel.translog.typing;

import java.util.ArrayList;
import java.util.List;

import org.uppermodel.translog.typing.dto.CharInsertEvent;
import org.uppermodel.translog.typing.dto.WritingEvent;
import org.uppermodel.translog.typing.dto.WritingPause;

/**
 * A tool for counting typing pauses.
 * 
 * @author Daniel Couto-Vale <daniel.couto-vale@ifaar.rwth-aachen.de>
 */
public class TypingPauseCounter {

	private List<WritingEvent> writingEvents;

	public final FrequencyData data008;
	public final FrequencyData data016;
	public final FrequencyData data032;
	public final FrequencyData data064;
	public final FrequencyData data128;
	public final FrequencyData data008m;
	public final FrequencyData data016m;
	public final FrequencyData data032m;
	public final FrequencyData data064m;
	public final FrequencyData data128m;
	public final FrequencyData data008n;
	public final FrequencyData data016n;
	public final FrequencyData data032n;
	public final FrequencyData data064n;
	public final FrequencyData data128n;
	public final FrequencyData data008a;
	public final FrequencyData data016a;
	public final FrequencyData data032a;
	public final FrequencyData data064a;
	public final FrequencyData data128a;
	public final FrequencyData data008b;
	public final FrequencyData data016b;
	public final FrequencyData data032b;
	public final FrequencyData data064b;
	public final FrequencyData data128b;
	public final FrequencyData data008c;
	public final FrequencyData data016c;
	public final FrequencyData data032c;
	public final FrequencyData data064c;
	public final FrequencyData data128c;
	public final FrequencyData data008d;
	public final FrequencyData data016d;
	public final FrequencyData data032d;
	public final FrequencyData data064d;
	public final FrequencyData data128d;
	public final List<Long> dtList;
	public int band;
	public BandBoundaries boundaries;
	public int baseline;

	public TypingPauseCounter(List<WritingEvent> writingEvents) {
		this.writingEvents = writingEvents;
		data008 = new FrequencyData(8, 0, 320000);
		data016 = new FrequencyData(16, 0, 320000);
		data032 = new FrequencyData(32, 0, 320000);
		data064 = new FrequencyData(64, 0, 320000);
		data128 = new FrequencyData(128, 0, 320000);
		data008m = new FrequencyData(8, 0, 320000);
		data016m = new FrequencyData(16, 0, 320000);
		data032m = new FrequencyData(32, 0, 320000);
		data064m = new FrequencyData(64, 0, 320000);
		data128m = new FrequencyData(64, 0, 320000);
		data008n = new FrequencyData(8, 0, 320000);
		data016n = new FrequencyData(16, 0, 320000);
		data032n = new FrequencyData(32, 0, 320000);
		data064n = new FrequencyData(64, 0, 320000);
		data128n = new FrequencyData(64, 0, 320000);
		data008a = new FrequencyData(8, 0, 320000);
		data016a = new FrequencyData(16, 0, 320000);
		data032a = new FrequencyData(32, 0, 320000);
		data064a = new FrequencyData(64, 0, 320000);
		data128a = new FrequencyData(128, 0, 320000);
		data008b = new FrequencyData(8, 0, 320000);
		data016b = new FrequencyData(16, 0, 320000);
		data032b = new FrequencyData(32, 0, 320000);
		data064b = new FrequencyData(64, 0, 320000);
		data128b = new FrequencyData(128, 0, 320000);
		data008c = new FrequencyData(8, 0, 320000);
		data016c = new FrequencyData(16, 0, 320000);
		data032c = new FrequencyData(32, 0, 320000);
		data064c = new FrequencyData(64, 0, 320000);
		data128c = new FrequencyData(128, 0, 320000);
		data008d = new FrequencyData(8, 0, 320000);
		data016d = new FrequencyData(16, 0, 320000);
		data032d = new FrequencyData(32, 0, 320000);
		data064d = new FrequencyData(64, 0, 320000);
		data128d = new FrequencyData(128, 0, 320000);
		dtList = new ArrayList<Long>();
	}

	public void count() {
		CharInsertEvent lastEvent = new CharInsertEvent();
		lastEvent.time = writingEvents.get(0).time;
		lastEvent.nextPause = new WritingPause();
		for (WritingEvent writingEvent : writingEvents) {
			CharInsertEvent event = (CharInsertEvent) writingEvent;
			event.prevPause = lastEvent.nextPause;
			event.nextPause = new WritingPause();
			event.prevPause.length = event.time - lastEvent.time;
			dtList.add(event.prevPause.length);
			data008.addMeasure(event.prevPause.length);
			data016.addMeasure(event.prevPause.length);
			data032.addMeasure(event.prevPause.length);
			data064.addMeasure(event.prevPause.length);
			data128.addMeasure(event.prevPause.length);
			if (event.subclass == CharInsertEvent.Subclass.ShiftKeyLow) {
				// N: Char-Up, something, Char-Down
				data008n.addMeasure(event.prevPause.length);
				data016n.addMeasure(event.prevPause.length);
				data032n.addMeasure(event.prevPause.length);
				data064n.addMeasure(event.prevPause.length);
				data128n.addMeasure(event.prevPause.length);
				if (lastEvent.subclass == CharInsertEvent.Subclass.ShiftKeyLow) {
					// D: Char-Up, Shift-Up, Shift-Down, Char-Down
					data008d.addMeasure(event.prevPause.length);
					data016d.addMeasure(event.prevPause.length);
					data032d.addMeasure(event.prevPause.length);
					data064d.addMeasure(event.prevPause.length);
					data128d.addMeasure(event.prevPause.length);
					event.prevPause.subclass = WritingPause.Subclass.Other;
				} else {
					// B: Char-Up, Shift-Down, Char-Down
					data008b.addMeasure(event.prevPause.length);
					data016b.addMeasure(event.prevPause.length);
					data032b.addMeasure(event.prevPause.length);
					data064b.addMeasure(event.prevPause.length);
					data128b.addMeasure(event.prevPause.length);
					event.prevPause.subclass = WritingPause.Subclass.AB;
				}
			} else {
				// M: Char-Up, something?, Char-Down
				data008m.addMeasure(event.prevPause.length);
				data016m.addMeasure(event.prevPause.length);
				data032m.addMeasure(event.prevPause.length);
				data064m.addMeasure(event.prevPause.length);
				data128m.addMeasure(event.prevPause.length);
				if (lastEvent.subclass == CharInsertEvent.Subclass.ShiftKeyLow) {
					// C: Char-Up, Shift-Up, Char-Down
					data008c.addMeasure(event.prevPause.length);
					data016c.addMeasure(event.prevPause.length);
					data032c.addMeasure(event.prevPause.length);
					data064c.addMeasure(event.prevPause.length);
					data128c.addMeasure(event.prevPause.length);
					event.prevPause.subclass = WritingPause.Subclass.BA;
				} else {
					// A: Char-Up, Char-Down
					data008a.addMeasure(event.prevPause.length);
					data016a.addMeasure(event.prevPause.length);
					data032a.addMeasure(event.prevPause.length);
					data064a.addMeasure(event.prevPause.length);
					data128a.addMeasure(event.prevPause.length);
					event.prevPause.subclass = WritingPause.Subclass.AA;
				}
			}
			lastEvent = event;
		}
		band = data032n.getMostFrequentBand();
		boundaries = data032n.getBandBoundaries(band);
		baseline = (int) (boundaries.getMin() + (boundaries.getGranularity() / 2));
	}
}
