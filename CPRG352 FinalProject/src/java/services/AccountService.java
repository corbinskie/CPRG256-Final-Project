package services;

import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.util.List;
import models.Role;
import models.User;

public class AccountService {

    public User get(String email) throws Exception {
        UserDB usersDB = new UserDB();
        User user = usersDB.get(email);
        return user;
    }

    public User login(String email, String password) {
        UserDB userDB = new UserDB();

        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword())) {
                return user;
            }
        } catch (Exception e) {
        }

        return null;
    }

    public List<User> getAll() throws Exception {
        UserDB userDB = new UserDB();
        List<User> usersList = userDB.getAll();
        return usersList;
    }

    public List<Role> getAllRoles() throws Exception {
        RoleDB roleDB = new RoleDB();
        List<Role> roleList = roleDB.getAll();
        return roleList;
    }

    public void delete(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        userDB.delete(user);
    }

    public void insert(String email, String fName, String lName, String password) throws Exception {
        User user = new User(email, true, fName, lName, password);
        Role role = new Role(2);
        user.setRole(role);
        UserDB userDB = new UserDB();
        userDB.insert(user);
    }

    public void insertAsAdmin(String email, boolean activity, int roleId, String password, String fName, String lName) throws Exception {
        User user = new User(email, activity, password, fName, lName);
        Role role = new Role(roleId);
        user.setRole(role);
        UserDB userDB = new UserDB();
        userDB.insert(user);
    }

    public void update(String email, boolean activity, String password, String fName, String lName) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        user.setActive(activity);
        user.setPassword(password);
        user.setFirstName(fName);
        user.setLastName(lName);

        userDB.update(user);
    }

    public void update(String email, boolean activity, int roleId, String password, String fName, String lName) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        user.setActive(activity);
        user.setPassword(password);
        user.setFirstName(fName);
        user.setLastName(lName);
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.get(roleId);
        user.setRole(role);

        userDB.update(user);
    }
}
