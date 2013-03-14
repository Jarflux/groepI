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
    private AnswerDao answerDao;

    public Answer getAnswerById(long id) {
        return answerDao.getAnswerById(id);
    }

    public void createAnswer(Answer answer) {
        answerDao.createAnswer(answer);
    }

    public void updateAnswer(Answer answer) {
        answerDao.updateAnswer(answer);
    }

    public void deleteAnswer(Answer answer) {
        answerDao.deleteAnswer(answer);
    }

    public List<Answer> getAllAnswers() {
        return answerDao.getAllAnswers();
    }

    public List<Answer> getAnswersByStopID(long id) {
        return answerDao.getAnswersByStopId(id);
    }
}
