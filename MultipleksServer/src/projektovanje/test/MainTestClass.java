package projektovanje.test;

import projektovanje.bin.oprema.Artikal;
import projektovanje.bin.nalog.Nalog;
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
       /* List<DTOArtikal> lista = new ArrayList<>();
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
        }*/
        System.out.println("Done1");
        Plata plata = new Plata(1,1.5,2.5,3.5,4.5,5.5,6.5,7.5,8.5,9.5,10.5,11.5,12.5,13.5,new Date(),new Date());
        Nalog test = new Nalog("T4","T4");
       Zaposleni coa = new Zaposleni(0,null,"Boss5","Fukara4","4609554160006",test);
       DTOZaposleni coadto = new DTOZaposleni(coa);
        DBDAOZaposleni dbz = new DBDAOZaposleni();
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/multipleks", "root", "admin");
        dbz.upisiUBazu(coadto,c);
        System.out.println("Writin to db done");

        ArrayList<DTOZaposleni> sviZaposleni = (ArrayList<DTOZaposleni>) dbz.citajIzBaze(c);
        sviZaposleni.stream().forEach(x-> System.out.println(x.getZaposleni()));
        System.out.println("Reading done, trying to update");
        coadto.getZaposleni().setIme("aeiou");
        System.out.println("novo ime: "+coadto.getZaposleni().getIme());
        coadto.getZaposleni().setPlata(plata);
        dbz.azurirajBazu(coadto,c);
        System.out.println("updating done");

        sviZaposleni = (ArrayList<DTOZaposleni>) dbz.citajIzBaze(c);
        sviZaposleni.stream().forEach(x-> System.out.println(x.getZaposleni()));
        System.out.println("Everything done");
        c.close();
    }
}
