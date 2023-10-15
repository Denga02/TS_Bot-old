import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.ArrayList;
import java.util.List;

public class TestSupport {
    private static final String NO_ACTORS_ARE_ONLINE = "Zurzeit ist niemand vom Support online";
    private static final String ACTORS_ARE_ONLINE = "Es wurden Leute aus Support benachrichtigt";
    private static final String ACTOR_MESSAGE = "Es braucht jemand Support";

    public static void HandleSupport(Channel targetChannel, int actorGroupID, int targetGroupId) {

        if (!targetChannel.isEmpty()) {
            if (Main.CheckClientsInGroup(actorGroupID)) {
                NotifyTarget(targetChannel.getId(), ACTORS_ARE_ONLINE);
                NotifyActors(actorGroupID);
            } else {
                NotifyTarget(targetChannel.getId(), NO_ACTORS_ARE_ONLINE);
            }
        }
    }



    private static void NotifyTarget(int targetRoomID, String message) {
        for (Client c : Main.getClientsFromSpecificGroup(targetRoomID)) {
            Main.api.pokeClient(c.getId(), message);
        }
    }


    private static void NotifyActors(int actorGroupId) {
        for (Client c : Main.getClientsFromSpecificGroup(actorGroupId)) {
            Main.api.pokeClient(c.getId(), ACTOR_MESSAGE);
        }

    }

}
