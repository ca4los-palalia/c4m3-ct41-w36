package com.came.control.web.vm;

import java.util.List;
import java.util.Map;

import com.came.control.beans.DirectoryTreeNode;
import com.came.control.model.domain.ModuloUsuario;
import com.came.control.web.layer.LayerWebOperaciones;

public class MenuVariables extends LayerWebOperaciones {

	private static final long serialVersionUID = 3599338863971941993L;

	protected String PAGE_TO_RENDER;
	protected Map<String, Object> args;
	protected boolean ownerOptions;
	protected boolean clientOptions;

	protected DirectoryTreeNode menuSelected;

	public boolean isOwnerOptions() {
		return ownerOptions;
	}

	public void setOwnerOptions(boolean ownerOptions) {
		this.ownerOptions = ownerOptions;
	}

	public boolean isClientOptions() {
		return clientOptions;
	}

	public void setClientOptions(boolean clientOptions) {
		this.clientOptions = clientOptions;
	}

	public DirectoryTreeNode getMenuSelected() {
		return menuSelected;
	}

	public void setMenuSelected(DirectoryTreeNode menuSelected) {
		this.menuSelected = menuSelected;
	}

}
