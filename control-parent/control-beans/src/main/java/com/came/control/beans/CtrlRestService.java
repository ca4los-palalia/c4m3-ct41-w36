package com.came.control.beans;

import java.io.Serializable;
import java.util.List;

public class CtrlRestService implements Serializable {

	private static final long serialVersionUID = -4411084049610950892L;

	private Object single;
	private String response;
	private boolean ok;
	private List<?> lista;

	public Object getSingle() {
		return single;
	}

	public void setSingle(Object single) {
		this.single = single;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public List<?> getLista() {
		return lista;
	}

	public void setLista(List<?> lista) {
		this.lista = lista;
	}
}
