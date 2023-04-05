import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventType;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;
import com.github.theholywaffle.teamspeak3.api.wrapper.ServerGroup;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class load {
    // connection and query values
    public static String ADD_GIB = "62.104.20.81";
    public static String ADD_LOCAL = "127.0.0.1";

    public static int PORT_GIB = 10126;

    public static String QUERRY_NAME_GIB = "dannybot";
    public static String QUERRY_PASS_GIB= "OTWcNvID";
    public static String QUERRY_NAME_LCOAL = "serveradmin";
    public static String QUERRY_PASS_LCOAL = "RXuslTQ+";

    public static int PORT_4_NET_PLAYERS = 11200;
    public static int PORT_STANDARD_QUERY = 10011;

    // unic ID of all important clients
    private static Object[] LEADER_UID = new Object[] {
            "test", "OL5KWACbqz8EnaM0rxTHr+Wrqs0=",
            "stefan", "7W1wETArO8qz/l3JRUwKhv9+KqU=",
            "danny", "5zhq99hN4UhkqB4pMIeStd8SDf4="
    };

    final public static TS3Config config = new TS3Config();
    final public static TS3Query query = new TS3Query(config);
    final public static TS3Api api = query.getApi();

    public static void main(String[] args) throws InterruptedException {
        // config logger
        PublicLogger.configLogging();
        //config and connection
        config.setHost(ADD_GIB); //10126
        config.setQueryPort(PORT_4_NET_PLAYERS);
        query.connect();
        api.login(QUERRY_NAME_GIB, QUERRY_PASS_GIB);
        api.selectVirtualServerByPort(PORT_GIB);
        api.setNickname("Clanbot_Test1");

        // gather Information
        //
        // get all channel ids
        for (Channel channel : map.Channel_List) {
            String name = channel.getName();
            int id = channel.getId();
            map.Channel_ID.put(name, id);
        }

        // get all Channel Server Group and Ids
        for (ServerGroup serverGroup : map.server_Groups) {
            map.Server_Group_ID_Map.put(serverGroup.getName(), serverGroup.getId());
        }

        // get all online players unic ID
        for (Client client : map.online_clients) {
            String name = client.getNickname();
            String uniqueId = client.getUniqueIdentifier();
            map.online_client_UID_Map.put(name, uniqueId);
        }

        // notify all users that bot is online
        PublicLogger.logger.info("bot is online");
        PublicLogger.logger.info(String.format("players online: %s", String.join(", ", map.online_client_UID_Map.keySet())));

        for (String  player_name : map.online_client_UID_Map.keySet()) {
            Client client = api.getClientByNameExact(player_name, false);
            if (client != null) {
                //api.sendPrivateMessage(client.getId(), "Der Bot ist online.");
            }
        }

        mover.room_afk("╚Irgendwann wieder da");
        mover.room_welcome("Willkommen", "╚Irgendwann wieder da");

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                PublicLogger.logger.info("is active");
            }
        }, 60*1000, 5*60*1000);


        // laods all methods
        //event.loadevent();
        //ChatBot.loader();
        //bot_connection.ping();
        //conncetion_info.online();
        //Support.loader(variablen.gib.support, variablen.gib.kommandant,variablen.gib.stellvertreter, variablen.gib.perso);
        //Support.message(variablen.gib.support,variablen.gib.kommandant, variablen.gib.perso, variablen.gib.stellvertreter);

        //query.exit();

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






