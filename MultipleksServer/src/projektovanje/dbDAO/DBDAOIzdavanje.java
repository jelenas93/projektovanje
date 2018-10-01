package projektovanje.dbDAO;

import projektovanje.bin.film.Film;
import projektovanje.bin.izdavanje.Izdavanje;
import projektovanje.bin.karta.Karta;
import projektovanje.bin.projekcija.Projekcija;
import projektovanje.bin.sala.Sala;
import projektovanje.bin.sala.Sjediste;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dto.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBDAOIzdavanje implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoIzdavanje, Connection konekcijaNaBazu) throws java.sql.SQLException {
        Boolean uspjesno = false;

        DTOIzdavanje lokalniDtoIzdavanje = (DTOIzdavanje) dtoIzdavanje;
        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement(
                "insert into Izdavanje values (?, ?, ?, ?, ?, ?)");
        preparedStatement.setInt(1,lokalniDtoIzdavanje.getIzdavanje().getKarta().getIdKarte());
        preparedStatement.setInt(2, lokalniDtoIzdavanje.getIzdavanje().getSjediste().getIdSjedista());
        preparedStatement.setInt(3, lokalniDtoIzdavanje.getIzdavanje().getSala().getIdSale());
        preparedStatement.setInt(4, lokalniDtoIzdavanje.getIzdavanje().getProjekcija().getIdProjekcije());
        preparedStatement.setInt(5, lokalniDtoIzdavanje.getIzdavanje().getProjekcija().getFilm().getIdFilma());
        preparedStatement.setInt(6, lokalniDtoIzdavanje.getIzdavanje().getZaposleni().getIdZaposlenog());
        preparedStatement.executeUpdate();

        uspjesno = true;
        return uspjesno;
    }

    @Override
    public List<DTOIzdavanje> citajIzBaze(Connection konekcijaNaBazu) throws java.sql.SQLException, IOException {
        List<DTOIzdavanje> listaIzdavanja = new ArrayList<>();

        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement("select * from Izdavanje");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            int idKarte = resultSet.getInt(1);
            int idSjedista = resultSet.getInt(2);
            int idSale = resultSet.getInt(3);
            int idProjekcije = resultSet.getInt(4);
            int idFilma = resultSet.getInt(5);
            int idZaposlenog = resultSet.getInt(6);

            DTOKarta dtoKarta = (DTOKarta) new DBDAOKarta().pretraziBazu(konekcijaNaBazu,String.valueOf(idKarte));
            Karta karta = dtoKarta.getKarta();
            DTOSala dtoSala = (DTOSala) new DBDAOSala().pretraziBazu(konekcijaNaBazu,String.valueOf(idSale));
            Sala sala = dtoSala.getSala();
            DTOSjediste dtoSjediste =(DTOSjediste) new DBDAOSjediste().dohvatiSjedisteIzSale(konekcijaNaBazu,String.valueOf(idSjedista),sala);
            Sjediste sjediste = dtoSjediste.getSjediste();
            DTOProjekcija dtoProjekcija = (DTOProjekcija)new  DBDAOProjekcija().pretraziBazu(konekcijaNaBazu,String.valueOf(idProjekcije));
            Projekcija projekcija = dtoProjekcija.getProjekcija();
            DTOZaposleni dtoZaposleni = (DTOZaposleni) new DBDAOZaposleni().pretraziBazu(konekcijaNaBazu,String.valueOf(idZaposlenog));
            Zaposleni zaposleni = dtoZaposleni.getZaposleni();

            Izdavanje izdavanje = new Izdavanje(karta, sjediste, sala, projekcija,zaposleni);
            DTOIzdavanje dtoIzdavanje = new DTOIzdavanje(izdavanje);
            listaIzdavanja.add(dtoIzdavanje);
        }
        return listaIzdavanja;
    }

    @Override
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu) throws SQLException {
        return true;
    }

    @Override
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) {
        return null;
    }

    @Override
    public List<DTOIzdavanje> ispisi(Connection konekcijaNaBazu) throws java.sql.SQLException, IOException {
        return citajIzBaze(konekcijaNaBazu);
    }

    public Boolean ukloniIzdavanje (DTOIzdavanje izdavanje,Connection konekcijaNaBazu) throws SQLException {
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("delete from Izdavanje where idKarte = ?");
        Integer idKarte = izdavanje.getIzdavanje().getKarta().getIdKarte();
        ps.setInt(1,idKarte);
        ps.executeUpdate();
        return true;
    }

    public List<? extends IDTO> pretraziBazuPoProjekciji(Connection konekcijaNaBazu, String parametarPretrage) throws SQLException, IOException {
        List<DTOIzdavanje> listaIzdavanja = new ArrayList<>();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("select * from Izdavanje where idProjekcije = ?");
        Integer idProjekcije = Integer.valueOf(parametarPretrage);
        DTOProjekcija dtoProjekcija = (DTOProjekcija)new  DBDAOProjekcija().pretraziBazu(konekcijaNaBazu,String.valueOf(idProjekcije));
        ps.setInt(1,idProjekcije);
        ResultSet resultSet = ps.executeQuery();
        while(resultSet.next()){
            int idKarte = resultSet.getInt(1);
            int idSjedista = resultSet.getInt(2);
            int idSale = resultSet.getInt(3);
            int idProjekcijeLok = resultSet.getInt(4);
            int idFilma = resultSet.getInt(5);
            int idZaposlenog = resultSet.getInt(6);

            DTOKarta dtoKarta = (DTOKarta) new DBDAOKarta().pretraziBazu(konekcijaNaBazu,String.valueOf(idKarte));
            Karta karta = dtoKarta.getKarta();
            DTOSala dtoSala = (DTOSala) new DBDAOSala().pretraziBazu(konekcijaNaBazu,String.valueOf(idSale));
            Sala sala = dtoSala.getSala();
            DTOSjediste dtoSjediste =(DTOSjediste) new DBDAOSjediste().dohvatiSjedisteIzSale(konekcijaNaBazu,String.valueOf(idSjedista),sala);
            Sjediste sjediste = dtoSjediste.getSjediste();
            Projekcija projekcija = dtoProjekcija.getProjekcija();
            DTOZaposleni dtoZaposleni = (DTOZaposleni) new DBDAOZaposleni().pretraziBazu(konekcijaNaBazu,String.valueOf(idZaposlenog));
            Zaposleni zaposleni = dtoZaposleni.getZaposleni();

            Izdavanje izdavanje = new Izdavanje(karta, sjediste, sala, projekcija,zaposleni);
            DTOIzdavanje dtoIzdavanje = new DTOIzdavanje(izdavanje);
            listaIzdavanja.add(dtoIzdavanje);
        }
        return listaIzdavanja;
    }
}
