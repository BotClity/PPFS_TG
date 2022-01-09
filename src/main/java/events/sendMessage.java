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
    final JavaPlugin plugin;
    TelegramBot bot ;
    String chatId ;

    public sendMessage(JavaPlugin plugin) {
        this.plugin = plugin;
        bot = new TelegramBot(plugin.getConfig().getString("Bot.Token"));
        chatId = plugin.getConfig().getString("Bot.Chat_Id");
    }


    @EventHandler
    public void ChatEvent(AsyncPlayerChatEvent e){
        String msg = e.getMessage();

        bot.execute(new SendMessage(chatId, "<" + e.getPlayer().getDisplayName() + ">" + msg));

    }
    @EventHandler
    public void Join(PlayerJoinEvent e){
        String joinMessage = e.getJoinMessage();
        bot.execute(new SendMessage(chatId, joinMessage));
    }
    @EventHandler
    public  void Leave(PlayerQuitEvent e){
        String leaveMessage = e.getQuitMessage();
        bot.execute(new SendMessage(chatId, leaveMessage));

    }

}
