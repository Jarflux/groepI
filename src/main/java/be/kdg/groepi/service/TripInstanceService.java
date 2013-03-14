package be.kdg.groepi.service;

import be.kdg.groepi.dao.TripInstanceDao;
import be.kdg.groepi.model.TripInstance;
import be.kdg.groepi.utils.TripMail;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private TripInstanceDao tripInstanceDoa;

    public TripInstance getTripInstanceById(long id) {
        return tripInstanceDoa.getTripInstanceById(id);
    }

    public void createTripInstance(TripInstance tripInstance) {
        tripInstanceDoa.createTripInstance(tripInstance);
    }

    public void updateTripInstance(TripInstance tripInstance) {
        tripInstanceDoa.updateTripInstance(tripInstance);
    }

    public void deleteTripInstance(TripInstance tripInstance) {
        tripInstanceDoa.deleteTripInstance(tripInstance);
    }

    public List<TripInstance> getAllTripInstances() {
        return tripInstanceDoa.getAllTripInstances();
    }

    public List<TripInstance> getAllTripInstancesByTripId(long tripId) {
        return tripInstanceDoa.getAllTripInstancesByTripId(tripId);
    }

    public List<TripInstance> getTripInstancesByOrganiserId(long id) {
        return tripInstanceDoa.getTripInstancesByOrganiserId(id);
    }

    public List<TripInstance> getPublicTripInstances() {
        return tripInstanceDoa.getPublicTripInstances();
    }

    public List<TripInstance> getTripInstancesByUserId(Long userId) {
        return tripInstanceDoa.getTripInstancesByUserId(userId);
    }

    public static void inviteByEmail(String receipients, String message, Long instanceId) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
        TripMail tim = (TripMail) context.getBean("tripMail");
        String[] receipient = receipients.split(",");
        for (int i = 0; i < receipient.length; i++) {
            tim.sendMail("info@trippie.be", receipient[i], "Je bent uitgenodigd op Trippie.be!",
                    "Ga naar http://tomcat.vincentverbist.be:8080/trips/viewinstance/" + instanceId + " om deel te nemen aan deze trip. \n"
                    + "De persoonlijke boodschap luidt: " + message
                    + "\n\n Bedankt! \nTrippe.be");
        }
    }
}