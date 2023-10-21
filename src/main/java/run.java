import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3ApiAsync;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.wrapper.ServerGroup;

import java.util.List;

public class run {
    private static volatile int clientId;
    public static int SUPP_GROUP_ID = 4598207;
    public static int NEW_USER_GROUP_ID = 4290234;

    public static void loop(TS3Api api) {
        TS3Query query = Main.getQuery();
        // here all stuff that should run every time the bot is connected

        //Connecting the query with the specific profile
        setup.setLoginRef(api);

        //method for Monitoring
        Monitoring.baseMonitoring();
        Monitoring.handleMessages(api);

        //method for afk mover
        Mover.afkMover(false, null, 20, true, "╚Irgendwann wieder da", query);
        Mover.afkMover(true, "Willkommen", 1, false, "╚Irgendwann wieder da", query);

        //method for chatbot
        ChatBot.handleMessages(api);

        //method for user support
        Support.HandleSupportWithTimer(api.getChannelByNameExact("Support", true), SUPP_GROUP_ID, NEW_USER_GROUP_ID, api);

        //methods for event handler
        EventHandler.ClientConnect("Support", NEW_USER_GROUP_ID, api);

        clientId = api.whoAmI().getId();


    }

    public static void setup(TS3Api api) {
        //config logger

        PublicLogger.configLogging();
        //notify all CLients that bot is online
        //Main.MessageToAllClients("Clanbot ist online");

        //print all Channels with their ID
        List<ServerGroup> channelGroups = api.getServerGroups();
        for (ServerGroup group : channelGroups) {
            System.out.println("Gruppenname: " + group.getName() + ", Gruppen-ID: " + group.getId());
        }

    }

}
