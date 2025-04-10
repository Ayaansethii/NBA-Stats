package Business;

import io.kubemq.sdk.basic.ServerAddressNotSuppliedException;
import io.kubemq.sdk.event.Event;
import io.kubemq.sdk.tools.Converter;

import javax.net.ssl.SSLException;
import java.io.IOException;

public class Messaging {

    public static void sendMessage(String message) throws IOException {
        String channelName = "league_channel";
        String clientID = "league-publisher";
        String kubeMQAddress = System.getProperty("kubeMQAddress", "kubemq:50000");


        System.out.println(" Sending message to KubeMQ: " + message);
        System.out.println(" Using address: " + kubeMQAddress);

        io.kubemq.sdk.event.Channel channel = new io.kubemq.sdk.event.Channel(
                channelName, clientID, false, kubeMQAddress
        );

        channel.setStore(true);
        Event event = new Event();
        event.setBody(Converter.ToByteArray(message));
        event.setEventId("event-store");

        try {
            channel.SendEvent(event);
            System.out.println(" Message sent.");
        } catch (SSLException | ServerAddressNotSuppliedException e) {
            System.out.println("Messaging error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
