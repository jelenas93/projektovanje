package projektovanje.test;

import projektovanje.bin.oprema.Artikal;
import projektovanje.bin.plata.Plata;
import projektovanje.bin.zaposleni.Administrator;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dbDAO.DBDAOAdministrator;
import projektovanje.dbDAO.DBDAOArtikal;
import projektovanje.dbDAO.DBDAOZaposleni;
import projektovanje.dto.DTOAdministrator;
import projektovanje.dto.DTOArtikal;
import projektovanje.dto.DTOZaposleni;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MainTestClass {
    public static void main(String[] args)throws Exception{
        List<DTOArtikal> lista = new ArrayList<>();
        Zaposleni zaposleni = new Zaposleni();
        zaposleni.setIdZaposlenog(2);
        Artikal artikal = new Artikal(3,"voce",7,299.9,"hrana","1100111", zaposleni);
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/multipleks", "root", "marko123");
        DBDAOArtikal dbdaoArtikal = new DBDAOArtikal();
        DTOArtikal dtoArtikal = new DTOArtikal(artikal);
        dbdaoArtikal.upisiUBazu(dtoArtikal, c);
        dbdaoArtikal.azurirajBazu(dtoArtikal, c);
        lista = dbdaoArtikal.citajIzBaze(c);
        Iterator<DTOArtikal> iterator = lista.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next().getArtikal().getNaziv());
        }
        System.out.println("Done");
    }
}
