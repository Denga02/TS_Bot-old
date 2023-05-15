import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.Timer;
import java.util.TimerTask;

public class load {

    /*
     static String ADD_GIB = "62.104.20.81";
     static String ADD_LOCAL = "127.0.0.1";
     static int PORT_GIB = 10126;
     static String QUERRY_NAME_GIB = "dannybot";
     static String QUERRY_PASS_GIB= "OTWcNvID";
     static String QUERRY_NAME_LCOAL = "bot";
     static String QUERRY_PASS_LCOAL = "68W6vEsH";
     static int PORT_4_NET_PLAYERS = 11200;
     static int PORT_STANDARD_QUERY = 10011;
     */

    public static void conncet_gib (String bot_name) {
        main.config.setHost("62.104.20.81"); //10126
        main.config.setQueryPort(11200);
        //port of 4 net players
        main.query.connect();
        main.api.login("dannybot", "OTWcNvID");
        main.api.selectVirtualServerByPort(10126);
        main.api.setNickname(bot_name);
    }

    public static void conncet_local(String bot_name) {
        main.config.setHost("127.0.0.1");
        main.query.connect();
        main.api.login("bot", "68W6vEsH");
        main.api.selectVirtualServerById(1);
        main.api.setNickname(bot_name);
    }

    public static void setup() {

        // basic monitroing to control, if the bot is online
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                PublicLogger.logger.info("is active");
            }
        }, 60*1000, 30*60*1000);

        //notify all clients that bot is online
        for (Client c : main.api.getClients()) {
            if(!c.isServerQueryClient())
            {
                main.api.sendPrivateMessage(c.getId(),"Bot ist online");
            }
        }
    }
}
