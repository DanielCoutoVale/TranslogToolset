package org.uppermodel.translog.typing;

import java.util.List;

import org.uppermodel.translog.typing.dto.TypingPause;
import org.uppermodel.translog.typing.dto.WritingEvent;
import org.uppermodel.translog.typing.dto.WritingPause;

/**
 * An estimator of typing pauses.
 * 
 * @author Daniel Couto-Vale <daniel.couto-vale@ifaar.rwth-aachen.de>
 */
public class TypingPauseEstimator {

	private long pauseA0;

	private long pauseB0;

	private long pauseC0;

	private long pauseA7;

	private long pauseB7;

	private long pauseC7;

	private long pauseA8;

	private long pauseB8;

	private long pauseC8;

	private long pauseA9;

	private long pauseB9;

	private long pauseC9;

	private long pauseAA;

	private long pauseBA;

	private long pauseCA;

	private long pauseAB;

	private long pauseBB;

	private long pauseCB;

	private long pauseAC;

	private long pauseBC;

	private long pauseCC;

	private long pauseAD;

	private long pauseBD;

	private long pauseCD;

	private long pauseAE;

	private long pauseBE;

	private long pauseCE;

	private long pauseAF;

	private long pauseBF;

	private long pauseCF;

	private final void calcBases(List<WritingEvent> writingEvents) {
		TypingPauseCounter c = new TypingPauseCounter(writingEvents);
		c.count();
		pauseA0 = c.data064a.getBasePause() * 2 + (long)Math.pow(2, 0);
		pauseB0 = c.data128b.getBasePause() * 2 + (long)Math.pow(2, 0);
		pauseC0 = c.data064c.getBasePause() * 2 + (long)Math.pow(2, 0);
		pauseA7 = c.data064a.getBasePause() * 2 + (long)Math.pow(2, 7);
		pauseB7 = c.data128b.getBasePause() * 2 + (long)Math.pow(2, 7);
		pauseC7 = c.data064c.getBasePause() * 2 + (long)Math.pow(2, 7);
		pauseA8 = c.data064a.getBasePause() * 2 + (long)Math.pow(2, 8);
		pauseB8 = c.data128b.getBasePause() * 2 + (long)Math.pow(2, 8);
		pauseC8 = c.data064c.getBasePause() * 2 + (long)Math.pow(2, 8);
		pauseA9 = c.data064a.getBasePause() * 2 + (long)Math.pow(2, 9);
		pauseB9 = c.data128b.getBasePause() * 2 + (long)Math.pow(2, 9);
		pauseC9 = c.data064c.getBasePause() * 2 + (long)Math.pow(2, 9);
		pauseAA = c.data064a.getBasePause() * 2 + (long)Math.pow(2, 10);
		pauseBA = c.data128b.getBasePause() * 2 + (long)Math.pow(2, 10);
		pauseCA = c.data064c.getBasePause() * 2 + (long)Math.pow(2, 10);
		pauseAB = c.data064a.getBasePause() * 2 + (long)Math.pow(2, 11);
		pauseBB = c.data128b.getBasePause() * 2 + (long)Math.pow(2, 11);
		pauseCB = c.data064c.getBasePause() * 2 + (long)Math.pow(2, 11);
		pauseAC = c.data064a.getBasePause() * 2 + (long)Math.pow(2, 12);
		pauseBC = c.data128b.getBasePause() * 2 + (long)Math.pow(2, 12);
		pauseCC = c.data064c.getBasePause() * 2 + (long)Math.pow(2, 12);
		pauseAD = c.data064a.getBasePause() * 2 + (long)Math.pow(2, 13);
		pauseBD = c.data128b.getBasePause() * 2 + (long)Math.pow(2, 13);
		pauseCD = (long)c.data064c.getBasePause() * 2L + (long)Math.pow(2, 13);
		pauseAE = (long)c.data064a.getBasePause() * 2L + (long)Math.pow(2, 14);
		pauseBE = (long)c.data128b.getBasePause() * 2L + (long)Math.pow(2, 14);
		pauseCE = (long)c.data064c.getBasePause() * 2L + (long)Math.pow(2, 14);
		pauseAF = (long)c.data064a.getBasePause() * 2L + (long)Math.pow(2, 15);
		pauseBF = (long)c.data128b.getBasePause() * 2L + (long)Math.pow(2, 15);
		pauseCF = (long)c.data064c.getBasePause() * 2L + (long)Math.pow(2, 15);
	}

	public final void estimate(List<WritingEvent> writingEvents) {
		this.calcBases(writingEvents);
		for (WritingEvent writingEvent : writingEvents) {
			long pause = writingEvent.prevPause.length;
			WritingPause.Subclass deltaType = writingEvent.prevPause.subclass;
			if (deltaType == null) {
				continue;
			}
			writingEvent.prevPause.typingPause = new TypingPause();
			switch (deltaType) {
			case AA:
				if (pause >= pauseA0) { writingEvent.prevPause.typingPause.length = 0; } else { writingEvent.prevPause.typingPause = null; }
				if (pause >= pauseA7) { writingEvent.prevPause.typingPause.length = 1; }
				if (pause >= pauseA8) { writingEvent.prevPause.typingPause.length = 2; }
				if (pause >= pauseA9) { writingEvent.prevPause.typingPause.length = 3; }
				if (pause >= pauseAA) { writingEvent.prevPause.typingPause.length = 4; }
				if (pause >= pauseAB) { writingEvent.prevPause.typingPause.length = 5; }
				if (pause >= pauseAC) { writingEvent.prevPause.typingPause.length = 6; }
				if (pause >= pauseAD) { writingEvent.prevPause.typingPause.length = 7; }
				if (pause >= pauseAE) { writingEvent.prevPause.typingPause.length = 8; }
				if (pause >= pauseAF) { writingEvent.prevPause.typingPause.length = 999; }
				break;
			case AB:
				if (pause >= pauseB0) { writingEvent.prevPause.typingPause.length = 0; } else { writingEvent.prevPause.typingPause = null; }
				if (pause >= pauseB7) { writingEvent.prevPause.typingPause.length = 1; }
				if (pause >= pauseB8) { writingEvent.prevPause.typingPause.length = 2; }
				if (pause >= pauseB9) { writingEvent.prevPause.typingPause.length = 3; }
				if (pause >= pauseBA) { writingEvent.prevPause.typingPause.length = 4; }
				if (pause >= pauseBB) { writingEvent.prevPause.typingPause.length = 5; }
				if (pause >= pauseBC) { writingEvent.prevPause.typingPause.length = 6; }
				if (pause >= pauseBD) { writingEvent.prevPause.typingPause.length = 7; }
				if (pause >= pauseBE) { writingEvent.prevPause.typingPause.length = 8; }
				if (pause >= pauseBF) { writingEvent.prevPause.typingPause.length = 999; }
				break;
			case BA:
				if (pause >= pauseC0) { writingEvent.prevPause.typingPause.length = 0; } else { writingEvent.prevPause.typingPause = null; }
				if (pause >= pauseC7) { writingEvent.prevPause.typingPause.length = 1; }
				if (pause >= pauseC8) { writingEvent.prevPause.typingPause.length = 2; }
				if (pause >= pauseC9) { writingEvent.prevPause.typingPause.length = 3; }
				if (pause >= pauseCA) { writingEvent.prevPause.typingPause.length = 4; }
				if (pause >= pauseCB) { writingEvent.prevPause.typingPause.length = 5; }
				if (pause >= pauseCC) { writingEvent.prevPause.typingPause.length = 6; }
				if (pause >= pauseCD) { writingEvent.prevPause.typingPause.length = 7; }
				if (pause >= pauseCE) { writingEvent.prevPause.typingPause.length = 8; }
				if (pause >= pauseCF) { writingEvent.prevPause.typingPause.length = 999; }
				break;
			default:
				break;
			}
		}
	}

}
