package com.ben.musicbot.commands.commands;

import com.ben.musicbot.commands.types.IServerCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class HelpCommand implements IServerCommand {
    @Override
    public void performCommand(String[] args, Guild guild, Member member, TextChannel textChannel, Message message) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Help is on the way! 🚑");
        embedBuilder.setColor(Color.BLACK);
        embedBuilder.setImage("https://i.imgur.com/ifdgPe1.png");
        textChannel.sendMessageEmbeds(embedBuilder.build()).queue();
    }
}
