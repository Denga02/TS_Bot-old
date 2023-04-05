import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.Timer;
import java.util.TimerTask;

public class mover {
    public static void room_afk(String room_out) {
        Timer timer_afk = new Timer();
        timer_afk.schedule(new TimerTask() {

            @Override
            public void run() {
                for (Client client : load.api.getClients()) {
                    boolean afk_room = false;

                    if (client.getChannelId() == map.Channel_ID.get("╔Anderer TS") || client.getChannelId() == map.Channel_ID.get("╠kurz mal weg") ||
                            client.getChannelId() == map.Channel_ID.get("╚Irgendwann wieder da")) {
                        afk_room = true;
                    }

                    if (client.isOutputMuted() && afk_room == false){
                            if (client.getIdleTime() > 15 * 60 * 1000 && client.getIdleTime() <  16 * 60 * 1000 ) {
                                load.api.sendPrivateMessage(client.getId(), "AFK Warnung!");
                            } else if (client.getIdleTime() > 2 * 60 * 1000) {
                                load.api.moveClient(client.getId(), map.Channel_ID.get(room_out));
                                load.api.sendPrivateMessage(client.getId(), "Du warst zu lange Afk und wurdest in " +room_out+ " gemoved");
                            }
                    }
                }
            }
        }, 1000, 35*1000);
    }

    public static void room_welcome (String room_src, String room_out) {;
        PublicLogger.logger.config(String.format("mover.room_welcome -> src %d", map.Channel_ID.get(room_src)));
        Timer timer_welcome = new Timer();
        timer_welcome.schedule(new TimerTask() {

            @Override
            public void run() {
                for (Client client : load.api.getClients()) {
                    if (((client.getChannelId() == map.Channel_ID.get(room_src)))){
                        if (client.getIdleTime() > 2 * 60 * 1000) {
                            load.api.moveClient(client.getId(), map.Channel_ID.get(room_out));
                            load.api.sendPrivateMessage(client.getId(), "Du warst zu lange in " + room_src +
                                    "und wurdest deswegen in " + room_out + "gemoved");
                        }
                    }
                }
            }
        }, 1000, 10*1000);

    }
}

//15 * 60 * 1000
// 15.0005 * 60 * 1000
// 20 * 60 * 1000


