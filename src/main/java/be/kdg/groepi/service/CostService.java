package be.kdg.groepi.service;

import be.kdg.groepi.dao.CostDao;
import be.kdg.groepi.model.Cost;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author: Ben Oeyen
 * Date: 6/02/13
 * Class: Cost Service
 * Description:
 */
@Service("costService")
@Transactional
public class CostService {

    @Autowired
    private CostDao costDao;

    public Cost getCostById(long id) {
        return costDao.getCostById(id);
    }

    public void createCost(Cost cost) {
        costDao.createCost(cost);
    }

    public void updateCost(Cost cost) {
        costDao.updateCost(cost);
    }

    public void deleteCost(Cost cost) {
        costDao.deleteCost(cost);
    }

    public List<Cost> getAllCosts() {
        return costDao.getAllCosts();
    }
}
