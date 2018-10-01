package projektovanje.services;

import projektovanje.bin.oprema.Artikal;
import projektovanje.bin.racun.Stavka;
import projektovanje.bin.transakcije.Racun;
import projektovanje.dbDAO.DBDAOArtikal;
import projektovanje.dbDAO.DBDAORacun;
import projektovanje.dbDAO.DBDAOStavka;
import projektovanje.dto.DTOArtikal;
import projektovanje.dto.DTORacun;
import projektovanje.dto.DTOStavka;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ServisZaRacune {
    public static void dodajRacun(String msg, Connection konekcijaNaBazu, ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException, SQLException {
        String odgovor = new String("WHICHONE");
        out.writeObject(odgovor);
        DTORacun racunDto = (DTORacun)in.readObject();
        Racun racun = racunDto.getRacun();
        List<Stavka> stavke = racun.getStavka();
        DBDAORacun racuni = new DBDAORacun();
        racuni.upisiUBazu(racunDto,konekcijaNaBazu);
        int idRacuna = racuni.zadnjiUmetnutiId(konekcijaNaBazu);
        DBDAOArtikal artikalDAO = new DBDAOArtikal();
        DBDAOStavka stavkaDAO = new DBDAOStavka();
        for (Stavka stavka : stavke) {
            stavka.setIdRacuna(idRacuna);
            stavkaDAO.upisiUBazu(new DTOStavka(stavka),konekcijaNaBazu);
            Artikal art = stavka.getArtikal();
            art.setKolicinaNaStanju(art.getKolicinaNaStanju()-stavka.getKolicina());
            artikalDAO.azurirajBazu(new DTOArtikal(art),konekcijaNaBazu);
        }
        odgovor = new String("OK");
        out.writeObject(odgovor);
    }

    public static void slanjeSvihRacuna(String msg, Connection konekcijaNaBazu, ObjectOutputStream out, ObjectInputStream in) throws IOException, SQLException {
        List<DTORacun> racuni = (List<DTORacun>) new DBDAORacun().ispisi(konekcijaNaBazu);
        out.writeObject(racuni);
    }
}
