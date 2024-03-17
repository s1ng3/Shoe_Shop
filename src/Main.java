public class Main {
    public static void main(String[] args) {

        LoginForm loginForm=new LoginForm(null);
        User user=loginForm.user;
        if(user!=null){
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new Shushop().setVisible(true);
                }
            });
            System.out.println("Authetification successful for: "+user.name);
            System.out.println("       Email "+user.email);
            System.out.println("       Phone " +user.phone);
            System.out.println("       Address " +user.address);
        }
        else{
            System.out.println("Authetification unsuccesful ");
        }
    }
}