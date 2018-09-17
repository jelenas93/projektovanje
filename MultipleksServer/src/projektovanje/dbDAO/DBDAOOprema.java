package projektovanje.dbDAO;

import projektovanje.bin.oprema.Oprema;
import projektovanje.bin.sala.Sjediste;
import projektovanje.dto.DTOOprema;
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
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("insert into Oprema values (default,?,?,?,?)");
        ps.setInt(1,lokalniOprema.getBrojInventara());
        ps.setString(2,lokalniOprema.getNaziv());
        ps.setBoolean(3,lokalniOprema.getIspravnost());
        ps.setInt(4,lokalniOprema.getZaposleni().getIdZaposlenog());
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
            DTOZaposleni zaposleni = (DTOZaposleni) zaposleniDao.pretraziBazu(konekcijaNaBazu,String.valueOf(idZaposlenog));
            Oprema o = new Oprema(idOpreme,brojInventara,naziv,ispravne,zaposleni.getZaposleni());
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
                "   naziv = ?" +
                "   ispravnost = ?" +
                "   idZaposlenog = ?" +
                "   where idOprema = ?");
        ps.setInt(1,lokalniOprema.getBrojInventara());
        ps.setString(2,lokalniOprema.getNaziv());
        ps.setBoolean(3,lokalniOprema.getIspravnost());
        ps.setInt(4,lokalniOprema.getZaposleni().getIdZaposlenog());
        ps.setInt(5,lokalniOprema.getIdOpreme());
        ps.executeUpdate();
        return true;
    }


    @Override
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) {
        return null;
    }

    @Override
    public List<DTOOprema> ispisi(Connection konekcijaNaBazu) {
        return null;
    }
}
