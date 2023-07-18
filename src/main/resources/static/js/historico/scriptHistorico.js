$(document).ready(function() {
	$('#staticBackdrop').modal('hide');
	
	
	//$('#input-nombre').hide();	
	//$('#input-fecha').hide();
	
	if ($('#input-visible').text() === 'nombre') {
		$('#input-nombre').css('display','');
		$('#input-fecha').css('display','none');	 	
	} else if ($('#input-visible').text() === 'fecha') {
		$('#input-fecha').css('display','');
		$('#input-nombre').css('display','none');
	}
	

	// Muestra el input según lo que sea seleccionado
	$('#busqueda-seleccion').change(function(){
		var seleccionCampo = $(this).val();
		if(seleccionCampo === '1'){
			$('#input-nombre').css('display','');
			$('#input-fecha').css('display','none');
			$('#floatingInput').val('');
		} else if(seleccionCampo === '2') {
			$('#input-fecha').css('display','');
			$('#input-nombre').css('display','none');
			$('#filtrado-fecha').val('');
		}
	});
	
	// Validar fecha de busqueda - Ejemplo realizado para posteriores validaciones.
	/*
	$('#filtrado-fecha').change(function(event) {
    	var fecha = $(this).val(); // Obtener el valor del campo de fecha

	    // Validar la fecha
	    if (fecha) {
	      var fechaActual = new Date(); // Obtener la fecha actual
	      var fechaSeleccionada = new Date(fecha); // Convertir la fecha seleccionada en un objeto Date
	
	      // Comparar la fecha seleccionada con la fecha actual
	      if (fechaSeleccionada < fechaActual) {
			event.preventDefault();
			$('#filtrado-fecha').val('');
	        alert('La fecha no puede ser menor que la actual');
	      }
	    }
  	});
	*/
	
});

	// ##### Función que captura el click cuando se pulsa una fila en la tabla de histórico.
	$('.objetos-historicos').on('click', function(event) {
	
	  event.stopPropagation(); // Evitar la propagación del evento hacia arriba
      // Acciones a realizar cuando se hace clic en una fila
      console.log('Se hizo clic en una fila');
      // Obtener datos de la fila
      var rowData = $(this).text();
      console.log('Datos de la fila:', rowData);
      
      // Eliminar los espacios en blanco al principio y al final de la cadena
	  cadena = rowData.trim();
		
	  // Dividir la cadena en elementos individuales utilizando el carácter de salto de línea como delimitador
	  var elementos = cadena.split('\n');
		
	  // Eliminar los espacios en blanco al principio y al final de cada elemento
	  elementos = elementos.map(function(elementoHistorico) {
		return elementoHistorico.trim();
	  });
	  
      // Pasamos los datos a la función.
      mostrarModalHistorico(elementos);
    });

    
    function mostrarModalHistorico(elementos) {
		// Array(7) [ "0", "Té", "", "07-07-2023", "20:21:12", "Jose Plasencia", "1,30€"]
		$('#staticBackdrop').modal('show');
		// Le pasamos los datos a el modal de forma directa
		$('#detalle-mesa').text(elementos[0]);
		$('#detalle-bebidas').text(elementos[1]);
		$('#detalle-comidas').text(elementos[2]);
		$('#detalle-dia').text(elementos[3]);
		$('#detalle-hora').text(elementos[4]);
		$('#detalle-mesero').text(elementos[5]);
		$('#detalle-cuenta').text(elementos[6]);
	};
	
	// Evitar que se ejecute el script al hacer clic dentro del modal
	$('.modal-dialog').on('click', function(event) {
  		event.stopPropagation();
	});
	
	
	
	
	
	
	
	
	
	
	
	
	