package projektovanje.dbDAO;

import projektovanje.bin.projekcija.Projekcija;
import projektovanje.bin.racun.Stavka;
import projektovanje.dto.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBDAOProjekcija implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoProjekcija, Connection konekcijaNaBazu) throws SQLException {
        DTOProjekcija lokalniDtoProjekcija = (DTOProjekcija) dtoProjekcija;
        Projekcija lokalniProjekcija = lokalniDtoProjekcija.getProjekcija();
        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement("insert into Projekcija values(default, ?, ?, ?,?)");
        preparedStatement.setInt(1, lokalniProjekcija.getFilm().getIdFilma());
        preparedStatement.setTimestamp(2, new Timestamp(lokalniProjekcija.getVrijeme().getTime()));
        preparedStatement.setInt(3, lokalniProjekcija.getZaposleni().getIdZaposlenog());
        preparedStatement.setInt(4,lokalniProjekcija.getIdRepertoara());
        preparedStatement.executeUpdate();
        return true;
    }

    @Override
    public List<DTOProjekcija> citajIzBaze(Connection konekcijaNaBazu) throws SQLException {
        Statement s = konekcijaNaBazu.createStatement();
        ResultSet rs = s.executeQuery("select * from Projekcija");
        ArrayList<DTOProjekcija> povratnaVrijednost = new ArrayList<>();
        while (rs.next()) {
            Integer idProjekcija = rs.getInt(1);
            Integer idFilma = rs.getInt(2);
            java.util.Date vrijemeFilma = new java.util.Date(rs.getTimestamp(3).getTime());
            Integer idZaposlenog = rs.getInt(4);
            Integer idRepertoara = rs.getInt(5);
            DTOZaposleni zaposleni = (DTOZaposleni) new DBDAOZaposleni().pretraziBazu(konekcijaNaBazu, String.valueOf(idZaposlenog));
            DTOFilm film = (DTOFilm) new DBDAOFilm().pretraziBazu(konekcijaNaBazu, String.valueOf(idFilma));
            Projekcija procitaniProjekcija = new Projekcija(idProjekcija, film.getFilm(), vrijemeFilma, zaposleni.getZaposleni(),idRepertoara);
            povratnaVrijednost.add(new DTOProjekcija(procitaniProjekcija));
        }
        return povratnaVrijednost;
    }

    @Override

    public Boolean azurirajBazu(IDTO dtoProjekcija, Connection konekcijaNaBazu) throws SQLException {
        DTOProjekcija lokalnaDTOProjekcija = (DTOProjekcija) dtoProjekcija;
        Projekcija lokalnaProjekcija = lokalnaDTOProjekcija.getProjekcija();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("update Projekcija" +
                "   set idFilma = ?," +
                "   vrijemeFilma = ?," +
                "   idZaposlenog = ?" +
                "   idRepertoara = ?" +
                "   where idProjekcije = ?");
        ps.setInt(1, lokalnaProjekcija.getFilm().getIdFilma());
        ps.setTimestamp(2, new Timestamp(lokalnaProjekcija.getVrijeme().getTime()));
        ps.setInt(3, lokalnaProjekcija.getZaposleni().getIdZaposlenog());
        ps.setInt(4,lokalnaProjekcija.getIdRepertoara());
        ps.setInt(5, lokalnaProjekcija.getIdProjekcije());
        ps.executeUpdate();
        return true;
    }

    @Override
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) throws SQLException {
        DTOProjekcija povratnaVrijednost = null;
        int idProjekcije = Integer.valueOf(parametarPretrage);
        PreparedStatement s = konekcijaNaBazu.prepareStatement("select * from Projekcija where idProjekcije = ?");
        s.setInt(1, idProjekcije);
        ResultSet rezultat = s.executeQuery();
        if (rezultat.next()) {
            int id = rezultat.getInt(1);
            int idFilma = rezultat.getInt(2);
            java.util.Date vrijeme = new java.util.Date(rezultat.getDate(3).getTime());
            int idZaposlenog = rezultat.getInt(4);
            int idRepertoara = rezultat.getInt(5);
            DTOZaposleni zaposleni = (DTOZaposleni) new DBDAOZaposleni().pretraziBazu(konekcijaNaBazu, String.valueOf(idZaposlenog));
            DTOFilm film = (DTOFilm) new DBDAOFilm().pretraziBazu(konekcijaNaBazu, String.valueOf(idFilma));
            Projekcija ret = new Projekcija(id, film.getFilm(), vrijeme, zaposleni.getZaposleni(),idRepertoara);
            povratnaVrijednost = new DTOProjekcija(ret);
        }
        return povratnaVrijednost;
    }

    @Override
    public List<DTOProjekcija> ispisi(Connection konekcijaNaBazu) throws SQLException {
        return citajIzBaze(konekcijaNaBazu);
    }

    public List<DTOProjekcija> pretraziSveProjekcijeZaRepertoar(Integer idRepertoara, Connection konekcijaNaBazu) throws SQLException {
        List<DTOProjekcija> povratnaVrijednost = new ArrayList<>();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("select * from Projekcija where idRepertoara = ?");
        ps.setInt(1, idRepertoara);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int idProjekcije = rs.getInt(1);
            DTOProjekcija projekcija = (DTOProjekcija) pretraziBazu(konekcijaNaBazu, String.valueOf(idProjekcije));
            povratnaVrijednost.add(projekcija);
        }
        return povratnaVrijednost;
    }
}
