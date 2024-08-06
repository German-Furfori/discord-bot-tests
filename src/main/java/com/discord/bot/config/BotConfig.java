package com.discord.bot.config;

import com.discord.bot.command.SumCommand;
import com.discord.bot.listener.Listener;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BotConfig {

    @Value("${com.discord.bot.app.token}")
    private String botToken;

    private final Listener listener;

    private final SumCommand sumCommand;

    @Bean
    public JDA jda() throws Exception {
        JDA jda = JDABuilder.createDefault(botToken)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();
        jda.addEventListener(listener);
        jda.addEventListener(sumCommand);
        return jda;
    }

}
