package Service;

import Model.UserSession;

public class HakAksesService {
    public static boolean isSuperadmin() {
        return UserSession.isLoggedIn() && "SUPERADMIN".equals(UserSession.getUser().getRole());
    }

    public static boolean isAdmin() {
        return UserSession.isLoggedIn() && ("ADMIN".equals(UserSession.getUser().getRole()) || "SUPERADMIN".equals(UserSession.getUser().getRole()));
    }
}
