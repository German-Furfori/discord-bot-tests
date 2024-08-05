package com.discord.bot.dto;

import lombok.Data;

@Data
public class MessageDto {

    private Long guildId;

    private Long channelId;

    private String message;

}
