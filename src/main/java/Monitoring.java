import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventType;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;


public class Monitoring {
    private static final Logger logger = PublicLogger.getLogger();
    public static void handleMessages() {

        // Get our own client ID by running the "whoami" command
        final int clientId = Main.api.whoAmI().getId();

        Main.api.registerEvent(TS3EventType.TEXT_PRIVATE);

        // Register the event listener
        Main.api.addTS3Listeners(new TS3EventAdapter() {

            @Override
            public void onTextMessage(TextMessageEvent e) {
                // Only react to channel messages not sent by the query itself
                if (e.getTargetMode() == TextMessageTargetMode.CLIENT && e.getInvokerId() != clientId) {
                    String message = e.getMessage().toLowerCase();

                    if (message.equals("!ping")) {
                        Main.api.moveQuery(Main.api.getClientInfo(clientId).getChannelId());
                        // Answer "!ping" with "pong"
                        Main.api.sendPrivateMessage(e.getInvokerId(), "pong");
                }
            }
        }
    });
    }

    public static void baseMonitoring () {
        // basic monitroing to control, if the bot is online
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                logger.info("is active");
            }
        }, 60*1000, 30*60*1000);
    }
}





