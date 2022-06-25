package com.ben.musicbot;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class MusicBot {

    private final ShardManager shardManager;

    public MusicBot() throws LoginException {
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(Token.token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.watching("( * ) ( * )"));
        shardManager = builder.build();
    }

    public ShardManager getShardManager() {
        return shardManager;
    }

    public static void main(String[] args) {
        try {
            MusicBot bot = new MusicBot();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
