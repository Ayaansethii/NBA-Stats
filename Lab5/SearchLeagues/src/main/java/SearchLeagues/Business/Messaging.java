package SearchLeagues.Business;

import io.grpc.stub.StreamObserver;
import io.kubemq.sdk.basic.ServerAddressNotSuppliedException;
import io.kubemq.sdk.event.EventReceive;
import io.kubemq.sdk.event.Subscriber;
import io.kubemq.sdk.subscription.EventsStoreType;
import io.kubemq.sdk.subscription.SubscribeRequest;
import io.kubemq.sdk.subscription.SubscribeType;
import io.kubemq.sdk.tools.Converter;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import SearchLeagues.Persistence.League_CRUD;
import SearchLeagues.Helper.Team;
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
        subscribeRequest.setEventsStoreType(EventsStoreType.StartAtSequence);
        subscribeRequest.setEventsStoreTypeValue(0);  // 0 means start from *newest* messages only

        StreamObserver<EventReceive> streamObserver = new StreamObserver<EventReceive>() {
            @Override
            public void onNext(EventReceive value) {
                try {
                    String body = (String) Converter.FromByteArray(value.getBody());
                    System.out.println("📥 Received Event: " + body);
                    String[] parts = body.split(":", 4);  // limit to 4 parts to allow empty teams

                    if (parts.length >= 3 && parts[0].equals("LEAGUE_CREATED")) {
                        String leagueID = parts[1];
                        String leagueName = parts[2];
                        String[] teamNames = (parts.length == 4 && !parts[3].isEmpty()) ? parts[3].split(",") : new String[0];

                        League league = new League(leagueID, leagueName);
                        for (String teamName : teamNames) {
                            if (!teamName.trim().isEmpty()) {
                                league.addTeam(new Team(teamName));
                            }
                        }

                        League_CRUD.insertLeague(league);  // still works even if no teams
                        System.out.println("✅ League saved to Search DB: " + leagueName + " with " + teamNames.length + " teams");
                    }

                } catch (Exception ex) {
                    Logger.getLogger(Messaging.class.getName()).log(Level.SEVERE, null, ex);
                }
            }


            @Override
            public void onError(Throwable t) {
                System.out.printf("❌ onError: %s", t.getMessage());
            }

            @Override
            public void onCompleted() {}
        };

        subscriber.SubscribeToEvents(subscribeRequest, streamObserver);
    }
}
