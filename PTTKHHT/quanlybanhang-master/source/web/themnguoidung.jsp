<%-- 
    Document   : themdanhmuc
    Created on : Sep 29, 2013, 7:44:11 PM
    Author     : Duong Dieu Phap
--%>

<%@page import="POJOs.Loaitaikhoan"%>
<%@page import="POJOs.Taikhoan"%>
<%@page import="java.util.List"%>
<%@page import="POJOs.Sanpham"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" ></jsp:include>
<%
    Taikhoan tk = null;
    if(request.getAttribute("nguoidung_thongtinchitiet") != null)
    {
        tk = (Taikhoan) request.getAttribute("nguoidung_thongtinchitiet");
    }
    
    %>
    <!--CONTENT-->
    <div class="content">
    	<p class="content-title"><%= (tk != null) ? "Cập nhật thông tin người dùng" : "Thêm người dùng mới" %></p>
        <form name="frmThemNguoiDungMoi" id="frmThemNguoiDungMoi" method="post" action="<%= request.getContextPath() + "/nguoidung-themmoi" + ((tk != null) ? "?id=" + tk.getMataikhoan() : "") %>" >
            
            Tên truy cập:<br/>
            <input type="text" class="txt" name="txtTenTruyCap" id="txtTenTruyCap" value="<%= (tk != null) ? tk.getTentruycap(): "" %>" required style="width:300px;" /><br/><br/>
            Mật khẩu:<br/>
            <input type="password" class="txt" name="txtMatKhau" id="txtMatKhau" required style="width:300px;" /><br/><br/>
            Xác nhận mật khẩu:<br/>
            <input type="password" class="txt" name="txtXacNhanMatKhau" id="txtXacNhanMatKhau" required style="width:300px;" /><br/><br/>
            Họ tên:<br/>
            <input type="text" class="txt" name="txtHoTen" id="txtHoTen" value="<%= (tk != null) ? tk.getHoten() : "" %>" required style="width:300px;" /><br/><br/>
            Loại tài khoản:<br/>
            <select class="txt" name="cmbLoaiTaiKhoan" id="cmbLoaiTaiKhoan" style="width:320px;">
            	<%
                    List<Loaitaikhoan> dsltk = DAO.TaiKhoanDAO.TimLoaiTaiKhoan("");
                    for(int i = 0; i < dsltk.size(); i++)
                    {
                        try
                        {
                            int tk_maloaitaikhoan = (tk != null) ? tk.getLoaitaikhoan().getMaloaitaikhoan() : 0;
                            int ltk_maloaitaikhoan = dsltk.get(i).getMaloaitaikhoan();
                            %>
                            <option <%= (tk_maloaitaikhoan == ltk_maloaitaikhoan) ? "selected='true'" : "" %>  value="<%= ltk_maloaitaikhoan %>">
                                <%= dsltk.get(i).getTenloaitaikhoan() %>
                            </option>
                            <%
                        }
                        catch(Exception ex)
                        {
                            System.err.print(ex.getMessage());
                        }
                    }
                %>
            </select>
            <br/><br/>
            
            <input type="submit" class="btn" name="btnThemNguoiDung" id="btnThemNguoiDung" value="<%= (tk != null) ? "Cập nhật" : "Thêm" %>" />
        </form>
        
    <%
        if(request.getAttribute("nguoidung_themmoi_kq").equals(true))
        {
            %>
            <b><%= (tk != null) ? "Cập nhật" : "Thêm" %> thành công!</b>
            <%
        }
                    
        %>
    </div><!--END CONTENT-->
<jsp:include page="footer.jsp" ></jsp:include>