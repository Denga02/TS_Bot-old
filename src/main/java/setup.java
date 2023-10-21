import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.reconnect.ConnectionHandler;
import com.github.theholywaffle.teamspeak3.api.reconnect.ReconnectStrategy;

public class setup {
    public static void setConfigGIB(TS3Config config) {
        config.setHost(TS3Configurations.HOST_GIB);
        config.setQueryPort(TS3Configurations.QUERY_PORT);
    }

    public static void setConfigRef(TS3Config config) {
        config.setHost(TS3Configurations.HOST_REF);
        config.setEnableCommunicationsLogging(true);
        config.setReconnectStrategy(ReconnectStrategy.exponentialBackoff());
        config.setConnectionHandler(new ConnectionHandler() {
            @Override
            public void onConnect(TS3Api api) {
                run.loop(api);
            }

            @Override
            public void onDisconnect(TS3Query ts3Query) {
                // Nothing
            }
        });
    }

    public static void setLoginGib(TS3Api api) {
        try {
            api.login(TS3Configurations.LOGIN_NAME_GIB, TS3Configurations.LOGIN_PASS_GIB);
            api.selectVirtualServerByPort(TS3Configurations.FOUR_NET_PLAYER_PORT);
            api.setNickname("botname");
        } catch (Exception e) {
            // Handle login errors
            e.printStackTrace();
        }
    }

    public static void setLoginRef(TS3Api api) {
        try {
            api.login(TS3Configurations.LOGIN_NAME_REF, TS3Configurations.LOGIN_PASS_REF);
            api.selectVirtualServerById(1);
            api.setNickname("botName");
        } catch (Exception e) {
            // Handle login errors
            e.printStackTrace();
        }
    }
}
