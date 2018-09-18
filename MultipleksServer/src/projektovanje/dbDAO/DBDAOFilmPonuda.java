package projektovanje.dbDAO;

import projektovanje.dto.DTOFilm;
import projektovanje.dto.DTOPonuda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBDAOFilmPonuda {

    public Boolean upisiUBazu(Integer idFilma,Integer idPonude, Connection konekcijaNaBazu) throws SQLException {
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("insert into filmPonuda values(?,?)");
        ps.setInt(1, idFilma);
        ps.setInt(2, idPonude);
        ps.executeUpdate();
        return true;
    }

    public List<DTOPonuda> pretraziSvePonudeZaFilm(Integer idFilma, Connection konekcijaNaBazu) throws SQLException {
        List<DTOPonuda> povratnaVrijednost = new ArrayList<>();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("select idPonude from filmPonuda where idFilma = ?");
        ps.setInt(1,idFilma);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int idPonude = rs.getInt(1);
            DTOPonuda Ponuda =(DTOPonuda) new DBDAOPonuda().pretraziBazu(konekcijaNaBazu, String.valueOf(idPonude));
            povratnaVrijednost.add(Ponuda);
        }
        return povratnaVrijednost;
    }

    public List<DTOFilm> pretraziSveFilmoveZaPonuda(Integer idPonude, Connection konekcijaNaBazu) throws SQLException {
        List<DTOFilm> povratnaVrijednost = new ArrayList<>();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("select idFilma from filmPonuda where idPonude = ?");
        ps.setInt(1,idPonude);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int idFilma = rs.getInt(1);
            DTOFilm film = (DTOFilm) new DBDAOFilm().pretraziBazu(konekcijaNaBazu,String.valueOf(idFilma));
            povratnaVrijednost.add(film);
        }
        return povratnaVrijednost;
    }
}
