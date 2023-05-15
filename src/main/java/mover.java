import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.Timer;
import java.util.TimerTask;

public class mover {

    public static void room_afk(String room_out) {
        Timer timer_afk = new Timer();
        timer_afk.schedule(new TimerTask() {

            @Override
            public void run() {
                for (Client client : main.api.getClients()) {
                    boolean afk_room = false;
                    String client_channel_name = main.api.getChannels().get(client.getChannelId()).getName();

                    if ( client_channel_name.equals("╔Anderer TS") || client_channel_name.equals("╠kurz mal weg") || client_channel_name.equals("╚Irgendwann wieder da") ) {
                        afk_room = true;
                    }
                    if ((client.isOutputMuted() && afk_room == false) && !client.isServerQueryClient()){
                            if (client.getIdleTime() > 1 * 60 * 1000 && client.getIdleTime() <  2 * 60 * 1000 ) {
                                main.api.sendPrivateMessage(client.getId(), "AFK Warnung!");
                                PublicLogger.logger.info("mover.room_afk: send AFK Warnung to " + client.getNickname() + "");
                            } else if (client.getIdleTime() > 3 * 60 * 1000) {
                                main.api.moveClient(client.getId(), main.api.getChannelByNameExact(room_out, false).getId());
                                main.api.sendPrivateMessage(client.getId(), "Du warst zu lange Afk und wurdest in " +room_out+ " gemoved");
                                PublicLogger.logger.info("mover.room_afk: move user" + client.getNickname() + " in " + room_out);
                            }
                    }
                }
            }
        }, 1000, 40*1000);
    }

    /*
    public static void room_welcome (String room_src, String room_out) {;
        Timer timer_afk = new Timer();
        timer_afk.schedule(new TimerTask() {

            @Override
            public void run() {
                for (Client client : main.api.getClients()) {
                    boolean afk_room = false;
                    String client_channel_name = main.api.getChannels().get(client.getChannelId()).getName();

                    if ( client_channel_name.equals("╔Anderer TS") || client_channel_name.equals("╠kurz mal weg") || client_channel_name.equals("╚Irgendwann wieder da") ) {
                        afk_room = true;
                    }
                    if ((client.isOutputMuted() && afk_room == false) && !client.isServerQueryClient()){
                        if (client.getIdleTime() > 1 * 60 * 1000 && client.getIdleTime() <  2 * 60 * 1000 ) {
                            main.api.sendPrivateMessage(client.getId(), "AFK Warnung!");
                            PublicLogger.logger.info("mover.room_afk: send AFK Warnung to " + client.getNickname() + "");
                        } else if (client.getIdleTime() > 3 * 60 * 1000) {
                            main.api.moveClient(client.getId(), main.api.getChannelByNameExact(room_out, false).getId());
                            main.api.sendPrivateMessage(client.getId(), "Du warst zu lange Afk und wurdest in " +room_out+ " gemoved");
                            PublicLogger.logger.info("mover.room_afk: move user" + client.getNickname() + " in " + room_out);
                        }
                    }
                }
            }
        }, 1000, 40*1000);
    }

     */
}

//15 * 60 * 1000
// 15.0005 * 60 * 1000
// 20 * 60 * 1000


