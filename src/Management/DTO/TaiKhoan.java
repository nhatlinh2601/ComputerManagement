package Management.DTO;

public class TaiKhoan {

    private String username,password;

    public TaiKhoan(String username, String password) {
        this.username = username;
        this.password = password;
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
}
