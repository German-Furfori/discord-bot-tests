package com.discord.bot.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommandEnum {

    SUM("sum", "Gives the sum of two numbers");

    private final String value;

    private final String description;

}
