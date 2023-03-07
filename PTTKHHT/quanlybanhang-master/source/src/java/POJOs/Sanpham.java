package POJOs;
// Generated Sep 24, 2013 9:20:03 AM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Sanpham generated by hbm2java
 */
public class Sanpham  implements java.io.Serializable {


     private int masanpham;
     private Danhmucsanpham danhmucsanpham;
     private String tensanpham;
     private String hinhanh;
     private Integer soluong;
     private long dongia;
     private Integer trangthai;
     private Set<Chitietdonhang> chitietdonhangs = new HashSet<Chitietdonhang>(0);

    public Sanpham() {
    }

	
    public Sanpham(int masanpham) {
        this.masanpham = masanpham;
    }
    public Sanpham(int masanpham, Danhmucsanpham danhmucsanpham, String tensanpham, String hinhanh, Integer soluong, long dongia, Integer trangthai, Set<Chitietdonhang> chitietdonhangs) {
       this.masanpham = masanpham;
       this.danhmucsanpham = danhmucsanpham;
       this.tensanpham = tensanpham;
       this.hinhanh = hinhanh;
       this.soluong = soluong;
       this.dongia = dongia;
       this.trangthai = trangthai;
       this.chitietdonhangs = chitietdonhangs;
    }
   
    public int getMasanpham() {
        return this.masanpham;
    }
    
    public void setMasanpham(int masanpham) {
        this.masanpham = masanpham;
    }
    public Danhmucsanpham getDanhmucsanpham() {
        return this.danhmucsanpham;
    }
    
    public void setDanhmucsanpham(Danhmucsanpham danhmucsanpham) {
        this.danhmucsanpham = danhmucsanpham;
    }
    public String getTensanpham() {
        return this.tensanpham;
    }
    
    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }
    public String getHinhanh() {
        return this.hinhanh;
    }
    
    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }
    public Integer getSoluong() {
        return this.soluong;
    }
    
    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }
    public long getDongia() {
        return this.dongia;
    }
    
    public void setDongia(long dongia) {
        this.dongia = dongia;
    }
    public Integer getTrangthai() {
        return this.trangthai;
    }
    
    public void setTrangthai(Integer trangthai) {
        this.trangthai = trangthai;
    }
    public Set<Chitietdonhang> getChitietdonhangs() {
        return this.chitietdonhangs;
    }
    
    public void setChitietdonhangs(Set<Chitietdonhang> chitietdonhangs) {
        this.chitietdonhangs = chitietdonhangs;
    }




}


