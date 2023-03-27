import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.Timer;
import java.util.TimerTask;

public class mover {
    public static void room_afk(String room) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                for (Client client : load.api.getClients()) {
                    if ((client.isOutputMuted() && (client.getChannelId() != map.Channel_ID.get(room)))){
                        //boolean x = (c.getChannelId() == variablen.gib.afk);
                        //boolean y = (c.getChannelId() == variablen.gib.andere_ts);
                        //boolean z = (c.getChannelId() == variablen.gib.kurz_weg);

                        //if((x ^ y ^ z ) ^ ( x && y && z )) {
                            if (client.getIdleTime() > 15 * 60 * 1000 && client.getIdleTime() < 15.0005 * 60 * 1000 ) {
                                load.api.sendPrivateMessage(client.getId(), "AFK Warnung!");
                            } else if (client.getIdleTime() > 20 * 60 * 1000) {
                                load.api.moveClient(client.getId(), map.Channel_ID.get(room));
                                load.api.sendPrivateMessage(client.getId(), "Du warst zu lange Afk und wurdest in den Afk-Channel gemoved");
                            }
                    }
                }
            }
        }, 1000, 35*1000);
    }

    public static void room_welcome (String room_src, String room_out) {;
        logger.getLogger().config(String.format("mover.room_welcome -> src %d", map.Channel_ID.get(room_src)));
        logger.getLogger().config(String.format("mover.room_welcome -> out %d", map.Channel_ID.get(room_out)));
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                for (Client client : load.api.getClients()) {
                    if (client.getChannelId() == map.Channel_ID.get(room_src) && client.getIdleTime() >= 2*60*1000){
                            load.api.moveClient(client.getId(), map.Channel_ID.get(room_out));
                            load.api.sendPrivateMessage(client.getId(), "Du wurdest in AFK gemoved");
                        }
                    }
                }
        }, 1000, 25*1000);

    }
}

//15 * 60 * 1000
// 15.0005 * 60 * 1000
// 20 * 60 * 1000


