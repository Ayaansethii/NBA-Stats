package SearchLeagues.Endpoint;

import io.kubemq.sdk.basic.ServerAddressNotSuppliedException;
import SearchLeagues.Business.Messaging;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLException;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyAppServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Runnable r = () -> {
            System.out.println("🚀 Starting KubeMQ listener thread...");
            try {
                Messaging.Receiving_Events_Store("league_channel");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        new Thread(r).start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Listener destroyed");
    }
}
