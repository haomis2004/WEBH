<%-- 
    Document   : donhang
    Created on : Sep 27, 2013, 5:03:43 PM
    Author     : LEEYOOL
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="POJOs.Chitietdonhang"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.List"%>
<%@page import="POJOs.Donhang"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" ></jsp:include>
<%
    //Lay danh sach
    List<Donhang> ds = (List<Donhang>) request.getAttribute("donhang_timkiem");
%>
    <!--CONTENT-->
    <div class="content">
    	<span class="ketquatimkiem">Tìm thấy <%= ds.size() %> kết quả.</span>
        
    	<!--TIM KIEM-->
        <form class="timkiem" name="frmTimKiem" id="frmTimKiem" method="post" action="<%= request.getContextPath() %>/donhang?action=timkiem">
            <input type="search" class="txt" name="txtSearch" id="txtSearch" value="<%= request.getAttribute("donhang_txtSearch") %>" placeholder="Nhập tên khách hàng cần tìm" style="width: 250px;" />
        </form><!--END TIM KIEM-->
    	
        <!--DANH SACH-->
        <div class="danhsach">
        	<ul class="danhsach-header">
                <li style="width:60px;">ID</li>
                <li style="width:250px;">Khách hàng</li>
                <li style="width:100px;">Ngày lập</li>
                <li style="width:200px;">Tổng tiền</li>
                <li style="width:100px; float:right; text-align:right;">
                </li>
            </ul>
            <div class="danhsach-chitiet">
                <%
                    for(int i = 0; i < ds.size(); i++)
                    {
                        int madonhang = ds.get(i).getMadonhang();
                        String _ngaylap = new SimpleDateFormat("dd/MM/yyyy").format(ds.get(i).getNgaylap());
                        %>
                            <ul class="danhsach-item">
                                <li style="width:60px;"><%= madonhang %></li>
                                <li style="width:250px;"><%= ds.get(i).getKhachhang().getHoten() %></li>
                                <li style="width:100px;"><%= _ngaylap %></li>
                                <li style="width:200px;"><%= Math.round(ds.get(i).getTongtien()) %></li>
                                <li style="width:100px; float:right; text-align:right;">
                                    <a class="lnkSua lnkChiTiet" name="btnChiTiet<%= madonhang %>" id="btnChiTiet<%= madonhang %>" data-id="<%= madonhang %>" data-trangthai="0" title="Xem chi tiết">Chi tiết</a>
                                </li>
                            </ul>
                                
                            <div class="danhsach-subitem" id="chiTietDonHang<%= madonhang %>">
                                <ul class="danhsach-subitem-header">
                                    <li style="width:250px;">Sản phẩm</li>
                                    <li style="width:100px;">Số lượng</li>
                                    <li style="width:130px;">Đơn giá</li>
                                    <li style="width:200px;">Tổng tiền</li>
                                </ul>
                                <%
                                    Set<Chitietdonhang> dsct = ds.get(i).getChitietdonhangs();
                                    for(Chitietdonhang ct : dsct)
                                    {
                                        int _soluong = ct.getSoluong();
                                        long _dongia = ct.getSanpham().getDongia();
                                        long _tongtien = _soluong*_dongia;
                                        %>
                                            <ul class="danhsach-subitem-item">
                                                <li style="width:250px;"><%= ct.getSanpham().getTensanpham() %></li>
                                                <li style="width:100px;"><%= _soluong %></li>
                                                <li style="width:130px;"><%= _dongia %></li>
                                                <li style="width:200px;"><%= _tongtien %></li>
                                            </ul>
                                        <%
                                    }
                                %>
                            </div>
                        <%
                    }
                %>
            </div>
        </div><!--END DANH SACH-->
    </div><!--END CONTENT-->

<jsp:include page="footer.jsp" ></jsp:include>