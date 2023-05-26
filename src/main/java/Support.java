import com.github.theholywaffle.teamspeak3.api.event.ClientMovedEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventType;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

public class Support {

    public static void load(int supportRoomId, int[] actorGroup) {
        checkUserInSupport(supportRoomId, actorGroup);
    }

    private static void checkUserInSupport(int supportRoomId, int[] actorGroup) {
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

    private static boolean checkActorsOnline(int[] actorGroup) {
        for (Client client : Main.api.getClients()) {
            for (int group : actorGroup) {
                if (client.isInServerGroup(group)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void notifyUserInSupport(int userId, boolean areActorsOnline) {
        Client client = Main.api.getClientInfo(userId);
        if (areActorsOnline) {
            Main.api.pokeClient(userId, "Es wurden Leute aus der Leitung benachrichtigt!");
        } else {
            Main.api.pokeClient(userId, "Zurzeit ist keiner da, der dir helfen kann.");
        }
    }

    private static void notifyActors(int userId, int[] actorGroup) {
        Client client = Main.api.getClientInfo(userId);
        for (Client actor : Main.api.getClients()) {
            for (int group : actorGroup) {
                if (actor.isInServerGroup(group)) {
                    Main.api.pokeClient(actor.getId(), "[URL=client://" + client.getChannelId() + "/" + client.getIp() + "]" + client.getNickname() + "[/URL] braucht Support!");
                }
            }
        }
    }
}
