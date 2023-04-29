 
	// Cuando pulsamos en borrar todos los productos de la lista
	$(document).on('click', '.borrar-productos', function() {
		borrarAtributosSession();
	});

	// Cuando solo pulsamos en borrar (-) un solo producto en específico
	$(document).on('click', '.borrar-icono', function() {
		// Busca el elemento padre correspondiente al producto que se va a eliminar
		var productoAEliminar = $(this).parent();
		var valorObjeto = $(this).parent().text();
		// Elimina el elemento de la lista de productos
		productoAEliminar.remove();
		
		$.get('/Memphis_Cafe/validarObjetosActuales/' + valorObjeto , function() {			
			var cantidadElementos = $('#lista-bebidas li').length;

			if (cantidadElementos === 0) {
				borrarAtributosSession();
			}
		});
	
	});
	
	// Funcioón para borrar los productos
	function borrarAtributosSession() {
		$.get('/Memphis_Cafe/limpiarObjetosSesion', function() {
			$('#lista-productos').empty();
			$('.texto-pagar').empty();
			$('.borrar-productos').hide();
		});
	}
	
	// Cuando solo pulsamos en sumar (+) un solo producto en específico
	$(document).on('click', '.aniadir-icono', function() {
		// Busca el elemento padre correspondiente al producto que se va a eliminar
		var valorObjeto = $(this).parent().text();
		
		// Obtengo el tipo de cafe.
		var nombreCafe = $('#suma-bebidas').text();
		
		$.get('/Memphis_Cafe/sumarObjetosActuales/' + valorObjeto + '/' + nombreCafe, function() {		
			var objeto1 = valorObjeto.replace(/\t/g, "").replace(/\n/g, "").replace(/\s/g, "").replace(',','.');
			var objeto2 = valorObjeto.replace(/\t/g, "").replace(/\n/g, "").replace(/\s/g, "").replace(',','.');
			var valor1_entero = parseFloat(objeto1);
			var valor2_entero = parseFloat(objeto2);
			
			var suma = valor1_entero + valor2_entero;
				// $(data).find('#suma-bebidas').text();
			$('#suma-bebidas').text(suma);
		});
	});
	
	
	
	