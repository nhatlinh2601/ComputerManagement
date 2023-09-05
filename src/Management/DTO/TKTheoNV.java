package Management.DTO;

import java.awt.*;
import java.util.Vector;

public class TKTheoNV {

    private String maHD, tenNV, caLamViec, ngayLap;
    private int soLuong;
    private Float tongTien;

    public TKTheoNV(String maHD, String tenNV, String caLamViec, String ngayLap, int soLuong, Float tongTien) {
        this.maHD = maHD;
        this.tenNV = tenNV;
        this.caLamViec = caLamViec;
        this.ngayLap = ngayLap;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
    }

    public TKTheoNV() {
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getCaLamViec() {
        return caLamViec;
    }

    public void setCaLamViec(String caLamViec) {
        this.caLamViec = caLamViec;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Float getTongTien() {
        return tongTien;
    }

    public void setTongTien(Float tongTien) {
        this.tongTien = tongTien;
    }

    public Vector<Object> toVector() {
        Vector<Object> v = new Vector<>();
        v.add("");
        v.add(maHD);
        v.add(tenNV);
        v.add(caLamViec);
        v.add(ngayLap);
        v.add(tongTien);
        v.add(soLuong);
        return v;
    }
}
