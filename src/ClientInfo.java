import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientInfo {
    public static void loader_test(int ID) {
        // List all clients in the console
        List<Channel> channels = Config.api.getChannels();
        Map<Integer, Channel> channelMap = new HashMap<>(channels.size());

        for (Channel channel : channels) {
            channelMap.put(channel.getId(), channel);
            Config.api.sendPrivateMessage(ID,channel.getName() + "    "  + channel.getId());
        }

        for (Client c : Config.api.getClients()) {
            // Get the client's channel
            Channel channel = channelMap.get(c.getChannelId());

            // Write the client and channel name into the console
            Config.api.sendPrivateMessage(ID, c.getNickname() + " in channel " + channel.getName() + "  Unic ID: " + c.getUniqueIdentifier());
        }
    }

    public static void loader_produktiv(int ID) {
        // List all clients in the console
        List<Channel> channels = Config.api.getChannels();
        Map<Integer, Channel> channelMap = new HashMap<>(channels.size());

        for (Channel channel : channels) {
            channelMap.put(channel.getId(), channel);

        }

        for (Client c : Config.api.getClients()) {
            // Get the client's channel
            Channel channel = channelMap.get(c.getChannelId());

            // Write the client and channel name into the console
            System.out.println(c.getNickname() + " in channel " + channel.getName());
        }
    }
}
