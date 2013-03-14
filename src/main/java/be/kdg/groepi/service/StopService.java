/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.groepi.service;

import be.kdg.groepi.dao.StopDao;
import be.kdg.groepi.model.Stop;
import be.kdg.groepi.utils.VuforiaUtil;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("stopService")
@Transactional
public class StopService {

    @Autowired
    private StopDao stopDoa;

    public Stop getStopById(long id) {
        return stopDoa.getStopById(id);
    }

    public void createStop(Stop stop) {
        stopDoa.createStop(stop);
    }

    public void updateStop(Stop stop) {
        stopDoa.updateStop(stop);
    }

    public void deleteStop(Stop stop) {
        stopDoa.deleteStop(stop);
    }

    public List<Stop> getAllStops() {
        return stopDoa.getAllStops();
    }

    public List<Stop> getAllTripStopsByTripId(Long tripId) {
        return stopDoa.getAllTripStopsByTripId(tripId);
    }

    public static void addToVuforia(Long stopid, File image) throws JSONException, IOException, URISyntaxException {
        VuforiaUtil.postTarget(image, stopid);
    }
}
