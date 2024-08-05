package com.discord.bot.controller;

import com.discord.bot.dto.MessageDto;
import com.discord.bot.service.BotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/bot")
public class BotController {

    private final BotService botService;

    @PostMapping(path = "/message", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void sendMessage(@RequestBody MessageDto messageDto) {
        log.info("[BotController - sendMessage] Request: [{}]", messageDto);
        botService.sendMessage(messageDto);
    }

}
