package events;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;

import java.util.Collection;
import java.util.function.Supplier;


public class sendMessage implements Listener {
    //Register variables
    final JavaPlugin plugin;
    TelegramBot bot ;
    String chatId ;

    public sendMessage(JavaPlugin plugin) {
        //Configure variables
        this.plugin = plugin;
        bot = new TelegramBot(plugin.getConfig().getString("Bot.Token"));
        chatId = plugin.getConfig().getString("Bot.Chat_Id");
    }


    @EventHandler
    public void ChatEvent(AsyncPlayerChatEvent e){
        //Get message(from player)
        String msg = e.getMessage();
        //Send message to telegram
        bot.execute(new SendMessage(chatId, "<" + e.getPlayer().getDisplayName() + ">" + msg));

    }
    @EventHandler
    public void Join(PlayerJoinEvent e){
        //Get join message
        String joinMessage = e.getJoinMessage();
        //Send join message to telegram
        bot.execute(new SendMessage(chatId, joinMessage));
    }
    @EventHandler
    public  void Leave(PlayerQuitEvent e){
        //Get leave message
        String leaveMessage = e.getQuitMessage();
        //Send leave message to telegram
        bot.execute(new SendMessage(chatId, leaveMessage));

    }

}
