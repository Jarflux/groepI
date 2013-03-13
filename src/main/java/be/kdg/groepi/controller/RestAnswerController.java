package be.kdg.groepi.controller;

import be.kdg.groepi.model.Answer;
import be.kdg.groepi.model.Stop;
import be.kdg.groepi.service.AnswerService;
import be.kdg.groepi.service.StopService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RestAnswerController {

    @RequestMapping(value = "/trips/createAnswer", method = RequestMethod.POST)
    public ModelAndView createAnswer(@RequestParam String answer, @RequestParam String stopId) {
        Stop stop = StopService.getStopById(Long.parseLong(stopId));
        stop.getAnswers().add(new Answer(answer, false, stop));
        StopService.updateStop(stop);
        return new ModelAndView("trips/editstop", "stopObject", stop);
    }

    @RequestMapping(value = "/trips/deleteAnswer", method = RequestMethod.POST)
    public String deleteAnswer(@RequestParam(value = "answerId") String answerId) {
        Answer answer = AnswerService.getAnswerById(Long.parseLong(answerId));
        AnswerService.deleteAnswer(answer);
        if (AnswerService.getAnswerById(Long.parseLong(answerId)) == null) {
            return "fail";
        } else {
            return "succes";
        }

    }
}
