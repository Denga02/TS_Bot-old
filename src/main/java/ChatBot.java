import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventType;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatBot {

    public static void handleMessages() {
        // Get our own client ID by running the "whoami" command
        final int clientId = Main.api.whoAmI().getId();

        Main.api.registerEvent(TS3EventType.TEXT_PRIVATE);
        Main.api.addTS3Listeners(new TS3EventAdapter() {
            @Override
            public void onTextMessage(TextMessageEvent e) {
                if (e.getTargetMode() == TextMessageTargetMode.CLIENT && e.getInvokerId() != clientId) {
                    String message = e.getMessage().toLowerCase();

                    switch (message) {
                        case "!kevin":
                            printKevin();
                            break;
                        case "!steven":
                            printSteven();
                            break;
                        case "!stefan":
                            Main.api.sendChannelMessage("Bin mal essen!");
                            break;
                        case "!help":
                            printHelp(e.getInvokerId());
                            break;
                        case "!chief" :
                            Main.api.sendChannelMessage("Micha sagt: Wir haben CChieftain noch zuhause!!");
                            break;
                        case "!danny" :
                            Main.api.sendChannelMessage("Dannnnnnny!!");
                            break;
                        case "!karsten":
                            Main.api.sendChannelMessage("Kaaaarsten!!");
                            break;
                        case "!jonas" :
                            Main.api.sendChannelMessage("WAAAS!!");
                            break;
                        case "!changelog" :
                            printChangeLog(e.getInvokerId());
                            break;
                        case "!online":
                            printOnline(e.getInvokerId());
                        default:
                            Main.api.sendPrivateMessage(e.getInvokerId(), "Falscher Befehl! gebe !help ein, um alle Befehle einzusehen");
                    }
                }
            }
            private void printHelp(int id) {
                Main.api.sendPrivateMessage(id, """
                        Überischt aller Befehle:
                        !help --> gibt eine Übersicht aller Befehle
                        !changelog --> übersicht, was im letzten update geändert wurde
                        !online --> zeigt wer alles online ist und in was für einem Raum
                        !kevin | !steven | !stefan | !chief | !karsten | !jonas --> nutzen auf eigene Gefahr"""
                );
            }
            private void printChangeLog(int id) {
                Main.api.sendPrivateMessage(id, """
                        Version 1.4
                        Code wurde gerefactored, dazu einige Bug fixes.
                        Der Afk Mover gibt nun keine Warnung mehr aus und wird nach 20 Minuten den user in den Afk Raum moven
                        Die funktionen des Chatbots sind wieder da. Für den Fall, dass Danny angezeigt wird statt der Bot,
                        muss einmal in Willkommen connceten, um eine Nachricht vom Bot zu bekommen. An besseren MEthoden wird bereits gearbeitet
                        """
                );
            }
            private void printKevin() {
                // loads variables from config.json
                try {
                    Configuration configuration = Configuration.load("/home/ansible/IdeaProjects/TS_Bot/target/config.json");
                    int counterKevin = configuration.getCounter().getKevin();

                    Configuration.CounterConfig counterConfig = configuration.getCounter();
                    counterConfig.setSteven(counterConfig.getKevin() + 1);
                    configuration.setCounter(counterConfig);
                    configuration.save("/home/ansible/IdeaProjects/TS_Bot/target/config.json");

                    // Main contents of the method
                    Main.api.sendChannelMessage("Kevin! Brünette mit fetten Hupen ist für dich unterwegs\n"
                            + "Kevin war wieder rallig! Zum " + counterKevin + ". mal kam heute eine Nutte vorbei!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            private void printSteven() {
                try {
                    Configuration configuration = Configuration.load("/home/ansible/IdeaProjects/TS_Bot/target/config.json");
                    int counterSteven = configuration.getCounter().getSteven();

                    Configuration.CounterConfig counterConfig = configuration.getCounter();
                    counterConfig.setSteven(counterConfig.getSteven() + 1);
                    configuration.setCounter(counterConfig);
                    configuration.save("/home/ansible/IdeaProjects/TS_Bot/target/config.json");

                    Main.api.sendChannelMessage("Abgelehnt!!\n" +
                            "Du bist der " + (counterSteven + 1) + ". der fragt...");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            private void printOnline(int id) {
                for (Client c : Main.api.getClients()) {
                    if(!c.isServerQueryClient())
                    {
                        Main.api.sendPrivateMessage(id,"User " + c.getNickname() + " is in channel "
                                + Main.getChannelNameById(c.getChannelId()));
                    }
                }
            }
        });
    }
}
