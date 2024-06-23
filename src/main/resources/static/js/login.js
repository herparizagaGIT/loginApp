$(document).ready(function() {

	$('#acceder').click(function(event) {

		var form = document.getElementById('myLoginForm');
		if (!form.checkValidity()) {

			event.preventDefault();
			event.stopPropagation();

			form.classList.add('was-validated');

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
					var myModal = new bootstrap.Modal(document.getElementById('loginStaticBackdrop'));
					if (data) {
						cargarPerfil();
					}
					else {
						myModal.show();
					}

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

			$('#homeContainer').html(data);
			$('#homeContainer').css('width', '90%');
			$('#homeContainer').removeClass('d-flex justify-content-center align-items-center');
		}
	});
}