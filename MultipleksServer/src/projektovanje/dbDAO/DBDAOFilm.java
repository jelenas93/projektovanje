package projektovanje.dbDAO;

import projektovanje.bin.film.Film;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dto.DTOFilm;
import projektovanje.dto.IDTO;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DBDAOFilm implements IDBDAO {

    @Override
    public Boolean upisiUBazu(IDTO dtoFilm, Connection konekcijaNaBazu) throws java.sql.SQLException {
        Boolean uspjesno = false;

        DTOFilm LokalniDtoFilm = (DTOFilm) dtoFilm;
        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement("insert into Film values(default, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setInt(1,LokalniDtoFilm.getFilm().getZaposleni().getIdZaposlenog());
        preparedStatement.setString(2,LokalniDtoFilm.getFilm().getNaziv());
        preparedStatement.setInt(3,LokalniDtoFilm.getFilm().getTrajanje());
        preparedStatement.setString(4, LokalniDtoFilm.getFilm().getOpis());
        preparedStatement.setString(5, LokalniDtoFilm.getFilm().getLinkTrailera());
        preparedStatement.setString(6, LokalniDtoFilm.getFilm().getTipFilma());
        preparedStatement.executeQuery();

        uspjesno = true;
        return uspjesno;
    }

    @Override
    public List<DTOFilm> citajIzBaze(Connection konekcijaNaBazu) throws java.sql.SQLException{
        List<DTOFilm> listaFilmova = new ArrayList<>();

        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement("select * from Film");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Zaposleni zaposleni = new Zaposleni();
            zaposleni.setIdZaposlenog(resultSet.getInt(2));
            Film film = new Film(resultSet.getInt(1), zaposleni, resultSet.getString(3), resultSet.getInt(4)
                    ,  resultSet.getString(5), resultSet.getString(6) , resultSet.getString(7) );
            DTOFilm dtoFilm = new DTOFilm(film);
            listaFilmova.add(dtoFilm);
        }

        return listaFilmova;
    }

    @Override
<<<<<<< HEAD
    public Boolean azurirajBazu(IDTO dtoFilm, Connection konekcijaNaBazu) throws java.sql.SQLException{
        Boolean uspjesno = false;
        DTOFilm lokalniDtoFilm = (DTOFilm)dtoFilm;

        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement(
                "update Film" +
                        "set idZaposlenog = ?," +
                        "    naziv = ?," +
                        "    trajanje = ?," +
                        "    opis = ?," +
                        "    link = ?," +
                        "    tipFilma = ?" +
                        "where idFilma = ?");
        preparedStatement.setInt(1, lokalniDtoFilm.getFilm().getZaposleni().getIdZaposlenog());
        preparedStatement.setString(2, lokalniDtoFilm.getFilm().getNaziv());
        preparedStatement.setInt(3, lokalniDtoFilm.getFilm().getTrajanje());
        preparedStatement.setString(4, lokalniDtoFilm.getFilm().getOpis());
        preparedStatement.setString(5, lokalniDtoFilm.getFilm().getLinkTrailera());
        preparedStatement.setString(6, lokalniDtoFilm.getFilm().getTipFilma());
        preparedStatement.setInt(7, lokalniDtoFilm.getFilm().getIdFilma());

        uspjesno = true;
        return uspjesno;
=======
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu) {
        return null;
>>>>>>> 119457b79ea8ebc88d4b6e2705a6ba9d3cd5687a
    }

    @Override
    public List<DTOFilm> pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) {
        return null;
    }

    @Override
    public List<DTOFilm> ispisi(Connection konekcijaNaBazu)throws java.sql.SQLException {
        return citajIzBaze(konekcijaNaBazu);
    }
}
