package gr.hua.dit.ErasmusRequest.repository;

import gr.hua.dit.ErasmusRequest.model.ErasmusCommittee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ErasmusCommitteeRepository extends JpaRepository<ErasmusCommittee,String> {
    ErasmusCommittee findByEmail(String email);

    List<ErasmusCommittee> findByField(String field);
}
