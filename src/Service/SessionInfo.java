package Service;

import Model.Entity.User;

public class SessionInfo {

    private static User loggedInUser;

    public static User getUserInstance(){
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        SessionInfo.loggedInUser = loggedInUser;
    }
}
