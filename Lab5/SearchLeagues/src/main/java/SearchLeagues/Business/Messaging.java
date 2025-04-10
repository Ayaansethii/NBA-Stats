package SearchLeagues.Business;

import io.grpc.stub.StreamObserver;
import io.kubemq.sdk.basic.ServerAddressNotSuppliedException;
import io.kubemq.sdk.event.EventReceive;
import io.kubemq.sdk.event.Subscriber;
import io.kubemq.sdk.subscription.EventsStoreType;
import io.kubemq.sdk.subscription.SubscribeRequest;
import io.kubemq.sdk.subscription.SubscribeType;
import io.kubemq.sdk.tools.Converter;

import java.util.logging.Level;
import java.util.logging.Logger;

import SearchLeagues.Persistence.League_CRUD;
import SearchLeagues.Helper.League;

import javax.net.ssl.SSLException;

public class Messaging {

    public static void Receiving_Events_Store(String channelName) throws ServerAddressNotSuppliedException, SSLException {
        String clientID = "search-leagues-subscriber";
        String kubeMQAddress = System.getProperty("kubeMQAddress", "localhost:50000");
        Subscriber subscriber = new Subscriber(kubeMQAddress);

        SubscribeRequest subscribeRequest = new SubscribeRequest();
        subscribeRequest.setChannel(channelName);
        subscribeRequest.setClientID(clientID);
        subscribeRequest.setSubscribeType(SubscribeType.EventsStore);
        subscribeRequest.setEventsStoreType(EventsStoreType.StartNewOnly);
        subscribeRequest.setEventsStoreTypeValue(1); // 👈 Add this line
// ✅ Correct usage

        StreamObserver<EventReceive> streamObserver = new StreamObserver<EventReceive>() {
            @Override
            public void onNext(EventReceive value) {
                try {
                    String body = (String) Converter.FromByteArray(value.getBody());
                    System.out.println("📥 Received Event: " + body);

                    String[] parts = body.split(":");

                    if (parts.length >= 3 && parts[0].equals("LEAGUE_CREATED")) {
                        String leagueName = parts[1];
                        String managerID = parts[2];
                        String leagueID = String.valueOf(leagueName.hashCode()); // Temporary ID logic

                        League league = new League(leagueID, leagueName);
                        League_CRUD.insertLeague(league);

                        System.out.println("✅ League saved to Search DB: " + leagueName);
                    }

                } catch (Exception ex) {
                    Logger.getLogger(Messaging.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void onError(Throwable t) {
                System.out.printf("❌ onError: %s%n", t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("🛑 Event stream completed.");
            }
        };

        subscriber.SubscribeToEvents(subscribeRequest, streamObserver);
    }
}
