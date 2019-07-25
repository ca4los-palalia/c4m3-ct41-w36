package com.came.control.web.components;

import org.zkoss.zul.Textbox;

public class Movilbox extends Textbox {
	private static final long serialVersionUID = 1L;

	public Movilbox() {
		setWidgetListener("onBind", "jq(this).mask('99-99-99-99-99');");
	}
}