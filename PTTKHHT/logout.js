var url = '../mtapi/';
$(document).ready(function() {

});

function logout() {
    $.ajax({
        type: "GET",
        url: url + "logout",
        success: function(response) {
            if (response.success === 'true') {
                window.location.href = 'index.html';
            } else {
                alert('Đăng xuất không thành công!');
            }
        },
        error: function(xhr, textStatus, errorThrown) {
            window.location.href = 'error.html';
        }
    });
}