<%-- 
    Document   : danhmuc
    Created on : Sep 26, 2013, 9:06:06 AM
    Author     : LEEYOOL
--%>
<%@page import="POJOs.Taikhoan"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="POJOs.Danhmucsanpham"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" ></jsp:include>
<%
    //Lay danh sach
    List<Taikhoan> ds = (List<Taikhoan>) request.getAttribute("nguoidung_timkiem");
    String thongbao = "";
    if(request.getAttribute("nguoidung_xoa_kq") != null)
    {
        if(request.getAttribute("nguoidung_xoa_kq").equals(true))
        {
            thongbao = "<b style='color:#f00;'>Xoá thành công.</b><br/>";
        }
    }
%>
    <!--CONTENT-->
    <div class="content">
        <span class="ketquatimkiem">
            <%= thongbao %>
            Tìm thấy <%= ds.size() %> kết quả.
        </span>
        
    	<!--TIM KIEM-->
        <form class="timkiem" name="frmTimKiem" id="frmTimKiem" method="post" action="<%= request.getContextPath() %>/nguoidung?action=timkiem">
            <input type="search" class="txt" name="txtSearch" id="txtSearch" value="<%= request.getAttribute("nguoidung_txtSearch") %>" placeholder="Nhập tên tài khoản cần tìm" style="width: 250px;" />
        </form><!--END TIM KIEM-->
    	
        <!--DANH SACH-->
        <div class="danhsach">
            <ul class="danhsach-header">
            	<li style="width:60px;">ID</li>
                <li style="width:250px;">Tên truy cập</li>
                <li style="width:250px;">Họ tên</li>
                <li style="width:200px;">Loại tài khoản</li>
                <li style="width:100px; float:right; text-align:right;">
                    <a href="<%= request.getContextPath() %>/nguoidung-themmoi" name="btnThemNguoiDung" id="btnThemNguoiDung" title="Thêm người dùng mới">+ Thêm mới</a>
                </li>            
            </ul>
            <div class="danhsach-chitiet">
                <%
                    for(int i = 0; i < ds.size(); i++)
                    {
                        int mataikhoan = ds.get(i).getMataikhoan();
                        
                        %>
                        <ul class="danhsach-item">
                            <li style="width:60px;"><%= mataikhoan %></li>
                            <li style="width:250px;"><%= ds.get(i).getTentruycap() %></li>
                            <li style="width:250px;"><%= ds.get(i).getHoten() %></li>
                            <li style="width:200px;"><%= ds.get(i).getLoaitaikhoan().getTenloaitaikhoan() %></li>
                            <li style="width:100px; float:right; text-align:right;">
                                <a href="<%= request.getContextPath() %>/nguoidung-themmoi?id=<%= mataikhoan %>" class="lnkSua" name="btnSua<%= mataikhoan %>" id="btnSua<%= mataikhoan %>" data-id="<%= mataikhoan %>" data-trangthai="0" title="Sửa người dùng">Sửa</a>
                                <a href="<%= request.getContextPath() %>/nguoidung?action=xoa&id=<%= mataikhoan %>" class="lnkXoa" name="btnXoa<%= mataikhoan %>" id="btnXoa<%= mataikhoan %>" data-id="<%= mataikhoan %>" title="Xoá người dùng">Xoá</a>
                            </li>
                        </ul>
                        <%
                    }
                %>
            </div>
        </div><!--END DANH SACH-->
    </div><!--END CONTENT-->
   
    
<jsp:include page="footer.jsp" ></jsp:include>