package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class RequisicionInboxController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_REQUISICION_INBOX_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService save(RequisicionInbox itemObject) {
		//itemReturn = createResponseToUnidad(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_REQUISICION_INBOX_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService delete(RequisicionInbox itemObject) {
		//itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_REQUISICION_INBOX_ALL_NEWS })
	@Produces("application/json")
	public String getAllNews(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getAllNews(Organizacion organizacion) {
		//itemReturn = createResponseToListUnidad(response);
				return "";
	}

	@PostMapping({ ConstPath.MAP_REQUISICION_INBOX_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getAll(Organizacion organizacion) {
		//itemReturn = createResponseToListUnidad(response);
		return "";
	}
}
