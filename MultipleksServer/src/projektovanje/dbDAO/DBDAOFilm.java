package projektovanje.dbDAO;

import projektovanje.bin.film.Film;
import projektovanje.bin.film.Zanr;
import projektovanje.bin.zaposleni.Administrator;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dto.*;

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
        preparedStatement.executeUpdate();

        uspjesno = true;
        return uspjesno;
    }

    @Override
    public List<DTOFilm> citajIzBaze(Connection konekcijaNaBazu) throws java.sql.SQLException{
        List<DTOFilm> listaFilmova = new ArrayList<>();

        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement("select * from Film");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            int idZaposlenog = resultSet.getInt(2);
            int idFilma = resultSet.getInt(1);
            DTOZaposleni zaposleni = (DTOZaposleni) new DBDAOZaposleni().pretraziBazu(konekcijaNaBazu,String.valueOf(idZaposlenog));
            List<DTOZanr> dtoZanrovi = new DBDAOFillmZanr().pretraziSveZanroveZaFilm(idFilma, konekcijaNaBazu);
            List<Zanr> zanrovi = new ArrayList<>();
            dtoZanrovi.forEach(x->zanrovi.add(x.getZanr()));
            Film film = new Film(idFilma, zaposleni.getZaposleni(), resultSet.getString(3), resultSet.getInt(4)
                    ,  resultSet.getString(5), resultSet.getString(6) , resultSet.getString(7),zanrovi);
            DTOFilm dtoFilm = new DTOFilm(film);
            listaFilmova.add(dtoFilm);
        }

        return listaFilmova;
    }

    @Override
    public Boolean azurirajBazu(IDTO dtoFilm, Connection konekcijaNaBazu) throws java.sql.SQLException{
        Boolean uspjesno = false;
        DTOFilm lokalniDtoFilm = (DTOFilm)dtoFilm;

        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement(
                "update Film" +
                        "   set idZaposlenog = ?," +
                        "   naziv = ?," +
                        "   trajanje = ?," +
                        "   opis = ?," +
                        "   link = ?," +
                        "   tipFilma = ?" +
                        "   where idFilma = ?");
        preparedStatement.setInt(1, lokalniDtoFilm.getFilm().getZaposleni().getIdZaposlenog());
        preparedStatement.setString(2, lokalniDtoFilm.getFilm().getNaziv());
        preparedStatement.setInt(3, lokalniDtoFilm.getFilm().getTrajanje());
        preparedStatement.setString(4, lokalniDtoFilm.getFilm().getOpis());
        preparedStatement.setString(5, lokalniDtoFilm.getFilm().getLinkTrailera());
        preparedStatement.setString(6, lokalniDtoFilm.getFilm().getTipFilma());
        preparedStatement.setInt(7, lokalniDtoFilm.getFilm().getIdFilma());
        preparedStatement.executeUpdate();
        uspjesno = true;
        return uspjesno;
    }

    @Override
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) throws java.sql.SQLException{
        DTOFilm lokalniFilm;
        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement("select * from Film where idFilma = ?");
        preparedStatement.setInt(1, Integer.parseInt(parametarPretrage));
        ResultSet resultSet = preparedStatement.executeQuery();
        Film film = new Film();
        if (resultSet.next()){
            int idFilma = resultSet.getInt(1);
            film.setIdFilma(idFilma);
            int idZaposlenog = resultSet.getInt(2);
            DTOZaposleni zaposleni = (DTOZaposleni) new DBDAOZaposleni().pretraziBazu(konekcijaNaBazu,String.valueOf(idZaposlenog));
            film.setZaposleni(zaposleni.getZaposleni());
            film.setNaziv(resultSet.getString(3));
            film.setTrajanje(resultSet.getInt(4));
            film.setOpis(resultSet.getString(5));
            film.setLinkTrailera(resultSet.getString(6));
            film.setTipFilma(resultSet.getString(7));
            List<DTOZanr> dtoZanrovi = new DBDAOFillmZanr().pretraziSveZanroveZaFilm(idFilma, konekcijaNaBazu);
            List<Zanr> zanrovi = new ArrayList<>();
            dtoZanrovi.forEach(x-> zanrovi.add(x.getZanr()));
            film.setZanrovi(zanrovi);
            lokalniFilm = new DTOFilm(film);
        }else{
            lokalniFilm = null;
        }
        return lokalniFilm;
    }

    @Override
    public List<DTOFilm> ispisi(Connection konekcijaNaBazu)throws java.sql.SQLException {
        return citajIzBaze(konekcijaNaBazu);
    }
}
