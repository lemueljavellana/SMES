package com.smes.view.frm.addEdit;

public class AddEditRowFrm {
	private String labelName;
	private String path;
	private String type;
	private String data;

	public AddEditRowFrm (String labelName, String path,
			String type, String data){
		this.labelName = labelName;
		this.path = path;
		this.type = type;
		this.data = data;
	}
	
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "AddEditRowFrm [data=" + data + ", labelName=" + labelName
				+ ", path=" + path + ", type=" + type + "]";
	}
	
}
