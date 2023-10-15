import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.wrapper.ServerGroup;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class Load {
    private static final Logger logger = PublicLogger.getLogger();

    static String HOST_GIB = "62.104.20.81";
    static int QUERY_PORT = 11200;
    static String LOGIN_NAME_GIB = "dannybot";
    static String LOGIN_PASS_GIB = "OTWcNvID";
    static int FOUR_NET_PLAYER_PORT = 10126;


    private static String CreateNickname(String Botname) {
        //random a number because of reconnection issues
        Random rand = new Random();
        int randomNumber = rand.nextInt(100) + 1;
        String nickname = "Clanbot" + randomNumber;

        return nickname;
    }

    public static void connectGib(String Botname) throws IOException {
        Main.config.setHost(HOST_GIB);
        Main.config.setQueryPort(QUERY_PORT);
        Main.query = new TS3Query(Main.config);
        Main.query.connect();
        Main.api = Main.query.getApi();
        Main.api.login(LOGIN_NAME_GIB, LOGIN_PASS_GIB);
        Main.api.selectVirtualServerByPort(FOUR_NET_PLAYER_PORT);
        Main.api.setNickname(CreateNickname(Botname));

        setup();
    }


    public static void conncetLocal(String botName) throws IOException {
        Main.config.setHost("127.0.0.1");
        Main.config.setFloodRate(TS3Query.FloodRate.UNLIMITED);
        Main.query = new TS3Query(Main.config);
        Main.query.connect();
        Main.api = Main.query.getApi();
        Main.api.login("serveradmin", "T8xQptXc");
        Main.api.selectVirtualServerById(1);
        Main.api.setNickname(botName);

        setup();
    }


    private static void setup() throws IOException {
        //config logger

        PublicLogger.configLogging();
        //notify all CLients that bot is online
        Main.MessageToAllClients("Clanbot ist online");

        //print all Channels with their ID
        List<ServerGroup> channelGroups = Main.api.getServerGroups();
        for (ServerGroup group : channelGroups) {
            System.out.println("Gruppenname: " + group.getName() + ", Gruppen-ID: " + group.getId());
        }

    }
}
