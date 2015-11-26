package org.uppermodel.translog.typing;

import org.uppermodel.translog.typing.dto.CharInsertEvent;

/**
 * A chart insert classifier for German keyboards with Windows German keyboard layout.
 * 
 * @author Daniel Couto-Vale <daniel.couto-vale@ifaar.rwth-aachen.de>
 */
public class GermanCharInsertClassifier implements CharInsertClassifier {

	public CharInsertEvent.Subclass classify(CharInsertEvent event) {
		if (Character.isLetter(event.character) && Character.isUpperCase(event.character)) {
			return CharInsertEvent.Subclass.ShiftKeyLow;
		}
		switch(event.character) {
		case '!':
		case '"':
		case '§':
		case '$':
		case '%':
		case '&':
		case '/':
		case '(':
		case ')':
		case '=':
		case '?':
		case '´':
		case '’':
		case '>':
		case 'Y':
		case 'X':
		case 'C':
		case 'V':
		case 'B':
		case 'N':
		case 'M':
		case ';':
		case ':':
		case '–':
			return CharInsertEvent.Subclass.ShiftKeyLow;
		case '@':
		case ',':
		case '¡':
		case '¿':
		case '{':
		case '[':
		case ']':
		case '}':
		case '\\':
		case '`':
		case ' ':
		case '≤':
		case '≥':
		case '|':
		case '»':
		case '©':
		case '«':
		case '_':
			return CharInsertEvent.Subclass.AltgrKeyLow;
		// TODO Catch characters that are produced when both shift and altgr keys are low.
		// return CharInsertEvent.Subclass.ShiftAltgrKeysLow;
		default:
			return CharInsertEvent.Subclass.Standard;
		}
	}

}
