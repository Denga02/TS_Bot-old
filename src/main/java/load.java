import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.Timer;
import java.util.TimerTask;

public class load {


    public static void conncet_gib(String bot_name) {
        main.config.setHost("62.104.20.81"); //10126
        main.query = new TS3Query(main.config);
        main.query.connect();
        main.api = main.query.getApi();
        main.api.login("dannybot", "OTWcNvID");
        main.api.selectVirtualServerByPort(10126);
        main.api.setNickname(bot_name);
    }


    public static void conncet_local(String bot_name) {
        main.config.setHost("127.0.0.1");
        main.query = new TS3Query(main.config);
        main.query.connect();
        main.api = main.query.getApi();
        main.api.login("bot", "68W6vEsH");
        main.api.selectVirtualServerById(1);
        main.api.setNickname(bot_name);
    }



    public static void setup() {
        //config logger
        PublicLogger.configLogging();

        //notify all clients that bot is online
        for (Client c : main.api.getClients()) {
            if(!c.isServerQueryClient())
            {
                main.api.sendPrivateMessage(c.getId(),"Bot ist online");
            }
        }
        PublicLogger.logger.info("Bot is online");
    }
}
