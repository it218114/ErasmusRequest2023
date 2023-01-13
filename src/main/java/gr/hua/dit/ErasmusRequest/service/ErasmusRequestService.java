package gr.hua.dit.ErasmusRequest.service;

import gr.hua.dit.ErasmusRequest.model.ErasmusRequest;

import java.util.List;
import java.util.Optional;

public interface ErasmusRequestService {
    public List<ErasmusRequest> findAllErasmusRequest();

    public Optional<ErasmusRequest> findErasmusRequestById(int id);

    public List<ErasmusRequest> findErasmusRequestByCreatedBy(String createdBy);

    public List<ErasmusRequest> findErasmusRequestByCreatedByAndStatus(String createdBy, String status);

    public List<ErasmusRequest> findErasmusRequestByStatus(String status);

    public ErasmusRequest findErasmusRequestBySendTo(String sendTo);

    public ErasmusRequest saveReqLetter(ErasmusRequest newErasmusRequest, String createdBy);

    ErasmusRequest updateErasmusRequest(String createdBy, ErasmusRequest newErasmusRequest, String sendTo);


     Object saveErasmusRequest(ErasmusRequest newErasmusRequest, String name);
}
