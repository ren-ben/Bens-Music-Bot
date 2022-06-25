package com.ben.musicbot.commands.commands;

import com.ben.musicbot.commands.types.IServerCommand;
import com.ben.musicbot.music.PlayerManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.Objects;

public class PlayCommand implements IServerCommand {
    @Override
    public void performCommand(String[] args, Guild guild, Member member, TextChannel textChannel, Message message) {
        if (!Objects.requireNonNull(guild.getSelfMember().getVoiceState()).inAudioChannel()) {
            textChannel.sendMessage("You need to be in a voice channel 🙃").queue();
            return;
        }

        if(!Objects.requireNonNull(member.getVoiceState()).inAudioChannel()) {
            textChannel.sendMessage("You need to be in the same voice channel 😤");
            return;
        }

        PlayerManager.getInstance().loadAndPlay(textChannel, "https://www.youtube.com/watch?v=5AtkWnBjGBU");
    }
}
