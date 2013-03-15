package be.kdg.groepi.service;

import be.kdg.groepi.dao.TripInstanceDao;
import be.kdg.groepi.model.TripInstance;
import be.kdg.groepi.utils.TripMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author: Ben Oeyen
 * Date: 6/02/13
 * Class: Trip Service
 * Description:
 */
@Service("tripInstanceService")
@Transactional
public class TripInstanceService {

    @Autowired
    private TripInstanceDao tripInstanceDao;

    public TripInstance getTripInstanceById(long id) {
        return tripInstanceDao.getTripInstanceById(id);
    }

    public void createTripInstance(TripInstance tripInstance) {
        tripInstanceDao.createTripInstance(tripInstance);
    }

    public void updateTripInstance(TripInstance tripInstance) {
        tripInstanceDao.updateTripInstance(tripInstance);
    }

    public void deleteTripInstance(TripInstance tripInstance) {
        tripInstanceDao.deleteTripInstance(tripInstance);
    }

    public List<TripInstance> getAllTripInstances() {
        return tripInstanceDao.getAllTripInstances();
    }

    public List<TripInstance> getAllTripInstancesByTripId(long tripId) {
        return tripInstanceDao.getAllTripInstancesByTripId(tripId);
    }

    public List<TripInstance> getTripInstancesByOrganiserId(long id) {
        return tripInstanceDao.getTripInstancesByOrganiserId(id);
    }

    public List<TripInstance> getPublicTripInstances() {
        return tripInstanceDao.getPublicTripInstances();
    }

    public List<Object[]> getTripInstancesByUserId(Long userId) {
        return tripInstanceDao.getTripInstancesByUserId(userId);
    }

    public void inviteByEmail(String receipients, String message, Long instanceId) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
        TripMail tim = (TripMail) context.getBean("tripMail");
        String[] receipient = receipients.split(",");
        for (int i = 0; i < receipient.length; i++) {
            tim.sendMail("info@trippie.be", receipient[i], "Je bent uitgenodigd op Trippie.be!",
                    "Ga naar http://tomcat.vincentverbist.be:8080/trip/view/" + instanceId + " om deel te nemen aan deze trip. \n"
                            + "De persoonlijke boodschap luidt: " + message
                            + "\n\n Bedankt! \nTrippe.be");
        }
    }
}