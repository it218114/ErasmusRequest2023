package gr.hua.dit.ErasmusRequest.repository;

import gr.hua.dit.ErasmusRequest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findBySurname(String surname);

    User findByIt(String it);



}
