package Controller;

import Service.AuthService;
import View.ViewLogin;
import View.ViewHomeAdmin;
import View.ViewHomeSuperadmin;
import Model.UserSession;
import javax.swing.JOptionPane;

public class LoginController {
    private ViewLogin view;
    private AuthService authService;

    public LoginController(ViewLogin view) {
        this.view = view;
        this.authService = new AuthService();
    }

    public void login() {
        String identifier = view.getTextUsernameEmail().getText();
        String password = view.getTextPassword().getText();

        if (identifier.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Username/Email dan Password tidak boleh kosong!");
            return;
        }

        if (authService.login(identifier, password)) {
            view.dispose();
            if ("SUPERADMIN".equals(UserSession.getUser().getRole())) {
                new ViewHomeSuperadmin().setVisible(true);
            } else {
                new ViewHomeAdmin().setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(view, "Login Gagal! Akun tidak ditemukan atau tidak aktif.");
        }
    }
}
