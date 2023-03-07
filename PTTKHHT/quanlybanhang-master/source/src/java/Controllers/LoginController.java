/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

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
            throws ServletException, IOException, NoSuchAlgorithmException {
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        
        try {
            
            //Lay action
            String action = request.getParameter("action");
            if (action == null) {
                //Đăng xuất
                DAO.TaiKhoanDAO.DangXuat(request, response);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else if (action.equals("dangnhap")) {
                request.setAttribute("tacvu", "thuchiendangnhap");
                
                //Dang nhap thanh cong
                if (DAO.TaiKhoanDAO.DangNhap(request, response)) {
                    
                    //Phan quyen truy cap
                    if(session.getAttribute("maloaitaikhoan").equals(1)) //admin
                    {
                        String redirectURL = request.getContextPath() + "/danhmuc";
                        response.sendRedirect(redirectURL);
                    }
                    else if(session.getAttribute("maloaitaikhoan").equals(2))//customer service
                    {
                        String redirectURL = request.getContextPath() + "/khachhang";
                        response.sendRedirect(redirectURL);
                    }
                    else if(session.getAttribute("maloaitaikhoan").equals(3))//merchandise
                    {
                        String redirectURL = request.getContextPath() + "/danhmuc";
                        response.sendRedirect(redirectURL);
                    }
                    else if(session.getAttribute("maloaitaikhoan").equals(4))//accountant
                    {
                        String redirectURL = request.getContextPath() + "/donhang";
                        response.sendRedirect(redirectURL);
                    }
                    
                } else //Khong thanh cong
                {
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            }

        } catch (Exception ex) {
            System.out.print(ex.getMessage());
            
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
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
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
