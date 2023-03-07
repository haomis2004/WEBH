<%-- 
    Document   : themdanhmuc
    Created on : Sep 29, 2013, 7:44:11 PM
    Author     : Duong Dieu Phap
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="POJOs.Khachhang"%>
<%@page import="POJOs.Danhmucsanpham"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" ></jsp:include>
<%
    Khachhang kh = null;
    if(request.getAttribute("khachhang_thongtinchitiet") != null)
    {
        kh = (Khachhang) request.getAttribute("khachhang_thongtinchitiet");
    }
    
    %>
    <!--CONTENT-->
    <div class="content">
    	<p class="content-title"><%= (kh != null) ? "Cập nhật khách hàng" : "Thêm danh khách hàng" %></p>
        <form name="frmThemKhachHangMoi" id="frmThemKhachHangMoi" method="post" action="<%= request.getContextPath() + "/khachhang-themmoi" + ((kh != null) ? "?id=" + kh.getMakhachhang() : "") %>" >
            Họ tên:<br/>
            <input type="text" class="txt" name="txtHoTen" id="txtHoTen" value="<%= (kh != null) ? kh.getHoten() : "" %>" required style="width:300px;" /><br/><br/>
            Giới tính:<br/>
            <select class="txt" name="cmbGioiTinh" id="cmbGioiTinh" style="width:320px;">
            	<option value="1">Nam</option>
                <option <%= (kh != null) ? ((kh.getGioitinh() == 0) ? "selected='true'" : "") : "" %> value="0">Nữ</option>
            </select>
            <br/><br/>
            Email:<br/>
            <input type="email" class="txt" name="txtEmail" id="txtEmail" value="<%= (kh != null) ? kh.getEmail() : "" %>" required style="width:300px;" /><br/><br/>
            Ngày sinh:<br/>
            <%
                String _ngaysinh = "";
                if (kh != null)
                {
                    _ngaysinh = new SimpleDateFormat("yyyy-MM-dd").format(kh.getNgaysinh());
                }
            %>
            <input type="date" class="txt" name="txtNgaySinh" id="txtNgaySinh" value="<%= _ngaysinh %>" required style="width:300px;" /><br/><br/>
            
            <input type="submit" class="btn" name="btnThemKhachHang" id="btnThemKhachHang" value="<%= (kh != null) ? "Cập nhật" : "Thêm" %>" />
        </form>
        
    <%
        if(request.getAttribute("khachhang_themmoi_kq").equals(true))
        {
            %>
            <b><%= (kh != null) ? "Cập nhật" : "Thêm" %> thành công!</b>
            <%
        }
                    
        %>
    </div><!--END CONTENT-->
<jsp:include page="footer.jsp" ></jsp:include>