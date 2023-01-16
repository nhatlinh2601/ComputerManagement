package Management.DTO;


import java.util.Date;

public class HoaDon {
    private String ma;
    private String ngayLap;
    private double tongTien;
    private String nhanVien;
    private int khachHang;
    public HoaDon(String ma, String ngayLap, double tongTien, String nhanVien) {
        super();
        this.ma = ma;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.nhanVien = nhanVien;
    }
    public HoaDon() {
        super();
        // TODO Auto-generated constructor stub
    }
    public String getMa() {
        return ma;
    }
    public void setMa(String ma) {
        this.ma = ma;
    }
    public String getNgayLap() {
        return ngayLap;
    }
    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }
    public double getTongTien() {
        return tongTien;
    }
    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
    public String getNhanVien() {
        return nhanVien;
    }
    public void setNhanVien(String nhanVien) {
        this.nhanVien = nhanVien;
    }
    public int getKhachHang() {
        return khachHang;
    }
    public void setKhachHang(int khachHang) {
        this.khachHang = khachHang;
    }
    @Override
    public String toString() {
        return "HoaDon [ma=" + ma + ", ngayLap=" + ngayLap + ", tongTien=" + tongTien + ", nhanVien=" + nhanVien
                + ", khachHang=" + khachHang + "]";
    }


}