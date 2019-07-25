package com.came.control.web.vm.modal;

import java.util.HashMap;
import java.util.Map;

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
import org.zkoss.zul.Button;
import org.zkoss.zul.Window;

import com.came.control.web.bean.MessageControl;

@VariableResolver({ DelegatingVariableResolver.class })
public class InformationVM {

	@Wire("#informationMD, #informationMDblPanel, #informationMDSouth, #informationMDBtnAcept")
	private Button botonAceptar;

	@Wire("#informationMD")
	private Window informationMD;

	private String globalCommandName;
	private MessageControl messageControl;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view, @ExecutionArgParam("itemFinder") String itemFinder,
			@ExecutionArgParam("MessageControl") MessageControl messageControlIn) {
		globalCommandName = itemFinder;
		messageControl = messageControlIn;
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		if (botonAceptar != null)
			botonAceptar.setFocus(true);
	}

	@Command
	public void transfer() {
		informationMD.detach();
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("MessageControl", messageControl);
		if ((this.globalCommandName != null) && (!this.globalCommandName.isEmpty()))
			BindUtils.postGlobalCommand(null, null, globalCommandName, args);
		else
			BindUtils.postGlobalCommand(null, null, "updateFromSelectedItem", args);

	}

	@Command
	public void closeDialog() {
		if (informationMD != null) {
			informationMD.detach();
		}
	}

	public MessageControl getMessageControl() {
		return messageControl;
	}

	public void setMessageControl(MessageControl messageControl) {
		this.messageControl = messageControl;
	}
}
