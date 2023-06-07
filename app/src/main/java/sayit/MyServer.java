package sayit;

import com.sun.net.httpserver.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class MyServer {
    private static final int SERVER_PORT = 8100;
    private static final String SERVER_HOSTNAME = "localhost";

    MyServer(AccountUIToServer connecter, ServerToDB database) throws IOException{
        
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)
        Executors.newFixedThreadPool(10);
        
        HttpServer server = HttpServer.create(
            new InetSocketAddress(SERVER_HOSTNAME, SERVER_PORT),
            0
        );

        server.createContext("/", new RequestHandler(connecter, database));
        server.createContext("/account", new RequestAccountHandler(connecter, database));
        server.setExecutor(threadPoolExecutor);
        server.start();
        System.out.println("Started Server");
    }
    
}
