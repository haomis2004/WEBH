/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import POJOs.Danhmucsanpham;
import POJOs.Sanpham;
import Util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Duong Dieu Phap
 */
public class DanhMucDAO {
    public static List<Danhmucsanpham> TimDanhMuc(String ten) 
    {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            String hql = "from Danhmucsanpham t where t.trangthai = 1 and t.tendanhmuc like :ten";
            Query q = session.createQuery(hql);
            q.setString("ten", "%" + ten + "%");
            
            List<Danhmucsanpham> ds = q.list();
            return ds;

        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        return null;
    }
    
    public static Danhmucsanpham LayDanhMuc(int id)
    {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            Danhmucsanpham dm = (Danhmucsanpham) session.get(Danhmucsanpham.class, id);
            return dm;

        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        return null;
    }
    
    public static boolean ThemDanhMuc(String tendanhmuc) 
    {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            
            Danhmucsanpham dm = new Danhmucsanpham();
            dm.setTendanhmuc(tendanhmuc);
            dm.setTrangthai(1);
            
            Transaction tran = session.beginTransaction();
            try
            {
                session.save(dm);
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
    
    public static boolean CapNhatDanhMuc(int madanhmuc, String tendanhmuc) 
    {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();            
            Transaction tran = session.beginTransaction();
            try
            {
                Danhmucsanpham d = (Danhmucsanpham) session.get(Danhmucsanpham.class, madanhmuc);                
                d.setTendanhmuc(tendanhmuc);
                
                session.update(d);
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
    
    public static boolean XoaDanhMuc(int madanhmuc)
    {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();            
            Transaction tran = session.beginTransaction();
            try
            {   
                //5. Xoa san pham voi Cascadate
                Danhmucsanpham kh = (Danhmucsanpham) session.get(Danhmucsanpham.class, madanhmuc);
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
