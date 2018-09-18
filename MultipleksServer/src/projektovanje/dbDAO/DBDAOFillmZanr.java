package projektovanje.dbDAO;

import projektovanje.dto.DTOFilm;
import projektovanje.dto.DTOZanr;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBDAOFillmZanr {

    public Boolean upisiUBazu(Integer idFilma,Integer idZanra, Connection konekcijaNaBazu) throws SQLException {
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("insert into filmZanr values(?,?)");
        ps.setInt(1,idFilma);
        ps.setInt(2,idZanra);
        ps.executeUpdate();
        return true;
    }

    public List<DTOZanr> pretraziSveZanroveZaFilm(Integer idFilma, Connection konekcijaNaBazu) throws SQLException {
        List<DTOZanr> povratnaVrijednost = new ArrayList<>();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("select idZanra from filmZanr where idFilma = ?");
        ps.setInt(1,idFilma);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int idZanra = rs.getInt(1);
            DTOZanr zanr =(DTOZanr) new DBDAOZanr().pretraziBazu(konekcijaNaBazu, String.valueOf(idZanra));
            povratnaVrijednost.add(zanr);
        }
        return povratnaVrijednost;
    }

    public List<DTOFilm> pretraziSveFilmoveZaZanr(Integer idZanra, Connection konekcijaNaBazu) throws SQLException {
        List<DTOFilm> povratnaVrijednost = new ArrayList<>();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("select idFilma from filmZanr where idZanra = ?");
        ps.setInt(1,idZanra);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int idFilma = rs.getInt(1);
            DTOFilm film = (DTOFilm) new DBDAOFilm().pretraziBazu(konekcijaNaBazu,String.valueOf(idFilma));
            povratnaVrijednost.add(film);
        }
        return povratnaVrijednost;
    }

    public void ukloniSveZandroveVezaneZaFilm(Connection konekcijaNaBazu, int idFilma) throws SQLException {
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("delete from FilmZanr where idFilma = ?");
        ps.setInt(1,idFilma);
        ps.execute();
    }
}
