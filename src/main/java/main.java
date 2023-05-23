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
    final public static TS3Query query = new TS3Query(config);
    final public static TS3Api api = query.getApi();

    public static void main(String[] args) throws InterruptedException {

        //connceting the bot
        Random rand = new Random();
        int randomNumber = rand.nextInt(100) + 1;
        String bot_name = "Bot" + randomNumber;
        load.conncet_local(bot_name);
        load.setup();

        PublicLogger.logger.info("Bot is online");
        api.moveClient(api.getClientByNameExact("Test", false),api.getChannelByNameExact("Default Channel", false));
        api.moveClient(api.getClientByNameExact("Test", false),api.getChannelByNameExact("Willkommen", false));

        mover.afkMover(false, null, 3, true, "╚Irgendwann wieder da");
        mover.afkMover(true, "Willkommen", 1, false, "╚Irgendwann wieder da");
        //ChatBot.loader();
        //event.loadevent();
        //ChatBot.loader();
        //bot_connection.ping();
        //conncetion_info.online();
        //Support.loader(variablen.gib.support, variablen.gib.kommandant,variablen.gib.stellvertreter, variablen.gib.perso);
        //Support.message(variablen.gib.support,variablen.gib.kommandant, variablen.gib.perso, variablen.gib.stellvertreter);

        //query.exit();

    }

    // Hilfsmethode, um den Kanalnamen anhand der Kanal-ID abzurufen
    public static String getChannelNameById(int channelId) {
        List<Channel> channels = api.getChannels();
        for (Channel channel : channels) {
            if (channel.getId() == channelId) {
                return channel.getName();
            }
        }
        return null; // Wenn kein Kanal mit der angegebenen ID gefunden wurde
    }
}





/*
class bot_connection
{
    // get ID from bot
    final static int clientId = Config.api.whoAmI().getId();

    static void ping () {
        //add new Event
        Config.api.registerEvent(TS3EventType.TEXT_SERVER);
        Config.api.addTS3Listeners(new TS3EventAdapter() {
            @Override
            public void onTextMessage(TextMessageEvent e) {
                super.onTextMessage(e);

                if (e.getTargetMode() == TextMessageTargetMode.SERVER && e.getInvokerId() != clientId) {
                    Client c = Config.api.getClientInfo(e.getInvokerId());
                    int channel = c.getChannelId();

                    String message = e.getMessage().toLowerCase();

                    if (message.equals("!ping")) {
                        // Answer "!ping" with "pong"
                        Config.api.moveClient(clientId, channel);
                        Config.api.sendPrivateMessage(e.getInvokerId(), "pong");
                    }
                }
            }
        });
    }


};

class conncetion_info {
    static void online ()
    {
        List<Channel> channels = Config.api.getChannels();
        Map<Integer, Channel> channelMap = new HashMap<>(channels.size());

        for (Channel channel : channels) {
            channelMap.put(channel.getId(), channel);
            //Channel ID ausgeben
            //System.out.println(channel.getName() + "    "  + channel.getId());
        }

        for (Client c : Config.api.getClients()) {
            // Get the client's channel
            Channel channel = channelMap.get(c.getChannelId());

            // Write the client and channel name into the console
            System.out.println(c.getNickname() + " in channel " + channel.getName());
            Config.api.sendPrivateMessage(c.getId(), "Bot ist online");
            //Config.api.pokeClient(c.getId(), "Updates sind fertig, schreibe den Bot !patchnotes um die Veränderungen zu sehen");

            //System.out.println(x);
        }
    }

};

 */






