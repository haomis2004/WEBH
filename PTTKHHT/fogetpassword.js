$(document).ready(function() {
    $("#buttonFogetPassword").click(function(e) {
		var input_phone  = document.getElementById("phone");
		var input_captcha  = document.getElementById("jcaptcha");								 
        e.preventDefault();
		if(validateInput()){
			 $.ajax({
				type: "POST",
				url: dirS + "fogetpassword",
				data: {phone: $("#phone").val().trim(), captcha: $("#jcaptcha").val().trim()},
				dataType: "json",
				success: function(response) {
					if (response.result === 'SUCCESS') {
						 alert(forgotpassword.sendPasswordResult);
						window.location.href = 'login.html';
					} else {                             
							alert(response.message);					
							input_captcha.value = '';
					        input_captcha.focus();     
							reloadCaptcha();
							document.getElementById("jcaptcha").value = '';
					}
				},
				error: function(xhr, textStatus, errorThrown) {
					window.location.href = 'error.html';
				}
			});
			
			
		}else{
			return;
		};
		
  
    });
    document.getElementById("login-captcha").src = dirS + "jcaptcha.jpg";
  
});


function validateInput() {
		var input_phone  = document.getElementById("phone");
		var input_captcha  = document.getElementById("jcaptcha");
		var phone =  $("#phone").val().trim();
		var  captcha = $("#jcaptcha").val().trim();
 		if(phone.length  == 0) {
			  alert(forgotpassword.phonenumberNotEmpty); 
			  input_phone.focus();
			  return false;
	  	}
		if(captcha.length  == 0) {
			  alert(forgotpassword.captchaNotEmpty);		  
			  input_captcha.focus();
			  return false;
	  	}
		return true;
}


function reloadCaptcha() {
    var d = new Date();
    document.getElementById("login-captcha").src = dirS + "jcaptcha.jpg#" + d.getTime();
}