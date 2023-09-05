package Management.GUI;

import javax.swing.*;
import java.awt.*;


public class FrmThongKe extends JPanel {


    public static FrmTKTheoNV frmTKTheoNV;
    public static FrmTKTinhTrangSP frmTKTinhTrangSP;
    public static FrmTKTongQuat frmTKTongQuat;

    public FrmThongKe() {
        init();
    }

    public void init() {
        JTabbedPane tpMain = new JTabbedPane();
        frmTKTheoNV = new FrmTKTheoNV();
        tpMain.addTab("Thống kê hóa đơn theo nhân viên", frmTKTheoNV);
        frmTKTinhTrangSP = new FrmTKTinhTrangSP();
        tpMain.addTab("Thống kê tình trạng sản phẩm", frmTKTinhTrangSP);
        frmTKTongQuat = new FrmTKTongQuat();
        tpMain.addTab("Thống kê theo doanh thu", frmTKTongQuat);
        this.setLayout(new BorderLayout());
        this.add(tpMain, BorderLayout.CENTER);
    }
}
