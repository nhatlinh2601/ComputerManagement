package Management.DTO;

import java.sql.Date;
import java.util.Vector;

public class TKTinhTrangSP {
    private String maSP, tenSP, nsx;
    private int soLuong;
    private Float donGia;


    public TKTinhTrangSP(String maSP, String tenSP, String nsx, int soLuong, Float donGia) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.nsx = nsx;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public TKTinhTrangSP() {
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getNsx() {
        return nsx;
    }

    public void setNsx(String nsx) {
        this.nsx = nsx;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Float getDonGia() {
        return donGia;
    }

    public void setDonGia(Float donGia) {
        this.donGia = donGia;
    }

    public Vector<Object> toVector() {
        Vector<Object> v = new Vector<>();
        v.add("");
        v.add(maSP);
        v.add(tenSP);
        v.add(soLuong);
        v.add(nsx);
        v.add(donGia);
        return v;
    }
}
