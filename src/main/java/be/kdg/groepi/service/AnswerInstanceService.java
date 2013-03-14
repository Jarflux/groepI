/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.groepi.service;

import be.kdg.groepi.dao.AnswerInstanceDao;
import be.kdg.groepi.model.AnswerInstance;
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
@Service("answerInstanceService")
@Transactional
public class AnswerInstanceService {

    @Autowired
    private AnswerInstanceDao answerInstanceDoa;

    public AnswerInstance getAnswerInstanceById(long id) {
        return answerInstanceDoa.getAnswerInstanceById(id);
    }

    public void createAnswerInstance(AnswerInstance answerInstance) {
        answerInstanceDoa.createAnswerInstance(answerInstance);
    }

    public void updateAnswerInstance(AnswerInstance answerInstance) {
        answerInstanceDoa.updateAnswerInstance(answerInstance);
    }

    public void deleteAnswerInstance(AnswerInstance answerInstance) {
        answerInstanceDoa.deleteAnswerInstance(answerInstance);
    }

    public List<AnswerInstance> getAllAnswerInstances() {
        return answerInstanceDoa.getAllAnswerInstances();
    }
}
