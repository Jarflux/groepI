/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.groepi.service;

import be.kdg.groepi.dao.AnswerDao;
import be.kdg.groepi.model.Answer;
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
@Service("answerService")
@Transactional
public class AnswerService {

    @Autowired
    private AnswerDao answerDoa;

    public Answer getAnswerById(long id) {
        return answerDoa.getAnswerById(id);
    }

    public void createAnswer(Answer answer) {
        answerDoa.createAnswer(answer);
    }

    public void updateAnswer(Answer answer) {
        answerDoa.updateAnswer(answer);
    }

    public void deleteAnswer(Answer answer) {
        answerDoa.deleteAnswer(answer);
    }

    public List<Answer> getAllAnswers() {
        return answerDoa.getAllAnswers();
    }

    public List<Answer> getAnswersByStopID(long id) {
        return answerDoa.getAnswersByStopId(id);
    }
}
