package org.uppermodel.translog.typing;

import org.uppermodel.translog.typing.dto.CharInsertEvent;

/**
 * A chart insert classifier.
 * 
 * @author Daniel Couto-Vale <daniel.couto-vale@ifaar.rwth-aachen.de>
 */
public interface CharInsertClassifier {

	public CharInsertEvent.Subclass classify(CharInsertEvent event);

}
