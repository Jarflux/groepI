package be.kdg.groepi.controller;

import be.kdg.groepi.model.Trip;
import be.kdg.groepi.model.TripInstance;
import be.kdg.groepi.service.TripInstanceService;
import be.kdg.groepi.utils.ExclusionStrategyUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mitch Va Daele
 * Date: 8-3-13
 * Time: 22:03
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("android")
public class AndroidRestController {
    @RequestMapping(value = "/showUserTripParticipations/{userId}", method = RequestMethod.GET)
    public void getUserTripParticipations(@PathVariable(value = "userId") String userId, HttpServletResponse response) {
        try {
            List<TripInstance> trips = TripInstanceService.getTripInstancesByUserId(Long.parseLong(userId));
            Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategyUtil()).serializeNulls().create();
            String json = gson.toJson(trips);
            response.getWriter().print(json);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
