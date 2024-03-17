import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm extends JDialog {
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JButton btnLOGIN;
    private JButton btnCANCEL;

    private JPanel loginPanel;

    public LoginForm(JFrame parent){
        super(parent);
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(450,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnLOGIN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email=tfEmail.getText();
                String password=String.valueOf(pfPassword.getPassword());

                user=getAutheticatedUser(email,password);

                if(user!=null){
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(LoginForm.this,"Email or Password is incorrect","Please retry",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnCANCEL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public User user;
    private User getAutheticatedUser(String email,String password){
        User user=null;
        final String Url="jdbc:mysql://localhost/MyStore?serverTimezone=UTC";
        final String Username="root";
        final String Password="serbymysql";
        try{
            Connection conn= DriverManager.getConnection(Url,Username,Password);
            Statement stnt=conn.createStatement();
            String sql="SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);

            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                user=new User();
                user.name=resultSet.getString("name");
                user.email=resultSet.getString("email");
                user.phone=resultSet.getString("phone");
                user.address=resultSet.getString("address");
                user.password=resultSet.getString("password");

            }
            stnt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }

}
