package com.ben.musicbot.commands.commands;

import com.ben.musicbot.commands.types.IServerCommand;
import com.ben.musicbot.music.GuildMusicManager;
import com.ben.musicbot.music.PlayerManager;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.Objects;

public class NowPlayingCommand implements IServerCommand {
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



        GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(guild);
        AudioPlayer audioPlayer = musicManager.audioPlayer;

        AudioTrack playingTrack = audioPlayer.getPlayingTrack();
        if(playingTrack == null) {
            textChannel.sendMessage("Hmm...🧐 Doesn't seem like there's a Track Playing").queue();
            return;
        }

        AudioTrackInfo info = playingTrack.getInfo();
        textChannel.sendMessageFormat("Now Playing '%s' by '%s' (Link: <%s>)", info.title, info.author, info.uri).queue();
    }
}
