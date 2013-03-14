/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.groepi.dao;

import be.kdg.groepi.model.AnswerInstance;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 * @author: Ben Oeyen
 * @date: 7-mrt-2013
 */
public interface AnswerInstanceDao {

    public void createAnswerInstance(AnswerInstance user) throws DataAccessException;

    public void deleteAnswerInstance(AnswerInstance user) throws DataAccessException;

    public void updateAnswerInstance(AnswerInstance user) throws DataAccessException;

    public AnswerInstance getAnswerInstanceById(Long id) throws DataAccessException;

    public List<AnswerInstance> getAllAnswerInstances() throws DataAccessException;
}