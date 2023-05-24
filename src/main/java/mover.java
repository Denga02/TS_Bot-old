import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class mover {
    private static final Logger logger = PublicLogger.getLogger();
    public static void afkMover(boolean roomRequired, String targetRoom, long maxIdleTime, boolean muteRequired, String moveRoom) {
        final int minutes = 60 * 1000;
        final int TIMER_DELAY_MS = 1000;
        final int TIMER_PERIOD_MS = 40 * 1000;

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                List<Client> clients = main.api.getClients();
                for (Client client : clients) {
                    if (!client.isServerQueryClient()) {

                        String clientRoom = main.getChannelNameById(client.getChannelId());
                        long idleTime = client.getIdleTime();
                        boolean isMuted = client.isOutputMuted();

                        // convert config to number
                        int config_number = convert_config_to_number();

                        switch (config_number) {
                            // specific room + mute
                            case 5 -> {
                                if (isMuted && clientRoom != null && clientRoom.equals(targetRoom) && idleTime > maxIdleTime && !Objects.equals(clientRoom, moveRoom)) {
                                    move_routine(client);
                                }
                            }
                            // none room + mute
                            case 6 -> {
                                if (idleTime > maxIdleTime * minutes && isMuted && !Objects.equals(clientRoom, moveRoom)) {
                                    move_routine(client);
                                }
                            }
                            // specific room + no mute
                            case 8 -> {
                                if (clientRoom != null && clientRoom.equals(targetRoom) && idleTime > maxIdleTime * minutes && !Objects.equals(clientRoom, moveRoom)) {
                                    move_routine(client);
                                }
                            }
                            // no room + no mute
                            case 9 -> {
                                if (idleTime > maxIdleTime * minutes && !Objects.equals(clientRoom, moveRoom)) {
                                    move_routine(client);
                                }
                            }
                        }
                        }
                    }
                }
            private int convert_config_to_number(){
                int number = 0;
                if (roomRequired) {
                    number += 1;}
                if (!roomRequired) {number += 2;}
                if (muteRequired) {number += 4;}
                if (!muteRequired) {number += 7;}
                return number;
            }

            private void move_routine(Client client){
                moveClientToRoom(client, moveRoom);
                sendMoveMessage(client.getId(), moveRoom);
                logClientMove(client.getNickname(), moveRoom);
            }

            private void moveClientToRoom(Client client, String moveRoom) {
                int clientId = client.getId();
                main.api.moveClient(clientId, main.api.getChannelByNameExact(moveRoom, false).getId());
            }

            private void sendMoveMessage(int clientId, String room)
            {
                main.api.sendPrivateMessage(clientId, "Du warst zu lange Afk und wurdest in " + room + " gemoved");
            }

            private void logClientMove(String nickname, String room)
            {
                logger.info("move user " + nickname + " in " + room);
            }

        }, TIMER_DELAY_MS, TIMER_PERIOD_MS);

    }
}



