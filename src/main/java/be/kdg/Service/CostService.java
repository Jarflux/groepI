package be.kdg.Service;

import be.kdg.Model.Cost;

import javax.persistence.*;
import java.util.List;

/**
 * Author: Ben Oeyen
 * Date: 6/02/13
 * Class: Cost Service
 * Description:
 */

@Entity
@Table(name = "T_COST")
public class CostService {
    public static Cost getCostById(long Id) {
        return null;
    }

    public static Cost createCost(Cost  cost) {
        return null;
    }

    public static Cost updateCost(Cost cost){
        return null;
    }

    public static Cost deleteCost(Cost cost) {
        return null;
    }

    public static List<Cost> getAllCosts() {
        return null;
    }
}
