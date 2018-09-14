package projektovanje.dbDAO;

import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dto.DTOZaposleni;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DBDAOZaposleni implements IDBDAO {

    @Override
    public synchronized Boolean upisiUBazu(IDTO zaposleni, Connection konekcijaNaBazu) throws SQLException {
        Boolean uspjesno = false;
        DTOZaposleni lokalniDTOZaposleni = (DTOZaposleni) zaposleni;
        Zaposleni lokalniZaposleni = lokalniDTOZaposleni.getZaposleni();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("insert into Zaposleni values (default,?,?,?,?,?,?)");
        ps.setInt(1,lokalniZaposleni.getPlata().getIDPlate());
        ps.setString(2,lokalniZaposleni.getIme());
        ps.setString(3,lokalniZaposleni.getPrezime());
        ps.setString(4,lokalniZaposleni.getJMBG());
        ps.setBoolean(5,true);
        ps.setString(6,"test1");
        ps.executeUpdate();
        return uspjesno;
    }

    @Override
    public List<? extends IDTO> citajIzBaze(Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<? extends IDTO> pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) {
        return null;
    }

    @Override
    public List<? extends IDTO> ispisi(Connection konekcijaNaBazu) {
        return null;
    }
}
