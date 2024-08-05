package com.discord.bot.service;

import com.discord.bot.dto.MessageDto;

public interface BotService {

    void sendMessage(MessageDto message);

}
