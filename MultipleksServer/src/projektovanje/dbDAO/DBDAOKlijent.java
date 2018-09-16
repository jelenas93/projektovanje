package projektovanje.dbDAO;

import projektovanje.bin.klijent.Klijent;
import projektovanje.dto.DTOKlijent;
import projektovanje.dto.DTONalog;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBDAOKlijent implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) throws SQLException {
        DTOKlijent lokalniDTOZaposleni = (DTOKlijent) dtoInstanca;
        Klijent lokalniKlijent = lokalniDTOZaposleni.getKlijent();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("insert into Klijent values (default, ?, ?, ?, ?)");
        ps.setString(1, lokalniKlijent.getKorisnickiNalog().getKorisnickiNalog());
        ps.setString(2, lokalniKlijent.getIme());
        ps.setString(3, lokalniKlijent.getPrezime());
        ps.setString(4, lokalniKlijent.getEmail());
        ps.executeUpdate();
        return true;
    }

    @Override
    public List<DTOKlijent> citajIzBaze(Connection konekcijaNaBazu)throws java.sql.SQLException {
        List<DTOKlijent> listaKlijenta = new ArrayList<>();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("select * from klijent");
        ResultSet rezultat = ps.executeQuery();
        while(rezultat.next()){
            DTONalog nalogDTO = (DTONalog) new DBDAONalog().pretraziBazu(konekcijaNaBazu,rezultat.getString(2));
            listaKlijenta.add(new DTOKlijent(new Klijent(rezultat.getInt(1), nalogDTO.getNalog(),
                    rezultat.getString(3), rezultat.getString(4), rezultat.getString(5))));
        }
        return listaKlijenta;
    }

    @Override
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu) throws SQLException {
        DTOKlijent lokalniKlijent = (DTOKlijent)list;
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("" +
                "update Klijent " +
                "set korisnickoIme = ?," +
                "    ime = ?," +
                "    prezime = ?," +
                "    email = ?" +
                "where idKlijenta = ?");
        ps.setString(1, lokalniKlijent.getKlijent().getKorisnickiNalog().getKorisnickiNalog());
        ps.setString(2, lokalniKlijent.getKlijent().getIme());
        ps.setString(3, lokalniKlijent.getKlijent().getPrezime());
        ps.setString(4, lokalniKlijent.getKlijent().getEmail());
        ps.setInt(5, lokalniKlijent.getKlijent().getIdKlijenta());
        ps.executeUpdate();
        return true;
    }

    public IDTO pretraziKlijentaPoNalogu(Connection konekcijaNaBazu, String parametarPretrage) throws java.sql.SQLException{
        DTOKlijent povratnaVrijednost = null;
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("select * from klijent where korisnickoIme = ?");
        ps.setString(1 , parametarPretrage);
        ResultSet rezultat = ps.executeQuery();
        if(rezultat.next()){
            DTONalog nalogDTO = (DTONalog) new DBDAONalog().pretraziBazu(konekcijaNaBazu,parametarPretrage);
            povratnaVrijednost = new DTOKlijent(new Klijent(rezultat.getInt(1), nalogDTO.getNalog(),
                    rezultat.getString(3), rezultat.getString(4), rezultat.getString(5)));
        }
        return povratnaVrijednost;
    }

    @Override
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) throws SQLException {
        DTOKlijent povratnaVrijednost = null;
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("select * from klijent where idKlijenta = ?");
        ps.setInt(1 , Integer.parseInt(parametarPretrage));
        ResultSet rezultat = ps.executeQuery();
        if(rezultat.next()){
            DTONalog nalogDTO = (DTONalog) new DBDAONalog().pretraziBazu(konekcijaNaBazu,rezultat.getString(parametarPretrage));
            povratnaVrijednost = new DTOKlijent(new Klijent(rezultat.getInt(1), nalogDTO.getNalog(),
                    rezultat.getString(3), rezultat.getString(4), rezultat.getString(5)));
        }
        return povratnaVrijednost;
    }

    @Override
    public List<DTOKlijent> ispisi(Connection konekcijaNaBazu) throws SQLException {
        return citajIzBaze(konekcijaNaBazu);
    }
}
