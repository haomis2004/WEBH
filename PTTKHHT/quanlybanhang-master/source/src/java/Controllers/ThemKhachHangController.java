/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Duong Dieu Phap
 */
@WebServlet(name = "ThemKhachHangController", urlPatterns = {"/khachhang-themmoi"})
public class ThemKhachHangController extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            //Tham so
            HttpSession session = request.getSession();
            request.setAttribute("ServeletName", "khachhang");
            
            //Kiem tra dang nhap & quyen truy cap
            if(DAO.TaiKhoanDAO.KiemTraDangNhap(request, response) &&
               (session.getAttribute("maloaitaikhoan").equals(1) || //admin
               session.getAttribute("maloaitaikhoan").equals(2))) //customer service
            {
                
                //Dang nhap thanh cong
                //Lay action
                String action = request.getParameter("btnThemKhachHang");
                
                if(action == null) //load
                { 
                    if(request.getParameter("id") != null) //sua
                    {
                        //Lay thong tin danh muc
                        int makhachhang = Integer.parseInt(request.getParameter("id"));
                        
                        //Chuyen thong tin danh muc
                        request.setAttribute("khachhang_thongtinchitiet", DAO.KhachHangDAO.LayKhachHang(makhachhang));
                        
                    }
                    else //them moi
                    {
                        request.setAttribute("khachhang_thongtinchitiet", null);
                    }
                    
                    request.setAttribute("khachhang_themmoi_kq", false);
                }
                else //submit
                {
                    if(request.getParameter("id") != null) //sua
                    {
                        int makhachhang = Integer.parseInt(request.getParameter("id"));
                        String hoten = request.getParameter("txtHoTen");
                        int gioitinh = Integer.parseInt(request.getParameter("cmbGioiTinh"));
                        String email = request.getParameter("txtEmail");
                        Date ngaysinh = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtNgaySinh"));
                        
                        request.setAttribute("khachhang_themmoi_kq", 
                                DAO.KhachHangDAO.CapNhatKhachHang(makhachhang, hoten, gioitinh, email, ngaysinh));
                        request.setAttribute("khachhang_thongtinchitiet", DAO.KhachHangDAO.LayKhachHang(makhachhang));
                    }
                    else //them
                    {
                        if(request.getParameter("txtHoTen") != null)
                        {
                            String hoten = request.getParameter("txtHoTen");
                            int gioitinh = Integer.parseInt(request.getParameter("cmbGioiTinh"));
                            String email = request.getParameter("txtEmail");
                            Date ngaysinh = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("txtNgaySinh"));
                            
                            request.setAttribute("khachhang_themmoi_kq", 
                                    DAO.KhachHangDAO.ThemKhachHang(hoten, gioitinh, email, ngaysinh));
                            request.setAttribute("khachhang_thongtinchitiet", null);
                        }
                    }
                }
                
                //Hien thi trang danh muc
                RequestDispatcher view = request.getRequestDispatcher("themkhachhang.jsp");
                view.forward(request, response);
            }
            else
            {
                //Dang nhap that bai
                //Bat dang nhap lai
                String redirectURL = request.getContextPath() + "/login";
                response.sendRedirect(redirectURL);
            }
            
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ThemKhachHangController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ThemKhachHangController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
