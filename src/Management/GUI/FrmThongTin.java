package Management.GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;
import javax.swing.ImageIcon;

public class FrmThongTin extends JPanel {


    public FrmThongTin() {
        setBackground(SystemColor.inactiveCaption);
        setForeground(SystemColor.activeCaption);

        JPanel pnIntro = new JPanel();
        JLabel lbIntro = new JLabel("CHÀO MỪNG ĐẾN VỚI HTQL BÁN HÀNG LINH KIỆN MÁY TÍNH");
        lbIntro.setBackground(new Color(255, 228, 225));
        lbIntro.setFont(new Font("Monospaced", Font.BOLD, 26));
        lbIntro.setForeground(Color.white);
        pnIntro.add(lbIntro);
        pnIntro.setBackground(new Color(29, 109, 135));
        JLabel lbImage = new JLabel("");
        lbImage.setIcon(new ImageIcon(FrmThongTin.class.getResource("/Image/220-hero.jpg")));
        add(pnIntro, BorderLayout.NORTH);
        add(lbImage, BorderLayout.CENTER);

    }
}

