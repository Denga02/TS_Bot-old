import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventType;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


public class load { ;
    private static final Logger LOGGER = Logger.getLogger("MyLogger");
    public static void main(String[] args) throws InterruptedException {

        //Standard Config üfr den Query Bot
        Config.config.setHost(variablen.gib.addresse); //10126
        Config.config.setQueryPort(variablen.query_ports.four_net_players);
        Config.query.connect();
        Config.api.login(variablen.gib.query_name, variablen.gib.query_pass);
        Config.api.selectVirtualServerByPort(variablen.gib.port);
        Config.api.setNickname("Clanbot");

        // Laden der verschiedenen Methoden und Klassen
        event.loadevent();
        ChatBot.loader();
        /*
        afk_mover.loader_afk(variablen.gib.afk);
        afk_mover.loader_eingang(variablen.gib.willkommen, variablen.gib.afk);
        */
        bot_connection.ping();
        conncetion_info.online();
        Support.loader(variablen.gib.support, variablen.gib.kommandant,variablen.gib.stellvertreter, variablen.gib.perso);
        Support.message(variablen.gib.support,variablen.gib.kommandant, variablen.gib.perso, variablen.gib.stellvertreter);
        afk_mover.loader_afk(variablen.gib.afk);
        afk_mover.loader_eingang(variablen.gib.willkommen, variablen.gib.afk);

        LOGGER.info("Bot ist online");




        while(true) {
            LOGGER.info("active");
            TimeUnit.SECONDS.sleep(10);


        }


        
    }
}



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

class Config
{
    public static final TS3Config config = new TS3Config();
    public static final TS3Query query = new TS3Query(config);
    public static final TS3Api api = query.getApi();


}





