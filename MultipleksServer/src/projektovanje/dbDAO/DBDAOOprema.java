package projektovanje.dbDAO;

import projektovanje.bin.oprema.Oprema;
import projektovanje.bin.sala.Sjediste;
import projektovanje.dto.DTOOprema;
import projektovanje.dto.DTOSjediste;
import projektovanje.dto.DTOZaposleni;
import projektovanje.dto.IDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBDAOOprema implements IDBDAO{
    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) throws SQLException {

        DTOOprema lokalniDTOOprema = (DTOOprema) dtoInstanca;
        Oprema lokalniOprema = lokalniDTOOprema.getOprema();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("insert into Oprema values (default,?,?,?,?,?)");
        ps.setInt(1,lokalniOprema.getBrojInventara());
        ps.setString(2,lokalniOprema.getNaziv());
        ps.setBoolean(3,lokalniOprema.getIspravnost());
        ps.setInt(4,lokalniOprema.getZaposleni().getIdZaposlenog());
        ps.setInt(5, lokalniOprema.getIdentifikator());
        ps.executeUpdate();
        return true;
    }

    @Override
    public List<DTOOprema> citajIzBaze(Connection konekcijaNaBazu) throws SQLException {
        Statement s = konekcijaNaBazu.createStatement();
        ResultSet rs = s.executeQuery("select * from Oprema");
        ArrayList<DTOOprema> povratnaVrijednost = new ArrayList<>();
        DBDAOZaposleni zaposleniDao = new DBDAOZaposleni();
        while (rs.next()){
            int idOpreme = rs.getInt(1);
            int brojInventara = rs.getInt(2);
            String naziv = rs.getString(3);
            Boolean ispravne = rs.getBoolean(4);
            Integer idZaposlenog = rs.getInt(5);
            Integer identifikator = rs.getInt(6);
            DTOZaposleni zaposleni = (DTOZaposleni) zaposleniDao.pretraziBazu(konekcijaNaBazu,String.valueOf(idZaposlenog));
            Oprema o = new Oprema(idOpreme,brojInventara,naziv,ispravne,zaposleni.getZaposleni(), identifikator);
            povratnaVrijednost.add(new DTOOprema(o));
        }
        return povratnaVrijednost;
    }

    @Override
    public Boolean azurirajBazu(IDTO dtoOprema, Connection konekcijaNaBazu) throws SQLException {
        DTOOprema lokalniDTOOprema = (DTOOprema) dtoOprema;
        Oprema lokalniOprema = lokalniDTOOprema.getOprema();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("update Oprema" +
                "   set brojInventara = ?," +
                "   naziv = ?," +
                "   ispravnost = ?," +
                "   idZaposlenog = ?," +
                "   identifikator = ?" +
                "   where idOpreme = ?");
        ps.setInt(1,lokalniOprema.getBrojInventara());
        ps.setString(2,lokalniOprema.getNaziv());
        ps.setBoolean(3,lokalniOprema.getIspravnost());
        ps.setInt(4,lokalniOprema.getZaposleni().getIdZaposlenog());
        ps.setInt(5, lokalniOprema.getIdentifikator());
        ps.setInt(6,lokalniOprema.getIdOpreme());
        ps.executeUpdate();
        return true;
    }

    public List<DTOOprema> pretraziOpremuPoNazivu(Connection konekcijaNaBazu, String parametarPretrage) throws SQLException {
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("select * from Oprema where naziv = ?");
        ps.setString(1,parametarPretrage);
        ResultSet rs = ps.executeQuery();
        List<DTOOprema> povratnaVrijednost = new ArrayList<>();
        DBDAOZaposleni zaposleniDao = new DBDAOZaposleni();
        while (rs.next()){
            int idOpreme = rs.getInt(1);
            int brojInventara = rs.getInt(2);
            String naziv = rs.getString(3);
            Boolean ispravne = rs.getBoolean(4);
            Integer idZaposlenog = rs.getInt(5);
            DTOZaposleni zaposleni = (DTOZaposleni) zaposleniDao.pretraziBazu(konekcijaNaBazu,String.valueOf(idZaposlenog));
            Oprema o = new Oprema(idOpreme,brojInventara,naziv,ispravne,zaposleni.getZaposleni(), rs.getInt(6));
            povratnaVrijednost.add(new DTOOprema(o));
        }
        return povratnaVrijednost;
    }

    @Override
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) throws SQLException {

        DTOOprema povratnaVrijednost = null;
        int idSale = Integer.valueOf(parametarPretrage);
        PreparedStatement s = konekcijaNaBazu.prepareStatement("select * from Oprema where idOpreme = ?");
        s.setInt(1,idSale);
        ResultSet rezultat = s.executeQuery();
        if(rezultat.next()){
            int id = rezultat.getInt(1);
            int brojInventara = rezultat.getInt(2);
            String naziv = rezultat.getString(3);
            Boolean ispravnost = rezultat.getBoolean(4);
            int idZaposlenog = rezultat.getInt(5);
            DTOZaposleni zaposleni = (DTOZaposleni) new DBDAOZaposleni().pretraziBazu(konekcijaNaBazu,String.valueOf(idZaposlenog));
            Oprema ret = new Oprema(id,brojInventara,naziv,ispravnost,zaposleni.getZaposleni(), rezultat.getInt(6));
            povratnaVrijednost = new DTOOprema(ret);
        }
        return povratnaVrijednost;
    }

    @Override
    public List<DTOOprema> ispisi(Connection konekcijaNaBazu) throws SQLException {
        return citajIzBaze(konekcijaNaBazu);
    }

    public DTOOprema pretraziOpremuPoIdentifikatoru(Connection konekcijaNaBazu, Integer identifikator) throws SQLException {
        DTOOprema dtoOprema = null;
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("select * from oprema where identifikator = ?");
        ps.setInt(1,identifikator);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
        {
            Integer pomInt = rs.getInt(5);
            DTOZaposleni dtoZaposleni = (DTOZaposleni)new DBDAOZaposleni().pretraziBazu(konekcijaNaBazu,pomInt.toString());
            Oprema oprema = new Oprema(rs.getInt(1), rs.getInt(2), rs.getString(3),
                    rs.getBoolean(4), dtoZaposleni.getZaposleni(), rs.getInt(6));
            dtoOprema = new DTOOprema(oprema);
        }
        return dtoOprema;
    }

}
