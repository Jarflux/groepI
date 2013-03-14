package be.kdg.groepi.service;

import be.kdg.groepi.dao.RequirementInstanceDao;
import be.kdg.groepi.model.RequirementInstance;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author: Ben Oeyen
 * Date: 6/02/13
 * Class: Requirement Service
 * Description:
 */
@Service("requirementInstanceService")
@Transactional
public class RequirementInstanceService {

    @Autowired
    private RequirementInstanceDao requirementInstanceDoa;

    public RequirementInstance getRequirementInstanceById(long id) {
        return requirementInstanceDoa.getRequirementInstanceById(id);
    }

    public void createRequirementInstance(RequirementInstance requirementInstance) {
        requirementInstanceDoa.createRequirementInstance(requirementInstance);
    }

    public void updateRequirementInstance(RequirementInstance requirementInstance) {
        requirementInstanceDoa.updateRequirementInstance(requirementInstance);
    }

    public void deleteRequirementInstance(RequirementInstance requirementInstance) {
        requirementInstanceDoa.deleteRequirementInstance(requirementInstance);
    }

    public List<RequirementInstance> getAllRequirementInstances() {
        return requirementInstanceDoa.getAllRequirementInstances();
    }
}
