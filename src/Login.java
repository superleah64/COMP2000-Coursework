import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Login extends JFrame {
    private JTextField usernameTxt;
    private JTextField passwordTxt;
    private JButton btnSubmit;
    private JPanel login;

    private ArrayList<AdminAccounts> users = new ArrayList<>();

    public void setUsers(ArrayList<AdminAccounts> users) {
        this.users = users;
    }

    public Login() {
        setContentPane(login);
        setPreferredSize(new Dimension(800, 800));
        pack();

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DataLoader dl = new DataLoader();
                dl.adminLoad();

                setUsers(dl.getUsers());

                AdminAccounts temp = new AdminAccounts();
                temp.setUsername(usernameTxt.getText());
                temp.setPassword(passwordTxt.getText());

                try {
                    for (int i = 0; i < users.size(); i++) {
                        if (users.get(i).getUsername().equals(temp.getUsername())) {
                            if (users.get(i).getPassword().equals(temp.getPassword())) {

                                Admin admin = new Admin();
                                admin.setVisible(true);

                            }
                        }
                    }
                } catch (IndexOutOfBoundsException exception) {
                    System.out.println("Username or password incorrect. Please try again.");
                }
            }
        });
    }
}