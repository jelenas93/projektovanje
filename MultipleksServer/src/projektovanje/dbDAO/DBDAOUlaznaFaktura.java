package projektovanje.dbDAO;

import projektovanje.bin.oprema.IOprema;
import projektovanje.bin.plata.Plata;
import projektovanje.bin.transakcije.UlaznaFaktura;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dto.DTOUlaznaFaktura;
import projektovanje.dto.DTOZaposleni;
import projektovanje.dto.IDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBDAOUlaznaFaktura implements IDBDAO {

    @Override
    public Boolean upisiUBazu(IDTO ulaznaFaktura, Connection konekcijaNaBazu) throws SQLException {
        DTOUlaznaFaktura lokalniDTOUlaznaFaktura = (DTOUlaznaFaktura) ulaznaFaktura;
        UlaznaFaktura lokalnaUlaznaFaktura = lokalniDTOUlaznaFaktura.getUlaznaFaktura();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("insert into UlaznaFaktura values (default,?,?,?,?,?,?,?,?)");
        ps.setInt(1,lokalnaUlaznaFaktura.getZaposleni().getIdZaposlenog());
        ps.setString(2,lokalnaUlaznaFaktura.getBrojRacina());
        ps.setString(3,lokalnaUlaznaFaktura.getVrstaTransakcije());
        ps.setString(4,lokalnaUlaznaFaktura.getJedinicaMjere());
        ps.setDouble(5,lokalnaUlaznaFaktura.getKolicina());
        ps.setDouble(6,lokalnaUlaznaFaktura.getCijena());
        ps.setString(7,lokalnaUlaznaFaktura.getKupac());
        ps.setDate(8,new java.sql.Date(lokalnaUlaznaFaktura.getDatum().getTime()));
        ps.executeUpdate();
        return true;
    }

    @Override
    public List<? extends IDTO> citajIzBaze(Connection konekcijaNaBazu) throws SQLException {
        ArrayList<DTOUlaznaFaktura> povratnaVrijednost = new ArrayList<>();
        Statement s = konekcijaNaBazu.createStatement();
        ResultSet rs = s.executeQuery("select * from ulaznaFaktura");
        while (rs.next()){
            int idFakture = rs.getInt(1);
            int idZaposlenog = rs.getInt(2);
            String brojRacuna = rs.getString(3);
            String vrstaTransakcije = rs.getString(4);
            String jedinicaMjere = rs.getString(5);
            Double kolicina = rs.getDouble(6);
            Double cijena = rs.getDouble(7);
            String kupac = rs.getString(8);
            Date datum = rs.getDate(9);
            UlaznaFaktura lokalnaUlaznaFaktura = new UlaznaFaktura(idFakture,new Zaposleni(idZaposlenog),brojRacuna,vrstaTransakcije,jedinicaMjere,kolicina,cijena,kupac,datum,null);
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
                "   brojRacuna = ?," +
                "   vrstaTransakcije = ?," +
                "   jedinicaMjere = ?," +
                "   kolicina = ?," +
                "   cijena = ?," +
                "   kupac = ?," +
                "   datum = ?" +
                "   where idFakture = ?");
        ps.setInt(1,lokalnaFaktura.getZaposleni().getIdZaposlenog());
        ps.setString(2,lokalnaFaktura.getBrojRacina());
        ps.setString(3,lokalnaFaktura.getVrstaTransakcije());
        ps.setString(4,lokalnaFaktura.getJedinicaMjere());
        ps.setDouble(5,lokalnaFaktura.getKolicina());
        ps.setDouble(6,lokalnaFaktura.getCijena());
        ps.setString(7,lokalnaFaktura.getKupac());
        ps.setDate(8,new java.sql.Date(lokalnaFaktura.getDatum().getTime()));
        ps.setInt(9,lokalnaFaktura.getIdFakute());
        ps.executeUpdate();
        return true;
    }

    @Override
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) throws SQLException {
        DTOUlaznaFaktura povratnaVrijednost = null;
        Integer idFakture = Integer.valueOf(parametarPretrage);
        PreparedStatement s = konekcijaNaBazu.prepareStatement("select * from UlaznaFaktura where idFakture = ?");
        s.setInt(1,idFakture);
        ResultSet rezultat = s.executeQuery();
        if(rezultat.next()){
            int id = rezultat.getInt(1);
            int idZaposlenog = rezultat.getInt(2);
            String brojRacuna = rezultat.getString(3);
            String vrstaTransakcije = rezultat.getString(4);
            String jedinicaMjere = rezultat.getString(5);
            Double kolicina = rezultat.getDouble(6);
            Double cijena = rezultat.getDouble(7);
            String kupac = rezultat.getString(8);
            java.util.Date datum = new java.util.Date(rezultat.getDate(9).getTime());
            povratnaVrijednost = new DTOUlaznaFaktura(new UlaznaFaktura(id,new Zaposleni(idZaposlenog),brojRacuna,
                                    vrstaTransakcije,jedinicaMjere,kolicina,cijena,kupac,datum,null));
        }
        return povratnaVrijednost;
    }

    @Override
    public List<? extends IDTO> ispisi(Connection konekcijaNaBazu) throws SQLException {
        return citajIzBaze(konekcijaNaBazu);

    }
}
