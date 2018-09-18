package projektovanje.dbDAO;

import projektovanje.dto.DTOArtikal;
import projektovanje.dto.DTOUlaznaFaktura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBDAOFakturaArtikal {

    public Boolean upisiUBazu(Integer idFakture, Integer idArtikla, Connection konekcijaNaBazu) throws SQLException {
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("insert into UlaznaFaktura_Artikal values(?,?)");
        ps.setInt(1, idArtikla);
        ps.setInt(2, idFakture);
        return true;
    }

    public List<DTOArtikal> pretraziSveArtikaleZaFakturu(Integer idFakture, Connection konekcijaNaBazu) throws SQLException {
        List<DTOArtikal> povratnaVrijednost = new ArrayList<>();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("select idArtikla from UlaznaFaktura_Artikal where idFakture = ?");
        ps.setInt(1, idFakture);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int idArtikla = rs.getInt(1);
            DTOArtikal artikal = (DTOArtikal) new DBDAOArtikal().pretraziBazu(konekcijaNaBazu, String.valueOf(idArtikla));
            povratnaVrijednost.add(artikal);
        }
        return povratnaVrijednost;
    }

    public List<DTOUlaznaFaktura> pretraziSveFaktureZaArtikal(Integer idArtikla, Connection konekcijaNaBazu) throws SQLException {
        List<DTOUlaznaFaktura> povratnaVrijednost = new ArrayList<>();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("select idFakture from UlaznaFaktura_Artikal where idArtikla = ?");
        ps.setInt(1, idArtikla);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int idFakture = rs.getInt(1);
            DTOUlaznaFaktura faktura = (DTOUlaznaFaktura) new DBDAOUlaznaFaktura().pretraziBazu(konekcijaNaBazu, String.valueOf(idFakture));
            povratnaVrijednost.add(faktura);
        }
        return povratnaVrijednost;
    }

}
