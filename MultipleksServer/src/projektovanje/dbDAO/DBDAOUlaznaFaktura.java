package projektovanje.dbDAO;

import projektovanje.bin.oprema.Artikal;
import projektovanje.bin.oprema.IOprema;
import projektovanje.bin.oprema.Oprema;
import projektovanje.bin.plata.Plata;
import projektovanje.bin.transakcije.UlaznaFaktura;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dto.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DBDAOUlaznaFaktura implements IDBDAO {

    @Override
    public Boolean upisiUBazu(IDTO ulaznaFaktura, Connection konekcijaNaBazu) throws SQLException {
        DTOUlaznaFaktura lokalniDTOUlaznaFaktura = (DTOUlaznaFaktura) ulaznaFaktura;
        UlaznaFaktura lokalnaUlaznaFaktura = lokalniDTOUlaznaFaktura.getUlaznaFaktura();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("insert into UlaznaFaktura values (default,?,?,?,?,?,?,?,?,?,?,?)");
        ps.setInt(1,lokalnaUlaznaFaktura.getZaposleni().getIdZaposlenog());
        ps.setString(2,lokalnaUlaznaFaktura.getJedinicaMjere());
        ps.setInt(3,lokalnaUlaznaFaktura.getKolicina());
        ps.setInt(4,lokalnaUlaznaFaktura.getIdentifikator());
        ps.setString(5,lokalnaUlaznaFaktura.getNazivRobe());
        ps.setDouble(6,lokalnaUlaznaFaktura.getProdajnaVrijednost());
        ps.setDouble(7,lokalnaUlaznaFaktura.getProdajnaCijena());
        ps.setInt(8,lokalnaUlaznaFaktura.getBrojFakture());
        ps.setDate(9,new java.sql.Date(lokalnaUlaznaFaktura.getDatumFakture().getTime()));
        ps.setString(10, lokalnaUlaznaFaktura.getDobavljac());
        ps.setString(11, lokalnaUlaznaFaktura.getBarKod());
        ps.executeUpdate();
        return true;
    }

    @Override
    public List<? extends IDTO> citajIzBaze(Connection konekcijaNaBazu) throws SQLException {
        ArrayList<DTOUlaznaFaktura> povratnaVrijednost = new ArrayList<>();
        Statement s = konekcijaNaBazu.createStatement();
        ResultSet rs = s.executeQuery("select * from ulaznaFaktura");
        DBDAOZaposleni zaposleniDao = new DBDAOZaposleni();
        DBDAOFakturaArtikal faktArtDao = new DBDAOFakturaArtikal();
        DBDAOFakturaOprema faktOprDao = new DBDAOFakturaOprema();
        while (rs.next()){
            int idFakture = rs.getInt(1);
            int idZaposlenog = rs.getInt(2);
            String jedinicaMjere = rs.getString(3);
            Integer kolicina = rs.getInt(4);
            Integer identifikator = rs.getInt(5);
            String nazivRobe = rs.getString(6);
            Double prodajnaVrijednost = rs.getDouble(7);
            Double prodajnaCijena = rs.getDouble(8);
            Integer brojFakture = rs.getInt(9);
            java.util.Date datumFakture = new java.util.Date(rs.getDate(10).getTime());
            String dobavljac = rs.getString(11);
            String barKod = rs.getString(12);
            DTOZaposleni zaposleniDTO =(DTOZaposleni) zaposleniDao.pretraziBazu(konekcijaNaBazu,String.valueOf(idZaposlenog));
            UlaznaFaktura lokalnaUlaznaFaktura = new UlaznaFaktura(idFakture,zaposleniDTO.getZaposleni(),jedinicaMjere,kolicina,
                    identifikator,nazivRobe,prodajnaVrijednost,prodajnaCijena, null, brojFakture, datumFakture, dobavljac, barKod);
            povratnaVrijednost.add(new DTOUlaznaFaktura(lokalnaUlaznaFaktura));
        }
        return povratnaVrijednost;
    }

    @Override
    public Boolean azurirajBazu(IDTO novaUlaznaFaktura, Connection konekcijaNaBazu) throws SQLException {
        DTOUlaznaFaktura lokalnaDTOUlaznaFaktura = (DTOUlaznaFaktura) novaUlaznaFaktura;
        UlaznaFaktura lokalnaFaktura = lokalnaDTOUlaznaFaktura.getUlaznaFaktura();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("update UlaznaFaktura" +
                "   set idZaposlenog = ?," +
                "   jedinicaMjere = ?," +
                "   kolicina = ?," +
                "   identifikator = ?," +
                "   nazivRobe = ?," +
                "   prodajnaVrijednost = ?," +
                "   prodajnaCijena = ?," +
                "   brojFakture = ?," +
                "   datumFakture = ?," +
                "   dobavljac = ?," +
                "   barKod = ?" +
                "   where idFakture = ?");
        ps.setInt(1,lokalnaFaktura.getZaposleni().getIdZaposlenog());
        ps.setString(2,lokalnaFaktura.getJedinicaMjere());
        ps.setInt(3,lokalnaFaktura.getKolicina());
        ps.setInt(4,lokalnaFaktura.getIdentifikator());
        ps.setString(5,lokalnaFaktura.getNazivRobe());
        ps.setDouble(6,lokalnaFaktura.getProdajnaVrijednost());
        ps.setDouble(7,lokalnaFaktura.getProdajnaCijena());
        ps.setInt(8, lokalnaFaktura.getBrojFakture());
        ps.setDate(9,new java.sql.Date(lokalnaFaktura.getDatumFakture().getTime()));
        ps.setString(10, lokalnaFaktura.getDobavljac());
        ps.setString(11, lokalnaFaktura.getBarKod());
        ps.setInt(12,lokalnaFaktura.getIdFakute());

        ps.executeUpdate();
        return true;
    }

    @Override
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) throws SQLException {
        DTOUlaznaFaktura povratnaVrijednost = null;
        Integer idFakture = Integer.valueOf(parametarPretrage);
        PreparedStatement s = konekcijaNaBazu.prepareStatement("select * from UlaznaFaktura where idFakture = ?");
        s.setInt(1,idFakture);
        ResultSet rs = s.executeQuery();
        DBDAOZaposleni zaposleniDao = new DBDAOZaposleni();
        if(rs.next()){
            idFakture = rs.getInt(1);
            int idZaposlenog = rs.getInt(2);
            String jedinicaMjere = rs.getString(3);
            Integer kolicina = rs.getInt(4);
            Integer identifikator = rs.getInt(5);
            String nazivRobe = rs.getString(6);
            Double prodajnaVrijednost = rs.getDouble(7);
            Double prodajnaCijena = rs.getDouble(8);
            java.sql.Date datumFakture = rs.getDate("datumFakture");
            java.util.Date datumFaktureUtil = new java.util.Date(datumFakture.getTime());
            DTOZaposleni zaposleniDTO =(DTOZaposleni) zaposleniDao.pretraziBazu(konekcijaNaBazu,String.valueOf(idZaposlenog));
            UlaznaFaktura lokalnaUlaznaFaktura = new UlaznaFaktura(idFakture,zaposleniDTO.getZaposleni(),jedinicaMjere,kolicina,
                    identifikator,nazivRobe,prodajnaVrijednost,prodajnaCijena, null, rs.getInt("brojfakture"),
                    datumFaktureUtil, rs.getString("dobavljac"), rs.getString("barKod"));
            povratnaVrijednost = new DTOUlaznaFaktura(lokalnaUlaznaFaktura);
        }
        return povratnaVrijednost;
    }

    @Override
    public List<? extends IDTO> ispisi(Connection konekcijaNaBazu) throws SQLException {
        return citajIzBaze(konekcijaNaBazu);

    }

}
