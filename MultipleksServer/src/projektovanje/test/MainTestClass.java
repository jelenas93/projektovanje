package projektovanje.test;

import projektovanje.bin.plata.Plata;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dbDAO.DBDAOZaposleni;
import projektovanje.dto.DTOZaposleni;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;

public class MainTestClass {
    public static void main(String[] args)throws Exception{
        Plata plata = new Plata(1,1.5,2.5,3.5,4.5,5.5,6.5,7.5,8.5,9.5,10.5,11.5,12.5,13.5,new Date(),new Date());
        Zaposleni coa = new Zaposleni("Aleksandar","Bosancic","0609994160006",plata,5,null);
        DTOZaposleni coadto = new DTOZaposleni(coa);
        DBDAOZaposleni dbz = new DBDAOZaposleni();
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/multipleks", "root", "admin");
        dbz.upisiUBazu(coadto,c);
        System.out.println("Done");
    }
}
