<%-- 
    Document   : themdanhmuc
    Created on : Sep 29, 2013, 7:44:11 PM
    Author     : Duong Dieu Phap
--%>

<%@page import="java.util.List"%>
<%@page import="POJOs.Sanpham"%>
<%@page import="POJOs.Danhmucsanpham"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp" ></jsp:include>
<%
    Sanpham sp = null;
    if(request.getAttribute("sanpham_thongtinchitiet") != null)
    {
        sp = (Sanpham) request.getAttribute("sanpham_thongtinchitiet");
    }
    
    %>
    <!--CONTENT-->
    <div class="content">
    	<p class="content-title"><%= (sp != null) ? "Cập nhật sản phẩm" : "Thêm sản phẩm mới" %></p>
        <form name="frmThemSanPhamMoi" id="frmThemSanPhamMoi" method="post" action="<%= request.getContextPath() + "/sanpham-themmoi" + ((sp != null) ? "?id=" + sp.getMasanpham() : "") %>" >
            Tên sản phẩm:<br/>
            <input type="text" class="txt" name="txtTenSanPham" id="txtTenSanPham" value="<%= (sp != null) ? sp.getTensanpham() : "" %>" required style="width:300px;" /><br/><br/>
            Danh mục:<br/>
            <select class="txt" name="cmbDanhMuc" id="cmbDanhMuc" style="width:320px;">
                <%
                    List<Danhmucsanpham> dsDanhMuc = DAO.DanhMucDAO.TimDanhMuc("");
                    for(int i = 0; i < dsDanhMuc.size(); i++)
                    {
                        try
                        {
                            int sp_madanhmuc = (sp != null) ? sp.getDanhmucsanpham().getMadanhmuc() : 0;
                            int dm_madanhmuc = dsDanhMuc.get(i).getMadanhmuc();
                            %>
                            <option <%= (sp_madanhmuc == dm_madanhmuc) ? "selected='true'" : "" %>  value="<%= dsDanhMuc.get(i).getMadanhmuc() %>">
                                <%= dsDanhMuc.get(i).getTendanhmuc() %>
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
            Hình ảnh:<br/>
            <input type="url" class="txt" name="txtHinhAnh" id="txtHinhAnh" value="<%= (sp != null) ? ((sp.getHinhanh() != null) ? sp.getHinhanh() : "") : "" %>" style="width:300px;" /><br/><br/>
            Số lượng:<br/>
            <input type="number" min="1" max="99999" class="txt" name="txtSoLuong" id="txtSoLuong" value="<%= (sp != null) ? sp.getSoluong() : "" %>" required style="width:300px;" /><br/><br/>
            Đơn giá:<br/>
            <input type="number" min="1" max="100000000" class="txt" name="txtDonGia" id="txtDonGia" value="<%= (sp != null) ? sp.getDongia() : "" %>" required style="width:300px;" /><br/><br/>
            
            <input type="submit" class="btn" name="btnThemSanPham" id="btnThemSanPham" value="<%= (sp != null) ? "Cập nhật" : "Thêm" %>" />
        </form>
        
    <%
        if(request.getAttribute("sanpham_themmoi_kq").equals(true))
        {
            %>
            <b><%= (sp != null) ? "Cập nhật" : "Thêm" %> thành công!</b>
            <%
        }
                    
        %>
    </div><!--END CONTENT-->
<jsp:include page="footer.jsp" ></jsp:include>