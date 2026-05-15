package Model;

public class UserSession {
    private static Pengguna currentUser;

    public static void setUser(Pengguna user) {
        currentUser = user;
    }

    public static Pengguna getUser() {
        return currentUser;
    }

    public static void clearSession() {
        currentUser = null;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }
}
