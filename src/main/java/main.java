import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.event.TS3Listener;
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.*;
import java.util.function.Supplier;

public class main {
    final public static TS3Config config = new TS3Config();
    public static TS3Query query;
    public static TS3Api api;

    public static void main(String[] args) throws InterruptedException {



        //connceting the bot
        Random rand = new Random();
        int randomNumber = rand.nextInt(100) + 1;
        String bot_name = "Bot" + randomNumber;
        load.conncet_local(bot_name);
        load.setup();

        PublicLogger.logger.info("Bot is online");


        mover.afkMover(false, null, 3, true, "╚Irgendwann wieder da");
        mover.afkMover(true, "Willkommen", 1, false, "╚Irgendwann wieder da");
        Monitoring.handleMessages();
    }

    // Hilfsmethode, um den Kanalnamen anhand der Kanal-ID abzurufen
    public static String getChannelNameById(int channelId) {
        List<Channel> channels = api.getChannels();
        for (Channel channel : channels) {
            if (channel.getId() == channelId) {
                return channel.getName();
            }
        }
        // Wenn kein Kanal mit der angegebenen ID gefunden wurde
        return null;
    }
}






