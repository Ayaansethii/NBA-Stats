package SearchLeagues.Endpoint;

import io.kubemq.sdk.basic.ServerAddressNotSuppliedException;
import SearchLeagues.Business.Messaging;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLException;

public class MyAppServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Runnable r = () -> {
            try {
                Messaging.Receiving_Events_Store("create_league_channel");
            } catch (ServerAddressNotSuppliedException e) {
                Logger.getLogger(MyAppServletContextListener.class.getName()).log(Level.SEVERE, null, e);
            } catch (SSLException ex) {
                Logger.getLogger(MyAppServletContextListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
        new Thread(r).start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Listener destroyed");
    }
}
