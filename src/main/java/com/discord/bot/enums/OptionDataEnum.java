package com.discord.bot.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.interactions.commands.OptionType;

@Getter
@RequiredArgsConstructor
public enum OptionDataEnum {

    SUM_OPTION_1(OptionType.INTEGER, "num1", "The number 1", true),

    SUM_OPTION_2(OptionType.INTEGER, "num2", "The number 2", true);

    private final OptionType optionType;

    private final String name;

    private final String description;

    private final Boolean isRequired;

}