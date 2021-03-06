package com.ben.musicbot.commands.commands;

import com.ben.musicbot.commands.types.IServerCommand;
import com.ben.musicbot.music.PlayerManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class PlayCommand implements IServerCommand {
    @Override
    public void performCommand(String[] args, Guild guild, Member member, TextChannel textChannel, Message message) {


        if (!Objects.requireNonNull(guild.getSelfMember().getVoiceState()).inAudioChannel()) {
            textChannel.sendMessage("You need to be in a voice channel 🙃").queue();
            return;
        }

        if (!Objects.requireNonNull(member.getVoiceState()).inAudioChannel()) {
            textChannel.sendMessage("You need to be in the same voice channel 😤");
            return;
        }

        String link = args[1];

        if (!isUrl(args[1])) {
            link = "ytsearch:" + link;
        }

        PlayerManager.getInstance().loadAndPlay(textChannel, link);

    }

    private boolean isUrl(String url) {
        try {
            new URI(url);
            return true;
        } catch (URISyntaxException e) {
            return false;
        }
    }
    }
