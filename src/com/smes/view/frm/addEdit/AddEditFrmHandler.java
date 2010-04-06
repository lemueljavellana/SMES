package com.smes.view.frm.addEdit;

import java.util.Collection;

public interface AddEditFrmHandler {
	Collection<AddEditRowFrm> getAddEditRowFrm ();
	void setAddEditRowFrm (Collection<AddEditRowFrm> rows);
	String getSubmitTarget (String target);
	String setSubmitTarget (String target);
}
