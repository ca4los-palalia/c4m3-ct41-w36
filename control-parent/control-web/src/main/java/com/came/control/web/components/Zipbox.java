package com.came.control.web.components;

import org.zkoss.zul.Textbox;

public class Zipbox extends Textbox {

	private static final long serialVersionUID = -7385973290176303521L;

	public Zipbox() {
		setWidgetListener("onBind", "jq(this).mask('99999');");
	}
}