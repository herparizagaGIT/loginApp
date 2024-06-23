document.addEventListener('DOMContentLoaded', function() {
	var form = document.getElementById('myRegisterForm');

	form.addEventListener('submit', function(event) {
		if (!form.checkValidity()) {
			event.preventDefault();
			event.stopPropagation();

			form.classList.add('was-validated');

		}
	}, false);

})

$(document).ready(function() {

	if ($('#mensaje').val() != "") {
		if ($('#mensaje').val().includes('éxito')) {

			$('#reintentarRegistro').css('display', 'none');
			$('#loguearme').css('display', 'block');
		}
		else {

			$('#reintentarRegistro').css('display', 'block');
			$('#loguearme').css('display', 'none');
		}
		var myModal = new bootstrap.Modal(document.getElementById('registerStaticBackdrop'));
		myModal.show();
	};

})