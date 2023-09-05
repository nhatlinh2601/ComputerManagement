package Management.DTO;

import java.util.Date;
import java.util.Vector;

public class SanPhamLK {

    private String maSP, tenSP, moTa, nsx, trangThai, donViTinh, soDK;
    private int soLuong;
    private float giaNhap;

    private float price;
    private Date ngaySX;

    public SanPhamLK(String maSP, String tenSP, String moTa, String nsx, int soLuong, String trangThai,
                     String donViTinh, String soDK, float price, Date ngaySX) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.moTa = moTa;
        this.nsx = nsx;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.donViTinh = donViTinh;
        this.soDK = soDK;
        this.price = price;
        this.ngaySX = ngaySX;
    }

    public SanPhamLK(String maSP, String tenSP, String moTa, String nsx, int soLuong, String trangThai,
                     String donViTinh, String soDK, float price, Date ngaySX, Float giaNhap) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.moTa = moTa;
        this.nsx = nsx;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.donViTinh = donViTinh;
        this.soDK = soDK;
        this.price = price;
        this.ngaySX = ngaySX;
        this.giaNhap = giaNhap;
    }

    public float getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(float giaNhap) {
        this.giaNhap = giaNhap;
    }

    public SanPhamLK() {
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

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
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

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public String getSoDK() {
        return soDK;
    }

    public void setSoDK(String soDK) {
        this.soDK = soDK;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getNgaySX() {
        return ngaySX;
    }

    public void setNgaySX(Date ngaySX) {
        this.ngaySX = ngaySX;
    }

    public Vector<Object> toVector() {
        Vector<Object> v = new Vector<>();
        v.add(maSP);
        v.add(tenSP);
        v.add(moTa);
        v.add(price);
        v.add(giaNhap);
        v.add(nsx);
        v.add(soLuong);
        v.add(trangThai);
        v.add(donViTinh);
        v.add(ngaySX);
        v.add(soDK);
        return v;
    }
}
