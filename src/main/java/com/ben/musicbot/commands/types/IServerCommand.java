package com.ben.musicbot.commands.types;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public interface IServerCommand {
    void performCommand(String[] args, Guild guild, Member member, TextChannel textChannel, Message message);
}
