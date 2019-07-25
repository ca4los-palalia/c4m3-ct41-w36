package com.came.control.core.async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.came.control.beans.constantes.ConstAtributos;
import com.came.control.model.domain.Organizacion;
import com.came.control.model.domain.Rol;
import com.came.control.model.services.RolService;

@Service
public class AsyncServices {

	@Autowired
	protected RolService rolService;
	
	@Async
	public Future<String> process() throws InterruptedException {
		Thread.sleep(3000);
		String processInfo = String.format("Processing is Done with Thread id= %d", Thread.currentThread().getId());
		return new AsyncResult<>(processInfo);
	}
	
//	@Async
//	public Future<CfdiResponse> inicializarObjeto() throws InterruptedException {
//		CfdiResponse responseCfdiRelacionados = new CfdiResponse();
//		return new AsyncResult<>(responseCfdiRelacionados);
//	}
//	
//	
//	@Async
//	public Future<CfdiResponse> procesarRelacionados(CancelacionMultipleRequest request, Usuario usuario, RespuestaMulti respuesta, CfgMedusa cfgMedusaApp, ParametrosCancelaMultiple item, CfdiRelacionadoService cfdiRelacionadoService) throws InterruptedException {
//		CtrlConsultaRelacionado serviceRelacionado = new CtrlConsultaRelacionado(cfdiRelacionadoService);
//		ParametrosObtenerCfdisRelacionados requestRelacionados = new ParametrosObtenerCfdisRelacionados();
//		requestRelacionados.setNoCertificado(respuesta.getXmlGenerico().getNoCertificado());
//		requestRelacionados.setRfcReceptor(respuesta.getXmlGenerico().getRfcReceptor());
//		requestRelacionados.setUuid(respuesta.getXmlGenerico().getUuid());
//		requestRelacionados.setXmlB64(item.getXmlB64());
//		
//		CfdiResponse responseCfdiRelacionados = serviceRelacionado
//				.getProcesarRespuesta(respuesta.getXmlGenerico(), usuario, ((request.isPdfGenerar() != null) ? request.isPdfGenerar() : false), request.getPdfNoPlantilla());
//		
//		return new AsyncResult<>(responseCfdiRelacionados);
//	}
	
	
	@Async
	public Future<List<Rol>> getRolesAltaUsuarios(Organizacion org) throws InterruptedException {
		List<Rol> rolesLocal = rolService.getByOrganizacion(org);
		List<Rol> roles = null;
		if(rolesLocal != null) {
			roles = new ArrayList<Rol>();
			for (Rol rol : rolesLocal) {
				if (!rol.getNombre().contentEquals(ConstAtributos.ROL_ADMIN)
						&& !rol.getNombre().contentEquals(ConstAtributos.ROL_SYSADMIN))
					roles.add(rol);
			}
		}
		return new AsyncResult<>(roles);
	}
}
