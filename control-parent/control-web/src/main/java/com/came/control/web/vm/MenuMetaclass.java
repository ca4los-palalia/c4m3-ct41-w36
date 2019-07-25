package com.came.control.web.vm;

import java.util.List;

import org.zkoss.bind.annotation.Init;

import com.came.control.beans.DirectoryTreeNode;
import com.came.control.beans.DirectoryTreeNodeCollection;
import com.came.control.model.domain.Modulo;
import com.came.control.model.domain.ModuloUsuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MenuMetaclass extends MenuVariables {
	private static final long serialVersionUID = 5093877120990395398L;

	@Init
	public void init() {
		initObjects();
//		initProperties();
	}

	public void initObjects() {
		PAGE_TO_RENDER = "pageToRender";
		
		
		
	}
	
	public DirectoryTreeNode<Modulo> generateModuloModules() {
		List<ModuloUsuario> modulos = moduloUsuarioRest.getByUsuario(usuario);
		DirectoryTreeNode<Modulo> root = new DirectoryTreeNode<Modulo>(null, null);
		if(modulos != null) {
			for (ModuloUsuario moduloUsuarioDb : modulos) {
				if(moduloUsuarioDb.getActivar()) {
					if(moduloUsuarioDb.getModulo().getSubModulos() == null)
						root.add(new DirectoryTreeNode<Modulo>(moduloUsuarioDb.getModulo()));
					else {
						TypeToken<List<Modulo>> token = new TypeToken<List<Modulo>>() {};
						List<Modulo> responseObject = new Gson().fromJson(moduloUsuarioDb.getModulo().getSubModulos(), token.getType());

						DirectoryTreeNodeCollection subModulo = new DirectoryTreeNodeCollection<Modulo>();
						for (Modulo modulo : responseObject) {
							subModulo.add(new DirectoryTreeNode<Modulo>(modulo));
						}
						root.add(new DirectoryTreeNode<Modulo>(moduloUsuarioDb.getModulo(), subModulo));
					}
				}
			}
		}
		
//		DirectoryTreeNode<Modulo> root = new DirectoryTreeNode<Modulo>(null,
//                new DirectoryTreeNodeCollection<Modulo>() {
//            private static final long serialVersionUID = 9019022379404376015L;
//
//            {
//                add(new DirectoryTreeNode<Modulo>(new Modulo(
//                        "/doc", "Release Notes and License", "")));
//                add(new DirectoryTreeNode<Modulo>(new Modulo(
//                        "/dist", null, ""),
//                        new DirectoryTreeNodeCollection<Modulo>() {
//                            private static final long serialVersionUID = 3541713473898615987L;
//
//                            {
//                                add(new DirectoryTreeNode<Modulo>(
//                                        new Modulo("/lib", null, ""),
//                                        new DirectoryTreeNodeCollection<Modulo>() {
//                                            private static final long serialVersionUID = 7225750712385675090L;
//
//                                            {
//                                                add(new DirectoryTreeNode<Modulo>(
//                                                        new Modulo(
//                                                                "/zkforge",
//                                                                null, ""),
//                                                        new DirectoryTreeNodeCollection<Modulo>() {
//                                                            private static final long serialVersionUID = 1204356757289701541L;
//
//                                                            {
//                                                                add(new DirectoryTreeNode<Modulo>(
//                                                                        new Modulo(
//                                                                                "ckez.jar",
//                                                                                "CKeditor",
//                                                                                "1709KB")));
//                                                                add(new DirectoryTreeNode<Modulo>(
//                                                                        new Modulo(
//                                                                                "timelinez.jar",
//                                                                                "Timeline",
//                                                                                "283KB")));
//                                                                add(new DirectoryTreeNode<Modulo>(
//                                                                        new Modulo(
//                                                                                "timeplotz.jar",
//                                                                                "Timeplot",
//                                                                                "112KB")));
//                                                                add(new DirectoryTreeNode<Modulo>(
//                                                                        new Modulo(
//                                                                                "gmapsz.jar",
//                                                                                "Google Maps",
//                                                                                "95KB")));
//                                                                add(new DirectoryTreeNode<Modulo>(
//                                                                        new Modulo(
//                                                                                "zuljsp.jar",
//                                                                                "JSP",
//                                                                                "129KB")));
//                                                            }
//                                                        }
//
//                                                        , true));
//
//                                                add(new DirectoryTreeNode<Modulo>(
//                                                        new Modulo(
//                                                                "/ext",
//                                                                null, ""),
//                                                        new DirectoryTreeNodeCollection<Modulo>() {
//                                                            private static final long serialVersionUID = 4142744663101866804L;
//
//                                                            {
//                                                                add(new DirectoryTreeNode<Modulo>(
//                                                                        new Modulo(
//                                                                                "commons-fileupload.jar",
//                                                                                "Upload Features", "")));
//                                                                add(new DirectoryTreeNode<Modulo>(
//                                                                        new Modulo(
//                                                                                "commons-io.jar",
//                                                                                "Upload Features", "")));
//                                                                add(new DirectoryTreeNode<Modulo>(
//                                                                        new Modulo(
//                                                                                "jcommon.jar",
//                                                                                "Chart Component", "")));
//                                                                add(new DirectoryTreeNode<Modulo>(
//                                                                        new Modulo(
//                                                                                "jfreechar.jar",
//                                                                                "Chart Component", "")));
//                                                                add(new DirectoryTreeNode<Modulo>(
//                                                                        new Modulo(
//                                                                                "jasperreports.jar",
//                                                                                "Jasperreport related Component", "")));
//                                                                add(new DirectoryTreeNode<Modulo>(
//                                                                        new Modulo(
//                                                                                "itext.jarjxl.jar",
//                                                                                "Jasperreport related Component", "")));
//                                                                add(new DirectoryTreeNode<Modulo>(
//                                                                        new Modulo(
//                                                                                "poi.jar",
//                                                                                "Jasperreport related Component", "")));
//                                                                add(new DirectoryTreeNode<Modulo>(
//                                                                        new Modulo(
//                                                                                "commons-collections.jar",
//                                                                                "Jasperreport related Component", "")));
//                                                                add(new DirectoryTreeNode<Modulo>(
//                                                                        new Modulo(
//                                                                                "commons-logging.jar",
//                                                                                "Jasperreport related Component", "")));
//                                                                add(new DirectoryTreeNode<Modulo>(
//                                                                        new Modulo(
//                                                                                "commons-digester.jar",
//                                                                                "Jasperreport related Component", "")));
//                                                                add(new DirectoryTreeNode<Modulo>(
//                                                                        new Modulo(
//                                                                                "bsh.jar",
//                                                                                "Scripting in Java interpreter for zscript (BeanShell)", "")));
//                                                                add(new DirectoryTreeNode<Modulo>(
//                                                                        new Modulo(
//                                                                                "js.jar",
//                                                                                "Scripting in JavaScript (Rhino)", "")));
//                                                                add(new DirectoryTreeNode<Modulo>(
//                                                                        new Modulo(
//                                                                                "groovy.jar",
//                                                                                "Scripting in Groovy", "")));
//                                                                add(new DirectoryTreeNode<Modulo>(
//                                                                        new Modulo(
//                                                                                "jruby.jar",
//                                                                                "Scripting in Ruby (JRuby)", "")));
//                                                                add(new DirectoryTreeNode<Modulo>(
//                                                                        new Modulo(
//                                                                                "jython.jar",
//                                                                                "Scripting in Python (Jython)", "")));
//                                                                add(new DirectoryTreeNode<Modulo>(
//                                                                        new Modulo(
//                                                                                "Filters.jar",
//                                                                                "Captcha Component.", "")));
//                                                                add(new DirectoryTreeNode<Modulo>(
//                                                                        new Modulo(
//                                                                                "mvel.jar",
//                                                                                "Evaluate the expressions (MVEL)", "")));
//                                                                add(new DirectoryTreeNode<Modulo>(
//                                                                        new Modulo(
//                                                                                "ognl.jar",
//                                                                                "Evaluate the expressions (OGNL)", "")));
//                                                            }
//                                                        }));
//
//                                                add(new DirectoryTreeNode<Modulo>(
//                                                        new Modulo(
//                                                                "zcommon.jar",
//                                                                "ZK Core Jar File",
//                                                                "413KB")));
//                                                add(new DirectoryTreeNode<Modulo>(
//                                                        new Modulo(
//                                                                "zcommon-el.jar",
//                                                                "ZK Core Jar File",
//                                                                "100KB")));
//                                                add(new DirectoryTreeNode<Modulo>(
//                                                        new Modulo(
//                                                                "zel.jar",
//                                                                "ZK Core Jar File",
//                                                                "151KB")));
//                                                add(new DirectoryTreeNode<Modulo>(
//                                                        new Modulo(
//                                                                "zhtml.jar",
//                                                                "ZK Core Jar File",
//                                                                "57KB")));
//                                                add(new DirectoryTreeNode<Modulo>(
//                                                        new Modulo(
//                                                                "zml",
//                                                                "ZK Core Jar File",
//                                                                "57KB")));
//                                                add(new DirectoryTreeNode<Modulo>(
//                                                        new Modulo(
//                                                                "zk.jar",
//                                                                "ZK Core Jar File",
//                                                                "1056KB")));
//                                                add(new DirectoryTreeNode<Modulo>(
//                                                        new Modulo(
//                                                                "zkbind.jar",
//                                                                "ZK Core Jar File",
//                                                                "270KB")));
//                                                add(new DirectoryTreeNode<Modulo>(
//                                                        new Modulo(
//                                                                "zkplus.jar",
//                                                                "ZK Core Jar File",
//                                                                "122KB")));
//                                                add(new DirectoryTreeNode<Modulo>(
//                                                        new Modulo(
//                                                                "zul.jar",
//                                                                "ZK Core Jar File",
//                                                                "1311KB")));
//                                                add(new DirectoryTreeNode<Modulo>(
//                                                        new Modulo(
//                                                                "zweb.jar",
//                                                                "ZK Core Jar File",
//                                                                "196KB")));
//
//                                            }
//                                        }
//
//                                ));
//
//                                add(new DirectoryTreeNode<Modulo>(
//                                        new Modulo("/src",
//                                                "Jar Format Source Code", "")));
//                                add(new DirectoryTreeNode<Modulo>(
//                                        new Modulo("/xsd",
//                                                "XSD files for Development", "")));
//                                add(new DirectoryTreeNode<Modulo>(
//                                        new Modulo("/WEB-INF",
//                                                "Configuration Files", "")));
//                            }
//                        }
//
//                ));
//
//            }
//        }, true); // dist opened
		
		return root;
	}


	public List<ModuloUsuario> buildergetNavbarList() {
		List<ModuloUsuario> modulos = moduloUsuarioRest.getByUsuario(usuario);
		for (ModuloUsuario moduloUsuarioDb : modulos) {
			if(moduloUsuarioDb.getActivar()) {
				if(moduloUsuarioDb.getModulo().getSubModulos() != null) {
					TypeToken<List<Modulo>> token = new TypeToken<List<Modulo>>() {};
					List<Modulo> responseObject = new Gson().fromJson(moduloUsuarioDb.getModulo().getSubModulos(), token.getType());
					moduloUsuarioDb.setSubModulo(responseObject);
				}
			}
		}
		return modulos;
	}

//	public void initProperties() {
//		leerVariablesProperties();
//		
//		ItemMenu requisicion = new ItemMenu();
//		requisicion.setCsrIcon("/images/toolbar/text_enriched16.png");
//		requisicion.setNombre(TAG_MENUVM_SUBTITLE1_ITEM_REQ);
//		
//		ItemMenu concentrado = new ItemMenu();
//		concentrado.setCsrIcon("/images/toolbar/filter_list16.png");
//		concentrado.setNombre(TAG_MENUVM_SUBTITLE1_ITEM_CONCENTR);
//		
//		moduloRequisiciones.add(requisicion);
//		moduloRequisiciones.add(concentrado);
//		
//		//-----------------------
//		
//		ItemMenu cotizacion = new ItemMenu();
//		cotizacion.setCsrIcon("/images/toolbar/list16.png");
//		cotizacion.setNombre(TAG_MENUVM_SUBTITLE2_ITEM_COT);
//		moduloCotizaciones.add(cotizacion);
//		
//		//-----------------------
//		
//		
//		ItemMenu ordenCompra = new ItemMenu();
//		ordenCompra.setCsrIcon("/images/toolbar/buy16.png");
//		ordenCompra.setNombre(TAG_MENUVM_SUBTITLE3_ITEM_ORDENCOMPRA);
//		
//		ItemMenu control = new ItemMenu();
//		control.setCsrIcon("/images/toolbar/control16.png");
//		control.setNombre(TAG_MENUVM_SUBTITLE3_ITEM_CONTROL);
//		
//		ItemMenu kardexItem = new ItemMenu();
//		kardexItem.setCsrIcon("/images/toolbar/control16.png");
//		kardexItem.setNombre(TAG_MENUVM_SUBTITLE3_ITEM_KARDEX);
//		
//		moduloOrdenesCompra.add(ordenCompra);
//		moduloOrdenesCompra.add(control);
//		moduloOrdenesCompra.add(kardexItem);
//		
//		//----------------------------
//		
//		ItemMenu catalogos = new ItemMenu();
//		catalogos.setCsrIcon("/images/toolbar/catalog16.png");
//		catalogos.setNombre(TAG_MENUVM_SUBTITLE4_ITEM_CATA);
//		
//		ItemMenu productos = new ItemMenu();
//		productos.setCsrIcon("/images/toolbar/product16.png");
//		productos.setNombre(TAG_MENUVM_SUBTITLE4_ITEM_PRODUC);
//		
//		ItemMenu proveedores = new ItemMenu();
//		proveedores.setCsrIcon("/images/toolbar/process.png");
//		proveedores.setNombre(TAG_MENUVM_SUBTITLE4_ITEM_PROVEEDOR);
//		
//		ItemMenu clientes = new ItemMenu();
//		clientes.setCsrIcon("/images/toolbar/clients16.png");
//		clientes.setNombre(TAG_MENUVM_SUBTITLE4_ITEM_CLIENTES);
//		
//		ItemMenu usuarios = new ItemMenu();
//		usuarios.setCsrIcon("/images/toolbar/user16.png");
//		usuarios.setNombre(TAG_MENUVM_SUBTITLE4_ITEM_USUARIO);
//		
//		moduloPanelControl.add(catalogos);
//		moduloPanelControl.add(productos);
//		moduloPanelControl.add(proveedores);
//		if(usuario.getOwner())
//			moduloPanelControl.add(clientes);
//		moduloPanelControl.add(usuarios);
//		
//		//------------------------------
//		ItemMenu perfil = new ItemMenu();
//		perfil.setCsrIcon("/images/toolbar/report.png");
//		perfil.setNombre(TAG_MENUVM_SUBTITLE5_ITEM_PERFIL);
//		moduloPerfiles.add(perfil);
//		
//	
//	}
//	
//	private void leerVariablesProperties(){
//		Properties propiedades = getPropertiesFiles();
//		TAG_MENUVM_SUBTITLE1_REQ = propiedades.getProperty("menuVM.subTitle1.requiciones");// = Requisiciones
//		TAG_MENUVM_SUBTITLE1_ITEM_REQ = propiedades.getProperty("menuVM.subTitle1.itemRequisicion");// = Requisición
//		TAG_MENUVM_SUBTITLE1_ITEM_CONCENTR = propiedades.getProperty("menuVM.subTitle1.itemConcentrado");// = Concentrado
//		TAG_MENUVM_SUBTITLE2_COTIZACIONES = propiedades.getProperty("menuVM.subTitle2.cotizaciones");// = Cotizaciones
//		TAG_MENUVM_SUBTITLE2_ITEM_COT = propiedades.getProperty("menuVM.subTitle2.itemCotizacion");// = Cotización 
//		TAG_MENUVM_SUBTITLE3_ORDENCOMPRA = propiedades.getProperty("menuVM.subTitle3.ordenCompra");// = Órdenes de compra
//		TAG_MENUVM_SUBTITLE3_ITEM_ORDENCOMPRA = propiedades.getProperty("menuVM.subTitle3.itemOrdenCompra");// = Orden de compra
//		TAG_MENUVM_SUBTITLE3_ITEM_CONTROL = propiedades.getProperty("menuVM.subTitle3.itemControl");// = Control de compras
//		TAG_MENUVM_SUBTITLE3_ITEM_KARDEX = propiedades.getProperty("menuVM.subTitle3.itemKardex");// = Kardex
//		
//		
//		TAG_MENUVM_SUBTITLE4_PANELCONTROL = propiedades.getProperty("menuVM.subTitle4.panelControl");// = Panel de control
//		TAG_MENUVM_SUBTITLE4_ITEM_CATA = propiedades.getProperty("menuVM.subTitle4.itemCatalogo");// = Catálogos
//		TAG_MENUVM_SUBTITLE4_ITEM_PRODUC = propiedades.getProperty("menuVM.subTitle4.itemProductos");// = Productos
//		TAG_MENUVM_SUBTITLE4_ITEM_PROVEEDOR = propiedades.getProperty("menuVM.subTitle4.itemProveedores");// = Proveedores
//		TAG_MENUVM_SUBTITLE4_ITEM_CLIENTES = propiedades.getProperty("menuVM.subTitle4.itemClientes");// = Clientes
//		TAG_MENUVM_SUBTITLE4_ITEM_USUARIO = propiedades.getProperty("menuVM.subTitle4.itemUsuarios");// = Usuarios
//		TAG_MENUVM_SUBTITLE5_PERFIL = propiedades.getProperty("menuVM.subTitle5.perfil");// = Perfil
//		TAG_MENUVM_SUBTITLE5_ITEM_PERFIL = propiedades.getProperty("menuVM.subTitle5.itemMiPerfil");// = Mi Perfil
//	}

}
