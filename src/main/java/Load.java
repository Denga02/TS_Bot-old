import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.logging.Logger;

public class Load {
    private static final Logger logger = PublicLogger.getLogger();


    public static void conncetGib(String botName) {
        Main.config.setHost("62.104.20.81"); //10126
        Main.config.setQueryPort(11200);
        Main.query = new TS3Query(Main.config);
        Main.query.connect();
        Main.api = Main.query.getApi();
        Main.api.login("dannybot", "OTWcNvID");
        Main.api.selectVirtualServerByPort(10126);
        Main.api.setNickname(botName);
    }


    public static void conncetLocal(String botName) {
        Main.config.setHost("127.0.0.1");
        Main.query = new TS3Query(Main.config);
        Main.query.connect();
        Main.api = Main.query.getApi();
        Main.api.login("serveradmin", "T8xQptXc");
        Main.api.selectVirtualServerById(1);
        Main.api.setNickname(botName);
        //local Windwos: bot, 68W6vEsH
        //Local Ubuntu: serveradmin, T8xQptXc
    }



    public static void setup() {
        //config logger
        PublicLogger.configLogging();

        //notify all clients that bot is online
        for (Client c : Main.api.getClients()) {
            if(!c.isServerQueryClient())
            {
                Main.api.sendPrivateMessage(c.getId(),"Bot ist online");
            }
        }
        logger.info("Bot is online");
    }
}
