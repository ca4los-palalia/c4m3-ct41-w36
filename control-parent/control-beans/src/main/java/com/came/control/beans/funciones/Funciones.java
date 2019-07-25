package com.came.control.beans.funciones;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.management.MalformedObjectNameException;

import org.springframework.stereotype.Repository;
import org.zkoss.image.AImage;
import org.zkoss.io.Files;

import com.came.control.beans.CfgCore;

@Repository
public class Funciones {

	public Date calendarToDate(Calendar calendar) {
		Date date = calendar.getTime();
		return date;
	}

	public Calendar dateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	/** Formato de parseo: yyyy-MM-dd **/
	public String calendarToString(Calendar calendar) {
		Date date = calendarToDate(calendar);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}

	public AImage getAImage(String path) {
		AImage aimagen = null;
		File file = null;
		if (path != null) {
			try {
				file = new File(path);
				aimagen = new AImage(file);
			} catch (Exception e) {
				System.out.println("El fichero " + file.getAbsolutePath() + " no se encuentra en el sistema");
			}
		}
		return aimagen;
	}

	public String copyImagenFromByte(byte imagen[], String path) {
		try {
			Files.copy(new File(path), new ByteArrayInputStream(imagen));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

	public LinkedHashMap<Integer, String> sortHashMapByValues(HashMap<Integer, String> passedMap) {
		List<Integer> mapKeys = new ArrayList<>(passedMap.keySet());
		List<String> mapValues = new ArrayList<>(passedMap.values());
		Collections.sort(mapValues);
		Collections.sort(mapKeys);

		LinkedHashMap<Integer, String> sortedMap = new LinkedHashMap<>();

		Iterator<String> valueIt = mapValues.iterator();
		while (valueIt.hasNext()) {
			String val = valueIt.next();
			Iterator<Integer> keyIt = mapKeys.iterator();

			while (keyIt.hasNext()) {
				Integer key = keyIt.next();
				String comp1 = passedMap.get(key);
				String comp2 = val;

				if (comp1.equals(comp2)) {
					keyIt.remove();
					sortedMap.put(key, val);
					break;
				}
			}
		}
		return sortedMap;
	}

	public byte[] redimensionarImagenByte(byte[] fileData, int width, int height, String extension) {
		ByteArrayInputStream in = new ByteArrayInputStream(fileData);
		try {
			BufferedImage img = ImageIO.read(in);
			if (height == 0) {
				height = (width * img.getHeight()) / img.getWidth();
			}
			if (width == 0) {
				width = (height * img.getWidth()) / img.getHeight();
			}
			Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);

			ByteArrayOutputStream buffer = new ByteArrayOutputStream();

			ImageIO.write(imageBuff, extension, buffer);

			return buffer.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public CfgCore getInstanciaDelServidor()
			throws MalformedObjectNameException, NullPointerException, UnknownHostException {
		
//		MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
//		Set<ObjectName> objectNames = beanServer.queryNames(new ObjectName("*:type=Connector,*"),
//				Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
//		String host = InetAddress.getLocalHost().getHostAddress();
//		String port = "";
//		
//		try {
//			port = objectNames.iterator().next().getKeyProperty("port");
//		} catch (Exception e) {
//			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//			port = String.valueOf(request.getServerPort());
//		}
		
		CfgCore urlHost = new CfgCore();
		urlHost.setAtributo("CONTEXTO_WEB");
		//urlHost.setValor("http://localhost:" + port + "/control-web");
		urlHost.setValor("http://localhost:9090/control-core");
		return urlHost;
	}
}
