package projektovanje.net;

import jdk.jshell.spi.ExecutionControl;
import projektovanje.bin.nalog.Nalog;
import projektovanje.db.ConnectionPool;

import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

public class ServerThread extends Thread{

    Socket klijentSoket = null;
    PrintWriter out = null;
    BufferedReader in = null;
    Connection konekcijaNaBazu= null;
    Nalog nalogTrenutnogKorisnika;

    public ServerThread(Socket klijent) throws IOException, SQLException {
        konekcijaNaBazu = ConnectionPool.getInstance().checkOut();
        klijentSoket = klijent;
        in = new BufferedReader(new InputStreamReader(klijentSoket.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(klijentSoket.getOutputStream()),true);
    }

    @Override
    public void run(){
        System.out.println("I did nothing");
    }
}
