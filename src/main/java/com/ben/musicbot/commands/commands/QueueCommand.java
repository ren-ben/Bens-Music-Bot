package com.ben.musicbot.commands.commands;

import com.ben.musicbot.commands.types.IServerCommand;
import com.ben.musicbot.music.GuildMusicManager;
import com.ben.musicbot.music.PlayerManager;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.restaction.MessageAction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class QueueCommand implements IServerCommand {
    @Override
    public void performCommand(String[] args, Guild guild, Member member, TextChannel textChannel, Message message) {
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(guild);
        BlockingQueue<AudioTrack> queue = musicManager.scheduler.queue;

        if(queue.isEmpty()) {
            textChannel.sendMessage("Seems like the Queue is Empty 🤯").queue();
            return;
        }

        final int trackCount = Math.min(queue.size(), 20);
        final List<AudioTrack> trackList = new ArrayList<>(queue);
        final MessageAction messageAction = textChannel.sendMessage("**Current Queue**\n");
        for (int i = 0; i < trackCount; i++) {
            final AudioTrack track = trackList.get(i);
            final AudioTrackInfo info = track.getInfo();

            messageAction.append('#')
                    .append(String.valueOf(i+1))
                    .append(" '")
                    .append(info.title)
                    .append("' by '")
                    .append(info.author)
                    .append("' ['")
                    .append(formatTime(track.getDuration()))
                    .append("']\n\n");
        }

        if (trackList.size() > trackCount) {
            messageAction.append("And ")
                    .append(String.valueOf(trackList.size() - trackCount))
                    .append(" more...");
        }

        messageAction.queue();
    }

    private String formatTime(long duration) {
        final long hours = duration / TimeUnit.HOURS.toMillis(1);
        final long minutes = duration / TimeUnit.MINUTES.toMillis(1);
        final long seconds = duration % TimeUnit.MINUTES.toMillis(1) / TimeUnit.SECONDS.toMillis(1);

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
