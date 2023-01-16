package Management.DTO;

import java.util.Date;
import java.util.Vector;

public class NhanVien {
    private String maNV,tenNV,soDT,gioiTinh,diaChi,CMND,caLamViec,username;
    private Date ngaySinh;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public NhanVien(String maNV, String tenNV, String soDT, String gioiTinh, String diaChi, String CMND, String caLamViec, Date ngaySinh, String username) {

        this.maNV = maNV;
        this.tenNV = tenNV;
        this.soDT = soDT;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.CMND = CMND;
        this.caLamViec = caLamViec;
        this.ngaySinh = ngaySinh;
        this.username=username;
    }

    public NhanVien() {
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getCaLamViec() {
        return caLamViec;
    }

    public void setCaLamViec(String caLamViec) {
        this.caLamViec = caLamViec;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Vector<Object> toVector() {
        Vector<Object> v = new Vector<>();
        v.add(maNV);
        v.add(tenNV);
        v.add(CMND);
        v.add(soDT);
        v.add(gioiTinh);
        v.add(diaChi);
        v.add(ngaySinh);
        v.add(caLamViec);
        v.add(username);
        return v;
    }
}
