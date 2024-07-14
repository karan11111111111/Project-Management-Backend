package com.projectManagement.service;

import com.projectManagement.modal.Chat;
import com.projectManagement.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService{
    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }
}
