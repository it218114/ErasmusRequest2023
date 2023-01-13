package gr.hua.dit.ErasmusRequest.service;

import gr.hua.dit.ErasmusRequest.model.User;

import java.util.List;

public interface UserService {
    public List<User> findAllUsers();

    public User findUserByIt(String it);

    public User findBySurname(String surname);

    public User saveUser(User newUser);

    User updateUser(String it, User user);

    User deleteUser(String userId);
}
