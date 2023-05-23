import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventType;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

public class ChatBot {
    /*
    public static void loader() {
        final int clientId = load.api.whoAmI().getId();

        load.api.registerEvent(TS3EventType.TEXT_SERVER);
        load.api.addTS3Listeners(new TS3EventAdapter() {
            @Override
            public void onTextMessage(TextMessageEvent e) {
                super.onTextMessage(e);

                if (e.getTargetMode() == TextMessageTargetMode.CLIENT && e.getInvokerId() != clientId) {
                    Client c = load.api.getClientInfo(e.getInvokerId());
                    int channel = c.getChannelId();

                    String message = e.getMessage().toLowerCase();
                    load.api.moveClient(clientId, channel);


                    switch (message) {
                        case "!aus":
                            load.query.exit();
                            break;
                        case "!kevin":
                            //variablen.counters.kevin_counter++;
                            //load.api.sendChannelMessage("Finn! Brünette mit fetten Hupen ist für dich unterwegs\n"
                            //+ "Kevin war wieder rallig! Zum " + variablen.counters.kevin_counter + ". mal kam heute eine Nutte vorbei!");
                            break;
                        case "!online":
                            ClientInfo.loader_produktiv(e.getInvokerId());
                            break;
                        case "!steven":
                            //variablen.counters.steven_counter++;
                            //load.api.sendChannelMessage("Abgelehnt!!\n" +
                            //"Du bist der " + variablen.counters.steven_counter + ". der fragt...");
                            break;
                        case "!stefan":
                            load.api.sendChannelMessage("Bin mal essen!");
                            break;
                        case "!help":
                            load.api.sendPrivateMessage(e.getInvokerId(),
                                    "Überischt aller Befehle:\n" +
                                    "!help --> gibt eine Übersicht aller Befehle\n" +
                                    "!online --> Auskunft wer alles online ist und im welchen Channel sich der User befindet\n" +
                                    "!kevin | !steven | !stefan | !chief | !karsten | !jonas --> nutzen auf eigene Gefahr"
                                    );
                            break;
                        case "!chief" :
                            load.api.sendChannelMessage("Micha sagt: Wir haben CHieftain noch zuhause!!");
                            break;
                        case "!danny" :
                            load.api.sendChannelMessage("Kaaaarsten!!");
                            break;
                        case "!karsten":
                            load.api.sendChannelMessage("Dannnnnnny!!");
                            break;
                        case "!jonas" :
                            load.api.sendChannelMessage("WAAAS!!");
                            break;
                        case "!patchnotes" :
                            changelog.patch(e.getInvokerId());
                            break;
                        default:
                            load.api.sendPrivateMessage(e.getInvokerId(), "Falscher Befehl! gebe !help ein, um alle Befehle einzusehen");
                    }
                }
            }
        });
    }
}

class changelog
{
    public static void patch (int ID)
    {
        load.api.sendPrivateMessage(ID,"Version 1.2");
        load.api.sendPrivateMessage(ID,
                "ChatBot: weitere Befehle wurden hinzugefügt. Um eine Übersicht zu bekommen, gebe im ChatBot !help ein\n"
                + "Support: Es gibt nun einen Support Raum, wenn man in diesen Channel beitrit bekommen alle aus der Leitung eine Benachrichtigung, dass jemand Support braucht\n"
                + "Afk Mover: es werden jetzt auch User in AFK gemoved, die länger als 2 Minuten in der Eingangshalle sitzen"
                );
    }



     */
}
