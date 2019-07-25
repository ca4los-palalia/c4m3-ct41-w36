package com.came.control.beans.funciones;

import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;

import com.came.control.beans.CtrlError;
import com.came.control.beans.constantes.ConstAtributos;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class CtrlUtilString {

	public CtrlError formatXmlPretty(String xml) {
		CtrlError ctrl = new CtrlError();

		if (xml != null && !xml.isEmpty()) {
			try {
				final InputSource src = new InputSource(new StringReader(xml));
				final Node document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src)
						.getDocumentElement();
				final Boolean keepDeclaration = Boolean.valueOf(xml.startsWith("<?xml"));

				final DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
				final DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("LS");
				final LSSerializer writer = impl.createLSSerializer();

				writer.getDomConfig().setParameter("format-pretty-print", Boolean.TRUE);
				writer.getDomConfig().setParameter("xml-declaration", keepDeclaration);

				ctrl.setMensaje(writer.writeToString(document));
				ctrl.setOk(true);
			} catch (Exception e) {
				ctrl.setExcepcion(e);
				ctrl.setMensaje(e.getMessage() + "\n" + exceptionToString(e));
				ctrl.setObjetoTratado(xml);
			}
		} else
			ctrl.setMensaje("XML Empty: " + xml);
		return ctrl;
	}

	public String exceptionToString(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}

	public String mapperToString(Map<String, Object> map) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			return ConstAtributos.ERROR_EXCEPTION + " " + e.getMessage();
		}
	}
	
	public String encoderB64(String string){
		return new String(Base64.encodeBase64(string.getBytes()));
	}
	
	public String decoderB64(String b64){
		return new String(Base64.decodeBase64(b64.getBytes()));
	}
}
