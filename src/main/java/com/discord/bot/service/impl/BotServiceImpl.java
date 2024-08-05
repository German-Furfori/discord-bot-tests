package com.discord.bot.service.impl;

import com.discord.bot.dto.MessageDto;
import com.discord.bot.service.BotService;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BotServiceImpl implements BotService {

    private final JDA jda;

    @Override
    public void sendMessage(MessageDto message) {
        Guild discordJavaDevTests = jda.getGuildById(message.getGuildId());
        if (discordJavaDevTests != null) {
            TextChannel textChannel = discordJavaDevTests.getTextChannelById(message.getChannelId());
            if (textChannel != null) {
                textChannel.sendMessage(message.getMessage()).queue();
            }
        }
    }
}
