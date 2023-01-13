package gr.hua.dit.ErasmusRequest.repository;

import gr.hua.dit.ErasmusRequest.model.ErasmusRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ErasmusRequestRepository extends JpaRepository<ErasmusRequest,Integer> {
    ErasmusRequest findErasmusRequestBySendTo(String sendTo);

    List<ErasmusRequest> findErasmusRequestByCreatedBy(String createdBy);

    List<ErasmusRequest> findErasmusRequestByCreatedByAndSendTo(String createdBy, String sendTo);

    List<ErasmusRequest> findErasmusRequestByCreatedByAndStatus(String createdBy, String status);

    List<ErasmusRequest> findErasmusRequestByStatus(String status);


}
