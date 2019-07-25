package com.came.control.web;

import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.Initiator;

import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.beans.funciones.CtrlUtils;
import com.came.control.beans.funciones.CtrlUtilsSession;
import com.came.control.model.domain.Organizacion;

public class IntegrityChecker implements Initiator {
	private Logger log = LogManager.getLogger(getClass().getName());

	public void doInit(Page page, Map<String, Object> args) throws Exception {
		CtrlUtilsSession sessionUtils = null;
		Object session = args.get(ConstAtributos.BENUTZER);
		if ((session instanceof CtrlUtilsSession)) {
			sessionUtils = (CtrlUtilsSession) session;
			Organizacion organizacion = (Organizacion) sessionUtils.getFromSession(ConstAtributos.SESSION_FIRMA);
			if (organizacion == null) {
				Executions.sendRedirect("/login.zul");
				return;
			}
		} else {
			CtrlUtils.showSuccessmessage("Session isn't context application", Clients.NOTIFICATION_TYPE_ERROR, 0, null);
			log.error("Session isn't context application");
			Executions.sendRedirect("/login.zul");
			return;
		}
	}
}
