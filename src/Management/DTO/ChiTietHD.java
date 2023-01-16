package Management.DTO;

public class ChiTietHD {

    private String maHD,maSP,donViTinh;
    private Float donGia;
    private int soLuong;

    public ChiTietHD(String maHD, String maSP, String donViTinh, Float donGia, int soLuong) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.donViTinh = donViTinh;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }

    public ChiTietHD() {
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public Float getDonGia() {
        return donGia;
    }

    public void setDonGia(Float donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
