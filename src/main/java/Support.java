import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class Support {
    private static final Logger logger = PublicLogger.getLogger();
    private static final String NO_ACTORS_ARE_ONLINE = "Zurzeit ist niemand vom Support online";
    private static final String ACTORS_ARE_ONLINE = "Es wurden Leute aus Support benachrichtigt";
    private static final String ACTOR_MESSAGE = "Es braucht jemand Support";
    private static final int PERIOD_TIME = 10 *  60 * 1000;
    private static final int DELAY_TIME = 0;

    public static void HandleSupportWithTimer(Channel targetChannel, int actorGroupID, int targetGroupId) {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (Main.CheckUserIsinChannel(targetChannel.getId())) {
                    if (Main.CheckClientsInGroup(actorGroupID)) {
                        Main.PokeClients(actorGroupID, ACTOR_MESSAGE);
                    }
                }
            }
        }, DELAY_TIME, PERIOD_TIME);


    }
    public static void HandleSupportWithNoTimer(Channel targetChannel, int actorGroupID, int targetGroupId) {

        if (Main.CheckUserIsinChannel(targetChannel.getId())) {
            if (Main.CheckClientsInGroup(actorGroupID)) {
                Main.PokeClients(targetGroupId, ACTORS_ARE_ONLINE);
                Main.PokeClients(actorGroupID, ACTOR_MESSAGE);
            } else {
                Main.PokeClients(targetGroupId, NO_ACTORS_ARE_ONLINE);
            }
        }

    }

}
