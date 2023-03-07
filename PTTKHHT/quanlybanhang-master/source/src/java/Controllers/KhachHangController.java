/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author LEEYOOL
 */
@WebServlet(name = "KhachHangController", urlPatterns = {"/khachhang"})
public class KhachHangController extends HttpServlet {

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
            throws ServletException, IOException {
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
                String action = request.getParameter("action");
                String txtSearch = "";
                
                if(action == null)
                { }
                else if(action.equals("timkiem"))
                {
                    if(request.getParameter("txtSearch") != null)
                    {
                        txtSearch = request.getParameter("txtSearch");
                    }
                }
                else if(action.equals("xoa"))
                {
                    if(request.getParameter("id") != null)
                    {
                        //Lay ID
                        int makhachhang = Integer.parseInt(request.getParameter("id"));
                        request.setAttribute("khachhang_xoa_kq", DAO.KhachHangDAO.XoaKhachHang(makhachhang));
                    }
                }
                
                //Tim kiem danh sach
                request.setAttribute("khachhang_timkiem", DAO.KhachHangDAO.TimKhachHang(txtSearch));
                request.setAttribute("khachhang_txtSearch", txtSearch);
                
                //Hien thi trang                
                RequestDispatcher view = request.getRequestDispatcher("khachhang.jsp");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
