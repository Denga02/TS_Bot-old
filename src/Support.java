import com.github.theholywaffle.teamspeak3.api.event.ClientMovedEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.Timer;
import java.util.TimerTask;

public class Support {
    public static void loader(int Raum, int Gruppe1, int Gruppe2, int Gruppe3)
    {
        Config.api.registerAllEvents();
        Config.api.addTS3Listeners(new TS3EventAdapter() {
            @Override
            public void onClientMoved(ClientMovedEvent e) {
                //super.onClientMoved(e);
                Client c = Config.api.getClientInfo(e.getClientId());
                if (e.getTargetChannelId() == Raum) {
                    boolean value = false;
                    for (Client sup : Config.api.getClients()) {
                        if (sup.isInServerGroup(Gruppe1) || sup.isInServerGroup(Gruppe2) || sup.isInServerGroup(Gruppe3)){
                            value = true;
                            Config.api.pokeClient(sup.getId(), "[URL=client://" + c.getChannelId() + "/" + c.getIp() + "]" + c.getNickname() + "[/URL] braucht Support!");
                        }
                    } if (value) {
                        Config.api.pokeClient(c.getId(), "Es wurden Leute aus der Leitung benachrichtigt!");
                    } else {
                        Config.api.pokeClient(c.getId(), "Zurzeit ist keiner dar, der dir helfen kann");
                    }
                }
            }
        });
    }

    public static void message(int Raum, int Gruppe1, int Gruppe2, int Gruppe3)
    {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                boolean val = false;
                for (Client c : Config.api.getClients()) {
                    if (c.isInServerGroup(Gruppe1) || c.isInServerGroup(Gruppe2) || c.isInServerGroup(Gruppe3)){
                        val = true;
                    } else {
                        val = false;
                    }
                    if (Config.api.getChannelInfo(Raum).getSecondsEmpty() == -1 && val == true) {
                        Config.api.pokeClient(c.getId(), "[URL=client://" + c.getChannelId() + "/" + c.getIp() + "]" + c.getNickname() + "[/URL] braucht Support!");
                    }
                }
            }
        }, 1000, 5*60*1000);
    }
}
