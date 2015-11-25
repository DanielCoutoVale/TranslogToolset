package org.uppermodel.translog.typing;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * A tool for counting typing pauses.
 * 
 * @author Daniel Couto-Vale <daniel.couto-vale@ifaar.rwth-aachen.de>
 */
public class PauseCounter {

	private static final String KEY = "Key".intern();

	private final NodeList eventNodes;

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



	public PauseCounter(NodeList eventNodes) {
		this.eventNodes = eventNodes;
		data008 = new FrequencyData(8,  0, 320000);
		data016 = new FrequencyData(16, 0, 320000);
		data032 = new FrequencyData(32, 0, 320000);
		data064 = new FrequencyData(64, 0, 320000);
		data128 = new FrequencyData(128, 0, 320000);
		data008m = new FrequencyData(8,  0, 320000);
		data016m = new FrequencyData(16, 0, 320000);
		data032m = new FrequencyData(32, 0, 320000);
		data064m = new FrequencyData(64, 0, 320000);
		data128m = new FrequencyData(64, 0, 320000);
		data008n = new FrequencyData(8,  0, 320000);
		data016n = new FrequencyData(16, 0, 320000);
		data032n = new FrequencyData(32, 0, 320000);
		data064n = new FrequencyData(64, 0, 320000);
		data128n = new FrequencyData(64, 0, 320000);
		data008a = new FrequencyData(8,  0, 320000);
		data016a = new FrequencyData(16, 0, 320000);
		data032a = new FrequencyData(32, 0, 320000);
		data064a = new FrequencyData(64, 0, 320000);
		data128a = new FrequencyData(128, 0, 320000);
		data008b = new FrequencyData(8,  0, 320000);
		data016b = new FrequencyData(16, 0, 320000);
		data032b = new FrequencyData(32, 0, 320000);
		data064b = new FrequencyData(64, 0, 320000);
		data128b = new FrequencyData(128, 0, 320000);
		data008c = new FrequencyData(8,  0, 320000);
		data016c = new FrequencyData(16, 0, 320000);
		data032c = new FrequencyData(32, 0, 320000);
		data064c = new FrequencyData(64, 0, 320000);
		data128c = new FrequencyData(128, 0, 320000);
		data008d = new FrequencyData(8,  0, 320000);
		data016d = new FrequencyData(16, 0, 320000);
		data032d = new FrequencyData(32, 0, 320000);
		data064d = new FrequencyData(64, 0, 320000);
		data128d = new FrequencyData(128, 0, 320000);
		dtList = new ArrayList<Long>();
	}
	
	public void count() {
		long ta = 0;
		boolean shiftUp = false;
		for (int i = 0; i < eventNodes.getLength(); i++) {
			Node node = eventNodes.item(i);
			if (node.getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}
			Element elm = (Element) node;
			if (KEY == elm.getNodeName().intern()) {
				long tb = Long.parseLong(elm.getAttribute("Time"));
				char ch;
				if (elm.getAttribute("Value").equals("")) {
					ch = '`'; // Empirically based guess
				} else {
					ch = elm.getAttribute("Value").charAt(0);
				}
				long dt = tb - ta;
				elm.setAttribute("DeltaTime", Long.toString(dt));
				boolean shiftDown = (Character.isLetter(ch) && Character.isUpperCase(ch)) || ch == ':' || ch == ';';
				if (tb != dt) {
					dtList.add(dt);
					data008.addMeasure(dt);
					data016.addMeasure(dt);
					data032.addMeasure(dt);
					data064.addMeasure(dt);
					data128.addMeasure(dt);
					if (shiftDown) {
						// N: Char-Up, something, Char-Down
						data008n.addMeasure(dt);
						data016n.addMeasure(dt);
						data032n.addMeasure(dt);
						data064n.addMeasure(dt);
						data128n.addMeasure(dt);
						if (shiftUp) {
							// D: Char-Up, Shift-Up, Shift-Down, Char-Down
							data008d.addMeasure(dt);
							data016d.addMeasure(dt);
							data032d.addMeasure(dt);
							data064d.addMeasure(dt);
							data128d.addMeasure(dt);
							elm.setAttribute("DeltaType", "D");
						} else {
							// B: Char-Up, Shift-Down, Char-Down
							data008b.addMeasure(dt);
							data016b.addMeasure(dt);
							data032b.addMeasure(dt);
							data064b.addMeasure(dt);
							data128b.addMeasure(dt);
							elm.setAttribute("DeltaType", "B");
						}
					} else {
						// M: Char-Up, something?, Char-Down
						data008m.addMeasure(dt);
						data016m.addMeasure(dt);
						data032m.addMeasure(dt);
						data064m.addMeasure(dt);
						data128m.addMeasure(dt);
						if (shiftUp) {
							// C: Char-Up, Shift-Up, Char-Down
							data008c.addMeasure(dt);
							data016c.addMeasure(dt);
							data032c.addMeasure(dt);
							data064c.addMeasure(dt);
							data128c.addMeasure(dt);
							elm.setAttribute("DeltaType", "C");
						} else {
							// A: Char-Up, Char-Down
							data008a.addMeasure(dt);
							data016a.addMeasure(dt);
							data032a.addMeasure(dt);
							data064a.addMeasure(dt);
							data128a.addMeasure(dt);
							elm.setAttribute("DeltaType", "A");
						}
					}
				}
				ta = tb;
				shiftUp = shiftDown;
			}
		}
		band = data032n.getMostFrequentBand();
		boundaries = data032n.getBandBoundaries(band);
		baseline = (int)(boundaries.getMin() + (boundaries.getGranularity() / 2));
	}
}
