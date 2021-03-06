package com.ben.musicbot.commands;

import com.ben.musicbot.commands.commands.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;

public class CommandManager extends ListenerAdapter {

    private final JoinCommand joinCommand;
    private final PlayCommand playCommand;
    private final StopCommand stopCommand;
    private final SkipCommand skipCommand;
    private final NowPlayingCommand nowPlayingCommand;
    private final QueueCommand queueCommand;
    private final RepeatCommand repeatCommand;
    private final LeaveCommand leaveCommand;
    private final HelpCommand helpCommand;

    public CommandManager() {
        this.joinCommand = new JoinCommand();
        this.playCommand = new PlayCommand();
        this.stopCommand = new StopCommand();
        this.skipCommand = new SkipCommand();
        this.nowPlayingCommand = new NowPlayingCommand();
        this.queueCommand = new QueueCommand();
        this.repeatCommand = new RepeatCommand();
        this.leaveCommand = new LeaveCommand();
        this.helpCommand = new HelpCommand();
    }
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if(!Objects.requireNonNull(event.getMember()).getUser().isBot()) {
            String[] args = event.getMessage().getContentRaw().split(" ");

            Guild guild = event.getGuild();
            Member member = event.getMember();
            TextChannel tc = event.getTextChannel();
            Message msg = event.getMessage();

            if ("!join".equals(args[0])) {
                joinCommand.performCommand(args, guild, member, tc, msg);
            } else if ("!play".equals(args[0])) {
                playCommand.performCommand(args,guild,member,tc,msg);
            } else if ("!stop".equals(args[0])) {
                stopCommand.performCommand(args,guild,member,tc,msg);
            } else if ("!skip".equals(args[0])) {
                skipCommand.performCommand(args,guild,member,tc,msg);
            } else if ("!nowplaying".equals(args[0])) {
                nowPlayingCommand.performCommand(args,guild,member,tc,msg);
            } else if ("!queue".equals(args[0])) {
                queueCommand.performCommand(args,guild,member,tc,msg);
            } else if ("!repeat".equals(args[0])) {
                repeatCommand.performCommand(args,guild,member,tc,msg);
            } else if ("!leave".equals(args[0])) {
                leaveCommand.performCommand(args,guild,member,tc,msg);
            } else if ("!help".equals(args[0])) {
                helpCommand.performCommand(args,guild,member,tc,msg);
            }
        }
    }
}
