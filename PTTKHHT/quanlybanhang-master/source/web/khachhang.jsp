<%-- 
    Document   : danhmuc
    Created on : Sep 26, 2013, 9:06:06 AM
    Author     : LEEYOOL
--%>
<%@page import="POJOs.Khachhang"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="POJOs.Danhmucsanpham"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" ></jsp:include>
<%
    //Lay danh sach
    List<Khachhang> ds = (List<Khachhang>) request.getAttribute("khachhang_timkiem");
    
    String thongbao = "";
    if(request.getAttribute("khachhang_xoa_kq") != null)
    {
        if(request.getAttribute("khachhang_xoa_kq").equals(true))
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
        <form class="timkiem" name="frmTimKiem" id="frmTimKiem" method="post" action="<%= request.getContextPath() %>/khachhang?action=timkiem">
            <input type="search" class="txt" name="txtSearch" id="txtSearch" value="<%= request.getAttribute("khachhang_txtSearch") %>" placeholder="Nhập tên khách hàng cần tìm" style="width: 250px;" />
        </form><!--END TIM KIEM-->
    	
        <!--DANH SACH-->
        <div class="danhsach">
            <ul class="danhsach-header">
                <li style="width:60px;">ID</li>
                <li style="width:300px;">Họ tên</li>
                <li style="width:80px;">Giới tính</li>
                <li style="width:250px;">Email</li>
                <li style="width:130px;">Ngày sinh</li>
                <li style="width:100px; float:right; text-align:right;">
                    <a href="<%= request.getContextPath() %>/khachhang-themmoi" name="btnThemKhachHang" id="btnThemKhachHang" title="Thêm khách hàng mới">+ Thêm mới</a>
                </li>           
            </ul>
            <div class="danhsach-chitiet">
                <%
                    for(int i = 0; i < ds.size(); i++)
                    {
                        int _makhachhang = ds.get(i).getMakhachhang();
                        String _ngaysinh = new SimpleDateFormat("dd/MM/yyyy").format(ds.get(i).getNgaysinh());
                        
                        %>
                        <ul class="danhsach-item">
                            <li style="width:60px;"><%= _makhachhang %></li>
                            <li style="width:300px;"><%= ds.get(i).getHoten() %></li>
                            <li style="width:80px;"><%= (ds.get(i).getGioitinh() == 1) ? "Nam" : "Nữ" %></li>
                            <li style="width:250px;"><%= ds.get(i).getEmail() %></li>
                            <li style="width:130px;"><%= _ngaysinh %></li>
                            <li style="width:100px; float:right; text-align:right;">
                                <a class="lnkSua" name="btnSua<%= _makhachhang %>" id="btnSua<%= _makhachhang %>" data-id="<%= _makhachhang %>" data-trangthai="0" title="Sửa khách hàng" href="<%= request.getContextPath() %>/khachhang-themmoi?id=<%= _makhachhang %>">Sửa</a>
                                <a class="lnkXoa" name="btnXoa<%= _makhachhang %>" id="btnXoa<%= _makhachhang %>" data-id="<%= _makhachhang %>" title="Xoá khách hàng" href="<%= request.getContextPath() %>/khachhang?action=xoa&id=<%= _makhachhang %>">Xoá</a>
                            </li>
                        </ul>
                        <%
                    }
                %>
            </div>
        </div><!--END DANH SACH-->
    </div><!--END CONTENT-->
   
    
<jsp:include page="footer.jsp" ></jsp:include>