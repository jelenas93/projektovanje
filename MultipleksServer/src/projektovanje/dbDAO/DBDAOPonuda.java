package projektovanje.dbDAO;

import projektovanje.bin.film.Film;
import projektovanje.bin.film.Ponuda;
import projektovanje.bin.oprema.Artikal;
import projektovanje.bin.racun.Stavka;
import projektovanje.dto.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBDAOPonuda implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) throws SQLException {
        DTOPonuda lokalniDTOPonuda = (DTOPonuda) dtoInstanca;
        Ponuda lokalniPonuda = lokalniDTOPonuda.getPonuda();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("insert into Ponuda values (default,?,?)");
        ps.setDate(1,new Date(lokalniPonuda.getDatumPonude().getTime()));
        ps.setInt(2,lokalniPonuda.getZaposleni().getIdZaposlenog());
        ps.executeUpdate();
        Integer idPonude = zadnjiUmetnutiId(konekcijaNaBazu);
        DBDAOFilmPonuda filmPonudaDao = new DBDAOFilmPonuda();
        lokalniPonuda.getFilm().forEach(x-> {
            try {
                filmPonudaDao.upisiUBazu(x.getIdFilma(),idPonude,konekcijaNaBazu);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return true;
    }

    @Override
    public List<DTOPonuda> citajIzBaze(Connection konekcijaNaBazu) throws SQLException, IOException {
        Statement s = konekcijaNaBazu.createStatement();
        ResultSet rs = s.executeQuery("select * from Ponuda");
        ArrayList<DTOPonuda> povratnaVrijednost = new ArrayList<>();
        DBDAOFilmPonuda filmPonudaDao = new DBDAOFilmPonuda();
        while (rs.next()){
            Integer idPonude = rs.getInt(1);
            java.util.Date datumPonude = new java.util.Date(rs.getDate(2).getTime());
            Integer idZaposlenog = rs.getInt(3);
            DTOZaposleni zaposleni = (DTOZaposleni) new DBDAOZaposleni().pretraziBazu(konekcijaNaBazu,String.valueOf(idZaposlenog));
            List<DTOFilm> dtoFilmovi = filmPonudaDao.pretraziSveFilmoveZaPonuda(idPonude,konekcijaNaBazu);
            List<Film> filmovi = new ArrayList<>();
            dtoFilmovi.forEach(x->filmovi.add(x.getFilm()));
            Ponuda ponuda = new Ponuda(idPonude,filmovi,datumPonude,zaposleni.getZaposleni());
            povratnaVrijednost.add(new DTOPonuda(ponuda));
        }
        return povratnaVrijednost;
    }

    @Override
    public Boolean azurirajBazu(IDTO noviPonuda, Connection konekcijaNaBazu) throws SQLException {
        DTOPonuda lokalniDTOPonuda = (DTOPonuda) noviPonuda;
        Ponuda lokalniPonuda = lokalniDTOPonuda.getPonuda();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("update Ponuda" +
                "   set datumPonude = ?," +
                "   idZaposlenog = ?" +
                "   where idPonude = ?");
        ps.setDate(1,new Date(lokalniPonuda.getDatumPonude().getTime()));
        ps.setInt(2,lokalniPonuda.getZaposleni().getIdZaposlenog());
        ps.setInt(3,lokalniPonuda.getIdPonude());
        ps.executeUpdate();
        return true;
    }

    @Override
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) throws SQLException, IOException {
        DTOPonuda povratnaVrijednost = null;
        int idPonude = Integer.valueOf(parametarPretrage);
        PreparedStatement s = konekcijaNaBazu.prepareStatement("select * from Ponuda where idPonude = ?");
        s.setInt(1,idPonude);
        ResultSet rezultat = s.executeQuery();
        DBDAOFilmPonuda filmPonudaDao = new DBDAOFilmPonuda();
        if(rezultat.next()){
            int id = rezultat.getInt(1);
            java.util.Date datumPonude = new java.util.Date(rezultat.getDate(2).getTime());
            int idZaposlenog = rezultat.getInt(3);
            DTOZaposleni zaposleni = (DTOZaposleni) new DBDAOZaposleni().pretraziBazu(konekcijaNaBazu,String.valueOf(idZaposlenog));
            List<DTOFilm> dtoFilmovi = filmPonudaDao.pretraziSveFilmoveZaPonuda(idPonude,konekcijaNaBazu);
            List<Film> filmovi = new ArrayList<>();
            dtoFilmovi.forEach(x->filmovi.add(x.getFilm()));
            povratnaVrijednost = new DTOPonuda(new Ponuda(id,filmovi,datumPonude,zaposleni.getZaposleni()));
        }
        return povratnaVrijednost;
    }

    @Override
    public List<DTOPonuda> ispisi(Connection konekcijaNaBazu) throws SQLException, IOException {
        return citajIzBaze(konekcijaNaBazu);
    }
}
