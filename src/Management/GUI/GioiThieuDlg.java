package Management.GUI;

import javax.swing.*;
import java.awt.*;

public class GioiThieuDlg extends JDialog {

    public GioiThieuDlg(){
        init();
    }

    public void init() {

        setSize(450, 250);

        setLocationRelativeTo(null);
        setTitle("About Us");

        ImageIcon icon = new ImageIcon("/Image/project-plan-icon.png");
        JLabel lbIntrp = new JLabel("PROJECT INTRODUCTION", icon, SwingConstants.CENTER);

        Font font = new Font("Monospaced", Font.BOLD, 32);
        lbIntrp.setFont(font);

        Font text = new Font("SF Mono", Font.PLAIN, 18);
        JLabel lbName = new JLabel("Name : Trần Thị Nhật Linh",SwingConstants.CENTER);
        lbName.setFont(text);
        JLabel lbClass = new JLabel("Class : 22IT2B",SwingConstants.CENTER);
        lbClass.setFont(text);
        JLabel lbID = new JLabel("ID Code : 22ITB.122",SwingConstants.CENTER);
        lbID.setFont(text);
        JLabel lbPro = new JLabel("Project : Computer parts sales manager",SwingConstants.CENTER);
        lbPro.setFont(text);
        JLabel lbSub = new JLabel("Subject : Object-oriented programming",SwingConstants.CENTER);
        lbSub.setFont(text);

        JPanel pnInfo = new JPanel(new GridLayout(5,1));
        pnInfo.add(lbName);
        pnInfo.add(lbClass);
        pnInfo.add(lbID);
        pnInfo.add(lbPro);
        pnInfo.add(lbSub);


        this.setLayout(new BorderLayout());
        this.add(lbIntrp, BorderLayout.NORTH);
        this.add(pnInfo, BorderLayout.CENTER);

        setVisible(true);


    }


}
