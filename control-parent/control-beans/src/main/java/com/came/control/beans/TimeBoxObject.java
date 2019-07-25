package com.came.control.beans;

import java.io.Serializable;

public class TimeBoxObject implements Serializable {

	private static final long serialVersionUID = 6470278787408250175L;

	private String idIndex;
	private String time;

	public String getIdIndex() {
		return idIndex;
	}

	public void setIdIndex(String idIndex) {
		this.idIndex = idIndex;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
