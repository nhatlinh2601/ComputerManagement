package Management.DTO;

import java.sql.Date;
import java.util.Vector;

public class KhachHang {

    private String maKH;
    private String tenKH;
    private String soDT;
    private String diaChi;
    private String maHD;

    public KhachHang( String tenKH, String soDT, String diaChi, String maHD, String gioiTinh, Date ngaySinh) {
        this.tenKH = tenKH;
        this.soDT = soDT;
        this.diaChi = diaChi;
        this.maHD = maHD;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    private String gioiTinh;


    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaKH() {
        return maKH;
    }

    private Date ngaySinh;


    public KhachHang(String maKH, String tenKH, String soDT, String diaChi, Date ngaySinh, String gioiTinh) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.soDT = soDT;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
    }

    public KhachHang() {
    }





    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public Vector<Object> toVector() {
        Vector<Object> v = new Vector<>();
        v.add(maKH);
        v.add(tenKH);
        v.add(diaChi);
        v.add(soDT);
        v.add(ngaySinh);
        v.add(gioiTinh);
        return v;
    }

    @Override
    public String toString() {
        return "KhachHang{" +
                "maKH='" + maKH + '\'' +
                ", tenKH='" + tenKH + '\'' +
                ", soDT='" + soDT + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", ngaySinh=" + ngaySinh +
                '}';
    }
}
