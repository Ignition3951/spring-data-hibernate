package com.utk.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.utk.model.Message;
import com.utk.repositories.MessageRepository;

@RestController
public class MessageController {

	private final MessageRepository messageRepository;

	public MessageController(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	@PostMapping("/messages")
	public void saveMessages(@RequestBody Message message) {
		messageRepository.save(message);
	}
}
