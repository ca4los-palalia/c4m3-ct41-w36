package com.came.control.web.components;

import org.zkoss.zul.Textbox;

public class Nssbox extends Textbox {
	private static final long serialVersionUID = 1L;

	public Nssbox() {
		setWidgetListener("onBind", "jq(this).mask('99-99-99-9999-9');");
	}
}