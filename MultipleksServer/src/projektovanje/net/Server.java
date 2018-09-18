package projektovanje.net;

import projektovanje.db.ConnectionPool;
import projektovanje.ostalo.Logovanje;
import projektovanje.ostalo.PropertyFileUtils;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;

public class Server {
        private static Logovanje logServera;
        static
        {
            logServera = new Logovanje(new Server());
        }
        protected Integer port = null;
        protected InetAddress adresaServera = null;
        ServerSocket serverSoket = null;

        public void upaliServer() throws Exception {
            port = Integer.valueOf((PropertyFileUtils.getValue("serverPort")));
            String adresaServeraString = PropertyFileUtils.getValue("serverAddress");
            adresaServera = InetAddress.getByName(adresaServeraString);
            serverSoket = new ServerSocket(port);
            ConnectionPool.getInstance();
            logServera.logujDogadjaj(Level.FINEST, this, "Server se uspjesno upalio.");
        }

        public void cekajKlijente() throws Exception {
            while(true){
                Socket klijent = serverSoket.accept();
                new ServerThread(klijent).start();
            }
        }
}
