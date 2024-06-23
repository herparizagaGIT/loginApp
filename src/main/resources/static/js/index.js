function showPassword() {
	var x = document.getElementById("userpass");
	if (x.type === "password") {
		x.type = "text";
	} else {
		x.type = "password";
	}
}




