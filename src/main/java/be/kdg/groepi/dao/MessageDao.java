/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kdg.groepi.dao;

import be.kdg.groepi.model.Message;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 * @author: Ben Oeyen
 * @date: 7-mrt-2013
 */
public interface MessageDao {

    public void createMessage(Message message) throws DataAccessException;

    public void deleteMessage(Message message) throws DataAccessException;

    public void updateMessage(Message message) throws DataAccessException;

    public Message getMessageById(Long id) throws DataAccessException;

    public List<Message> getAllMessages() throws DataAccessException;

    public List<Message> getMessagesByTripInstanceId(Long id) throws DataAccessException;

    public List<Message> getLastTenMessages() throws DataAccessException;
}