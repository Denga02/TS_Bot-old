import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = PublicLogger.getLogger();
    final public static TS3Config config = new TS3Config();
    public static TS3Query query;
    public static TS3Api api;
    public static int SUPP_GROUP_ID = 4598207;
    public static int NEW_USER_GROUP_ID = 4290234;

    public static void main(String[] args) throws IOException {

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
        Support.load(api.getChannelByNameExact("Support", false).getId(), SUPP_GROUP_ID);

        //methods for event handler
        EventHandler.ClientConnect("Support", NEW_USER_GROUP_ID);
    }

    //method to get channel name through channel ID
    public static String getChannelNameById(int channelId) {
        List<Channel> channels = api.getChannels();
        for (Channel channel : channels) {
            if (channel.getId() == channelId) {
                return channel.getName();
            }
        }
        //return null if the don´t find channel with this ID
        return null;
    }

    public static void MessageToAllClients(String Message) {

        //loop through all clients except the query
        for (Client c : Main.api.getClients()) {
            if(!c.isServerQueryClient())
            {
                Main.api.sendPrivateMessage(c.getId(),Message);
            }
        }
    }
    public static boolean CheckClientsInGroup(int groupId) {

        for (Client c : Main.api.getClients()) {
            if (c.isInServerGroup(groupId)) {
                return true;
            }
        }
        return false;
    }
    public static List<Client> getClientsFromSpecificGroup(int groupId) {
        List<Client> list = new ArrayList<>();

        for (Client c : Main.api.getClients()) {
            if (c.isInServerGroup(groupId)) {
                list.add(c);
            }
        }
        return list;
    }
}






