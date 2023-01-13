package gr.hua.dit.ErasmusRequest.controller;

import gr.hua.dit.ErasmusRequest.model.User;
import gr.hua.dit.ErasmusRequest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/admin")
@CrossOrigin(origins="*")
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class UserRestController {
    @Autowired
    UserService userService;


    //USED FOR TESTING
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public List<User> getAllUsers() {
        //List<String> allIts = new ArrayList<>();
        /*for(int i=0;i<userService.findAllUsers().size();i++){
            allIts.add(userService.findAllUsers().get(i).getIt());
        }

         */
        return userService.findAllUsers();

    }

    //USED IN SYSTEM
    @PostMapping(path = "/users/register")
    public ResponseEntity<User> saveUsers(@RequestBody User newUser,Authentication auth) {
        System.out.println(newUser.toString()+"  "+auth.getName());
        for(int i=0;i<userService.findAllUsers().size();i++){
            if(Objects.equals(newUser.getIt(), userService.findAllUsers().get(i).getIt())){
                System.out.println("Exists!");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();

            }
        }
        System.out.println("Saved!");
        return ResponseEntity.status(HttpStatus.CREATED).body((userService.saveUser(newUser)));


    }

    //USED FOR TESTING
    @GetMapping("/users/modify/{userIt}")
    public ResponseEntity<User> getUserByIt(@PathVariable("userIt") String userIt, Authentication authentication) {
        System.out.println("Inside getuserbyit method");
        return ResponseEntity.ok().body(userService.findUserByIt(userIt));

    }

    //USED IN SYSTEM
    @PutMapping ("/users/modify/{userIt}")
    public ResponseEntity<User> updateUser(@PathVariable("userIt") String UserId,@RequestBody User newUser) {
        return ResponseEntity.ok().body(userService.updateUser(UserId,newUser));

    }

    //USED IN SYSTEM
    @DeleteMapping("/users/modify/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable("userId") String UserId) {
        userService.deleteUser(UserId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();

    }



}
