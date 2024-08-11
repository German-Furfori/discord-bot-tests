package com.discord.bot.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import java.awt.*;
import java.util.Map;
import java.util.Random;

import static com.discord.bot.enums.CommandEnum.*;
import static com.discord.bot.enums.OptionDataEnum.SUM_OPTION_1;
import static com.discord.bot.enums.OptionDataEnum.SUM_OPTION_2;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommandManager extends ListenerAdapter {

    private final Random rand = new Random();

    private final Map<String, String> rpsResponse = Map.of("1", "Rock", "2", "Paper", "3", "Scissor");

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (SUM.getValue().equals(event.getName())) doSum(event);
        if (EMBED.getValue().equals(event.getName())) doEmbed(event);
        if (RPS.getValue().equals(event.getName())) doRpsEmbed(event);
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        Integer randomNumber = rand.nextInt(3) + 1;
        String response = rpsResponse.get(randomNumber.toString());
        if ("rock-button".equals(event.getButton().getId())) event.reply(response).queue();
        if ("paper-button".equals(event.getButton().getId())) event.reply(response).queue();
        if ("scissor-button".equals(event.getButton().getId())) event.reply(response).queue();
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
        embed.setDescription("Description of Embed ✌");
        embed.addField("Field 1", "value 1", true);
        embed.addField("Field 2", "value 2", true);
        embed.addField("Field 3", "value 3", true);
        embed.addField("Field 4", "value 4", true);
        embed.setImage("https://rickandmortyapi.com/api/character/avatar/1.jpeg");
        embed.setFooter("Footer");
        embed.setColor(Color.GREEN);
        event.replyEmbeds(embed.build()).queue();
    }

    private void doRpsEmbed(SlashCommandInteractionEvent event) {
        log.info("[CommandManager - doRpsEmbed]");
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Rock, paper, scissors");

        Button rockButton = Button.danger("rock-button", "✊");
        Button paperButton = Button.danger("paper-button", "✋");
        Button scissorButton = Button.danger("scissor-button", "✌");

        event.reply("")
                .addEmbeds(embed.build())
                .addActionRow(rockButton, paperButton, scissorButton)
                .queue();
    }

}
