import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3ApiAsync;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Objects;
import java.util.logging.Logger;

public class Mover {
    private static final Logger logger = PublicLogger.getLogger();

    public static void afkMover(boolean roomRequired, String targetRoom, long maxIdleTime, boolean muteRequired, String moveRoom, TS3Query query) {
        final int minutes = 60 * 1000;
        final int TIMER_DELAY_MS = 1000;
        final int TIMER_PERIOD_MS = 40 * 1000;
        TS3Api api = query.getApi();

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                if (!query.isConnected()) {
                    timer.cancel(); // Timer beenden, wenn die Verbindung geschlossen wurde
                    return;
                }
                List<Client> clients = api.getClients();
                for (Client client : clients) {
                    if (!client.isServerQueryClient()) {

                        String clientRoom = Main.getChannelNameById(client.getChannelId(), api);
                        long idleTime = client.getIdleTime();
                        boolean isMuted = client.isOutputMuted();

                        // Rest des Codes wie zuvor
                        int config_number = convert_config_to_number();
                        switch (config_number) {
                            case 5 -> {
                                if (isMuted && clientRoom != null && clientRoom.equals(targetRoom) && idleTime > maxIdleTime && !Objects.equals(clientRoom, moveRoom)) {
                                    move_routine(client);
                                }
                            }
                            case 6 -> {
                                if (idleTime > maxIdleTime * minutes && isMuted && !Objects.equals(clientRoom, moveRoom)) {
                                    move_routine(client);
                                }
                            }
                            case 8 -> {
                                if (clientRoom != null && clientRoom.equals(targetRoom) && idleTime > maxIdleTime * minutes && !Objects.equals(clientRoom, moveRoom)) {
                                    move_routine(client);
                                }
                            }
                            case 9 -> {
                                if (idleTime > maxIdleTime * minutes && !Objects.equals(clientRoom, moveRoom)) {
                                    move_routine(client);
                                }
                            }
                        }
                    }
                }
            }

            private int convert_config_to_number() {
                int number = 0;
                if (roomRequired) {
                    number += 1;
                }
                if (!roomRequired) {
                    number += 2;
                }
                if (muteRequired) {
                    number += 4;
                }
                if (!muteRequired) {
                    number += 7;
                }
                return number;
            }

            private void move_routine(Client client) {
                moveClientToRoom(client, moveRoom, api);
                sendMoveMessage(client.getId(), moveRoom);
                logClientMove(client.getNickname(), moveRoom);
            }

            private void moveClientToRoom(Client client, String moveRoom, TS3Api api) {
                int clientId = client.getId();
                api.moveClient(clientId, api.getChannelByNameExact(moveRoom, false).getId());
            }

            private void sendMoveMessage(int clientId, String room) {
                api.sendPrivateMessage(clientId, "Du warst zu lange Afk und wurdest in " + room + " gemoved");
            }

            private void logClientMove(String nickname, String room) {
                logger.info("move user " + nickname + " in " + room);
            }
        };

        timer.schedule(task, TIMER_DELAY_MS, TIMER_PERIOD_MS);
    }
}
