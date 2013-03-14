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
    private MessageDao messageDoa;

    public Message getMessageById(long id) {
        return messageDoa.getMessageById(id);
    }

    public void createMessage(Message message) {
        messageDoa.createMessage(message);
    }

    public void updateMessage(Message message) {
        messageDoa.updateMessage(message);
    }

    public void deleteMessage(Message message) {
        messageDoa.deleteMessage(message);
    }

    public List<Message> getAllMessages() {
        return messageDoa.getAllMessages();
    }

    public List<Message> getMessagesByTripInstanceId(Long id) {
        return messageDoa.getMessagesByTripInstanceId(id);
    }
}
