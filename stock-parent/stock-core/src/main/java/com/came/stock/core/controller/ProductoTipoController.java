package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class ProductoTipoController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_PRODUCTO_TIPO_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService save(ProductoTipo itemObject) {
		// itemReturn = createResponseToProductoTipo(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_TIPO_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService delete(ProductoTipo itemObject) {
		// itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_TIPO_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getById(Long idFinder, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("idFinder", idFinder);
//		tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToProductoTipo(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_TIPO_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getAll(Organizacion organizacion) {
		// itemReturn = createResponseToListProductoTipo(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_TIPO_BY_NOMBRE })
	@Produces("application/json")
	public String getByNombre(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByNombre(String nombre, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("nombre", nombre);
//		tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToProductoTipo(response);
		return "";
	}
}
