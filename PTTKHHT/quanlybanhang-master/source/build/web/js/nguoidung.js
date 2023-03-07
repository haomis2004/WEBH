
//Xoá danh mục
function deleteItem(id)
{
    var itemID = parseInt($(id).attr("data-id"));
    
    if (confirm("Bạn có chắc muốn xoá dòng có ID = " + itemID + " ?"))
    {
        //Xoá danh mục
        //
        return true;
    }
    else
    {
        return false;
    }
}


$(document).ready(function() {

    $(".lnkXoa").click(function() {
        return deleteItem(this);
    });

    //Kiem tra du lieu form
    $("#frmThemNguoiDungMoi").submit(function(e) {
        var pass = $.trim($("#txtMatKhau").val());
        var confirmpass = $.trim($("#txtXacNhanMatKhau").val());

        if (pass !== confirmpass)
        {
            alert("Mật khẩu xác nhận không đúng!");
            return false;
        }
    });

});

