package be.kdg.groepi.controller;

import be.kdg.groepi.model.TripInstance;
import be.kdg.groepi.service.TripInstanceService;
import be.kdg.groepi.utils.ExclusionStrategyUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 8-3-13
 * Time: 22:03
 * To change this template use File | Settings | File Templates.
 */
@Controller("restAndroidController")
@RequestMapping("android")
public class AndroidRestController {

    @Autowired
    protected TripInstanceService tripInstanceService;

    @RequestMapping(value = "/showUserTripParticipations/{userId}", method = RequestMethod.GET)
    public void getUserTripParticipations(@PathVariable(value = "userId") String userId, HttpServletResponse response) {
        try {
            List<Object[]> trips = tripInstanceService.getTripInstancesByUserId(Long.parseLong(userId));
            List<TripInstance> trips2= new ArrayList<TripInstance>();
            Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategyUtil()).serializeNulls().create();
            for (int i = 0; i < trips.size(); i++) {
                        TripInstance temp = (TripInstance) trips.get(i)[0];
                                        trips2.add(temp);
            }


                    String json = gson.toJson(trips2);
            response.getWriter().print(json);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
