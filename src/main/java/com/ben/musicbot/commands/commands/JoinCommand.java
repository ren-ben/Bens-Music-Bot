package com.ben.musicbot.commands.commands;

import com.ben.musicbot.commands.types.IServerCommand;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.managers.AudioManager;

public class JoinCommand implements IServerCommand {
    @Override
    public void performCommand(String[] args, Guild guild, Member member, TextChannel textChannel, Message message) {

        final Member self = guild.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();


        assert selfVoiceState != null;
        if(selfVoiceState.inAudioChannel()) {
            textChannel.sendMessage("Already In A Voice Channel!").queue();
            return;
        }

        final GuildVoiceState memberVoiceState = member.getVoiceState();

        assert memberVoiceState != null;
        if(!memberVoiceState.inAudioChannel()) {
            textChannel.sendMessage("You need to be in a voice channel!").queue();
            return;
        }

        final AudioManager audioManager = guild.getAudioManager();
        AudioChannel memberChannel = memberVoiceState.getChannel();

        audioManager.openAudioConnection(memberChannel);
        assert memberChannel != null;
        textChannel.sendMessageFormat("Connecting to `\uD83D\uDD0A %s`", memberChannel.getName()).queue();
    }
}
