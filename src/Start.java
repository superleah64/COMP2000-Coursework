import javax.swing.*;
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

        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Kiosk kiosk = new Kiosk();
                kiosk.setVisible(true);
                setVisible(false);
            }
        });
    }
}
