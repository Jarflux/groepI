/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.groepi.dao;

import be.kdg.groepi.model.Answer;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 * @author: Ben Oeyen
 * @date: 7-mrt-2013
 */
public interface AnswerDao {

    public void createAnswer(Answer answer) throws DataAccessException;

    public void deleteAnswer(Answer answer) throws DataAccessException;

    public void updateAnswer(Answer answer) throws DataAccessException;

    public Answer getAnswerById(Long id) throws DataAccessException;

    public List<Answer> getAllAnswers() throws DataAccessException;

    public List<Answer> getAnswersByStopId(long id);
}
