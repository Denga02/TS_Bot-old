import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;
import com.github.theholywaffle.teamspeak3.api.wrapper.ServerGroup;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class Main {
    final public static TS3Config config = new TS3Config();
    public static TS3Query query;
    public static TS3Api api;
    private static final Logger logger = PublicLogger.getLogger();

    public static void main(String[] args) throws InterruptedException {
        //random bot because of reconnection issues
        Random rand = new Random();
        int randomNumber = rand.nextInt(100) + 1;
        String nickname = "Bot" + randomNumber;

        //loading bot with a specific authentication
        Load.conncetLocal(nickname);

        //first setup, by starting the bot
        Load.setup();

        List<ServerGroup> channelGroups = Main.api.getServerGroups();

        for (ServerGroup group : channelGroups) {
            System.out.println("Gruppenname: " + group.getName() + ", Gruppen-ID: " + group.getId());
        }

        //method for Monitoring
        Monitoring.baseMonitoring();
        Monitoring.handleMessages();

        //method for afk mover
        Mover.afkMover(false, null, 3, true, "╚Irgendwann wieder da");
        Mover.afkMover(true, "Willkommen", 1, false, "╚Irgendwann wieder da");

        //method for chatbot
        ChatBot.handleMessages();

        //method for user support
        int [] serverGroupId = {7, 4, 3};
        Support.load(api.getChannelByNameExact("Support", false).getId(), serverGroupId);

        //methods for event handler
        EventHandler.notifyOnJoin("Support", 9);
        //EventHandler.ClientJoinServer("Support", 9);
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
}





