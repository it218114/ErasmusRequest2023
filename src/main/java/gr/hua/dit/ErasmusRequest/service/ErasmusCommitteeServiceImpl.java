package gr.hua.dit.ErasmusRequest.service;

import gr.hua.dit.ErasmusRequest.model.ErasmusCommittee;
import gr.hua.dit.ErasmusRequest.repository.ErasmusCommitteeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ErasmusCommitteeServiceImpl implements ErasmusCommitteeService {

    @Autowired
    private ErasmusCommitteeRepository ErasmusCommitteeRepo;

    @Override
    public List<ErasmusCommittee> findErasmusCommittee() {
        return ErasmusCommitteeRepo.findAll();
    }

    @Override
    public ErasmusCommittee findErasmusCommitteeByEmail(String email) {
        ErasmusCommittee ec = ErasmusCommitteeRepo.findByEmail(email);
        return ec;
    }

    @Override
    public List<ErasmusCommittee> findErasmusCommitteeByField(String field) {
        return ErasmusCommitteeRepo.findByField(field);
    }

    @Override
    public ErasmusCommittee saveErasmusCommittee(ErasmusCommittee newErasmusCommittee) {
        ErasmusCommittee ec = ErasmusCommitteeRepo.save(newErasmusCommittee);
        return ec;
    }

    @Override
    public Collection<?> findErasmusCommitteByField(String approved) {
        return null;
    }
}
