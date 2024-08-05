package com.discord.bot.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;

import static com.discord.bot.enums.CommandEnum.SUM;
import static com.discord.bot.enums.OptionDataEnum.SUM_OPTION_1;
import static com.discord.bot.enums.OptionDataEnum.SUM_OPTION_2;

public class SumCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (SUM.getValue().equals(event.getName())) doSum(event);

    }

    private void doSum(SlashCommandInteractionEvent event) {
        Integer num1 = event.getOption(SUM_OPTION_1.getName()).getAsInt();
        Integer num2 = event.getOption(SUM_OPTION_2.getName()).getAsInt();

        event.reply("Result: " + num1 * num2).queue();
    }
}