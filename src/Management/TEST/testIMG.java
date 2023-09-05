package Management.TEST;

import Management.DAO.DatabaseHelper;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class testIMG {

    public testIMG() throws Exception {
        layImg();
    }


    public void layImg() throws Exception {
        Connection conn = DatabaseHelper.getConnection();
        String sql = "SELECT image FROM user WHERE id = 4";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            InputStream input = result.getBinaryStream("image");
            OutputStream output = new FileOutputStream("image.jpg");
            byte[] buffer = new byte[1024];
            while (input.read(buffer) > 0) {
                output.write(buffer);

            }
            input.close();
            output.close();
        }
        File imageFile = new File("image.jpg");
        BufferedImage image = ImageIO.read(imageFile);

        // Create a panel to display the image
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(image.getWidth(), image.getHeight());
            }
        };

        // Create a frame to hold the panel
        JFrame frame = new JFrame();
        frame.add(imagePanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }








    public static void main(String[] args) throws Exception {
        new testIMG();
    }





        private static OutputStream someMethodThatReturnsAnOutputStream() {
            // TODO: Implement this method
            return null;
        }

        private static void displayImage(BufferedImage image) {
            // TODO: Implement this method
        }
    }


