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
        DTOZaposleni lokalniDTOZaposleni = (DTOZaposleni) zaposleni;
        Zaposleni lokalniZaposleni = lokalniDTOZaposleni.getZaposleni();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("insert into Zaposleni values (default,?,?,?,?,?,?)");
        Plata koristenaPlata = lokalniZaposleni.getPlata()==null? Plata.nulaPlata:lokalniZaposleni.getPlata();
        ps.setInt(1,koristenaPlata.getIDPlate());
        ps.setString(2,lokalniZaposleni.getIme());
        ps.setString(3,lokalniZaposleni.getPrezime());
        ps.setString(4,lokalniZaposleni.getJMBG());
        ps.setBoolean(5,true);
        ps.setString(6,lokalniZaposleni.getNalog().getKorisnickiNalog());
        ps.executeUpdate();
        return true;
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
    public Boolean azurirajBazu(IDTO noviZaposleni, Connection konekcijaNaBazu) throws SQLException {
        DTOZaposleni lokalniDTOZaposleni = (DTOZaposleni) noviZaposleni;
        Zaposleni lokalniZaposleni = lokalniDTOZaposleni.getZaposleni();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("update Zaposleni" +
                "   set idPlate = ?," +
                        "   ime = ?," +
                        "   prezime = ?," +
                        "   JMBG = ?," +
                        "   korisnickoIme = ?," +
                        "   aktivan = ?" +
                        "   where idZaposlenog = ?");
        ps.setInt(1,lokalniZaposleni.getPlata().getIDPlate());
        ps.setString(2,lokalniZaposleni.getIme());
        ps.setString(3,lokalniZaposleni.getPrezime());
        ps.setString(4,lokalniZaposleni.getJMBG());
        ps.setString(5,lokalniZaposleni.getNalog().getKorisnickiNalog());
        ps.setBoolean(6,true);
        ps.setInt(7,lokalniZaposleni.getIdZaposlenog());
        ps.executeUpdate();
        return true;
    }

    @Override
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) throws SQLException {
        DTOZaposleni povratnaVrijednost = null;
        int idZaposlenog = Integer.valueOf(parametarPretrage);
        PreparedStatement s = konekcijaNaBazu.prepareStatement("select * from zaposleni where idZaposlenog = ?");
        s.setInt(1,idZaposlenog);
        ResultSet rezultat = s.executeQuery();
        if(rezultat.next()){
            int id = rezultat.getInt(1);
            int idPlate = rezultat.getInt(2);
            String ime = rezultat.getString(3);
            String prezime = rezultat.getString(4);
            String JMBG = rezultat.getString(5);
            Boolean aktivan = rezultat.getBoolean(6);
            String korisnickoIme = rezultat.getString(7);
            povratnaVrijednost = new DTOZaposleni(new Zaposleni(id,new Plata(idPlate),ime,prezime,JMBG,new Nalog(korisnickoIme)));
        }
        return povratnaVrijednost;
    }

    @Override
    public List<? extends IDTO> ispisi(Connection konekcijaNaBazu) throws SQLException {
        return citajIzBaze(konekcijaNaBazu);
    }
}
