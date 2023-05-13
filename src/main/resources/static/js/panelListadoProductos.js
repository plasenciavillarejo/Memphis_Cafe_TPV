 
 
 	// Cuando se inicia la aplicacíón se verifica que haya alguna cuenta existente
	$(document).ready(function() {	
		
		actualizarInputTotal();
		
		// Cuando se añade cualquier comida desde el button se cargará automaticamente el precio
		//actualizarComidaInputTotal();
		
		// Se llama a la fucion para verificar que se ha cambiado o no el switch
		cambioSwitch();

	});
 
	// Cuando pulsamos en borrar todos los productos de la lista
	$(document).on('click', '.borrar-productos', function() {
		borrarTodosAtributosSession();
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
		var cantidadElementos = $('#lista-bebidas li').length;
		// Si ya no hay más datos en la lista, se borra todo y se oculta	
		if (cantidadElementos === 0) {
			borrarAtributosSession();
			}	
		});
	});
	
	// Función para borrar los productos
	function borrarAtributosSession() {
		
		var cantidadElementosBebida = $('#lista-bebidas li').length;
		var canditdadElementosComida = $('#lista-comidas li').length;
		
		$.get('/Memphis_Cafe/limpiarObjetosSesion/' + cantidadElementosBebida + '/' + canditdadElementosComida, function() {
			if (cantidadElementosBebida === 0 && canditdadElementosComida === 0) {
				$('#lista-productos').empty();
				$('#lista-comida').empty();
				$('.texto-pagar').empty();
				$('.borrar-productos').hide();
			} else if (cantidadElementosBebida === 0) {
				$('#lista-productos').empty();
			} else if (canditdadElementosComida === 0) {
				$('#lista-comida').empty();
			}

			
		});
	}
	
	// Función para borrar la comanda por completo.
	
	function borrarTodosAtributosSession() {
		$.get('/Memphis_Cafe/limpiarComandaEntera', function() {
			$('#lista-productos').empty();
			$('#lista-comida').empty();
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
			// Recorro la lista para obtener el elemento seleccionado
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
		var nuevoPrecio = 0;
		var nuevoPrecioComida = 0;
		$('#lista-bebidas').each(function() {
			$('.borrar-bebida-especifica').each(function() {
				var precioBebida = Number($(this).text().trim().replace(' €', '').replace(',', '.'));
				nuevoPrecio += precioBebida;
			});
		});

		$('#lista-comidas').each(function() {
			$('.borrar-comida-especifica').each(function() {
				var precioComida = Number($(this).text().trim().replace(' €', '').replace(',', '.'));
				nuevoPrecioComida += precioComida;
			});
		});		
		
		precioPagarFinal = nuevoPrecio + nuevoPrecioComida;
		if (isNaN(nuevoPrecioComida)) {
				$('#suma-cuenta').val('0.00');
			} else {
				$('#suma-cuenta').val(precioPagarFinal.toFixed(2));
			}
		
	}

	
	// ###### INIC - LÓGICA PARA LA PARTE DE EL DESAYUNO ######
	
	
	// Verificar si el switch está activado o desactivado para enviar média o entera.
	// escuchar el evento de cambio en el checkbox utilizando el evento change()
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
	
	
	/*
	// Función encargada de aniadir el desayuno. -> Por ahora está comentada ya que inicilamente no crea el div
	$(document).on('click', '.boton-desayuno', function(event) {
		event.preventDefault(); // Evita que se recargue la página
		
		var valorObjeto = $(this).parent().text();
		var nuevaCadena = valorObjeto.replace(/[\n\t]/g, '');	
		var nombreDesayuno = nuevaCadena.match(/^\s*([^\d]+)/)[1].trim(); // Extrae el nombre
		
		//var valorBoton = $("#tamanioButtonPrincipal").text();
		var switchValueTru = $('#flexSwitchCheckDefault').prop('checked');
		
		$.get('/Memphis_Cafe/desayuno/'+ nombreDesayuno + '/' + switchValueTru, function(data) {
			console.log("Precio: ", data.precio, "Desayuno: ", data.nombreComida);
			
			var productoActualizado = false;
			var contar = 0;
			$('#lista-comidas li').each(function() {
				var nombreDesayunoLista = $(this).find('#nombre-comida-seleccionado').text();
				var precioDesayunoLista = $(this).find('.borrar-comida-especifica').text().trim();
				contar ++;
				if(nombreDesayuno===nombreDesayunoLista){					
					// Actualizao el valor
					$(this).find('.borrar-comida-especifica').text(data);
					productoActualizado = true;
				}
			});
			
			if(!productoActualizado) {
				
				//$('#nombre-comida-seleccionado').show()
				// Mostramos el desayuno en pantalla
				//$('#lista-comida').show();
				
				// Verifica si la lista existe
				if ($("#lista-comidas").length) {
					// Agrega elementos a la lista
				// Obtener la lista de comidas
				$('div.card').show();
				var listaComidas = $("#lista-comidas");

				// Crear un nuevo elemento li con el nombre y precio de la comida
				var nuevaComida = 
					'<div class="card-header">'+
					'<li id="borrar-comidas" class="list-group-item contar-bebida">' +
					'<ion-icon class="borrar-icono" name="remove-circle"></ion-icon>' +
					'<span id="nombre-comida-seleccionado">' + ' ' + data.nombreComida + ' ' +' </span>' +
					'<span id="suma-comidas">' + ' ' + data.precio + ' €' +'</span>' +
					'<ion-icon class="aniadir-icono" name="add-circle"></ion-icon>' +
					'</li>' +
					'</div>';
				
				// Agregar el nuevo elemento al final de la lista
				listaComidas.append(nuevaComida);
				
				//$("#nombre-comida-seleccionado").text(data.nombreComida);
				//$("#suma-comidas").text(data.precio);
				
				}
			}
		});
		
	});
	
	
	*/
	
	
	// ###### FIN - LÓGICA PARA LA PARTE DE EL DESAYUNO ######
	
	