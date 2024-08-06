package com.discord.bot.listener;

import static com.discord.bot.enums.OptionDataEnum.SUM_OPTION_1;
import static com.discord.bot.enums.OptionDataEnum.SUM_OPTION_2;

import com.discord.bot.enums.CommandEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Listener extends ListenerAdapter {

    private static final Long DISCORD_JAVA_DEV_TESTS_ID = 1269727820821696656L;

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        Guild guild = event.getJDA().getGuildById(DISCORD_JAVA_DEV_TESTS_ID);
        if (guild != null) guild
                .upsertCommand(CommandEnum.SUM.getValue(), CommandEnum.SUM.getDescription())
                .addOptions(
                        new OptionData(SUM_OPTION_1.getOptionType(), SUM_OPTION_1.getName(), SUM_OPTION_1.getDescription(), SUM_OPTION_1.getIsRequired()),
                        new OptionData(SUM_OPTION_2.getOptionType(), SUM_OPTION_2.getName(), SUM_OPTION_2.getDescription(), SUM_OPTION_2.getIsRequired()))
                .queue();
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        String message = event.getMessage().getContentRaw();
        log.info("[Listener - onMessageReceived] Author: {}, Message: {}", event.getAuthor().getName(), message);
        MessageChannel messageChannel = event.getChannel();
        messageChannel.sendMessage(message).queue();
    }
}