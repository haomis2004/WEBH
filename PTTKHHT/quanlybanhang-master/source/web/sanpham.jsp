<%-- 
    Document   : sanpham
    Created on : Sep 26, 2013, 9:07:29 AM
    Author     : LEEYOOL
--%>

<%@page import="sun.org.mozilla.javascript.internal.regexp.SubString"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@page import="POJOs.Sanpham"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" ></jsp:include>
<%
    //Lay danh sach
    List<Sanpham> ds = (List<Sanpham>) request.getAttribute("sanpham_timkiem");
    String thongbao = "";
    if(request.getAttribute("sanpham_xoa_kq") != null)
    {
        if(request.getAttribute("sanpham_xoa_kq").equals(true))
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
        <form class="timkiem" name="frmTimKiem" id="frmTimKiem" method="post" action="<%= request.getContextPath() %>/sanpham?action=timkiem">
            <input type="search" class="txt" name="txtSearch" id="txtSearch" value="<%= request.getAttribute("sanpham_txtSearch") %>" placeholder="Nhập tên sản phẩm cần tìm" style="width: 250px;" />
        </form><!--END TIM KIEM-->
    	
        <!--DANH SACH-->
        <div class="danhsach">
        	<ul class="danhsach-header">
                <li style="width:60px;">ID</li>
                <li style="width:280px;">Tên sản phẩm</li>
                <li style="width:250px;">Danh mục</li>
                <li style="width:200px;">Ảnh</li>
                <li style="width:80px;">Số lượng</li>
                <li style="width:150px;">Đơn giá</li>
                <li style="width:100px; float:right; text-align:right;">
                    <a href="<%= request.getContextPath() %>/sanpham-themmoi" name="btnThemSanPham" id="btnThemSanPham" title="Thêm sản phẩm mới">+ Thêm mới</a>
                </li>
            </ul>
            <div class="danhsach-chitiet">
                <%
                    for(int i = 0; i < ds.size(); i++)
                    {
                        int masanpham = ds.get(i).getMasanpham();
                        long dongia = ds.get(i).getDongia();
                        %>
                        <ul class="danhsach-item">
                            <li style="width:60px;"><%= masanpham %></li>
                            <li style="width:280px;"><%= ds.get(i).getTensanpham() %></li>
                            <li style="width:250px;"><%= ds.get(i).getDanhmucsanpham().getTendanhmuc() %></li>
                            <li style="width:200px;">
                                <a href="<%= ds.get(i).getHinhanh() %>" title="<%= ds.get(i).getHinhanh() %>" target="_blank">
                                    <% 
                                        if(ds.get(i).getHinhanh() != null)
                                        {
                                            String hinhanh = ds.get(i).getHinhanh();
                                            int l = hinhanh.length();
                                            if(l > 24)
                                            {
                                                out.print("..." + hinhanh.substring(l - 25));
                                            }
                                            else
                                            {
                                                out.print("..." + hinhanh);
                                            }
                                        }
                                    %>
                                </a>
                            </li>
                            <li style="width:80px;"><%= ds.get(i).getSoluong() %></li>
                            <li style="width:150px;"><%= dongia %></li>
                            <li style="width:100px; float:right; text-align:right;">
                                <a class="lnkSua" name="btnSua<%= masanpham %>" id="btnSua<%= masanpham %>" data-id="<%= masanpham %>" data-trangthai="0" title="Sửa sản phẩm" href="<%= request.getContextPath() %>/sanpham-themmoi?id=<%= masanpham %>">Sửa</a>
                                <a class="lnkXoa" name="btnXoa<%= masanpham %>" id="btnXoa<%= masanpham %>" data-id="<%= masanpham %>" title="Xoá sản phẩm" href="<%= request.getContextPath() %>/sanpham?action=xoa&id=<%= masanpham %>">Xoá</a>
                            </li>
                        </ul>
                        <%
                    }
                %>
                
            </div>
        </div><!--END DANH SACH-->
    </div><!--END CONTENT-->
    
<jsp:include page="footer.jsp" ></jsp:include>