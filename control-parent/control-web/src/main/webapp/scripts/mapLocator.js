function buscadorDireccion() {
	var inp = zk.Widget.$('$finderAddress').getValue();
	if (inp == null || inp === "") {
		alert("Ingrese una direccion valida");
		return;
	}

	var xmlhttp = new XMLHttpRequest();

	var url = "https://nominatim.openstreetmap.org/search?format=json&limit=3&q="
			+ inp;
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var myArr = JSON.parse(this.responseText);
			zk.Widget.$('$mapTransferTxt').setValue(JSON.stringify(getArrayItems(myArr)));
			zk.Widget.$('$mapTransferTxt').fireOnChange();
			zk.Widget.$('$listenerSelectCity').fire('onShowNotification', 'PARAMETERS', { toServer : true });
		}
	};
	xmlhttp.open("GET", url, true);
	xmlhttp.send();
}

function getArrayItems(jsonResponse) {
	var i;
	if (jsonResponse.length > 0) {
		var array = [];
		for (i = 0; i < jsonResponse.length; i++) {
			var geo = new Object();
			geo.latitud = jsonResponse[i].lat;
			geo.longitud = jsonResponse[i].lon;
			geo.descripcion = jsonResponse[i].display_name;
			array.push(geo);
		}
		return array;
	} else
		return null;
}

function updateBindGeoSelected(geoSelected) {
	zk.Widget.$('$mapTransferTxt').setValue(JSON.stringify(geoSelected));
	zk.Widget.$('$mapTransferTxt').fireOnChange();
	zk.Widget.$('$listenerUpdateDataGeoSelect').fire('onShowNotification', 'PARAMETERS', { toServer : true });
}


function reload() {
	navigator.geolocation.getCurrentPosition(success2, error2, optionsCurrentPosition2);
}

function success2(pos) {
	var crd = pos.coords;
	relocalizarMapa(crd);
}

function error2(err) {
	console.warn('ERROR(' + err.code + '): ' + err.message);
};

var optionsCurrentPosition2 = {
	enableHighAccuracy: true,
	timeout: 5000,
	maximumAge: 0
};
