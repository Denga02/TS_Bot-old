import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventType;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.logging.Logger;

public class EventHandler {

    private static final Logger logger = PublicLogger.getLogger();
    public static void ClientConnect(String supportRoom, int NewUserServerGroupID) {
        Main.api.registerEvent(TS3EventType.SERVER);
        Main.api.addTS3Listeners(new TS3EventAdapter() {
            @Override
            public void onClientJoin(ClientJoinEvent e) {
                Client client = Main.api.getClientInfo(e.getClientId());

                //send welcome message
                SendMessage(client);

                HandleNewClients(NewUserServerGroupID, Main.api.getChannelByNameExact(supportRoom, true), client);
            }
        });
    }

    private static void SendMessage (Client client) {
        Main.api.sendPrivateMessage(client.getId(), "Willkommen [B]" + client.getNickname() + "[/B]!");
        logger.info("ClientConncetion: " + client.getNickname() + " joined the server");
    }

    private static void HandleNewClients (int ServerGroup, Channel channel, Client client) {
        if (client.isInServerGroup(ServerGroup)) {
            Main.api.moveClient(client.getId(), channel.getId());
            logger.info("EventHandler: moved " + client.getNickname() + " to Room " + channel.getName());
        }
    }
}
