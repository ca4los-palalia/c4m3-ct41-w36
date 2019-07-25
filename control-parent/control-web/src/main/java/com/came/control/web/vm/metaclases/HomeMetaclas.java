package com.came.control.web.vm.metaclases;

import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;

import com.came.control.web.layer.LayerWebOperaciones;

public class HomeMetaclas extends LayerWebOperaciones {

	private static final long serialVersionUID = -4300111966341793696L;

	@Wire("#header")
	public Div header;
	@Wire("#menu")
	public Div menu;
	@Wire("#content")
	public Div content;
	@Wire("#footer")
	public Div footer;
}
