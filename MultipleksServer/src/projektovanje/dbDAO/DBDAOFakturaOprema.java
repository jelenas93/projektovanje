package projektovanje.dbDAO;

import projektovanje.dto.DTOOprema;
import projektovanje.dto.DTOUlaznaFaktura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBDAOFakturaOprema {

    public Boolean upisiUBazu(Integer idFakture, Integer idOpreme, Connection konekcijaNaBazu) throws SQLException {
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("insert into UlaznaFaktura_Oprema values(?,?)");
        ps.setInt(1, idOpreme);
        ps.setInt(2, idFakture);
        return true;
    }

    public List<DTOOprema> pretraziSvuOpremuZaFakturu(Integer idFakture, Connection konekcijaNaBazu) throws SQLException {
        List<DTOOprema> povratnaVrijednost = new ArrayList<>();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("select idOpreme from UlaznaFaktura_Oprema where idFakture = ?");
        ps.setInt(1, idFakture);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int idOpreme = rs.getInt(1);
            DTOOprema Oprema = (DTOOprema) new DBDAOOprema().pretraziBazu(konekcijaNaBazu, String.valueOf(idOpreme));
            povratnaVrijednost.add(Oprema);
        }
        return povratnaVrijednost;
    }

    public List<DTOUlaznaFaktura> pretraziSveFaktureZaOpremu(Integer idOpreme, Connection konekcijaNaBazu) throws SQLException {
        List<DTOUlaznaFaktura> povratnaVrijednost = new ArrayList<>();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("select idFakture from UlaznaFaktura_Oprema where idOpreme = ?");
        ps.setInt(1, idOpreme);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int idFakture = rs.getInt(1);
            DTOUlaznaFaktura faktura = (DTOUlaznaFaktura) new DBDAOUlaznaFaktura().pretraziBazu(konekcijaNaBazu, String.valueOf(idFakture));
            povratnaVrijednost.add(faktura);
        }
        return povratnaVrijednost;
    }
}
