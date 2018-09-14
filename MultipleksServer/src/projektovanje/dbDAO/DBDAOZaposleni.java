package projektovanje.dbDAO;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dto.DTOZaposleni;
import projektovanje.dto.IDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBDAOZaposleni implements IDBDAO {

    @Override
    public synchronized Boolean upisiUBazu(IDTO zaposleni, Connection konekcijaNaBazu) throws SQLException {
        Boolean uspjesno = false;
        DTOZaposleni lokalniDTOZaposleni = (DTOZaposleni) zaposleni;
        Zaposleni lokalniZaposleni = lokalniDTOZaposleni.getZaposleni();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("insert into Zaposleni values (default,?,?,?,?,?,?)");
        Plata koristenaPlata = lokalniZaposleni.getPlata()==null? Plata.nulaPlata:lokalniZaposleni.getPlata();
        ps.setInt(1,koristenaPlata.getIDPlate());
        ps.setString(2,lokalniZaposleni.getIme());
        ps.setString(3,lokalniZaposleni.getPrezime());
        ps.setString(4,lokalniZaposleni.getJMBG());
        ps.setBoolean(5,true);
<<<<<<< HEAD
        ps.setString(6,"test1");
=======
        ps.setString(6,lokalniZaposleni.getNalog().getKorisnickiNalog());
        ps.executeUpdate();
        return uspjesno;
    }

    @Override
    public List<? extends IDTO> citajIzBaze(Connection konekcijaNaBazu) throws SQLException {
        Statement s = konekcijaNaBazu.createStatement();
        ResultSet rs = s.executeQuery("select * from zaposleni");
        ArrayList<DTOZaposleni> povratnaVrijednost = new ArrayList<>();
        while (rs.next()){
            Zaposleni procitaniZaposleni = new Zaposleni(rs.getInt(1),new Plata(rs.getInt(2)),
                    rs.getString(3),rs.getString(4),rs.getString(5)
                    ,new Nalog(rs.getString(7),null));
            povratnaVrijednost.add(new DTOZaposleni(procitaniZaposleni));
        }
        return povratnaVrijednost;
    }

    @Override
<<<<<<< HEAD
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu) {
        return null;
=======
    public Boolean azurirajBazu(IDTO noviZaposleni, Connection konekcijaNaBazu) throws SQLException {
        DTOZaposleni lokalniDTOZaposleni = (DTOZaposleni) noviZaposleni;
        Zaposleni lokalniZaposleni = lokalniDTOZaposleni.getZaposleni();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("update Zaposleni " +
                "set idPlate = ?" +
                "ime=?" +
                "prezime=?" +
                "JMBG=?" +
                "nalog=?" +
                "where idZaposlenog=?");
        ps.setInt(1,lokalniZaposleni.getPlata().getIDPlate());
        ps.setString(2,lokalniZaposleni.getIme());
        ps.setString(3,lokalniZaposleni.getPrezime());
        ps.setString(4,lokalniZaposleni.getJMBG());
        ps.setString(5,lokalniZaposleni.getNalog().getKorisnickiNalog());
        ps.setInt(6,lokalniZaposleni.getIdZaposlenog());
        return true;
>>>>>>> 119457b79ea8ebc88d4b6e2705a6ba9d3cd5687a
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
