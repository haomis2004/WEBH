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
public class SanPhamDAO {
    public static List<Sanpham> TimSanPham(String ten) 
    {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            String hql = "from Sanpham t where t.trangthai = 1 and t.tensanpham like :ten";
            Query q = session.createQuery(hql);
            q.setString("ten", "%" + ten + "%");
            
            List<Sanpham> ds = q.list();
            return ds;

        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        return null;
    }
    
    public static List<Sanpham> TimSanPham(int madanhmuc) 
    {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            String hql = "from Sanpham t where t.trangthai = 1 and t.madanhmucsanpham =:madanhmuc";
            Query q = session.createQuery(hql);
            q.setInteger("madanhmuc", madanhmuc);
            
            List<Sanpham> ds = q.list();
            return ds;

        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        return null;
    }
    
    public static Sanpham LaySanPham(int id)
    {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            Sanpham sp = (Sanpham) session.get(Sanpham.class, id);            
            return sp;

        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        return null;
    }
    
    public static boolean ThemSanPham(String tensanpham, String hinhanh, int madanhmucsanpham, 
            int soluong, long donggia) 
    {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            
            Danhmucsanpham dm = DAO.DanhMucDAO.LayDanhMuc(madanhmucsanpham);
            Sanpham sp = new Sanpham();
            
            sp.setTensanpham(tensanpham);
            sp.setHinhanh((hinhanh.equals("")) ? null : hinhanh);
            sp.setDanhmucsanpham(dm);
            sp.setSoluong(soluong);
            sp.setDongia(donggia);
            sp.setTrangthai(1);
            
            Transaction tran = session.beginTransaction();
            try
            {
                session.save(sp);
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
    
    public static boolean CapNhatSanPham(int masanpham, String tensanpham, String hinhanh, 
            int madanhmucsanpham, int soluong, long dongia) 
    {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();            
            Transaction tran = session.beginTransaction();
            
            try
            {
                Sanpham s = (Sanpham) session.get(Sanpham.class, masanpham);
                Danhmucsanpham d = (Danhmucsanpham) session.get(Danhmucsanpham.class, madanhmucsanpham);
                
                s.setTensanpham(tensanpham);
                s.setHinhanh((hinhanh.equals("")) ? null : hinhanh);
                s.setDanhmucsanpham(d);
                s.setSoluong(soluong);
                s.setDongia(dongia);
                s.setTrangthai(1);
                
                session.update(s);
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
    
    public static boolean XoaSanPham(int masanpham)
    {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();            
            Transaction tran = session.beginTransaction();
            try
            {   
                //5. Xoa san pham voi Cascadate
                Sanpham kh = (Sanpham) session.get(Sanpham.class, masanpham);
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
