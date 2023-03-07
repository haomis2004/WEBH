<%-- 
    Document   : danhmuc
    Created on : Sep 26, 2013, 9:06:06 AM
    Author     : LEEYOOL
--%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="POJOs.Danhmucsanpham"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" ></jsp:include>
<%
    //Lay danh sach danh muc
    List<Danhmucsanpham> ds = (List<Danhmucsanpham>) request.getAttribute("danhmuc_timkiem");
    
    String thongbao = "";
    if(request.getAttribute("danhmuc_xoa_kq") != null)
    {
        if(request.getAttribute("danhmuc_xoa_kq").equals(true))
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
        <form class="timkiem" name="frmTimKiem" id="frmTimKiem" method="post" action="<%= request.getContextPath() %>/danhmuc?action=timkiem">
            <input type="search" class="txt" name="txtSearch" id="txtSearch" value="<%= request.getAttribute("danhmuc_txtSearch") %>" placeholder="Nhập tên danh mục cần tìm" style="width: 250px;" />
        </form><!--END TIM KIEM-->
    	
        <!--DANH SACH-->
        <div class="danhsach">
            <ul class="danhsach-header">
            	<li style="width:50px;">ID</li>
                <li style="width:500px;">Tên danh mục</li>
                <li style="width:100px; float:right; text-align:right;">
                    <a href="<%= request.getContextPath() %>/danhmuc-themmoi" name="btnThemDanhMuc" id="btnThemDanhMuc" title="Thêm danh mục mới">+ Thêm mới</a>
                </li>            
            </ul>
            <div class="danhsach-chitiet">
                <%
                    for(int i = 0; i < ds.size(); i++)
                    {
                        int madanhmuc = ds.get(i).getMadanhmuc();
                        
                        %>
                        <ul class="danhsach-item">
                            <li style="width:50px;"><%= madanhmuc %></li>
                            <li style="width:400px;">
                                <input type="text" class="editField" name="txtTenDanhMuc<%= madanhmuc %>" id="txtTenDanhMuc<%= madanhmuc %>" readonly value="<%= ds.get(i).getTendanhmuc() %>" title="<%= ds.get(i).getTendanhmuc() %>" />
                            </li>
                            <li style="width:100px; float:right; text-align:right;">
                                <a href="<%= request.getContextPath() %>/danhmuc-themmoi?id=<%= madanhmuc %>" class="lnkSua" name="btnSua<%= madanhmuc %>" id="btnSua<%= madanhmuc %>" data-id="<%= madanhmuc %>" data-trangthai="0" title="Sửa danh mục">Sửa</a>
                                <a href="<%= request.getContextPath() %>/danhmuc?action=xoa&id=<%= madanhmuc %>" class="lnkXoa" name="btnXoa<%= madanhmuc %>" id="btnXoa<%= madanhmuc %>" data-id="<%= madanhmuc %>" title="Xoá danh mục">Xoá</a>
                            </li>
                        </ul>
                        <%
                    }
                %>
            </div>
        </div><!--END DANH SACH-->
    </div><!--END CONTENT-->
   
    
<jsp:include page="footer.jsp" ></jsp:include>