package projektovanje.test;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dbDAO.DBDAOZaposleni;
import projektovanje.dto.DTOZaposleni;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Date;

public class MainTestClass {
    public static void main(String[] args)throws Exception{
        Plata plata = new Plata(1,1.5,2.5,3.5,4.5,5.5,6.5,7.5,8.5,9.5,10.5,11.5,12.5,13.5,new Date(),new Date());
        Nalog test = new Nalog("T3","T3");
       Zaposleni coa = new Zaposleni(5,null,"Boss2","Fukara2","0609554160006",test);
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
        coadto.getZaposleni().setPlata(plata);
        dbz.azurirajBazu(coadto,c);
        System.out.println("updating done");

        sviZaposleni = (ArrayList<DTOZaposleni>) dbz.citajIzBaze(c);
        sviZaposleni.stream().forEach(x-> System.out.println(x.getZaposleni()));
        System.out.println("Everything done");
        c.close();
    }
}
