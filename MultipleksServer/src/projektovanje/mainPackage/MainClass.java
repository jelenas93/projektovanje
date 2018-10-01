package projektovanje.mainPackage;

import projektovanje.net.Server;
import projektovanje.ostalo.Logovanje;
import sun.util.logging.PlatformLogger;

import java.util.logging.Level;

public class MainClass {
    public static void main(String[] args)throws Exception {
        Server server = new Server();
        server.upaliServer();
        server.cekajKlijente();
    }
}
