document.title = "Trang quên mật khẩu - Smart Motor";
// navigate
$('#menu-monitor').text("Giám sát");
$('#menu-mtrackreview').text("Hành trình"); 
$('#menu-report').text("Báo cáo"); 
$('#menu-help').text("Trợ giúp"); 
$('#menu-contact').text("Liên hệ"); 
$('#menu-connectuser').text("Đấu nối"); 
$('#menu-admin').text("Quản trị"); 
// account-box
$('#menu-changepassword').text("Đổi mật khẩu");
$('#menu-userguide').text("Hướng dẫn");
$('#menu-logout').text("Thoát");
// navigate mobile
$('#menu-mobile-monitor').text("Giám sát");
$('#menu-mobile-mtrackreview').text("Hành trình"); 
$('#menu-mobile-help').text("Trợ giúp"); 
$('#menu-mobile-contact').text("Liên hệ");
$('#menu-mobile-connectuser').text("Đấu nối"); 
$('#menu-mobile-changepassword').text("Đổi mật khẩu");
$('#menu-mobile-userguide').text("Hướng dẫn");
$('#menu-mobile-logout').text("Thoát");

$('#title-id').text("Quên mật khẩu");
$('#reg-phone-id').text("Số điện thoại đăng ký");
$('#refresh-captcha-id').html("(Click vào hình để làm mới captcha!)");

$('#jcaptcha').attr("placeholder", "Mã xác nhận");
$('#buttonFogetPassword').val("Gửi");

// bottom
$('#footer').html("Cơ quan chủ quản: Tập đoàn Công nghiệp - Viễn thông Quân đội. Giấy phép số: 144/GP-BC do Bộ Thông tin - Truyền thông cấp ngày 18/04/2007 <a href='http://ipv6-test.com/validate.php?url=http://smartmotor.viettel.vn'><img src='http://ipv6-test.com/button-ipv6-80x15.png' alt='ipv6 ready' title='ipv6 ready' border='0' /></a>");

forgotpassword = {
	backgroundLogoFull : "url(Images/Logo.png) no-repeat",
	backgroundLogoMobile : "url(Images/Logo-2.png) no-repeat",
	phonenumberNotEmpty : "Nhập vào số điện thoại!",
	captchaNotEmpty : "Nhập vào captcha!",
	sendPasswordResult : "Mật khẩu mới đã được gửi tới số điện thoại của bạn!"
}

function initBackground() {
	var w = document.body.clientWidth;
	if (w >= 1200) {
		$('#logo-bg').css({'background' : forgotpassword.backgroundLogoFull});
	} else {
		$('#logo-bg').css({'background' : forgotpassword.backgroundLogoMobile});
	}
}
initBackground();