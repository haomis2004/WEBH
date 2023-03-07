/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import POJOs.Loaitaikhoan;
import POJOs.Taikhoan;
import Util.HibernateUtil;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Duong Dieu Phap
 */
public class TaiKhoanDAO {
    
    /**
     *
     * @param user
     * @param pass
     * @return
     */
    private static Taikhoan TruyVanDangNhap(String user, String pass) 
    {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String md5pass = hashMD5(pass);

            String hql = "from Taikhoan t where t.tentruycap =:user and t.matkhau =:pass";
            Query q = session.createQuery(hql);
            q.setString("user", user);
            q.setString("pass", md5pass);
            
            Taikhoan tk = (Taikhoan) q.uniqueResult();
            return tk;

        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        return null;
    }
    
    public static List<Loaitaikhoan> TimLoaiTaiKhoan(String ten) 
    {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            String hql = "from Loaitaikhoan t where t.tenloaitaikhoan like :ten";
            Query q = session.createQuery(hql);
            q.setString("ten", "%" + ten + "%");
            
            List<Loaitaikhoan> ds = q.list();
            return ds;

        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        return null;
    }
    
    public static Boolean DangNhap(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException {
        if(request.getParameter("action").equals("dangnhap"))
        {
            HttpSession session = request.getSession();
            String txtUsername = request.getParameter("txtUsername");
            String txtPassword = request.getParameter("txtPassword");
            
            Taikhoan tk = DAO.TaiKhoanDAO.TruyVanDangNhap(txtUsername, txtPassword);
            if(tk != null)
            {
                session.setAttribute("tentruycap", tk.getTentruycap());
                session.setAttribute("matkhau", tk.getMatkhau());
                session.setAttribute("maloaitaikhoan", tk.getLoaitaikhoan().getMaloaitaikhoan());
                session.setAttribute("hoten", tk.getHoten());
                return true;
            }
            else
            {
                DAO.TaiKhoanDAO.DangXuat(request, response);
            }
        }
        return false;
    }
    
    public static Boolean KiemTraDangNhap(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        HttpSession session = request.getSession();
        
        //Nếu chưa đăng nhập
        if(session.getAttribute("tentruycap").equals(""))
        {
            return false;
        }
        
        //Da dang nhap
        return true;
    }
    
    public static void DangXuat(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("tentruycap", "");
        session.setAttribute("matkhau", "");
        session.setAttribute("maloaitaikhoan", "");
        session.setAttribute("hoten", "");
    }
    
    public static String hashMD5(String str) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes());
        
        byte byteData[] = md.digest();
        
        //convert the byte to hex format method 1
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
 
        return sb.toString();
    }
}
