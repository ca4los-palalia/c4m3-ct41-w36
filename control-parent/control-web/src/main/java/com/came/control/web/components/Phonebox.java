package com.came.control.web.components;

import org.zkoss.zul.Textbox;

public class Phonebox extends Textbox {
	private static final long serialVersionUID = 1L;

	public Phonebox() {
		setWidgetListener("onBind", "jq(this).mask('(999) 9-99-99-99');");
	}
}