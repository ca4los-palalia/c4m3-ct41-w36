// Variables Globales
tiempo = {};
intervalo = window.setInterval(mostrar_hora, 1); // Frecuencia de actualización
i = 0; // Esta variable me ayudará a definir los estados de intervalo

function mostrar_hora() {
	var clock = new Date(); // Obtener la fecha y almacenar en clock
	// Inserta la hora almacenada en clock en el span con id hora
	tiempo.horas = document.getElementById('hora');
	tiempo.horas.innerHTML = clock.getHours();
	
//	if (clock.getHours() >= 13) {
//		tiempo.horas.innerHTML = clock.getHours() - 12;
//		tiempo.horas.innerHTML = "0" + tiempo.horas.innerHTML;
//	}
//	if (clock.getHours() < 10) {
//		tiempo.horas.innerHTML = "0" + clock.getHours();
//	};
	// Inserta los minutos almacenados en clock en el span con id minuto
	if (clock.getMinutes() < 10) {
		tiempo.minutos.innerHTML = "0" + clock.getMinutes();
	} else {
		tiempo.minutos = document.getElementById('minuto')
	}
	;
	if (clock.getMinutes() < 10) {
		tiempo.minutos.innerHTML = "0" + clock.getMinutes();
	} else {
		tiempo.minutos.innerHTML = clock.getMinutes();
	}
	;
	// Inserta los segundos almacenados en clock en el span con id segundo
	tiempo.segundos = document.getElementById('segundo')
	if (clock.getSeconds() < 10) {
		tiempo.segundos.innerHTML = "0" + clock.getSeconds();
	} else {
		tiempo.segundos.innerHTML = clock.getSeconds();
	}
	;

	// Inserta los milisegundos almacenados en clock en el span con id
	// milisegundo
	tiempo.milisegundos = document.getElementById('milisegundo')
	if (clock.getMilliseconds() < 99) {
		tiempo.milisegundos.innerHTML = "0" + clock.getSeconds();
	} else if (clock.getMilliseconds() < 10) {
		tiempo.milisegundos.innerHTML = "00" + clock.getSeconds();
	} else {
		tiempo.milisegundos.innerHTML = clock.getMilliseconds();
	}
	;

}



