package com.came.stock.web.utils;

import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;

import com.came.stock.constantes.StockConstants;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.utilidades.SessionUtils;
import com.came.stock.utilidades.StockUtils;

public class IntegrityChecker implements Initiator {
	private Logger log = LogManager.getLogger(getClass().getName());

	public void doInit(Page page, Map<String, Object> args) throws Exception {
		SessionUtils sessionUtils = null;
		Object session = args.get(StockConstants.BENUTZER);
		if ((session instanceof SessionUtils)) {
			sessionUtils = (SessionUtils) session;
			Organizacion organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
			if (organizacion == null) {
				Executions.sendRedirect("/login.zul");
				return;
			}
		} else {
			StockUtils.showSuccessmessage("Session isn't context application", "error", Integer.valueOf(0), null);

			this.log.error("Session isn't context application");
			Executions.sendRedirect("/login.zul");
			return;
		}
	}
}
