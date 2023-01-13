package gr.hua.dit.ErasmusRequest.service;

import gr.hua.dit.ErasmusRequest.model.ErasmusRequest;
import gr.hua.dit.ErasmusRequest.repository.ErasmusRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ErasmusRequestServiceImpl implements ErasmusRequestService{

    @Autowired
    private ErasmusRequestRepository reqRepo;

    @Override
    public List<ErasmusRequest> findAllErasmusRequest() {
        return reqRepo.findAll();
    }

    @Override
    public Optional<ErasmusRequest> findErasmusRequestById(int id) {
        return reqRepo.findById(id);
    }

    @Override
    public List<ErasmusRequest> findErasmusRequestByCreatedBy(String createdBy) {

        return reqRepo.findErasmusRequestByCreatedBy(createdBy);
    }

    @Override
    public List<ErasmusRequest> findErasmusRequestByCreatedByAndStatus(String createdBy, String status) {

        return reqRepo.findErasmusRequestByCreatedByAndStatus(createdBy,status);
    }

    @Override
    public List<ErasmusRequest> findErasmusRequestByStatus(String status) {
        return reqRepo.findErasmusRequestByStatus(status);
    }

    @Override
    public ErasmusRequest findErasmusRequestBySendTo(String sendTo) {
        ErasmusRequest reqLetter = reqRepo.findErasmusRequestBySendTo(sendTo);
        return reqLetter;
    }

    @Override
    public ErasmusRequest saveErasmusRequest(ErasmusRequest newErasmusRequest,String createdBy) {
        newErasmusRequest.setCreatedBy(createdBy);
        newErasmusRequest.setStatus("null");

        return reqRepo.save(newErasmusRequest);
    }


    @Override
    public ErasmusRequest updateErasmusRequest(String createdBy, ErasmusRequest newErasmusRequest,String sendTo) {
        ErasmusRequest retrieveErasmusRequest = reqRepo.findErasmusRequestByCreatedByAndSendTo(createdBy,sendTo).get(0);

        if(retrieveErasmusRequest==null){
            try{
                throw new Exception("ErasmusRequest Not Found");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        retrieveErasmusRequest.setStatus(newErasmusRequest.getStatus());
        reqRepo.save(retrieveErasmusRequest);
        return reqRepo.findErasmusRequestByCreatedByAndSendTo(createdBy,sendTo).get(0);
    }
}