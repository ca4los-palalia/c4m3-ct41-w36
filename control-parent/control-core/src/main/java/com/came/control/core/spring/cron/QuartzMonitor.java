package com.came.control.core.spring.cron;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.came.control.beans.ConfigureTimer;
import com.came.control.beans.RegistroEncapsulado;
import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.core.spring.InitialData;
import com.came.control.model.domain.Asistencia;
import com.came.control.model.domain.Configuracion;
import com.came.control.model.services.AsistenciaService;
import com.came.control.model.services.ConfiguracionService;
import com.google.gson.Gson;

public class QuartzMonitor extends QuartzJobBean {

	private static final Logger logger = Logger.getLogger(QuartzMonitor.class);
	
	private AsistenciaService asistenciaService;
	private ConfiguracionService configuracionService;
	private String timeCutConfigure;
	protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
		Calendar calendar = Calendar.getInstance();
		JobDataMap dataMap = ctx.getJobDetail().getJobDataMap();
		configuracionService = (ConfiguracionService) dataMap.get("configuracionService");
		
		timeCutConfigure = getTimeConfigure();
		
		if(timeCutConfigure.equals(getTimeNow(calendar))) {
			logger.info("Iniciando corte de incidencias");
			asistenciaService = (AsistenciaService) dataMap.get("asistenciaService");
			List<Asistencia> list = asistenciaService.getAllWhithFecha(calendar.getTime());
			
			if(list != null) {
				Gson gson = new Gson();
				for (Asistencia itemAs : list) {
					RegistroEncapsulado registro = itemAs.getRegistro() == null ? null : gson.fromJson(itemAs.getRegistro(), RegistroEncapsulado.class); 
				}
			}
		}
	}
	
	private String getTimeNow(Calendar cal) {
		Integer hrs = cal.get(Calendar.HOUR_OF_DAY);
		Integer min = cal.get(Calendar.MINUTE);
		return hrs + ":"+ min;
	}
	
	private String getTimeConfigure() {
		String cfgTimerStr = null;
		List<Configuracion> list = configuracionService.getAll();
		for (Configuracion item : list) {
			if(item.getOrganizacion().getIdOrganizacion().equals(1L) && item.getClave().equals(ConstAtributos.CFG_TIME_INCIDENCIAS)) {
				ConfigureTimer cfgTimer = new Gson().fromJson(item.getValor(), ConfigureTimer.class);
				cfgTimerStr = cfgTimer.getHora();
				break;
			}
		}
		return cfgTimerStr != null ? cfgTimerStr : null;
	}
}
