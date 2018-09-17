package projektovanje.test;

import projektovanje.bin.film.Ponuda;
import projektovanje.bin.oprema.Artikal;
import projektovanje.bin.racun.Stavka;
import projektovanje.bin.transakcije.Racun;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dbDAO.DBDAOPonuda;
import projektovanje.dbDAO.DBDAORacun;
import projektovanje.dto.DTOPonuda;
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
        DBDAOPonuda dao = new DBDAOPonuda();
        //List<Stavka>stavke = new ArrayList<>();
        //Artikal a1 = new Artikal(1,"Smoki",5,4.3,"tip","123456",z);
        //Artikal a2 = new Artikal(2,"Cips",4,3.0,"jestivi","65432",z);
        //Stavka s1 = new Stavka(1,3,5.4,a1);
        //Stavka s2 = new Stavka(2,4,6.5,a2);
        //stavke.add(s1);
        //stavke.add(s2);
        //Racun racun = new Racun(1,stavke,new java.util.Date(),565.55,z);
        System.out.println("pokusavama procitati sve:");
        ArrayList<DTOPonuda> stavkaIzBaze = (ArrayList<DTOPonuda>) dao.citajIzBaze(c);
        stavkaIzBaze.forEach(x->System.out.println(x.getPonuda()));

        Ponuda p = new Ponuda(1,null,new java.util.Date(),z);
        System.out.println("Pokusavam dodati u bazu");
        dao.upisiUBazu(new DTOPonuda(p),c);
        System.out.println("Dodao");
        System.out.println("pokusavam opet procitati sve:");
        stavkaIzBaze = (ArrayList<DTOPonuda>) dao.citajIzBaze(c);
        stavkaIzBaze.forEach(x->System.out.println(x.getPonuda()));

        System.out.println("Pokusavam update");
        p.setDatumPonude(new java.util.Date(System.currentTimeMillis()+100000000));
        dao.azurirajBazu(new DTOPonuda(p),c);

        System.out.println("Update gotov opet citam");

        stavkaIzBaze = (ArrayList<DTOPonuda>) dao.citajIzBaze(c);
        stavkaIzBaze.forEach(x->System.out.println(x.getPonuda()));

        System.out.println("all done");
    }
}
