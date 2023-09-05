package Management.DTO;

public class TaiKhoan {

    private String username, password, loaiTK;

    public TaiKhoan(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public TaiKhoan(String username, String password, String loaiTK) {
        this.username = username;
        this.password = password;
        this.loaiTK = loaiTK;
    }

    public String getLoaiTK() {
        return loaiTK;
    }

    public void setLoaiTK(String loaiTK) {
        this.loaiTK = loaiTK;
    }

    public TaiKhoan() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", loaiTK='" + loaiTK + '\'' +
                '}';
    }
}
