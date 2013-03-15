package be.kdg.groepi.controller;

import be.kdg.groepi.model.Answer;
import be.kdg.groepi.model.Stop;
import be.kdg.groepi.service.AnswerService;
import be.kdg.groepi.service.StopService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("restAnswerController")
@RequestMapping("answer")
public class RestAnswerController {
    private static final Logger logger = Logger.getLogger(RestAnswerController.class);

    @Autowired
    protected StopService stopService;
    @Autowired
    protected AnswerService answerService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createAnswer(@RequestParam String answer, @RequestParam String stopId) {
        logger.debug("RestAnswerController: createAnswer");
        Stop stop = stopService.getStopById(Long.parseLong(stopId));
        stop.getAnswers().add(new Answer(answer, false, stop));
        stopService.updateStop(stop);
        return new ModelAndView("trips/editstop", "stopObject", stop);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteAnswer(@RequestParam(value = "answerId") String answerId) {
        logger.debug("RestAnswerController: deleteAnswer");
        Answer answer = answerService.getAnswerById(Long.parseLong(answerId));
        answerService.deleteAnswer(answer);
        if (answerService.getAnswerById(Long.parseLong(answerId)) == null) {
            return "fail";
        } else {
            return "succes";
        }

    }
}
