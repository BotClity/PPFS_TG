package bot;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class bot extends TelegramLongPollingBot {
    //Register variables
    final JavaPlugin plugin;
    final String token ;
    final String name;

    public bot(JavaPlugin plg) {
        //Configure variables
        plugin = plg;
        token = plugin.getConfig().getString("Bot.Token");
        name = plugin.getConfig().getString("Bot.Name");
    }


    @Override
    public void onUpdateReceived(Update update) {
        //Check update has a message
        if (update.hasMessage()){
            Message message = update.getMessage();
            long get_id = message.getChatId();
            String chat_id = plugin.getConfig().getString("Bot.Chat_Id");
            long id = Long.parseLong(chat_id);
            //Check id (update) == id(telegram chat id)
            if (get_id == id){
                //Check message has a text
                if(message.hasText()){
                    //Get and check User(who send message)
                    if (message.getFrom().getUserName() != null) {
                        Bukkit.broadcastMessage("<" + message.getFrom().getUserName() + "> " + message.getText());
                    }else{
                        Bukkit.getLogger().warning("Don`t find user");
                    }

                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onRegister() {
        //Send log when bot started
        Bukkit.getLogger().info("bot started");
    }
}
