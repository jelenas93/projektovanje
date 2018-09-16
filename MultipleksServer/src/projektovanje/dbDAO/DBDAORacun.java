package projektovanje.dbDAO;

import projektovanje.bin.racun.Stavka;
import projektovanje.bin.transakcije.Racun;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dto.DTORacun;
import projektovanje.dto.DTOZaposleni;
import projektovanje.dto.IDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBDAORacun implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoRacun, Connection konekcijaNaBazu) throws SQLException {
        DTORacun lokalniDtoRacun = (DTORacun) dtoRacun;
        Racun lokalniRacun = lokalniDtoRacun.getRacun();
        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement("insert into Racun values(default, ?, ?, ?)");
        preparedStatement.setTimestamp(1,new Timestamp(lokalniRacun.getDatumIzdavanja().getTime()));
        preparedStatement.setDouble(2,lokalniRacun.getUkupndaCijena());
        preparedStatement.setInt(3,lokalniRacun.getZaposleni().getIdZaposlenog());
        preparedStatement.executeUpdate();
        return true;
    }

    @Override
    public List<? extends IDTO> citajIzBaze(Connection konekcijaNaBazu) throws SQLException {

        Statement s = konekcijaNaBazu.createStatement();
        ResultSet rs = s.executeQuery("select * from Racun");
        ArrayList<DTORacun> povratnaVrijednost = new ArrayList<>();
        while (rs.next()){
            Integer idRacuna = rs.getInt(1);
            java.util.Date datumIzdavanja = new Date(rs.getTime(2).getTime());
            Double ukupnaCijena = rs.getDouble(3);
            Integer idZaposlenog = rs.getInt(4);
            DTOZaposleni zaposleni = (DTOZaposleni) new DBDAOZaposleni().pretraziBazu(konekcijaNaBazu,String.valueOf(idZaposlenog));
            List<Stavka> stavke = new DBDAOStavka().dohvatiSveStavkeSaRacuna(idRacuna,konekcijaNaBazu);
            Racun procitaniRacun = new Racun(idRacuna,stavke,datumIzdavanja,ukupnaCijena,zaposleni.getZaposleni());
            povratnaVrijednost.add(new DTORacun(procitaniRacun));
        }
        return povratnaVrijednost;
    }

    @Override
    public Boolean azurirajBazu(IDTO novaRacun, Connection konekcijaNaBazu) throws SQLException {
        DTORacun lokalnaDTORacun = (DTORacun) novaRacun;
        Racun lokalnaRacun = lokalnaDTORacun.getRacun();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("update Racun" +
                "   set datumIzdavanja = ?," +
                "   ukupnaCijena = ?," +
                "   idZaposlenog = ?" +
                "   where idRacuna = ?");
        ps.setTimestamp(1,new Timestamp(lokalnaRacun.getDatumIzdavanja().getTime()));
        ps.setDouble(2,lokalnaRacun.getUkupndaCijena());
        ps.setInt(3,lokalnaRacun.getZaposleni().getIdZaposlenog());
        ps.setInt(4,lokalnaRacun.getIdRacuna());
        ps.executeUpdate();
        return true;
    }

    @Override
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) throws SQLException {
        DTORacun povratnaVrijednost = null;
        int idRacuna = Integer.valueOf(parametarPretrage);
        PreparedStatement s = konekcijaNaBazu.prepareStatement("select * from Racun where idRacuna = ?");
        s.setInt(1,idRacuna);
        ResultSet rezultat = s.executeQuery();
        if(rezultat.next()){
            int id = rezultat.getInt(1);
            java.util.Date vrijeme = new Date(rezultat.getTime(2).getTime());
            Double ukupnaCijena = rezultat.getDouble(3);
            int idZaposlenog = rezultat.getInt(4);
            DTOZaposleni zaposleni = (DTOZaposleni) new DBDAOZaposleni().pretraziBazu(konekcijaNaBazu,String.valueOf(idZaposlenog));
            List<Stavka> stavke = new DBDAOStavka().dohvatiSveStavkeSaRacuna(idRacuna,konekcijaNaBazu);
            povratnaVrijednost = new DTORacun(new Racun(id,stavke,vrijeme,ukupnaCijena,zaposleni.getZaposleni()));
        }
        return povratnaVrijednost;
    }

    @Override
    public List<? extends IDTO> ispisi(Connection konekcijaNaBazu) throws SQLException {
        return citajIzBaze(konekcijaNaBazu);
    }
}
