import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventType;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.io.IOException;
import java.util.logging.Logger;

public class ChatBot {
    private static final Logger logger = PublicLogger.getLogger();
    private static boolean RecognizedCommand = true;
    private static String FILE_PATH = "/opt/ts_bot/json_files/counter.json";

    public static void handleMessages(TS3Api api) {
        // Get our own client ID by running the "whoami" command
        final int clientId = api.whoAmI().getId();
        System.out.println(api.getClientInfo(clientId));

        api.registerEvent(TS3EventType.TEXT_PRIVATE);
        api.addTS3Listeners(new TS3EventAdapter() {
            @Override
            public void onTextMessage(TextMessageEvent e) {
                if (e.getTargetMode() == TextMessageTargetMode.CLIENT && e.getInvokerId() != clientId) {
                    String command = e.getMessage().toLowerCase();

                    //query change channel if is not in the same as the invoker
                    if (api.getClientInfo(clientId).getChannelId() != api.getClientInfo(e.getInvokerId()).getChannelId()) {
                        api.moveQuery(api.getClientInfo(e.getInvokerId()).getChannelId());
                    }

                    //set RecocnizedCommand true before each passage of the Swicthes
                    RecognizedCommand = true;

                    switch (command) {
                        case "!kevin":
                            printKevin();
                            break;
                        case "!steven":
                            printSteven();
                            break;
                        case "!stefan":
                            api.sendChannelMessage("Bin mal essen!");
                            break;
                        case "!help":
                            printHelp(e.getInvokerId());
                            break;
                        case "!chief" :
                            api.sendChannelMessage("Micha sagt: Wir haben CChieftain noch zuhause!!");
                            break;
                        case "!danny" :
                            api.sendChannelMessage("Dannnnnnny!!");
                            break;
                        case "!karsten":
                            api.sendChannelMessage("Kaaaarsten!!");
                            break;
                        case "!jonas" :
                            api.sendChannelMessage("WAAAS!!");
                            break;
                        case "!changelog" :
                            printChangeLog(e.getInvokerId());
                            break;
                        case "!online":
                            printOnline(e.getInvokerId());
                            break;
                        case "!supp" :
                            HandleSupp();
                        default:
                            api.sendPrivateMessage(e.getInvokerId(), "Falscher Befehl! gebe !help ein, um alle Befehle einzusehen");
                            RecognizedCommand = false;
                    }

                    if (RecognizedCommand) {
                        ChatBotLogger(e.getInvokerId(), command);
                    } else {
                        ChatBotLogger(e.getInvokerId(), "Falscher Befehl");
                    }
                }
            }
            private void printHelp(int id) {
                api.sendPrivateMessage(id,
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
                api.sendPrivateMessage(id,
                        """
                        Version 2.2
                        rebuild support
                        added config file for counter
                        """
                );
            }
            private void printKevin() {

                // loads variables from config.json
                try {
                    Configuration configuration = Configuration.load(FILE_PATH);
                    int counterKevin = configuration.getCounter().getKevin();

                    Configuration.CounterConfig counterConfig = configuration.getCounter();
                    counterConfig.setKevin(counterConfig.getKevin() + 1);
                    configuration.setCounter(counterConfig);
                    configuration.save(FILE_PATH);



                    // Main contents of the method
                    api.sendChannelMessage(
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
                    Configuration configuration = Configuration.load(FILE_PATH);
                    int counterSteven = configuration.getCounter().getSteven();

                    Configuration.CounterConfig counterConfig = configuration.getCounter();
                    counterConfig.setSteven(counterConfig.getSteven() + 1);
                    configuration.setCounter(counterConfig);
                    configuration.save(FILE_PATH);

                    api.sendChannelMessage(
                            "Abgelehnt!!\n" + "Du bist der " + (counterSteven) + ". der fragt..."
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            private void printOnline(int id) {
                for (Client c : api.getClients()) {
                    if(!c.isServerQueryClient())
                    {
                        api.sendPrivateMessage(id,
                                "User " + c.getNickname() + " is in channel "
                                        + Main.getChannelNameById(c.getChannelId(), api));
                    }
                }
            }
            private void ChatBotLogger(int InvokerID, String command) {
                logger.info("ChatBot: User " + api.getClientInfo(InvokerID).getNickname() + " activated " + command);
            }

            private void HandleSupp() {
                //
            }
        });
    }
}
