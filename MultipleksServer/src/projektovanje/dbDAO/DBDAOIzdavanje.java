package projektovanje.dbDAO;

import projektovanje.bin.film.Film;
import projektovanje.bin.izdavanje.Izdavanje;
import projektovanje.bin.karta.Karta;
import projektovanje.bin.projekcija.Projekcija;
import projektovanje.bin.sala.Sala;
import projektovanje.bin.sala.Sjediste;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dto.DTOIzdavanje;
import projektovanje.dto.IDTO;

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
                "insert into Izdavanje (?, ?, ?, ?, ?, ?)");
        preparedStatement.setInt(1,lokalniDtoIzdavanje.getIzdavanje().getKarta().getIdKarte());
        preparedStatement.setInt(2, lokalniDtoIzdavanje.getIzdavanje().getSjediste().getIdSjedista());
        preparedStatement.setInt(3, lokalniDtoIzdavanje.getIzdavanje().getSala().getIdSale());
        preparedStatement.setInt(4, lokalniDtoIzdavanje.getIzdavanje().getProjekcija().getIdProjekcije());
        preparedStatement.setInt(5, lokalniDtoIzdavanje.getIzdavanje().getFilm().getIdFilma());
        preparedStatement.setInt(6, lokalniDtoIzdavanje.getIzdavanje().getZaposleni().getIdZaposlenog());
        preparedStatement.executeUpdate();

        uspjesno = true;
        return uspjesno;
    }

    @Override
    public List<DTOIzdavanje> citajIzBaze(Connection konekcijaNaBazu)throws java.sql.SQLException {
        List<DTOIzdavanje> listaIzdavanja = new ArrayList<>();

        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement("select * from Izdavanje");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Karta karta = new Karta();
            Sjediste sjediste = new Sjediste();
            Sala sala = new Sala();
            Projekcija projekcija = new Projekcija();
            Film film = new Film();
            Zaposleni zaposleni = new Zaposleni();

            karta.setIdKarte(resultSet.getInt(1));
            sjediste.setIdSjedista(resultSet.getInt(2));
            sala.setIdSale(resultSet.getInt(3));
            projekcija.setIdProjekcije(resultSet.getInt(4));
            film.setIdFilma(resultSet.getInt(5));
            zaposleni.setIdZaposlenog(resultSet.getInt(6));

            Izdavanje izdavanje = new Izdavanje(karta, sjediste, sala, projekcija, film, zaposleni);
            DTOIzdavanje dtoIzdavanje = new DTOIzdavanje(izdavanje);
            listaIzdavanja.add(dtoIzdavanje);
        }
        return listaIzdavanja;
    }

    @Override
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu) throws SQLException {
        return null;
    }

    @Override
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) {
        return null;
    }

    @Override
    public List<DTOIzdavanje> ispisi(Connection konekcijaNaBazu)throws java.sql.SQLException {
        return citajIzBaze(konekcijaNaBazu);
    }
}
