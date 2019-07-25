package com.came.control.web.vm;

import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zhtml.A;
import org.zkoss.zhtml.Li;
import org.zkoss.zhtml.Span;
import org.zkoss.zhtml.Ul;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.DefaultTreeModel;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Tree;
import org.zkoss.zul.TreeModel;
import org.zkoss.zul.TreeNode;

import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.model.domain.Modulo;
import com.came.control.model.domain.ModuloUsuario;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Usuario;

@VariableResolver({ DelegatingVariableResolver.class })
public class MenuVM extends MenuMetaclass {
	private static final long serialVersionUID = -2153432633385920494L;

	@Wire("#mainWindowMenu, #tree")
	private Tree tree;
	@Wire("#mainWindowMenu, #gnb")
	private Ul gnb;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view) {
		super.init();
		usuario = ((Usuario) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_USUARIO));
		organizacion = ((Organizacion) ctrlUtilsSession.getFromSession(ConstAtributos.SESSION_FIRMA));
		
		args = new HashMap();
	}
	

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		
		List<ModuloUsuario> list = buildergetNavbarList();
		for (ModuloUsuario muObject : list) {
			Li liItem = new Li();
			liItem.setSclass("gnb1");
			
			A aref = new A();
			aref.setAttribute("href", "#");
			
			
			Span span = new Span();
			
			Label label = new Label();;
			label.setValue(muObject.getModulo().getNombre());
			
			span.appendChild(label);
			aref.appendChild(span);
			liItem.appendChild(aref);
			
			if(muObject.getSubModulo() != null && muObject.getSubModulo().size() > 0)
				liItem.appendChild(crearSubmodulo(muObject.getSubModulo(), muObject));	
			gnb.appendChild(liItem);
		}
		
		
		
	}
	
	private Div crearSubmodulo(List<Modulo> subModulo, ModuloUsuario moduloUsuarioSelected) {
		Div div = new Div();
		Ul ul = new Ul();
		
		for (Modulo item : subModulo) {
			Li li = new Li();
			li.setSclass("gnb11");
			
			A aref = new A();
			aref.setAttribute("href", "#");
			
			
			Span span = new Span();
			
			Label label = new Label();;
			label.setValue(item.getNombre());
			
			span.appendChild(label);
			aref.appendChild(span);
			li.appendChild(aref);
			
			ul.appendChild(li);
		}
		div.appendChild(ul);
		return div;
	}
	
	
	public TreeModel<TreeNode<Modulo>> getTreeModel() {
        return new DefaultTreeModel<Modulo>(generateModuloModules());
    }
	
	@Command
	public void menuClicked() {
		if(menuSelected != null) {
			Modulo m = (Modulo) menuSelected.getData();
			ctrlUtilsSession.addToSession(ConstAtributos.SESSION_MODULO_USUARIO, m);
			args.put("pageToRender", m.getRuta());
			BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
		}
	}
	
	

	@Command
	public void showConcentrado() {
		args.put("pageToRender", "/modulos/requisicion/concentrado.zul");
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

}
