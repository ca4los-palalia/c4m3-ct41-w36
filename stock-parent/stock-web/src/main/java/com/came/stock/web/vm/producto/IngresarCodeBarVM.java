package com.came.stock.web.vm.producto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import com.came.stock.model.domain.CodigoBarrasProducto;
import com.came.stock.model.domain.ConffyaPartidaGenerica;
import com.came.stock.model.domain.Producto;
import com.came.stock.utilidades.StockUtils;
import com.came.stock.web.vm.BasicStructure;

@VariableResolver({ DelegatingVariableResolver.class })
public class IngresarCodeBarVM extends BasicStructure {
	private static final long serialVersionUID = 3098239433101641553L;
	@Wire("#ingresarCodeBarModalDialog")
	private Window productosModalDialog;
	
	private CodigoBarrasProducto nuevoCodigo;
	private String globalCommandName;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("updateCommandFromItemFinder") String updateCommandFromItemFinder,
			@ExecutionArgParam("catalogoProductos") List<Producto> catalogoProductos,
			@ExecutionArgParam("partidaGenerica") ConffyaPartidaGenerica partidaGenerica) {
		Selectors.wireComponents(view, this, false);
		globalCommandName = updateCommandFromItemFinder;
		nuevoCodigo = new CodigoBarrasProducto();
		leerVariablesProperties();
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	private boolean generarCodigoDeBarras(String codigo){
		boolean ok = true;
		try {
			nuevoCodigo.setBarCode(barCodeEan128(codigo));
			nuevoCodigo.setCodigo(codigo);
		} catch (Exception e) {
			ok = false;
			e.printStackTrace();
		}
		return ok;
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void transferProduct() {
		if (nuevoCodigo != null && (nuevoCodigo.getCodigo() != null && !nuevoCodigo.getCodigo().isEmpty())   ) {
			if(generarCodigoDeBarras(nuevoCodigo.getCodigo())){
				productosModalDialog.detach();
				Map<String, Object> args = new HashMap();
				args.put("barCode", nuevoCodigo);
				if ((globalCommandName != null) && (!globalCommandName.isEmpty())) {
					BindUtils.postGlobalCommand(null, null, globalCommandName, args);
				} else {
					BindUtils.postGlobalCommand(null, null, "updateFromSelectedItem", args);
				}
			}else
				StockUtils.showSuccessmessage("Error al generar el codigo intente con otro numero",
						"error", Integer.valueOf(0), null);
		}else
			StockUtils.showSuccessmessage("Ingrese un codigo para generar el codigo de barras",
					"error", Integer.valueOf(0), null);
	}

	@Command
	public void closeDialog() {
		if (this.productosModalDialog != null) {
			this.productosModalDialog.detach();
		}
	}

	private void leerVariablesProperties(){
		Properties propiedades = getPropertiesFiles();
		WINDOW_BUSCADOR_PRODUCTOS_TITLE = propiedades.getProperty("window.buscador.productos.title");//Buscar Productos
		WINDOW_BUSCADOR_PRODUCTOS_EMPTY = propiedades.getProperty("window.buscador.productos.empty");//Catálogo de productos vacío
		
		GENERICZUL_LABEL_CLAVE = propiedades.getProperty("genericZUL.label.clave");//Clave:
		GENERICZUL_LABEL_NOMBRE = propiedades.getProperty("genericZUL.label.nombre");//Nombre:
		GENERICZUL_COLUMN_CLAVE = propiedades.getProperty("genericZUL.column.clave");//Clave
		GENERICZUL_COLUMN_NOMBRE = propiedades.getProperty("genericZUL.column.nombre");//Nombre
		GENERICZUL_COLUMN_MARCA = propiedades.getProperty("genericZUL.column.marca");//Marca
		GENERICZUL_COMMAND_CANCELAR = propiedades.getProperty("genericZUL.command.cancelar");//Cancelar
		GENERICZUL_COMMAND_ACEPTAR = propiedades.getProperty("genericZUL.command.aceptar");//Aceptar
		WINDOW_MESSAGE1_TITLE = propiedades.getProperty("window.message1.title");//Producto no encontrado
		WINDOW_MESSAGE1_CONTENT = propiedades.getProperty("window.message1.content");// No se obtubieron resultados bajo el parametro de "
		WINDOW_MESSAGE2_CONTENT = propiedades.getProperty("window.message2.content");// Los parametros especificados no generaron ningún resultado
	}

	public CodigoBarrasProducto getNuevoCodigo() {
		return nuevoCodigo;
	}

	public void setNuevoCodigo(CodigoBarrasProducto nuevoCodigo) {
		this.nuevoCodigo = nuevoCodigo;
	}

	
	
	
}
