import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.reconnect.ReconnectStrategy;
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import javax.sound.midi.Synthesizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Main {
    private static TS3Query query;
    private static final Logger logger = PublicLogger.getLogger();
    public static int SUPP_GROUP_ID = 4598207;
    public static int NEW_USER_GROUP_ID = 4290234;

    public static void main(String[] args) throws IOException {

        final TS3Config config = new TS3Config();
        setup.setConfigRef(config);

        query = new TS3Query(config);


        query.connect();

        run.setup(query.getApi());

        /*
        //Connecting the query with the specific profile
        Load.connectGib("Clanbot");
        //Load.conncetLocal("Test");

        //method for Monitoring
        Monitoring.baseMonitoring();
        Monitoring.handleMessages();

        //method for afk mover
        Mover.afkMover(false, null, 20, true, "╚Irgendwann wieder da");
        Mover.afkMover(true, "Willkommen", 1, false, "╚Irgendwann wieder da");

        //method for chatbot
        ChatBot.handleMessages();

        //method for user support
        Support.HandleSupportWithTimer(Main.api.getChannelByNameExact("Support", true), SUPP_GROUP_ID, NEW_USER_GROUP_ID);

        //methods for event handler
        EventHandler.ClientConnect("Support", NEW_USER_GROUP_ID);

         */
    }

    public static TS3Query getQuery() {
        return query;
    }




/*
###############################################################################################################################################################
####                                                        selbst gebaute Funktionen                                                                       ###
###############################################################################################################################################################

 */
//method to get channel name through channel ID
public static String getChannelNameById(int channelId, TS3Api api) {
    List<Channel> channels = api.getChannels();
    for (Channel channel : channels) {
        if (channel.getId() == channelId) {
            return channel.getName();
        }
    }
    //return null if the don´t find channel with this ID
    return null;
}

    public static void MessageToAllClients(String Message, TS3Api api) {

        //loop through all clients except the query
        for (Client c : api.getClients()) {
            if(!c.isServerQueryClient())
            {
                api.sendPrivateMessage(c.getId(),Message);
            }
        }
    }
    public static boolean CheckClientsInGroup(int groupId, TS3Api api) {

        for (Client c : api.getClients()) {
            if (c.isInServerGroup(groupId)) {
                return true;
            }
        }
        return false;
    }
    public static List<Client> getClientsFromSpecificGroup(int groupId, TS3Api api) {
        List<Client> list = new ArrayList<>();

        for (Client c : api.getClients()) {
            if (c.isInServerGroup(groupId)) {
                list.add(c);
            }
        }
        return list;
    }
    public static boolean CheckUserIsinChannel(int channelId, TS3Api api) {
        for ( Client c : api.getClients()) {
            if(c.getChannelId() == channelId) {
                return true;
            }
        }
        return false;
    }

    public static void PokeClients(int groupId, String message, TS3Api api) {
        for (Client c : Main.getClientsFromSpecificGroup(groupId, api)) {
            api.pokeClient(c.getId(), message);
            logger.info("Support: " + message + " wurde an " + c.getNickname() + " gesendet");
        }
    }
}






