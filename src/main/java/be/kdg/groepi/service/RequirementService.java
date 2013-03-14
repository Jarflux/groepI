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
    private RequirementDao requirementDao;

    public Requirement getRequirementById(long id) {
        return requirementDao.getRequirementById(id);
    }

    public void createRequirement(Requirement requirement) {
        requirementDao.createRequirement(requirement);
    }

    public void updateRequirement(Requirement requirement) {
        requirementDao.updateRequirement(requirement);
    }

    public void deleteRequirement(Requirement requirement) {
        requirementDao.deleteRequirement(requirement);
    }

    public List<Requirement> getAllRequirements() {
        return requirementDao.getAllRequirements();
    }
}
