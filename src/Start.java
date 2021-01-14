import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Start extends JFrame{
    private JButton startBtn;
    private JPanel panel1;

    public Start(){

        setContentPane(panel1);
        setPreferredSize(new Dimension(800, 400));
        pack();

        DataLoader stock = new DataLoader();
        stock.stockLoad();

        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Kiosk kiosk = null;
                try {
                    kiosk = new Kiosk();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                kiosk.setVisible(true);
                setVisible(false);
            }
        });
    }
}
