<%-- 
    Document   : themdanhmuc
    Created on : Sep 29, 2013, 7:44:11 PM
    Author     : Duong Dieu Phap
--%>

<%@page import="POJOs.Danhmucsanpham"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" ></jsp:include>
<%
    Danhmucsanpham dm = null;
    if(request.getAttribute("danhmuc_thongtinchitiet") != null)
    {
        dm = (Danhmucsanpham) request.getAttribute("danhmuc_thongtinchitiet");
    }
    
    %>
    <!--CONTENT-->
    <div class="content">
    	<p class="content-title"><%= (dm != null) ? "Cập nhật danh mục" : "Thêm danh mục mới" %></p>
        <form name="frmThemDanhMucMoi" id="frmThemDanhMucMoi" method="post" action="<%= request.getContextPath() + "/danhmuc-themmoi" + ((dm != null) ? "?id=" + dm.getMadanhmuc() : "") %>" >
        	Tên danh mục:<br/>
                <input type="text" class="txt" name="txtTenDanhMuc" id="txtTenDanhMuc" value="<%= (dm != null) ? dm.getTendanhmuc() : "" %>" required style="width:300px;" />
            <input type="submit" class="btn" name="btnThemDanhMuc" id="btnThemDanhMuc" value="<%= (dm != null) ? "Cập nhật" : "Thêm" %>" />
        </form>
        
    <%
        if(request.getAttribute("danhmuc_themmoi_kq").equals(true))
        {
            %>
            <b><%= (dm != null) ? "Cập nhật" : "Thêm" %> thành công!</b>
            <%
        }
                    
        %>
    </div><!--END CONTENT-->
<jsp:include page="footer.jsp" ></jsp:include>