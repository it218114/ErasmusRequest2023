package gr.hua.dit.ErasmusRequest.service;

import gr.hua.dit.ErasmusRequest.model.ErasmusRequest;
import gr.hua.dit.ErasmusRequest.model.User;
import gr.hua.dit.ErasmusRequest.repository.ErasmusRequestRepository;
import gr.hua.dit.ErasmusRequest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ErasmusRequestRepository erasmusRequestRepository;

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }


    public User findUserByIt(String it) {
        return userRepo.findByIt(it);
    }


    public User findBySurname(String surname) {
        User user = userRepo.findBySurname(surname);
        return user;
    }

    @Override
    public User saveUser(User newUser){

        return userRepo.save(newUser);


    }
    @Override
    public User updateUser(String it,User user) {

        User retrievedUser=userRepo.findByIt(it);
        if(retrievedUser==null)
            try {
                throw new Exception("User not found");
            } catch (Exception e) {
                e.printStackTrace();
            }
        userRepo.save(user);
        return userRepo.findByIt(it);

    }

    @Override
    public User deleteUser(String userId) {

       User retrievedUser=userRepo.findByIt(userId);
        List<ErasmusRequest> retrieveLetter = erasmusRequestRepository.findErasmusRequestByCreatedBy(userId);
        if(retrievedUser==null)
            try {
                throw new Exception("User not found");
            } catch (Exception e) {
                e.printStackTrace();
            }
        userRepo.deleteById(userId);
            //delete all requests from user
          if(retrieveLetter!=null){
              for(int i=0;i<retrieveLetter.size();i++){
                  erasmusRequestRepository.deleteAllById(Collections.singleton(retrieveLetter.get(i).getId()));
              }
          }
        return retrievedUser;



    }

}
