import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Login extends JFrame {
    private JTextField usernameTxt;
    private JTextField passwordTxt;
    private JButton loginBtn;
    private JPanel login;

    // created a copy of the adminAccounts array from DataLoader
    private ArrayList<AdminAccounts> users = new ArrayList<>();

    // set the array
    public void setUsers(ArrayList<AdminAccounts> users) {
        this.users = users;
    }

    public Login() {
        setContentPane(login);
        setPreferredSize(new Dimension(500, 300));
        pack();

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DataLoader data = new DataLoader();
                data.adminLoad();

                setUsers(data.getUsers());

                AdminAccounts temp = new AdminAccounts();
                temp.setUsername(usernameTxt.getText());
                temp.setPassword(passwordTxt.getText());

                // if the username and password combination both match with one in the database, the admin form is opened
                try {
                    for (int i = 0; i < users.size(); i++) {
                        if (users.get(i).getUsername().equals(temp.getUsername())) {
                            if (users.get(i).getPassword().equals(temp.getPassword())) {

                                Admin admin = new Admin();
                                admin.setVisible(true);
                                break;
                            }
                        }
                    }
                        //JOptionPane.showMessageDialog(null,"Username or password incorrect. Please try again.");

                } catch (IndexOutOfBoundsException exception) {

                }
            }
        });
    }
}