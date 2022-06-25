package com.ben.musicbot;

import com.ben.musicbot.commands.CommandManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class MusicBot {

    public static void main(String[] args) throws LoginException {

        JDA jda = JDABuilder.createDefault(Token.token)
        .setStatus(OnlineStatus.ONLINE)
        .setActivity(Activity.listening("🥝")).build();
        jda.addEventListener(new CommandManager());
    }
}
