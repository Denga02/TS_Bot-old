import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import com.github.theholywaffle.teamspeak3.api.wrapper.ServerGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class map {

    // maps and there's list
    public static Map<String, Integer> Channel_ID = new HashMap<String, Integer>();
    public static List<Channel> Channel_List = load.api.getChannels();
    public static List<ServerGroup> server_Groups = load.api.getServerGroups();
    public static Map<String, Integer> Server_Group_ID_Map = new HashMap<>();
    public static  Map<String, String> online_client_UID_Map = new HashMap<>();
    public static List<Client> online_clients = load.api.getClients();



}



