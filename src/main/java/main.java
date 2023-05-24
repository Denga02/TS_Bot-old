import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.event.TS3Listener;
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.*;
import java.util.function.Supplier;

import static java.lang.Thread.sleep;

public class main {
    final public static TS3Config config = new TS3Config();
    public static TS3Query query;
    public static TS3Api api;

    public static void main(String[] args) throws InterruptedException {

        //random bot because of reconncetion issues
        Random rand = new Random();
        int randomNumber = rand.nextInt(100) + 1;
        String bot_name = "Bot" + randomNumber;

        //loading bot with a specific authentification
        load.conncet_local(bot_name);

        //first setup, by starting the bot
        load.setup();

        for (int i = 0; i < 10; i++) {
            PublicLogger.logger.info("Test");
            Thread.sleep(1000);
        }


        // functions for Monitoring
        Monitoring.base_monitoring();
        Monitoring.handleMessages();

        //functions for afk mover
        mover.afkMover(false, null, 3, true, "╚Irgendwann wieder da");
        mover.afkMover(true, "Willkommen", 1, false, "╚Irgendwann wieder da");

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






