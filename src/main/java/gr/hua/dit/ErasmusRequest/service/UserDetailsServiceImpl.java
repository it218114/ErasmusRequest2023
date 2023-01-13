package gr.hua.dit.ErasmusRequest.service;

import gr.hua.dit.ErasmusRequest.model.User;
import gr.hua.dit.ErasmusRequest.model.UserDetailsImpl;
import gr.hua.dit.ErasmusRequest.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



import org.springframework.beans.factory.annotation.Autowired;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User user=userRepo.findByIt(username);


        if(user==null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException(username + "not found");
        }
        return new UserDetailsImpl(user);
    }

}