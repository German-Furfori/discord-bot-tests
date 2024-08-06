package com.discord.bot.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.awt.*;

import static com.discord.bot.enums.CommandEnum.EMBED;
import static com.discord.bot.enums.CommandEnum.SUM;
import static com.discord.bot.enums.OptionDataEnum.SUM_OPTION_1;
import static com.discord.bot.enums.OptionDataEnum.SUM_OPTION_2;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommandManager extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (SUM.getValue().equals(event.getName())) doSum(event);
        if (EMBED.getValue().equals(event.getName())) doEmbed(event);
    }

    private void doSum(SlashCommandInteractionEvent event) {
        log.info("[CommandManager - doSum]");
        Integer num1 = event.getOption(SUM_OPTION_1.getName()).getAsInt();
        Integer num2 = event.getOption(SUM_OPTION_2.getName()).getAsInt();

        event.reply("Result: " + num1 * num2).queue();
    }

    private void doEmbed(SlashCommandInteractionEvent event) {
        log.info("[CommandManager - doEmbed]");
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Title of Embed");
        embed.setDescription("Description of Embed");
        embed.addField("Field 1", "value 1", true);
        embed.addField("Field 2", "value 2", true);
        embed.addField("Field 3", "value 3", true);
        embed.addField("Field 4", "value 4", true);
        embed.setImage("https://rickandmortyapi.com/api/character/avatar/1.jpeg");
        embed.setFooter("Footer");
        embed.setColor(Color.GREEN);
        event.replyEmbeds(embed.build()).queue();
    }

}
