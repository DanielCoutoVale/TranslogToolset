package org.uppermodel.translog;

import java.util.List;

public interface TranslogDocument {

	public String getVersion();

	public String getTranslatorId();

	public String getSourceText();

	public String getInitialTargetText();

	public String getFinalTargetText();

	public List<TranslogEvent> getEvents();

	public boolean isCompliant();

}
