import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.Timer;
import java.util.TimerTask;

public class Support {
    public void load(int supportRoomId, int [] actorGroup) {
        //checking user is in support
        //checking is actor User online
        //send notification to user who needs support
        //notify actor that user needs support with a timer
        if (checkUserInSupport() ) {
            notifyUserInSupport();
            notifyActors();
        }
    }

    private boolean checkUserInSupport () {
        for ()
    }

        Main.api.registerAllEvents();
        Main.api.addTS3Listeners(new TS3EventAdapter() {
            @Override
            public void onClientMoved(ClientMovedEvent e) {
                Client c = Main.api.getClientInfo(e.getClientId());
                if (e.getTargetChannelId() == supportRoomId) {
                    if (checkActorsOnline();)
                    boolean value = false;
                    for (Client sup : Main.api.getClients()) {
                        if (sup.isInServerGroup(Gruppe1) || sup.isInServerGroup(Gruppe2) || sup.isInServerGroup(Gruppe3)){
                            value = true;
                            Main.api.pokeClient(sup.getId(), "[URL=client://" + c.getChannelId() + "/" + c.getIp() + "]" + c.getNickname() + "[/URL] braucht Support!");
                        }
                    } if (value) {
                        Main.api.pokeClient(c.getId(), "Es wurden Leute aus der Leitung benachrichtigt!");
                    } else {
                        Main.api.pokeClient(c.getId(), "Zurzeit ist keiner dar, der dir helfen kann");
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
                for (Client c : Main.api.getClients()) {
                    if (c.isInServerGroup(Gruppe1) || c.isInServerGroup(Gruppe2) || c.isInServerGroup(Gruppe3)){
                        val = true;
                    } else {
                        val = false;
                    }
                    if (Main.api.getChannelInfo(Raum).getSecondsEmpty() == -1 && val == true) {
                        Main.api.pokeClient(c.getId(), "[URL=client://" + c.getChannelId() + "/" + c.getIp() + "]" + c.getNickname() + "[/URL] braucht Support!");
                    }
                }
            }
        }, 1000, 5*60*1000);
    }
}
