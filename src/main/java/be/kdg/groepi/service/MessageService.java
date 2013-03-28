package be.kdg.groepi.service;

import be.kdg.groepi.dao.MessageDao;
import be.kdg.groepi.model.Message;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author: Ben Oeyen
 * Date: 6/02/13
 * Class: Message Service
 * Description:
 */
@Service("messageService")
@Transactional
public class MessageService {

    @Autowired
    private MessageDao messageDao;

    public Message getMessageById(long id) {
        return messageDao.getMessageById(id);
    }

    public void createMessage(Message message) {
        messageDao.createMessage(message);
    }

    public void updateMessage(Message message) {
        messageDao.updateMessage(message);
    }

    public void deleteMessage(Message message) {
        messageDao.deleteMessage(message);
    }

    public List<Message> getAllMessages() {
        return messageDao.getAllMessages();
    }

    public List<Message> getLastTenMessages(){
        return messageDao.getLastTenMessages();
    }

    public List<Message> getMessagesByTripInstanceId(Long id) {
        return messageDao.getMessagesByTripInstanceId(id);
    }
}
