document.addEventListener('DOMContentLoaded', function() {
	var formNewPass = document.getElementById('myRecoverPassForm');

	formNewPass.addEventListener('submit', function(event) {

		var x = $("#newpass");
		var y = $("#confirmpass");
		if (!formNewPass.checkValidity() || x.val() !== y.val()) {

			event.preventDefault();
			event.stopPropagation();

			formNewPass.classList.add('was-validated');

		}
	}, false);
});

$(document).ready(function() {

	$('#newpass, #confirmpass').on('input', function() {
		if ($('#newpass').val() == $('#confirmpass').val()) {

			$('#confirmpass').removeClass('is-invalid').addClass('is-valid');
			$('.invalid-feedback .confirmpass ').css('display', 'none');
		} else {

			$('#confirmpass').removeClass('is-valid').addClass('is-invalid');
			$('.invalid-feedback .confirmpass ').css('display', 'block');
		}
	});

	if ($('#mensajeNuevaClave').val() != "") {
		if ($('#mensajeNuevaClave').val().includes('éxito')) {
			$('#reintentarRecuperarClave').css('display', 'none');
			$('#loguearme').css('display', 'block');
		}
		else {

			$('#reintentarRecuperarClave').css('display', 'block');
			$('#loguearme').css('display', 'none');
		}

		var myModal = new bootstrap.Modal(document.getElementById('recoverPassStaticBackdrop'));
		myModal.show();
	};

})

function showPasswords() {
	var x = document.getElementById("newpass");
	var y = document.getElementById("confirmpass");
	if (x.type === "password" && y.type === "password") {
		x.type = "text";
		y.type = "text";
	} else {
		x.type = "password";
		y.type = "password";
	}
}