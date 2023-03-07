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
 * @author Duong Dieu Phap
 */
@WebServlet(name = "NguoiDungController", urlPatterns = {"/nguoidung"})
public class NguoiDungController extends HttpServlet {

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
            request.setAttribute("ServeletName", "nguoidung");
            
            
            //Kiem tra dang nhap & quyen truy cap
            if(DAO.TaiKhoanDAO.KiemTraDangNhap(request, response) &&
               (session.getAttribute("maloaitaikhoan").equals(1))) //admin
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
                        int mataikhoan = Integer.parseInt(request.getParameter("id"));
                        request.setAttribute("nguoidung_xoa_kq", DAO.NguoiDungDAO.XoaTaiKhoan(mataikhoan));
                    }
                }
                
                //Tim kiem danh sach
                request.setAttribute("nguoidung_timkiem", DAO.NguoiDungDAO.TimTaiKhoan(txtSearch));
                request.setAttribute("nguoidung_txtSearch", txtSearch);
                
                //Hien thi trang danh muc
                RequestDispatcher view = request.getRequestDispatcher("nguoidung.jsp");
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
