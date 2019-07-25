package com.came.control.beans.funciones;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.management.ManagementFactory;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.Query;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Repository;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

import com.came.control.beans.CfgCore;
import com.came.control.beans.Registro;
import com.came.control.beans.constantes.ConstAtributos;

@Repository
public class CtrlUtils {
	@SuppressWarnings("unused")
	private static final String ALGORITHM = "md5";
	@SuppressWarnings("unused")
	private static final String DIGEST_STRING = "HG58YZ3CR9";
	@SuppressWarnings("unused")
	private static final String CHARSET_UTF_8 = "utf-8";
	@SuppressWarnings("unused")
	private static final String SECRET_KEY_ALGORITHM = "DESede";
	@SuppressWarnings("unused")
	private static final String TRANSFORMATION_PADDING = "DESede/CBC/PKCS5Padding";
	private DecimalFormat format = new DecimalFormat("###,###,###.00");

	public Window createModelDialog(String locationView) {
		Window window = (Window) Executions.createComponents(locationView, null, null);

		return window;
	}

	public Window createModelDialogWithParams(String locationView, Map<String, Object> params) {
		Window window = (Window) Executions.createComponents(locationView, null, params);

		return window;
	}

	public void redirect(String page) {
		Executions.getCurrent().sendRedirect(page);
	}

	public static void showSuccessmessage(String mensaje, String tipo, Integer duracionEnVista, Component componente) {
		Clients.showNotification(mensaje, tipo, componente, null, duracionEnVista.intValue());
	}

	public String formatCurrency(Double quantity) {
		if (quantity != null) {
			return this.format.format(quantity);
		}
		return null;
	}

	public Calendar convertirDateToCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	public Date convertirCalendarToDate(Calendar calendar) {
		Date date = calendar.getTime();
		return date;
	}
	
	public Calendar convertirStringToCalendar(String fechaString) {
		Calendar cal = null;
		if(fechaString != null) {
			try {
				
				cal = DatatypeConverter.parseDateTime(fechaString);
				//SimpleDateFormat sdf = new SimpleDateFormat(ConstAtributos.FORMAT_DATE_YYY_MM_DDTHH_MM_SSSZ, new Locale ("es","ES"));
				//cal.setTime(sdf.parse(fechaString));// all done
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return cal;
	}
	
	public Calendar convertirStringToCalendarWithFormat(String fechaString, String formatter) {
		Calendar cal = null;
		if(fechaString != null) {
			try {
				cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat(formatter);
				cal.setTime(sdf.parse(fechaString));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return cal;
	}

	/**
	 * @param Calendar
	 * @param format 
	 * 
	 * <ul>
	 * 		<li>dd/MM/yyyy</li>
	 *		<li>yyyy-MM-dd</li>
	 *		<li>MM/dd/yyyy HH:mm:ss</li>
	 * </ul
	 **/
	public String convertirCalendarToString(Calendar calendar, String format) {
		Date date = convertirCalendarToDate(calendar);
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	public Calendar convertirStringToCalendar(Integer dia, Integer mes, Integer anyo) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1, anyo.intValue());
		calendar.set(2, mes.intValue() - 1);
		calendar.set(5, dia.intValue());
		return calendar;
	}
	
	public static Date getDifferenceBetwenDates(Date dateInicio, Date dateFinal) {
	    long milliseconds = dateFinal.getTime() - dateInicio.getTime();
	    int seconds = (int) (milliseconds / 1000) % 60;
	    int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
	    int hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24);
	    Calendar c = Calendar.getInstance();
	    c.set(Calendar.SECOND, seconds);
	    c.set(Calendar.MINUTE, minutes);
	    c.set(Calendar.HOUR_OF_DAY, hours);
	    return c.getTime();
	}
	
	
	public Calendar getFechaControladaCalendarCambiarHr(Calendar fecha, int hr, int min) {
		Calendar fechaMutada = Calendar.getInstance();
		fechaMutada = builderCalendar(fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH), fecha.get(Calendar.DAY_OF_MONTH), hr, min, 0);
		return fechaMutada;
	}
	
	public Calendar getFechaControladaCalendar(Calendar fecha, boolean principio) {
		Calendar fechaMutada = Calendar.getInstance();
		if (principio)
			fechaMutada = builderCalendar(fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH), fecha.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		else
			fechaMutada = builderCalendar(fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH), fecha.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		return fechaMutada;
	}
	
	public Date getFechaControladaDate(Calendar fecha, boolean principio) {
		Calendar fechaMutada = Calendar.getInstance();
		if (principio)
			fechaMutada = builderCalendar(fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH), fecha.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		else
			fechaMutada = builderCalendar(fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH), fecha.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		return fechaMutada.getTime();
	}
	
	public String getFechaControladaString(Calendar fecha, boolean principio) {
		Calendar fechaMutada = Calendar.getInstance();
		if (principio)
			fechaMutada = builderCalendar(fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH), fecha.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		else
			fechaMutada = builderCalendar(fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH), fecha.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		
		SimpleDateFormat format1 = new SimpleDateFormat(ConstAtributos.FORMAT_DATE_YYY_MM_DD_HH_MM_SS);
		return format1.format(fechaMutada.getTime());
	}
	
	private Calendar builderCalendar(int anio, int month, int day, int hr, int min, int sec) {
		Calendar fechaMutada = Calendar.getInstance();
		fechaMutada.set(Calendar.YEAR, anio);
		fechaMutada.set(Calendar.MONTH, month);
		fechaMutada.set(Calendar.DAY_OF_MONTH, day);
	
		fechaMutada.set(Calendar.HOUR_OF_DAY, hr);
		fechaMutada.set(Calendar.MINUTE, min);
		fechaMutada.set(Calendar.SECOND, sec);
		return fechaMutada;
	}

	public static String encrypt(String message) {

		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] digestOfPassword = md.digest("HG58YZ3CR9".getBytes("utf-8"));

			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
			int j = 0;
			for (int k = 16; j < 8;) {
				keyBytes[(k++)] = keyBytes[(j++)];
			}
			SecretKey key = new SecretKeySpec(keyBytes, "DESede");

			IvParameterSpec iv = new IvParameterSpec(new byte[8]);
			Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			cipher.init(1, key, iv);

			byte[] plainTextBytes = message.getBytes("utf-8");
			byte[] cipherText = cipher.doFinal(plainTextBytes);

			return new String(cipherText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String decrypt(String message) {
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] digestOfPassword = md.digest("HG58YZ3CR9".getBytes("utf-8"));

			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
			int j = 0;
			for (int k = 16; j < 8;) {
				keyBytes[(k++)] = keyBytes[(j++)];
			}
			SecretKey key = new SecretKeySpec(keyBytes, "DESede");

			IvParameterSpec iv = new IvParameterSpec(new byte[8]);
			Cipher decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			decipher.init(2, key, iv);

			byte[] plainText = decipher.doFinal(message.getBytes());

			return new String(plainText, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getFechaActualConHora() {
		Date date = new Date();
		DateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
		return hourdateFormat.format(date);
	}
	
	public String dateToString(Date date) {
		DateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
		return hourdateFormat.format(date);
	}

	// Convierte texto de ISO-8859-1 a UTF-8
	public static String convertFromUTF8(String s) {
		String out = null;
		try {
			out = new String(s.getBytes("ISO-8859-1"), "UTF-8");
		} catch (java.io.UnsupportedEncodingException e) {
			return null;
		}
		return out;
	}

	// Convierte texto de UTF-8 a ISO-8859-1
	public static String convertToUTF8(String s) {
		String out = null;
		try {
			out = new String(s.getBytes("UTF-8"), "ISO-8859-1");
		} catch (java.io.UnsupportedEncodingException e) {
			return null;
		}
		return out;
	}
	
	
	public Registro crearRegistro(boolean activar, String fecha, String label) {
		Registro registro = new Registro();
		registro.setActivar(activar);
		registro.setHoraRegistrada(fecha);
		registro.setLabel(label);
		return registro;
	}

	// ----------------------------------------------------

	public String Encriptar(String texto) {

		String secretKey = "qualityinfosolutions"; // llave para encriptar datos
		String base64EncryptedString = "";

		try {

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

			SecretKey key = new SecretKeySpec(keyBytes, "DESede");
			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(Cipher.ENCRYPT_MODE, key);

			byte[] plainTextBytes = texto.getBytes("utf-8");
			byte[] buf = cipher.doFinal(plainTextBytes);
			byte[] base64Bytes = Base64.encodeBase64(buf);
			base64EncryptedString = new String(base64Bytes);

		} catch (Exception ex) {
		}
		return base64EncryptedString;
	}

	public String Desencriptar(String textoEncriptado) throws Exception {

		String secretKey = "qualityinfosolutions"; // llave para desenciptar
													// datos
		String base64EncryptedString = "";

		try {
			byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
			SecretKey key = new SecretKeySpec(keyBytes, "DESede");

			Cipher decipher = Cipher.getInstance("DESede");
			decipher.init(Cipher.DECRYPT_MODE, key);

			byte[] plainText = decipher.doFinal(message);

			base64EncryptedString = new String(plainText, "UTF-8");

		} catch (Exception ex) {
		}
		return base64EncryptedString;
	}

	public int diferenciaEnDias(Date fechaMayor, Date fechaMenor) {
		long diferenciaEn_ms = fechaMayor.getTime() - fechaMenor.getTime();
		long dias = diferenciaEn_ms / (1000 * 60 * 60 * 24);
		return (int) dias;
	}

	public static CfgCore getInstanciaDelServidor()
			throws MalformedObjectNameException, NullPointerException, UnknownHostException {
		MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
		Set<ObjectName> objectNames = beanServer.queryNames(new ObjectName("*:type=Connector,*"),
				Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
		String host = InetAddress.getLocalHost().getHostAddress();
		String port = objectNames.iterator().next().getKeyProperty("port");

		CfgCore urlHost = new CfgCore();
		urlHost.setAtributo(ConstAtributos.CONTEXT_WEB);
		urlHost.setValor("http" + "://" + host + ":" + port + "/cyber-core");
		return urlHost;

	}

	public void descomprimir(String ficheroZip, String directorioSalida) {
		final int TAM_BUFFER = 4096;
		byte[] buffer = new byte[TAM_BUFFER];

		ZipInputStream flujo = null;
		try {
			flujo = new ZipInputStream(new BufferedInputStream(new FileInputStream(ficheroZip)));
			ZipEntry entrada;
			while ((entrada = flujo.getNextEntry()) != null) {
				String nombreSalida = directorioSalida + File.separator + entrada.getName();
				if (entrada.isDirectory()) {
					File directorio = new File(nombreSalida);
					directorio.mkdir();
				} else {
					BufferedOutputStream salida = null;
					try {
						int leido;
						salida = new BufferedOutputStream(new FileOutputStream(nombreSalida), TAM_BUFFER);
						while ((leido = flujo.read(buffer, 0, TAM_BUFFER)) != -1) {
							salida.write(buffer, 0, leido);
						}
					} finally {
						if (salida != null) {
							salida.close();
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (flujo != null) {
				try {
					flujo.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			new File(ficheroZip).delete();
		}
	}

	public void copyInputStreamToDiskOneFile(InputStream in, File fileOutput) {
		try {
			OutputStream out = new FileOutputStream(fileOutput);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public AImage pathImagenToAimage(String pathImagen) {
		AImage aimagen = null;
		File file = null;
		try {
			file = new File(pathImagen);
			aimagen = new AImage(file);
		} catch (Exception e) {
			System.out.println("El archivo no se encuentra en el sistema " + e.getMessage());
		}
		return aimagen;
	}
	
	
	public Float haversineDistance(String latStr1, String lonStr1, String latStr2, String lonStr2) {
		DecimalFormat df = new DecimalFormat("#.00");
		//DecimalFormat df = new DecimalFormat("#.00000000");
		latStr1 = df.format(new Double(latStr1));
		lonStr1 = df.format(new Double(lonStr1));
		latStr2 = df.format(new Double(latStr2));
		lonStr2 = df.format(new Double(lonStr2));
		
		final int R = 6371; // Radious of the earth
		Double lat1 = Double.parseDouble(latStr1);
		Double lon1 = Double.parseDouble(lonStr1);
		Double lat2 = Double.parseDouble(latStr2);
		Double lon2 = Double.parseDouble(lonStr2);
		Double latDistance = toRad(lat2-lat1);
		Double lonDistance = toRad(lon2-lon1);
		Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		Double distance = R * c;
		
		//retornar la distancia en metros por la conversion 1Km =1000m
		String strMts = String.valueOf(distance * 1000);
		String [] splitter = strMts.replace(".", "-").split("-");
		strMts = splitter[0] + "." + (splitter[1].length() > 4 ? splitter[1].substring(0, 4) : splitter[1]);
		return new Float(strMts) ;
		//System.out.println(“The distance between two lat and long is::” + distance);
	}
	
	private static Double toRad(Double value) {
		return value * Math.PI / 180;
	}

}
