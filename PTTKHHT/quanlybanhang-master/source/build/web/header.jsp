<%-- 
    Document   : header
    Created on : Sep 27, 2013, 5:03:58 PM
    Author     : PHAP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Quản lý bán hàng</title>
    
    <link type="text/css" href="css/main.css" rel="stylesheet" />
    <script type="text/javascript" src="js/jquery_min.js"></script>
    
    <%
        session = request.getSession();        
        String serveletName = "";
        
        
        try {
            serveletName = request.getAttribute("ServeletName").toString();

            if (serveletName == "danhmuc") {
                out.print("<script type='text/javascript' src='js/danhmuc.js' ></script>");
            } else if (serveletName == "sanpham") {
                out.print("<script type='text/javascript' src='js/sanpham.js' ></script>");
            } else if (serveletName == "donhang") {
                out.print("<script type='text/javascript' src='js/donhang.js' ></script>");
            } else if (serveletName == "khachhang") {
                out.print("<script type='text/javascript' src='js/khachhang.js' ></script>");
            } else if (serveletName == "nguoidung") {
                out.print("<script type='text/javascript' src='js/nguoidung.js' ></script>");
            }
        } catch (Exception ex) {
            System.err.print(ex.getMessage());
        }
    %>
    
   
</head>

<body>
    <!--HEADER-->
    <div class="header">
    	<h1>QUẢN LÝ BÁN HÀNG</h1>
        <div class="logout">
            Xin chào <b><%= session.getAttribute("hoten") %></b> | <a href="<%= request.getContextPath() %>/login" title="Log out">Đăng xuất</a>
        </div>
        <!--NAVIGATION BAR-->
        <ul class="navigation">
            <%
                if(session.getAttribute("maloaitaikhoan").equals(1) || //admin
                   session.getAttribute("maloaitaikhoan").equals(3))//merchandise
                {
                    %>
                        <a href="<%= request.getContextPath() + "/danhmuc" %>" title="Danh mục sản phẩm" class="<% if (serveletName == "danhmuc") { out.print("navigation-item-selected"); } %>"><li>Danh mục sản phẩm</li></a>
                        <a href="<%= request.getContextPath() + "/sanpham" %>" title="Quản lý sản phẩm" class="<% if (serveletName == "sanpham") { out.print("navigation-item-selected"); } %>"><li>Quản lý sản phẩm</li></a>
                    <%
                }
                if(session.getAttribute("maloaitaikhoan").equals(1) || //admin
                   session.getAttribute("maloaitaikhoan").equals(4))//accountant
                {
                    %>
                        <a href="<%= request.getContextPath() + "/donhang" %>" title="Quản lý đơn hàng" class="<% if (serveletName == "donhang") { out.print("navigation-item-selected"); } %>"><li>Quản lý đơn hàng</li></a>
                    <%
                }
                if(session.getAttribute("maloaitaikhoan").equals(1) || //admin
                   session.getAttribute("maloaitaikhoan").equals(2))//accountant
                {
                    %>
                        <a href="<%= request.getContextPath() + "/khachhang" %>" title="Quản lý khách hàng" class="<% if (serveletName == "khachhang") { out.print("navigation-item-selected"); } %>"><li>Quản lý khách hàng</li></a>
                    <%
                }
                if(session.getAttribute("maloaitaikhoan").equals(1)) //admin
                {
                    %>
                        <a href="<%= request.getContextPath() + "/nguoidung" %>" title="Quản lý người dùng" class="<% if (serveletName == "nguoidung") { out.print("navigation-item-selected"); } %>"><li>Quản lý người dùng</li></a>
                    <%
                }
            %>
            
            
            
            
        </ul><!--NAVIGATION BAR-->
    </div><!--END HEADER-->