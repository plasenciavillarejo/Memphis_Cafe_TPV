 
 
 	// Cuando se inicia la aplicacíón se verifica que haya alguna cuenta existente
	$(document).ready(function() {	
		actualizarInputTotal();
		
		// Se llama a la fucion para verificar que se ha cambiado o no el switch
		cambioSwitch();

	});
 
	// Cuando pulsamos en borrar todos los productos de la lista
	$(document).on('click', '.borrar-productos', function() {
		borrarAtributosSession();
	});

	// Cuando solo pulsamos en borrar (-) un solo producto en específico
	$(document).on('click', '.borrar-icono', function(event) {
		// Busca el elemento padre correspondiente al producto que se va a eliminar
		var valorObjeto = $(this).parent().text();
		var nuevaCadena = valorObjeto.replace(/[\n\t]/g, '');	
		/* La expresión regular /^\s*([^\d]+)/ busca al inicio de la cadena (^) cualquier cantidad de espacios
		 (\s*) seguida de cualquier cantidad de caracteres que no sean dígitos ([^\d]+) y captura ese 
		 grupo de caracteres ((...)) 
		 El método match() devuelve un array con el texto coincidente y los grupos capturados, por lo que 
		 match(/^\s*([^\d]+)/)[1] devuelve el nombre sin los espacios al principio y al final
		 */
		var nombreCafe = nuevaCadena.match(/^\s*([^\d]+)/)[1].trim(); // Extrae el nombre
		/*
		La expresión regular /\d+(?:[.,]\d+)?/ busca cualquier secuencia de dígitos (\d+) seguida opcionalmente 
		por un punto o una coma y más dígitos ((?:[.,]\d+)?), sin capturar el grupo. El método match() devuelve 
		un array con todas las coincidencias, por lo que match(/\d+(?:[.,]\d+)?/)[0] devuelve el primer número 
		que aparece en la cadena. 
		*/
		var precioCafe = nuevaCadena.match(/\d+(?:[.,]\d+)?/)[0].replace(",", "."); // Extrae el precio
		
		event.preventDefault(); // Evita que se recargue la página
		$.get('/Memphis_Cafe/validarObjetosActuales/' + nombreCafe + '/' + precioCafe , function(resultadoString) {		
			var cantidadElementos = $('#lista-bebidas li').length;
			// Recorro la lista para obtener el elemnto seleccionado
			$('#lista-bebidas li').each(function() {
				var nombreCafeLista = $(this).find('#nombre-cafe-seleccionado').text();
				var precioCafeLista = $(this).find('.borrar-bebida-especifica').text().trim();
				console.log(nombreCafeLista,precioCafeLista);
				if(nombreCafe===nombreCafeLista){					
					if(resultadoString=== '0,0' || resultadoString=== '0'){
						// Elimino este objeto de la lista
						$(this).closest('ul').remove();
						actualizarInputTotal();
					} else{
						// Actualizao el valor
						//$(this).find('.borrar-bebida-especifica').text(resultadoString).toFixed(2)
						$(this).find('.borrar-bebida-especifica').text(resultadoString)
						actualizarInputTotal();
					}
				}		
		});
		// Si ya no hay más datos en la lista, se borra todo y se oculta	
		if (cantidadElementos === 1) {
			borrarAtributosSession();
			}	
		});
	});


	// Función para borrar los productos
	function borrarAtributosSession() {
		$.get('/Memphis_Cafe/limpiarObjetosSesion', function() {
			$('#lista-productos').empty();
			$('.texto-pagar').empty();
			$('.borrar-productos').hide();
		});
	}
	
	// Cuando solo pulsamos en sumar (+) un solo producto en específico
	$(document).on('click', '.aniadir-icono', function(event) {
		event.preventDefault(); // Evita que se recargue la página
		var valorObjeto = $(this).parent().text();
		var nuevaCadena = valorObjeto.replace(/[\n\t]/g, '');	
		var nombreCafe = nuevaCadena.match(/^\s*([^\d]+)/)[1].trim(); // Extrae el nombre
		var precioCafe = nuevaCadena.match(/\d+(?:[.,]\d+)?/)[0].replace(",", "."); // Extrae el precio
		
		$.get('/Memphis_Cafe/sumarObjetosActuales/' + nombreCafe + '/' + precioCafe, function(resultadoString) {		
			// Recorro la lista para obtener el elemnto seleccionado
			$('#lista-bebidas li').each(function() {
				var nombreCafeLista = $(this).find('#nombre-cafe-seleccionado').text();
				var precioCafeLista = $(this).find('.borrar-bebida-especifica').text().trim();
				console.log(nombreCafeLista,precioCafeLista);
				
				if(nombreCafe===nombreCafeLista){					
					// Actualizao el valor
					//$(this).find('.borrar-bebida-especifica').text(resultadoString).toFixed(2)
					$(this).find('.borrar-bebida-especifica').text(resultadoString)
					actualizarInputTotal();
				}
			});
		});
	});
	
	
	function actualizarInputTotal() {
		$('#lista-bebidas').each(function() {
			var nuevoPrecio = 0;
			$('.borrar-bebida-especifica').each(function() {
				var precioBebida = Number($(this).text().trim().replace(' €', '').replace(',', '.'));
				nuevoPrecio += precioBebida;
			});
			if (isNaN(nuevoPrecio)) {
				$('#suma-cuenta').val('0.00');
			} else {
				$('#suma-cuenta').val(nuevoPrecio.toFixed(2));
			}
		});
	}

	
	// ###### INIC - LÓGICA PARA LA PARTE DE EL DESAYUNO ######
	
	
	// Verificar si el switch está activado o desactivado para enviar média o entera.
	//  escuchar el evento de cambio en el checkbox utilizando el evento change()
	
	function cambioSwitch() {
	$("#flexSwitchCheckDefault").change(function() {
		if ($(this).is(":checked")) {
			$('#flexSwitchCheckDefault').css({
				"background-color": "#43b39b",
				"border": "none"
			});
			$('#switch-desayuno').text('Entera');
		} else {
			$('#flexSwitchCheckDefault').css({
				"background-color": "#FFF",
				"border": "1px solid #43b39b"
				});
			$('#switch-desayuno').text('Media');
		}
	});
	}
	
	
	
	
	
	
	// ###### FIN - LÓGICA PARA LA PARTE DE EL DESAYUNO ######
	
	