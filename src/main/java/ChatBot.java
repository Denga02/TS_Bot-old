import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventType;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import org.w3c.dom.css.Counter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ChatBot {
    private static final Logger logger = PublicLogger.getLogger();
    private static boolean RecocnizedCommand = true;

    public static void handleMessages() {
        // Get our own client ID by running the "whoami" command
        final int clientId = Main.api.whoAmI().getId();

        Main.api.registerEvent(TS3EventType.TEXT_PRIVATE);
        Main.api.addTS3Listeners(new TS3EventAdapter() {
            @Override
            public void onTextMessage(TextMessageEvent e) {
                if (e.getTargetMode() == TextMessageTargetMode.CLIENT && e.getInvokerId() != clientId) {
                    String command = e.getMessage().toLowerCase();

                    //query change channel if is not in the same as the invoker
                    if (Main.api.getClientInfo(clientId).getChannelId() != Main.api.getClientInfo(e.getInvokerId()).getChannelId()) {
                        Main.api.moveQuery(Main.api.getClientInfo(e.getInvokerId()).getChannelId());
                    }

                    //set RecocnizedCommand true before each passage of the Swicthes
                    RecocnizedCommand = true;

                    switch (command) {
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
                            break;
                        default:
                            Main.api.sendPrivateMessage(e.getInvokerId(), "Falscher Befehl! gebe !help ein, um alle Befehle einzusehen");
                            RecocnizedCommand = false;
                    }

                    if (RecocnizedCommand) {
                        ChatBotLogger(e.getInvokerId(), command);
                    } else {
                        ChatBotLogger(e.getInvokerId(), "Falscher Befehl");
                    }
                }
            }
            private void printHelp(int id) {
                Main.api.sendPrivateMessage(id,
                        """
                        Überischt aller Befehle:
                        !help --> gibt eine Übersicht aller Befehle
                        !changelog --> übersicht, was im letzten update geändert wurde
                        !online --> zeigt wer alles online ist und in was für einem Raum sich dieser befindet
                        !kevin | !steven | !stefan | !chief | !karsten | !jonas --> nutzen auf eigene Gefahr
                        """
                );
            }
            private void printChangeLog(int id) {
                Main.api.sendPrivateMessage(id,
                        """
                        Version 2.1
                        Bug fixes
                        Chatbot Funktionen sind wieder aktiv
                        """
                );
            }
            private void printKevin() {

                // loads variables from config.json
                try {
                    Configuration configuration = Configuration.load("/home/danny/IdeaProjects/TS_Bot/config.json");
                    int counterKevin = configuration.getCounter().getKevin();

                    Configuration.CounterConfig counterConfig = configuration.getCounter();
                    counterConfig.setKevin(counterConfig.getKevin() + 1);
                    configuration.setCounter(counterConfig);
                    configuration.save("/home/danny/IdeaProjects/TS_Bot/config.json");



                    // Main contents of the method
                    Main.api.sendChannelMessage(
                            "Kevin! Brünette mit fetten Hupen ist für dich unterwegs\n"
                            + "Kevin war wieder rallig! Zum " + counterKevin + ". mal kam heute eine Nutte vorbei!"
                    );

                         } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            private void printSteven() {
                try {
                    // loads v
                    Configuration configuration = Configuration.load("/home/danny/IdeaProjects/TS_Bot/config.json");
                    int counterSteven = configuration.getCounter().getSteven();

                    Configuration.CounterConfig counterConfig = configuration.getCounter();
                    counterConfig.setSteven(counterConfig.getSteven() + 1);
                    configuration.setCounter(counterConfig);
                    configuration.save("/home/danny/IdeaProjects/TS_Bot/config.json");

                    Main.api.sendChannelMessage(
                            "Abgelehnt!!\n" + "Du bist der " + (counterSteven) + ". der fragt..."
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            private void printOnline(int id) {
                for (Client c : Main.api.getClients()) {
                    if(!c.isServerQueryClient())
                    {
                        Main.api.sendPrivateMessage(id,
                                "User " + c.getNickname() + " is in channel "
                                        + Main.getChannelNameById(c.getChannelId()));
                    }
                }
            }
            private void ChatBotLogger(int InvokerID, String command) {
                logger.info("ChatBot: User " + Main.api.getClientInfo(InvokerID).getNickname() + " activated " + command);
            }
        });
    }
}
