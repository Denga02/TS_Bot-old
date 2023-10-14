import com.github.theholywaffle.teamspeak3.api.event.ClientMovedEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventType;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class Support {
    final static int PERIOD_TIME = 10 * 1000;

    public static void load(int supportRoomId, int actorGroup) {
        checkUserInSupport(supportRoomId, actorGroup);

    }

    private static void checkUserInSupport(int supportRoomId, int actorGroup) {
        Main.api.registerEvent(TS3EventType.CHANNEL, supportRoomId);
        Main.api.addTS3Listeners(new TS3EventAdapter() {
            @Override
            public void onClientMoved(ClientMovedEvent e) {
                if (e.getTargetChannelId() == supportRoomId) {
                    boolean areActorsOnline = checkActorsOnline(actorGroup);
                    notifyUserInSupport(e.getClientId(), areActorsOnline);
                    if (areActorsOnline) {
                        notifyActors(e.getClientId(), actorGroup);
                    }
                }
            }
        });
    }

    private static boolean checkActorsOnline(int actorGroup) {
        for (Client client : Main.api.getClients()) {
                if (client.isInServerGroup(actorGroup)) {
                    return true;
            }
        }
        return false;
    }

    private static void notifyUserInSupport(int userId, boolean areActorsOnline) {
        if (areActorsOnline) {
            Main.api.pokeClient(userId, "Es wurden Leute aus der Leitung benachrichtigt!");
        } else {
            Main.api.pokeClient(userId, "Zurzeit ist keiner da, der dir helfen kann.");
        }
    }

    private static void notifyActors(int userId, int actorGroup) {
        for (Client actor : Main.api.getClients()) {
                if (actor.isInServerGroup(actorGroup)) {
                    System.out.println("Support wird benachrichtigt");
                    //Main.api.pokeClient(actor.getId(), "[URL=client://" + client.getChannelId() + "/" + client.getIp() + "]" + client.getNickname() + "[/URL] braucht Support!");
                }
        }
    }
}
