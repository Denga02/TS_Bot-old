import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.Timer;
import java.util.TimerTask;

public class afk_mover {

    public static void loader_afk(int Raum_afk)
    {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                for (Client c : Config.api.getClients()) {
                    if ((c.isOutputMuted() && (c.getChannelId() != variablen.gib.afk))){
                        //boolean x = (c.getChannelId() == variablen.gib.afk);
                        //boolean y = (c.getChannelId() == variablen.gib.andere_ts);
                        //boolean z = (c.getChannelId() == variablen.gib.kurz_weg);

                        //if((x ^ y ^ z ) ^ ( x && y && z )) {
                            if (c.getIdleTime() > 15 * 60 * 1000 && c.getIdleTime() < 15.0005 * 60 * 1000 && !(c.getUniqueIdentifier().contains(variablen.gib.stefan_id))) {
                                Config.api.sendPrivateMessage(c.getId(), "AFK Warnung!");
                            } else if (c.getIdleTime() > 20 * 60 * 1000  &&  !(c.getUniqueIdentifier().contains(variablen.gib.stefan_id))) {
                                Config.api.moveClient(c.getId(), Raum_afk);
                                Config.api.sendPrivateMessage(c.getId(), "Du warst zu lange Afk und wurdest in den Afk-Channel gemoved");
                            }
                    }
                }
            }
        }, 1000, 35*1000);
    }
    public static void loader_eingang (int Raum_in, int Raum_afk) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                for (Client c : Config.api.getClients()) {
                    if (c.getChannelId() == Raum_in && c.getIdleTime() >= 2*60*1000){
                            Config.api.moveClient(c.getId(), Raum_afk);
                            Config.api.sendPrivateMessage(c.getId(), "Du wurdest in AFK gemoved");
                        }
                    }
                }
        }, 1000, 25*1000);

    }
}

//15 * 60 * 1000
// 15.0005 * 60 * 1000
// 20 * 60 * 1000
