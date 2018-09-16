package projektovanje.dbDAO;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;
import projektovanje.bin.sala.Sala;
import projektovanje.bin.sala.Sjediste;
import projektovanje.dto.DTOSjediste;
import projektovanje.dto.DTOZaposleni;
import projektovanje.dto.IDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBDAOSjediste implements IDBDAO {

    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) throws SQLException {
        DTOSjediste lokalniDTOSjediste = (DTOSjediste) dtoInstanca;
        Sjediste lokalniSjediste = lokalniDTOSjediste.getSjediste();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("insert into Sjediste values (default,?,?,?)");
        ps.setInt(1,lokalniSjediste.getSala().getIdSale());
        ps.setInt(2,lokalniSjediste.getVrsta());
        ps.setInt(3,lokalniSjediste.getKolona());
        ps.executeUpdate();
        return true;
    }

    @Override
    public List<? extends IDTO> citajIzBaze(Connection konekcijaNaBazu) throws SQLException {
        Statement s = konekcijaNaBazu.createStatement();
        ResultSet rs = s.executeQuery("select * from Sjediste");
        ArrayList<DTOSjediste> povratnaVrijednost = new ArrayList<>();
        while (rs.next()){
            Sjediste procitaniSjediste = new Sjediste(rs.getInt(1),new Sala(rs.getInt(2)),rs.getInt(3),rs.getInt(4));
            povratnaVrijednost.add(new DTOSjediste(procitaniSjediste));
        }
        return povratnaVrijednost;
    }

    @Override
    public Boolean azurirajBazu(IDTO noviSjediste, Connection konekcijaNaBazu) throws SQLException {
        DTOSjediste lokalniDTOSjediste = (DTOSjediste) noviSjediste;
        Sjediste lokalniSjediste = lokalniDTOSjediste.getSjediste();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("update Sjediste" +
                "   set idSale = ?," +
                "   vrsta = ?," +
                "   kolona = ?" +
                "   where idSjedista = ?");
        ps.setInt(1,lokalniSjediste.getSala().getIdSale());
        ps.setInt(2,lokalniSjediste.getVrsta());
        ps.setInt(3,lokalniSjediste.getKolona());
        ps.setInt(4,lokalniSjediste.getIdSjedista());
        ps.executeUpdate();
        return true;
    }

    @Override
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) throws SQLException {
        DTOSjediste povratnaVrijednost = null;
        int idSjedista = Integer.valueOf(parametarPretrage);
        PreparedStatement s = konekcijaNaBazu.prepareStatement("select * from Sjediste where idSjedista = ?");
        s.setInt(1,idSjedista);
        ResultSet rezultat = s.executeQuery();
        if(rezultat.next()){
            int id = rezultat.getInt(1);
            int idSale = rezultat.getInt(2);
            int vrsta = rezultat.getInt(3);
            int kolona = rezultat.getInt(4);
            povratnaVrijednost = new DTOSjediste(new Sjediste(id,new Sala(idSale),vrsta,kolona));
        }
        return povratnaVrijednost;
    }

    public List<DTOSjediste> pretraziSjedistaZaSalu(Sala sala,Connection konekcijaNaBazu) throws SQLException {
        List<DTOSjediste> povratnaVrijednost = new ArrayList<>();
        PreparedStatement s = konekcijaNaBazu.prepareStatement("select * from Sjediste where idSale = ?");
        s.setInt(1,sala.getIdSale());
        ResultSet rezultat = s.executeQuery();
        while(rezultat.next()){
            int id = rezultat.getInt(1);
            int idSale2 = rezultat.getInt(2);
            int vrsta = rezultat.getInt(3);
            int kolona = rezultat.getInt(4);
            povratnaVrijednost.add (new DTOSjediste(new Sjediste(id,sala,vrsta,kolona)));
        }
        return povratnaVrijednost;
    }


    @Override
    public List<? extends IDTO> ispisi(Connection konekcijaNaBazu) throws SQLException {

        return citajIzBaze(konekcijaNaBazu);

    }
}
