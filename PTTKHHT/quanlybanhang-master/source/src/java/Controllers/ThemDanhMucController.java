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
@WebServlet(name = "ThemDanhMucController", urlPatterns = {"/danhmuc-themmoi"})
public class ThemDanhMucController extends HttpServlet {

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
            request.setAttribute("ServeletName", "danhmuc");
            
            //Kiem tra dang nhap & quyen truy cap
            if(DAO.TaiKhoanDAO.KiemTraDangNhap(request, response) &&
               (session.getAttribute("maloaitaikhoan").equals(1) || //admin
               session.getAttribute("maloaitaikhoan").equals(3))) //merchandise
            {
                
                //Dang nhap thanh cong
                //Lay action
                String action = request.getParameter("btnThemDanhMuc");
                
                if(action == null) //load
                { 
                    if(request.getParameter("id") != null) //sua
                    {
                        //Lay thong tin danh muc
                        int madanhmuc = Integer.parseInt(request.getParameter("id"));
                        
                        //Chuyen thong tin danh muc
                        request.setAttribute("danhmuc_thongtinchitiet", DAO.DanhMucDAO.LayDanhMuc(madanhmuc));
                        
                    }
                    else //them moi
                    {
                        request.setAttribute("danhmuc_thongtinchitiet", null);
                    }
                    
                    request.setAttribute("danhmuc_themmoi_kq", false);
                }
                else //submit
                {
                    if(request.getParameter("id") != null) //sua
                    {
                        int madanhmuc = Integer.parseInt(request.getParameter("id"));
                        String tendanhmuc = request.getParameter("txtTenDanhMuc");
                        
                        request.setAttribute("danhmuc_themmoi_kq", 
                                DAO.DanhMucDAO.CapNhatDanhMuc(madanhmuc, tendanhmuc));
                        request.setAttribute("danhmuc_thongtinchitiet", DAO.DanhMucDAO.LayDanhMuc(madanhmuc));
                    }
                    else //them
                    {
                        if(request.getParameter("txtTenDanhMuc") != null)
                        {
                            String tendanhmuc = request.getParameter("txtTenDanhMuc");
                            request.setAttribute("danhmuc_themmoi_kq", DAO.DanhMucDAO.ThemDanhMuc(tendanhmuc));
                            request.setAttribute("danhmuc_thongtinchitiet", null);
                        }
                    }
                }
                
                //Hien thi trang danh muc
                RequestDispatcher view = request.getRequestDispatcher("themdanhmuc.jsp");
                view.forward(request, response);
            }
            else
            {
                //Dang nhap that bai
                //Bat dang nhap lai
                String redirectURL = request.getContextPath() + "/login";
                response.sendRedirect(redirectURL);
            }
            
        } catch(IOException | ServletException ex){
            System.err.print(ex.getMessage());
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
