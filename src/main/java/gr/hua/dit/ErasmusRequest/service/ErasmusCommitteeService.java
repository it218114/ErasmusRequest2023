package gr.hua.dit.ErasmusRequest.service;


import gr.hua.dit.ErasmusRequest.model.ErasmusCommittee;

import java.util.Collection;
import java.util.List;


public interface ErasmusCommitteeService {
    public List<ErasmusCommittee> findErasmusCommittee();

    public ErasmusCommittee findErasmusCommitteeByEmail(String email);

    public List<ErasmusCommittee> findErasmusCommitteeByField(String field);

    public ErasmusCommittee saveErasmusCommittee(ErasmusCommittee newErasmusCommittee);

    Collection<?> findErasmusCommitteeByField(String approved);
}
