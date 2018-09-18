package projektovanje.net;

import projektovanje.ostalo.PropertyFileUtils;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

        protected Integer port = null;
        protected InetAddress adresaServera = null;
        ServerSocket serverSoket = null;

        public void upaliServer() throws Exception {

            port = 9000;//Integer.valueOf((PropertyFileUtils.getValue("serverPort")));
            String adresaServeraString = PropertyFileUtils.getValue("serverAddress");
            adresaServera = InetAddress.getByName(adresaServeraString);
            serverSoket = new ServerSocket(port);
        }

        public void cekajKlijente() throws Exception {
            while(true){
                Socket klijent = serverSoket.accept();
                new ServerThread(klijent).start();
            }
        }
}
