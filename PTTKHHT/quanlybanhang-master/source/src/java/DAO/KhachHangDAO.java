/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import POJOs.Chitietdonhang;
import POJOs.Donhang;
import POJOs.Khachhang;
import Util.HibernateUtil;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Duong Dieu Phap
 */
public class KhachHangDAO {
    public static List<Khachhang> TimKhachHang(String ten) 
    {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            String hql = "from Khachhang t where t.trangthai = 1 and t.hoten like :ten";
            Query q = session.createQuery(hql);
            q.setString("ten", "%" + ten + "%");
            
            List<Khachhang> ds = q.list();
            return ds;

        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        return null;
    }
    
    public static Khachhang LayKhachHang(int id)
    {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            Khachhang kh = (Khachhang) session.get(Khachhang.class, id);            
            return kh;

        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        return null;
    }
    
    public static boolean ThemKhachHang(String hoten, int gioitinh, String email, Date ngaysinh) 
    {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            
            Khachhang kh = new Khachhang();
            kh.setHoten(hoten);
            kh.setGioitinh(gioitinh);
            kh.setEmail(email);
            kh.setNgaysinh(ngaysinh);
            kh.setTrangthai(1);
            
            Transaction tran = session.beginTransaction();
            try
            {
                session.save(kh);
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
    
    public static boolean CapNhatKhachHang(int makhachhang, String hoten, int gioitinh, String email, Date ngaysinh) 
    {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();            
            Transaction tran = session.beginTransaction();
            try
            {
                Khachhang kh = (Khachhang) session.get(Khachhang.class, makhachhang);
                kh.setHoten(hoten);
                kh.setGioitinh(gioitinh);
                kh.setEmail(email);
                kh.setNgaysinh(ngaysinh);
                
                session.update(kh);
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
    
    public static boolean XoaKhachHang(int makhachhang)
    {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();            
            Transaction tran = session.beginTransaction();
            try
            {   
                //5. Xoa khach hang voi Cascadate
                Khachhang kh = (Khachhang) session.get(Khachhang.class, makhachhang);
                session.delete(kh);
                
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
