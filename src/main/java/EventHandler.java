import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

public class EventHandler {
    public static void ClientJoinServer(String supportRoom, int serverGroupId) {
        Main.api.registerAllEvents();
        Main.api.addTS3Listeners(new TS3EventAdapter() {
            @Override
            public void onTextMessage(TextMessageEvent e) {
                Client c = Main.api.getClientInfo(e.getInvokerId());
                Main.api.sendPrivateMessage(c.getId(), "Willkommen [B]"+c.getNickname()+"[/B]!\n");

                if (c.isInServerGroup(serverGroupId)) {
                    Main.api.moveClient(e.getInvokerId(), Main.api.getChannelByNameExact(supportRoom, false).getId());
                }
            }
        });
    }
}
