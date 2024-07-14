package com.projectManagement.service;

import com.projectManagement.modal.Chat;
import com.projectManagement.modal.Message;
import com.projectManagement.modal.User;
import com.projectManagement.repository.MessageRepository;
import com.projectManagement.repository.ProjectRepository;
import com.projectManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectService projectService;


    @Override
    public Message sendMessage(Long senderId, Long projectId, String content) throws Exception {
        User sender = userRepository.findById(senderId)
                .orElseThrow(()-> new Exception("User not found with id: "+ senderId));
        Chat chat = projectService.getProjectById(projectId).getChat();

        Message message = new Message();
        message.setContent(content);
        message.setSender(sender);
        message.setCreateAt(LocalDateTime.now());
        message.setChat(chat);
        Message savedMessage = messageRepository.save(message);
        chat.getMessages().add(savedMessage);

        return savedMessage;
    }

    @Override
    public List<Message> getMessagesByProjectId(Long projectId) throws Exception {
        Chat chat = projectService.getChatByProjectId(projectId);
        List<Message>findByChatIdOrderByCreateAtAsc = messageRepository.findByChatIdOrderByCreateAtAsc(chat.getId());
        return findByChatIdOrderByCreateAtAsc;
    }
}
