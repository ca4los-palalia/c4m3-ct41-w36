package com.came.control.core.spring;

import java.util.HashMap;
import java.util.Map;

import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.came.control.core.spring.cron.QuartzMonitor;
import com.came.control.model.services.AsistenciaService;
import com.came.control.model.services.ConfiguracionService;

@Configuration
public class QuartzConfiguration {

	@Autowired
	private AsistenciaService asistenciaService;
	@Autowired
	private ConfiguracionService configuracionService;

	private static final String TIMER_EXPRESION = "0 0/1 * 1/1 * ? *";

	public SchedulerFactoryBean scheduler;

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		// SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
		scheduler = new SchedulerFactoryBean();
		scheduler.setTriggers(
				new Trigger[] { cronTriggerFactoryLcoBean().getObject(), cronTriggerFactoryLcoBean().getObject() });

		return scheduler;
	}

	// ------- UPDATE LCO -----------------
	@SuppressWarnings("rawtypes")
	@Bean
	public JobDetailFactoryBean jobDetailFactoryLcoBean() {
		JobDetailFactoryBean factory = new JobDetailFactoryBean();
		factory.setJobClass(QuartzMonitor.class);
		@SuppressWarnings("unchecked")
		Map<String, Object> map = new HashMap();
		map.put("asistenciaService", asistenciaService);
		map.put("configuracionService", configuracionService);
		factory.setJobDataAsMap(map);
		factory.setGroup("mygroup");
		factory.setName("myjob");
		return factory;
	}

	@Bean
	public CronTriggerFactoryBean cronTriggerFactoryLcoBean() {
		// inicializarPorPrimeraVezLco();
		CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
		stFactory.setJobDetail(jobDetailFactoryLcoBean().getObject());
		stFactory.setStartDelay(3000L);
		stFactory.setName("triggerLco");
		stFactory.setGroup("lco");
		stFactory.setCronExpression(TIMER_EXPRESION);
		return stFactory;
	}
}
