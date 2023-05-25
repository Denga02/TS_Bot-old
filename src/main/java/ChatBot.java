import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventType;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.io.IOException;

public class ChatBot {

    public static void handleMessages() {


        // Get our own client ID by running the "whoami" command
        final int clientId = main.api.whoAmI().getId();

        main.api.registerEvent(TS3EventType.TEXT_PRIVATE);

        // Register the event listener
        main.api.addTS3Listeners(new TS3EventAdapter() {

            @Override
            public void onTextMessage(TextMessageEvent e) {

                // Only react to channel messages not sent by the query itself
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
                            main.api.sendChannelMessage("Bin mal essen!");
                            break;
                        case "!help":
                            printHelp(e.getInvokerId());
                            break;
                        case "!chief" :
                            main.api.sendChannelMessage("Micha sagt: Wir haben CHieftain noch zuhause!!");
                            break;
                        case "!danny" :
                            main.api.sendChannelMessage("Kaaaarsten!!");
                            break;
                        case "!karsten":
                            main.api.sendChannelMessage("Dannnnnnny!!");
                            break;
                        case "!jonas" :
                            main.api.sendChannelMessage("WAAAS!!");
                            break;
                        case "!patchnotes" :
                            printChangeLog(e.getInvokerId());
                            break;
                        default:
                            main.api.sendPrivateMessage(e.getInvokerId(), "Falscher Befehl! gebe !help ein, um alle Befehle einzusehen");
                    }
                }
            }
            private void printHelp(int id) {
                main.api.sendPrivateMessage(id, """
                        Überischt aller Befehle:
                        !help --> gibt eine Übersicht aller Befehle
                        !online --> Auskunft wer alles online ist und im welchen Channel sich der User befindet
                        !kevin | !steven | !stefan | !chief | !karsten | !jonas --> nutzen auf eigene Gefahr"""
                );
            }

            private void printChangeLog(int id) {
                main.api.sendPrivateMessage(id, """
                        Version 1.2
                        Überischt aller Befehle:
                        !help --> gibt eine Übersicht aller Befehle
                        !online --> Auskunft wer alles online ist und im welchen Channel sich der User befindet
                        !kevin | !steven | !stefan | !chief | !karsten | !jonas --> nutzen auf eigene Gefahr"""
                );
            }

            private void printKevin() {
                // loads variables from config.json
                try {
                    Configuration configuration = Configuration.load("/home/ansible/IdeaProjects/TS_Bot/target/config.json");
                    int counterKevin = configuration.getCounter().getKevin();

                    main.api.sendChannelMessage("Kevin! Brünette mit fetten Hupen ist für dich unterwegs\n"
                            + "Kevin war wieder rallig! Zum " + counterKevin + ". mal kam heute eine Nutte vorbei!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            private void printSteven() {
                // loads variables from config.json
                try {
                    Configuration configuration = Configuration.load("/home/ansible/IdeaProjects/TS_Bot/target/config.json");
                    int counterSteven = configuration.getCounter().getSteven();

                    Configuration.CounterConfig counterConfig = configuration.getCounter();
                    counterConfig.setKevin(counterConfig.getSteven() + 1);
                    configuration.save("/home/ansible/IdeaProjects/TS_Bot/target/config.json");

                    main.api.sendChannelMessage("Abgelehnt!!\n" +
                            "Du bist der " + counterSteven + ". der fragt...");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }



}
