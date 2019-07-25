package com.came.control.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.came.control.beans.constantes.ConstAtributos;

public class DatabaseMetaclas {

	@Autowired
	protected SessionFactory sessionFactory;
 
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	
	public Calendar getFechaControladaCalendar(Calendar fecha, boolean principio) {
		Calendar fechaMutada = Calendar.getInstance();

		fechaMutada.set(1, fecha.get(1));
		fechaMutada.set(2, fecha.get(2));
		fechaMutada.set(5, fecha.get(5));
		if (principio) {
			fechaMutada.set(11, 0);
			fechaMutada.set(12, 0);
			fechaMutada.set(13, 0);
		} else {
			fechaMutada.set(11, 23);
			fechaMutada.set(12, 59);
			fechaMutada.set(13, 59);
		}
		return fechaMutada;
	}
	
	public String getFechaControladaString(Calendar fecha, boolean principio) {
		
		Calendar fechaMutada = Calendar.getInstance();

		fechaMutada.set(1, fecha.get(1));
		fechaMutada.set(2, fecha.get(2));
		fechaMutada.set(5, fecha.get(5));
		if (principio) {
			fechaMutada.set(11, 0);
			fechaMutada.set(12, 0);
			fechaMutada.set(13, 0);
		} else {
			fechaMutada.set(11, 23);
			fechaMutada.set(12, 59);
			fechaMutada.set(13, 59);
		}
		SimpleDateFormat format1 = new SimpleDateFormat(ConstAtributos.FORMAT_DATE_YYY_MM_DD_HH_MM_SS);
		return format1.format(fechaMutada.getTime());
	}

}
