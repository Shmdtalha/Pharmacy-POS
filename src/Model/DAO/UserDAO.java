package Model.DAO;

import Model.Entity.Manager;
import Model.Entity.SalesAssistant;
import Model.Entity.User;
import Util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public void save() {

    }


    public void delete() {

    }

    public User findUser(String username, String password) {
        String query = "SELECT * FROM User WHERE username = ? AND password = ?";

        try{
            Connection conn = DBConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet count = pst.executeQuery();
            boolean isFound = false;
            User u = null;

            while(count.next()){
                u = new User();
                isFound = true;
                u.setId(count.getString(1));
                u.setName(count.getString(2));
                u.setUsername(count.getString(4));
                u.setPassword(count.getString(5));

                if(count.getString(3) == "MANAGER_ROLE"){
                    Manager m = new Manager();
                    u.setRole(m);
                }
                else{
                    SalesAssistant s = new SalesAssistant();
                    u.setRole(s);
                }
            }

            return u;

        } catch (Exception ex) {

        }
        return null;
    }
}
