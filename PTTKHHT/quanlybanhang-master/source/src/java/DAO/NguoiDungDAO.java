/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import POJOs.Loaitaikhoan;
import POJOs.Taikhoan;
import Util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Duong Dieu Phap
 */
public class NguoiDungDAO {
    public static List<Taikhoan> TimTaiKhoan(String ten) 
    {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            String hql = "from Taikhoan t where t.trangthai = 1 and t.hoten like :ten";
            Query q = session.createQuery(hql);
            q.setString("ten", "%" + ten + "%");
            
            List<Taikhoan> ds = q.list();
            return ds;

        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        return null;
    }
    
    public static Taikhoan LayTaiKhoan(int id)
    {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            Taikhoan tk = (Taikhoan) session.get(Taikhoan.class, id);
            return tk;

        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        return null;
    }
    
    public static boolean ThemTaiKhoan(String tentruycap, String matkhau, int maloaitaikhoan, String hoten) 
    {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            
            Taikhoan tk = new Taikhoan();
            Loaitaikhoan ltk = (Loaitaikhoan) session.get(Loaitaikhoan.class, maloaitaikhoan);
            
            tk.setTentruycap(tentruycap);
            tk.setMatkhau(DAO.TaiKhoanDAO.hashMD5(matkhau));
            tk.setHoten(hoten);
            tk.setLoaitaikhoan(ltk);
            tk.setTrangthai(1);
            
            Transaction tran = session.beginTransaction();
            try
            {
                session.save(tk);
                tran.commit();
            }
            catch(Exception ex)
            {
                tran.rollback();
                System.err.print(ex.getMessage());
                return false;
            }
            finally
            {
                session.close();
            }
            
            return true;

        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        
        return false;
    }
    
    public static boolean CapNhatTaiKhoan(int mataikhoan, String tentruycap, String matkhau, 
            int maloaitaikhoan, String hoten) 
    {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();            
            Transaction tran = session.beginTransaction();
            try
            {
                Taikhoan tk = (Taikhoan) session.get(Taikhoan.class, mataikhoan);
                Loaitaikhoan ltk = (Loaitaikhoan) session.get(Loaitaikhoan.class, maloaitaikhoan);
                
                tk.setTentruycap(tentruycap);
                tk.setMatkhau(DAO.TaiKhoanDAO.hashMD5(matkhau));
                tk.setHoten(hoten);
                tk.setLoaitaikhoan(ltk);
                
                session.update(tk);
                session.flush();
                tran.commit();
            }
            catch(Exception ex)
            {
                tran.rollback();
                System.err.print(ex.getMessage());
                return false;
            }
            finally
            {
                session.close();
            }
            
            return true;

        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        
        return false;
    }
    
    public static boolean XoaTaiKhoan(int mataikhoan)
    {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();            
            Transaction tran = session.beginTransaction();
            try
            {
                Taikhoan tk = (Taikhoan) session.get(Taikhoan.class, mataikhoan);
                
                session.delete(tk);
                session.flush();
                tran.commit();
            }
            catch(Exception ex)
            {
                tran.rollback();
                System.err.print(ex.getMessage());
                return false;
            }
            finally
            {
                session.close();
            }
            
            return true;

        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        
        return false;
    }
}
