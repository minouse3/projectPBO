package Service;

import DAO.PenggunaDAO;
import DAOInterface.PenggunaDAOInterface;
import Model.Pengguna;
import Model.UserSession;

public class AuthService {
    private PenggunaDAOInterface penggunaDAO;

    public AuthService() {
        this.penggunaDAO = new PenggunaDAO();
    }

    public boolean login(String identifier, String password) {
        Pengguna p = penggunaDAO.login(identifier, password);
        if (p != null) {
            UserSession.setUser(p);
            return true;
        }
        return false;
    }

    public void logout() {
        UserSession.clearSession();
    }
}
