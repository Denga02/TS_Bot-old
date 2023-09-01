import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventType;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

public class EventHandler {

    public static void notifyOnJoin(String supportRoom, int serverGroupId) {
        Main.api.registerEvent(TS3EventType.SERVER);
        Main.api.addTS3Listeners(new TS3EventAdapter() {
            @Override
            public void onClientJoin(ClientJoinEvent e) {
                Client client = Main.api.getClientInfo(e.getClientId());

                // Begrüßungsnachricht senden
                String welcomeMessage = "Willkommen [B]" + client.getNickname() + "[/B]!";
                Main.api.sendPrivateMessage(client.getId(), welcomeMessage);

            }
        });
    }
}
