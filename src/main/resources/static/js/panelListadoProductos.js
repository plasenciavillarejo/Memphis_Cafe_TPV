 
 
 	// Cuando se inicia la aplicacíón se verifica que haya alguna cuenta existente
	$(document).ready(function() {	
		
		actualizarInputTotal();
		
		// Cuando se añade cualquier comida desde el button se cargará automaticamente el precio
		//actualizarComidaInputTotal();
		
		// Se llama a la fucion para verificar que se ha cambiado o no el switch
		cambioSwitch();

		// Validamos los elementos que contiene la lista
		$("#lista-bebidas").trigger("DOMSubtreeModified");
		$("#lista-comidas").trigger("DOMSubtreeModified");		
	});
 
 	
 
	// Cuando pulsamos en borrar todos los productos de la lista
	$(document).on('click', '.borrar-productos', function() {
		// Actualizamos el color de el botón de sweet alert.
		swal({
			title: "Estás seguro de que desea borrar la comanda?",
			text: "Al borrarla, desaparecerá todos los productos",
			icon: "warning",
			buttons: true,
			dangerMode: true,
		})
			.then((willDelete) => {
				if (willDelete) {
					borrarTodosAtributosSession();
					swal("La comanda ha sido eliminada con éxito!", {
						icon: "success",
						
					});
				} /*else {
					swal("Your imaginary file is safe!");
				} */
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
			} else if (canditdadElementosComida === 0 || canditdadElementosComida.indexOf('-')) {
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
	
	
	// ### Ini - Funciones encargadas de escuchar los productos que se añade para mantener el "Card" o pasar a una lista ### //
	// ##################################################################################################################### //
	// Se dispara cuando se agrega un nuevo nodo a un elemento bebida. 
	$("#lista-bebidas").on("DOMSubtreeModified", function() {
	// Si el panel contiene más de 7 bebidas se cambia a una lista.
		var cantidadBebidas = $('#lista-bebidas li').length;
		
		if (cantidadBebidas >= 1 && cantidadBebidas <=6) {
			$("#convertir-lista ul").each(function() {
				$(this).removeClass("list-group-flush");
				$('#convertir-lista').removeClass("overflow-auto").removeAttr("style");			
			});
		} else {
			$("#convertir-lista ul").each(function() {
				$(this).removeClass("list-group-flush");
				$('#convertir-lista').addClass("overflow-auto").css("height","250px");
			});
		}
	});
	
	
	// Se dispara cuando se agrega un nuevo nodo a un elemento comida. 
	$("#lista-comidas").on("DOMSubtreeModified", function() {
	// Si el panel contiene más de 7 comidas se cambia a una lista.
		var cantidadComidas = $('#lista-comidas li').length;
		
		if (cantidadComidas >= 1 && cantidadComidas <=6) {
			$("#convertir-lista-comida ul").each(function() {
				$(this).removeClass("list-group-flush");
				$('#convertir-lista').removeClass("overflow-auto").removeAttr("style");			
			});
		} else {
			$("#convertir-lista-comida ul").each(function() {
				$(this).removeClass("list-group-flush");
				$('#convertir-lista-comida').addClass("overflow-auto").css("height","250px");
			});
		}
	});
	
	function borrarEstilosBebida() {
		// Si el panel contiene más de 7 bebidas se cambia a una lista.
		var cantidadBebidas = $('#lista-bebidas li').length;
		
		if (cantidadBebidas >= 1 && cantidadBebidas <=6) {
			$("#convertir-lista ul").each(function() {
				$(this).removeClass("list-group-flush");
				$('#convertir-lista').removeClass("overflow-auto").removeAttr("style");			
			});
		} else {
			$("#convertir-lista ul").each(function() {
				$(this).removeClass("list-group-flush");
				$('#convertir-lista').addClass("overflow-auto").css("height","250px");
			});
		} 
	}

	function borrarEstilosComida() {
		// Si el panel contiene más de 7 comidass se cambia a una lista.
		var cantidadComidas = $('#lista-comidas li').length;
		
		if (cantidadComidas >= 1 && cantidadComidas <=6) {
			$("#convertir-lista-comida ul").each(function() {
				$(this).removeClass("list-group-flush");
				$('#convertir-lista').removeClass("overflow-auto").removeAttr("style");			
			});
		} else {
			$("#convertir-lista-comida ul").each(function() {
				$(this).removeClass("list-group-flush");
				$('#convertir-lista-comida').addClass("overflow-auto").css("height","250px");
			});
		}
	}

	// ### Fin - Funciones encargadas de escuchar los productos que se añade para mantener el "Card" o pasar a una lista ### //
	// ##################################################################################################################### //



	// Cuando solo pulsamos en sumar (+) un solo producto en específico - bebida
	$(document).on('click', '.aniadir-icono-bebida', function(event) {
		event.preventDefault(); // Evita que se recargue la página
		var valorObjeto = $(this).parent().text();
		var nuevaCadena = valorObjeto.replace(/[\n\t]/g, '')
			.replace('x','').replace(' ','')
			.replace(/[\t]/g,'')
			.replace('€','')
			.split(' - ');
			
		var nombreBebida = nuevaCadena[1].match(/^\s*([^\d]+)/)[1].trim();
		var regex = /\d+(?:,\d+)?/;
		var resultado = nuevaCadena[1].match(regex);
		var precioBebida = parseFloat(resultado[0].replace(",", "."));
		var obtenerTablaBBDD = nuevaCadena[1].split(' ');
		var tablaBBDD = obtenerTablaBBDD[obtenerTablaBBDD.length - 1]; // Obtener el último campo
		
		$.get('/Memphis_Cafe/sumarPrecioBebida/' + nombreBebida + '/' + precioBebida +  '/' + tablaBBDD, function(resultadoString) {		
			// Recorro la lista para obtener el elemento seleccionado
			$('#lista-bebidas li').each(function() {
				var nombreBebidaLista = $(this).find('#nombre-cafe-seleccionado').text();
				var precioBebidaLista = $(this).find('.borrar-bebida-especifica').text().trim();
				console.log(nombreBebidaLista,precioBebidaLista);
				
				if(nombreBebida===nombreBebidaLista){					
					// Actualizao el valor
					$(this).find('#totalBebida').text(resultadoString.aumentoTotal)
					$(this).find('.borrar-bebida-especifica').text(resultadoString.aumentoPrecio)
					actualizarInputTotal();
				}
			});
		});
	});


	// Cuando solo pulsamos en sumar (+) un solo producto en específico - comida
	$(document).on('click', '.aniadir-icono-comida', function(event) {
		event.preventDefault(); // Evita que se recargue la página
		var valorObjeto = $(this).parent().text();
		var nuevaCadena = valorObjeto.replace(/[\n\t]/g, '')
			.replace('x','').replace(' ','')
			.replace(/[\t]/g,'')
			.replace('€','')
			.split(' - ');
			
		var nombreComida = nuevaCadena[1].match(/^\s*([^\d]+)/)[1].trim();
		var regex = /\d+(?:,\d+)?/;
		var resultado = nuevaCadena[1].match(regex);
		var precioComida = parseFloat(resultado[0].replace(",", "."));
		var obtenerTablaBBDD = nuevaCadena[1].split(' ');
		var tablaBBDD = obtenerTablaBBDD[obtenerTablaBBDD.length - 1]; // Obtener el último campo
		
		// Verificamos si está o no seleccionado el switch
		var switchValue = $("#flexSwitchCheckDefault").prop("checked");
		
		// Si está fuera de la página de desayuno este objeto es indefinido por tanto debe ir a false.
		if (typeof switchValue === "undefined") {
			switchValue = false;
		}
		
		$.get('/Memphis_Cafe/sumarPrecioComida/' + nombreComida + '/' + precioComida + '/' + switchValue + '/' + tablaBBDD,
			 function(resultadoString) {		
			// Recorro la lista para obtener el elemento seleccionado
			$('#lista-comidas li').each(function() {
				var nombreComidaLista = $(this).find('#nombre-comida-seleccionado').text();
				var precioComidaLista = $(this).find('.borrar-comida-especifica').text().trim();
				console.log(nombreComidaLista,precioComidaLista);
				
				if(nombreComida===nombreComidaLista){					
					// Actualizao el valor
					$(this).find('.borrar-comida-especifica').text(resultadoString)
					actualizarInputTotal();
				}
			});
		});
	});


	// Cuando solo pulsamos en borrar (-) un solo producto en específico - bebida
	$(document).on('click', '.borrar-icono-bebida', function(event) {
		// Busca el elemento padre correspondiente al producto que se va a eliminar
		var valorObjeto = $(this).parent().text();
		var nuevaCadena = valorObjeto.replace(/[\n\t]/g, '')
			.replace('x','').replace(' ','')
			.replace(/[\t]/g,'')
			.replace('€','')
			.split(' - ');
			
		var nombreBebida = nuevaCadena[1].match(/^\s*([^\d]+)/)[1].trim();
		var regex = /\d+(?:,\d+)?/;
		var resultado = nuevaCadena[1].match(regex);
		var precioBebida = parseFloat(resultado[0].replace(",", "."));
		var obtenerTablaBBDD = nuevaCadena[1].split(' ');
		var tablaBBDD = obtenerTablaBBDD[obtenerTablaBBDD.length - 1]; // Obtener el último campo
		
		event.preventDefault(); // Evita que se recargue la página
		$.get('/Memphis_Cafe/restarPrecioBebida/' + nombreBebida + '/' + precioBebida +  '/' + tablaBBDD, function(resultadoString) {		
			// Recorro la lista para obtener el elemnto seleccionado
			$('#lista-bebidas li').each(function() {
				var nombreBebidaLista = $(this).find('#nombre-cafe-seleccionado').text();
				var precioBebidaLista = $(this).find('.borrar-bebida-especifica').text().trim();
				console.log(nombreBebidaLista,precioBebidaLista);
				if(nombreBebida===nombreBebidaLista){					
					if(resultadoString.aumentoPrecio == '0,0' || resultadoString.aumentoPrecio == '0'){
						// Elimino este objeto de la lista
						$(this).closest('ul').remove();
						actualizarInputTotal();
					} else{
						// Actualizao el valor
						$(this).find('#totalBebida').text(resultadoString.aumentoTotal)
						$(this).find('.borrar-bebida-especifica').text(resultadoString.aumentoPrecio)
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
		
		// Verificamos la cantidad de elementos que hay en la lista para matener algunos estilos
		borrarEstilosBebida();
		
	});
	
	
	// Cuando solo pulsamos en borrar (-) un solo producto en específico - comida
	$(document).on('click', '.borrar-icono-comida', function(event) {
		// Busca el elemento padre correspondiente al producto que se va a eliminar
		var valorObjeto = $(this).parent().text();
		var nuevaCadena = valorObjeto.replace(/[\n\t]/g, '')
			.replace('x','').replace(' ','')
			.replace(/[\t]/g,'')
			.replace('€','')
			.split(' - ');
			
		var nombreComida = nuevaCadena[1].match(/^\s*([^\d]+)/)[1].trim();
		var regex = /\d+(?:,\d+)?/;
		var resultado = nuevaCadena[1].match(regex);
		var precioComida = parseFloat(resultado[0].replace(",", "."));
		var obtenerTablaBBDD = nuevaCadena[1].split(' ');
		var tablaBBDD = obtenerTablaBBDD[obtenerTablaBBDD.length - 1]; // Obtener el último campo
		
		// Verificamos si está o no seleccionado el switch
		var switchValue = $("#flexSwitchCheckDefault").prop("checked");
		
		// Si está fuera de la página de desayuno este objeto es indefinido por tanto debe ir a false.
		if (typeof switchValue === "undefined") {
			switchValue = false;
		}
		
		event.preventDefault(); // Evita que se recargue la página
		$.get('/Memphis_Cafe/restarPrecioComida/' + nombreComida + '/' + precioComida + '/' + switchValue + "/" + tablaBBDD,
			 function(resultadoString) {		
			// Recorro la lista para obtener el elemnto seleccionado
			$('#lista-comidas li').each(function() {
				var nombreComidaLista = $(this).find('#nombre-comida-seleccionado').text();
				var precioComidaLista = $(this).find('.borrar-comida-especifica').text().trim();
				console.log(nombreComidaLista,precioComidaLista);
				if(nombreComida===nombreComidaLista){					
					if(resultadoString=== '0,0' || resultadoString=== '0'){
						// Elimino este objeto de la lista
						$(this).closest('ul').remove();
						actualizarInputTotal();
					} else{
						// Actualizao el valor
						//$(this).find('.borrar-bebida-especifica').text(resultadoString).toFixed(2)
						$(this).find('.borrar-comida-especifica').text(resultadoString)
						actualizarInputTotal();
					}
				}		
		});
		var cantidadElementos = $('#lista-comidas li').length;
		// Si ya no hay más datos en la lista, se borra todo y se oculta	
		if (cantidadElementos === 0) {
			borrarAtributosSession();
			}	
		});
		
		// Verificamos la cantidad de elementos que hay en la lista para matener algunos estilos
		borrarEstilosComida();
		
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
		
		// Validamos los elementos que contiene la lista
		$("#lista-bebidas").trigger("DOMSubtreeModified");
		
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
	
	