package com.bots.botclity.ppfs_test;

import bot.bot;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import events.sendMessage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;

public final class PPFS_test extends JavaPlugin {
    //Register telegram bot (GitHub) and get chat id
    TelegramBot bot = new TelegramBot(this.getConfig().getString("Bot.Token"));
    String chatId = this.getConfig().getString("Bot.Chat_Id");
    private final JavaPlugin plugin = this;

    @Override
    public void onEnable() {
        //Check if there is a config.yml
        File config = new File(getDataFolder() + File.separator + "config.yml");
        if (!config.exists()){
            //Create config
            getLogger().info("Creating config...");
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
            this.getLogger().warning("Please configure config.yml");
            //Turn off plugin
            Bukkit.getPluginManager().disablePlugin(this);
        }

        //Send messages to telegram and server
        bot.execute(new SendMessage(chatId, "Server enabled"));
        Bukkit.getLogger().info("Plugin enabled.");
        //Register event listener
        Bukkit.getPluginManager().registerEvents(new sendMessage(plugin),this);


        //Register telegram bot(Api)
        bot bot = new bot(plugin);
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
        ////Send messages to telegram and server
        bot.execute(new SendMessage(chatId, "Server disabled"));
        Bukkit.getLogger().info("Plugin disabled");

    }
}
