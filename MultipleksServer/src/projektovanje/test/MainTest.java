package projektovanje.test;

import projektovanje.bin.oprema.Artikal;
import projektovanje.bin.racun.Stavka;
import projektovanje.bin.transakcije.Racun;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dbDAO.DBDAORacun;
import projektovanje.dto.DTORacun;
import projektovanje.dto.DTOStavka;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class MainTest {

    public static void main(String[] args)throws Exception{
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/multipleks", "root", "admin");
        Zaposleni z = new Zaposleni(1);
        DBDAORacun dao = new DBDAORacun();
        List<Stavka>stavke = new ArrayList<>();
        Artikal a1 = new Artikal(1,"Smoki",5,4.3,"tip","123456",z);
        Artikal a2 = new Artikal(2,"Cips",4,3.0,"jestivi","65432",z);
        Stavka s1 = new Stavka(1,3,5.4,a1);
        Stavka s2 = new Stavka(2,4,6.5,a2);
        stavke.add(s1);
        stavke.add(s2);
        Racun racun = new Racun(1,stavke,new java.util.Date(),565.55,z);
        System.out.println("pokusavama procitati sve:");
        ArrayList<DTORacun> stavkaIzBaze = (ArrayList<DTORacun>) dao.citajIzBaze(c);
        stavkaIzBaze.forEach(x->System.out.println(x.getRacun()));

        System.out.println("Pokusavam dodati u bazu");
        dao.upisiUBazu(new DTORacun(racun),c);
        System.out.println("Dodao");
        System.out.println("pokusavam opet procitati sve:");
        stavkaIzBaze = (ArrayList<DTORacun>) dao.citajIzBaze(c);
        stavkaIzBaze.forEach(x->System.out.println(x.getRacun()));

        System.out.println("Pokusavam update");
        racun.setUkupndaCijena(546.0);
        dao.azurirajBazu(new DTORacun(racun),c);

        System.out.println("Update gotov opet citam");

        stavkaIzBaze = (ArrayList<DTORacun>) dao.citajIzBaze(c);
        stavkaIzBaze.forEach(x->System.out.println(x.getRacun()));

        System.out.println("all done");
    }
}
