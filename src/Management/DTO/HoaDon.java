package Management.DTO;


import java.util.Date;
import java.util.Vector;

public class HoaDon {
    private String ma;
    private Date ngayLap;
    private double tongTien;
    private String nhanVien;
    private String khachHang;

    public HoaDon(String ma, Date ngayLap, double tongTien, String nhanVien) {
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

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
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

    public String getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(String khachHang) {
        this.khachHang = khachHang;
    }

    @Override
    public String toString() {
        return "HoaDon [ma=" + ma + ", ngayLap=" + ngayLap + ", tongTien=" + tongTien + ", nhanVien=" + nhanVien
                + ", khachHang=" + khachHang + "]";
    }

    public Vector<Object> toVector() {
        Vector<Object> v = new Vector<>();
        v.add("");
        v.add(ma);
        v.add(ngayLap);
        v.add(tongTien);
        v.add(khachHang);
        v.add(nhanVien);

        return v;
    }


}