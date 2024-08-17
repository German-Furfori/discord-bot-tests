package com.discord.bot.service.impl;

import com.discord.bot.dto.MessageDto;
import com.discord.bot.service.BotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class BotServiceImpl implements BotService {

    private final JDA jda;

    private boolean enableRemindMe = true;

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

    @Scheduled(fixedDelay = 2000)
    private void remindMe() {
        if (!enableRemindMe) {
            return;
        }

        Instant inst = Instant.now();
        LocalDateTime dateTime = LocalDateTime.ofInstant(inst, ZoneId.of("UTC-3"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedDateTime = dateTime.format(formatter);

        log.info("[BotService - remindMe] Time: {}", formattedDateTime);
        Guild discordJavaDevTests = jda.getGuildById(1269727820821696656L);
        if (discordJavaDevTests != null) {
            TextChannel textChannel = discordJavaDevTests.getTextChannelById(1270036386551955529L);
            if (textChannel != null) {
                textChannel.sendMessage(formattedDateTime).queue();
            }
        }
    }

    @Scheduled(cron = "0 * * * * MON-SUN")
    private void enableOrDisableSchedule() {
        this.enableRemindMe = !this.enableRemindMe;
    }

}
