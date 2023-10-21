import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventType;
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.logging.Logger;

public class EventHandler {

    private static final Logger logger = PublicLogger.getLogger();
    public static void ClientConnect(String supportRoom, int NewUserServerGroupID, TS3Api api) {
        api.registerEvent(TS3EventType.SERVER);
        api.addTS3Listeners(new TS3EventAdapter() {
            @Override
            public void onClientJoin(ClientJoinEvent e) {
                Client client = api.getClientInfo(e.getClientId());

                //send welcome message
                SendMessage(client, api);

                HandleNewClients(NewUserServerGroupID, api.getChannelByNameExact(supportRoom, true), client, api);
            }
        });
    }

    private static void SendMessage (Client client, TS3Api api) {
        api.sendPrivateMessage(client.getId(), "Willkommen [B]" + client.getNickname() + "[/B]!");
        logger.info("ClientConncetion: " + client.getNickname() + " joined the server");
    }

    private static void HandleNewClients (int ServerGroup, Channel channel, Client client, TS3Api api) {
        if (client.isInServerGroup(ServerGroup)) {
            api.moveClient(client.getId(), channel.getId());
            logger.info("EventHandler: moved " + client.getNickname() + " to Room " + channel.getName());

            Support.HandleSupportWithNoTimer(api.getChannelByNameExact("Support", true), Main.SUPP_GROUP_ID, Main.NEW_USER_GROUP_ID, api);
        }
    }
}
