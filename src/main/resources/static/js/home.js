document.addEventListener('DOMContentLoaded', function() {
	var form = document.getElementById('myRegisterForm');

	form.addEventListener('submit', function(event) {
		if (!form.checkValidity()) {
			event.preventDefault();
			event.stopPropagation();

			form.classList.add('was-validated');
			//document.getElementById('containerRegistro').style.marginTop = "5%";

		}
	}, false);

})
document.addEventListener('DOMContentLoaded', function() {
	var formNewPass = document.getElementById('myPassForm');

	formNewPass.addEventListener('submit', function(event) {

		var x = $("#newpass");
		var y = $("#confirmpass");
		if (!formNewPass.checkValidity() || x.val() !== y.val()) {

			event.preventDefault();
			event.stopPropagation();

			formNewPass.classList.add('was-validated');
			document.getElementById('containerClave').style.marginTop = "5%";

		}
	}, false);
});

function showPassword() {
	var x = document.getElementById("userpass");
	if (x.type === "password") {
		x.type = "text";
	} else {
		x.type = "password";
	}
}

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

	if ($('#mensaje').val() != "" && $('#mensaje').val() != undefined) {
		if ($('#mensaje').val().includes('éxito')) {

			$('#reintentarRegistro').css('display', 'none');
			$('#loguearme').css('display', 'block');
		}
		else {

			$('#reintentarRegistro').css('display', 'block');
			$('#loguearme').css('display', 'none');
		}
		var myModal = new bootstrap.Modal(document.getElementById('staticBackdropRegistro'));
		myModal.show();
	};

	if ($('#mensajeNuevaClave').val() != "" && $('#mensajeNuevaClave').val() != undefined) {
		if ($('#mensajeNuevaClave').val().includes('éxito')) {
			$('#reintentarRecuperarClave').css('display', 'none');
			$('#loguearme').css('display', 'block');
		}
		else {

			$('#reintentarRecuperarClave').css('display', 'block');
			$('#loguearme').css('display', 'none');
		}

		var myModal = new bootstrap.Modal(document.getElementById('staticBackdropNewPassword'));
		myModal.show();
	};
	$('#acceder').click(function(event) {

		var form = document.getElementById('myForm');
		if (!form.checkValidity()) {

			event.preventDefault();
			event.stopPropagation();

			form.classList.add('was-validated');
			//document.getElementById('container').style.marginTop = "5%";

		}
		else {
			event.preventDefault();

			var user = {
				email: $('#usermail').val(),
				password: $('#userpass').val()
			}

			$.ajax({
				type: 'POST',
				contentType: 'application/json',
				url: '/app/login',
				data: JSON.stringify(user),
				success: function(data) {
					var myModal = new bootstrap.Modal(document.getElementById('staticBackdrop'));
					if (data) {
						//alert("Felicidades, puede ver el contenido")
						cargarPerfil();
					}
					else {
						myModal.show();
					}

				},
				complete: function() {
					event.preventDefault();
				}
			});
		}
	})


})

function cargarPerfil() {
	$.ajax({
		type: 'GET',
		contentType: 'application/json',
		url: '/app/profile',
		data: { "email": $('#usermail').val() },
		success: function(data) {

			$('#container').html(data);
			$('#container').css('width', '90%');
			$('#container').removeClass('d-flex justify-content-center align-items-center');
		}
	});
}

//function validarCamposRegistro() {
//
//	var form = document.getElementById('myRegisterForm');
//	if (!form.checkValidity()) {
//
//		event.preventDefault();
//		event.stopPropagation();
//
//		form.classList.add('was-validated');
//		document.getElementById('containerRegistro').style.marginTop = "5%";
//
//	}
//}


