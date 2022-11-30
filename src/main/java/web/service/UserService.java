package web.service;


import web.models.User;


import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    public User show(Integer id);

    public void save(User user);

    public void update(Integer id, User updatedUser);

    public void delete(Integer id);
}
