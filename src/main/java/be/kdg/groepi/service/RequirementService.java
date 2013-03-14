package be.kdg.groepi.service;

import be.kdg.groepi.dao.RequirementDao;
import be.kdg.groepi.model.Requirement;
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
@Service("requirementService")
@Transactional
public class RequirementService {

    @Autowired
    private RequirementDao requirementDoa;

    public Requirement getRequirementById(long id) {
        return requirementDoa.getRequirementById(id);
    }

    public void createRequirement(Requirement requirement) {
        requirementDoa.createRequirement(requirement);
    }

    public void updateRequirement(Requirement requirement) {
        requirementDoa.updateRequirement(requirement);
    }

    public void deleteRequirement(Requirement requirement) {
        requirementDoa.deleteRequirement(requirement);
    }

    public List<Requirement> getAllRequirements() {
        return requirementDoa.getAllRequirements();
    }
}
