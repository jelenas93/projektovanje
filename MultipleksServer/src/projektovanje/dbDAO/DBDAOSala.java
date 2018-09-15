package projektovanje.dbDAO;

import projektovanje.bin.sala.Sala;
import projektovanje.bin.sala.Sjediste;
import projektovanje.dto.DTOSala;
import projektovanje.dto.DTOSjediste;
import projektovanje.dto.IDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBDAOSala implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) throws SQLException {
        DTOSala lokalniDTOSala = (DTOSala) dtoInstanca;
        Sala lokalniSala = lokalniDTOSala.getSala();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("insert into Sala values (default,?,?)");
        ps.setInt(1,lokalniSala.getBrojVrsta());
        ps.setInt(3,lokalniSala.getBrojKolona());
        ps.executeUpdate();
        return true;
    }

    @Override
    public List<DTOSala> citajIzBaze(Connection konekcijaNaBazu) throws SQLException {
        Statement s = konekcijaNaBazu.createStatement();
        ResultSet rs = s.executeQuery("select * from Sala");
        DBDAOSjediste sjedisteDao = new DBDAOSjediste();
        ArrayList<DTOSala> povratnaVrijednost = new ArrayList<>();
        while (rs.next()){
            int idSale = rs.getInt(1);
            List<DTOSjediste> dtoSjedista = sjedisteDao.pretraziSjedistaZaSalu(idSale,konekcijaNaBazu);
            ArrayList<Sjediste> sjedista = new ArrayList<>();
            dtoSjedista.forEach(x->sjedista.add(x.getSjediste()));
            Sala procitaniSala = new Sala(idSale,rs.getInt(2),rs.getInt(3),sjedista);
            povratnaVrijednost.add(new DTOSala(procitaniSala));
        }
        return povratnaVrijednost;

    }

    @Override

    public Boolean azurirajBazu(IDTO dtoSala, Connection konekcijaNaBazu) throws SQLException {
        DTOSala lokalniDTOSala = (DTOSala) dtoSala;
        Sala lokalniSala = lokalniDTOSala.getSala();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("update Sala" +
                "   brojvrsta = ?," +
                "   brojkolona = ?" +
                "   where idSale = ?");
        ps.setInt(1,lokalniSala.getBrojVrsta());
        ps.setInt(2,lokalniSala.getBrojKolona());
        DBDAOSjediste sjedisteDao = new DBDAOSjediste();
        lokalniSala.getSjedista().forEach(x-> {
            try {
                sjedisteDao.azurirajBazu(new DTOSjediste(x),konekcijaNaBazu);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        ps.executeUpdate();
        return true;
    }

    @Override
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) throws SQLException {

        DBDAOSjediste sjedistaDao = new DBDAOSjediste();
        DTOSala povratnaVrijednost = null;
        int idSale = Integer.valueOf(parametarPretrage);
        PreparedStatement s = konekcijaNaBazu.prepareStatement("select * from Sala where idSale = ?");
        s.setInt(1,idSale);
        ResultSet rezultat = s.executeQuery();
        if(rezultat.next()){
            int id = rezultat.getInt(1);
            int vrsta = rezultat.getInt(2);
            int kolona = rezultat.getInt(3);
            List<DTOSjediste> sjedistaDto = sjedistaDao.pretraziSjedistaZaSalu(id,konekcijaNaBazu);
            ArrayList<Sjediste> sjedista = new ArrayList<>();
            sjedistaDto.forEach(x->sjedista.add(x.getSjediste()));
            povratnaVrijednost = new DTOSala(new Sala(id,vrsta,kolona,sjedista));
        }
        return povratnaVrijednost;
    }

    @Override
    public List<DTOSala> ispisi(Connection konekcijaNaBazu) throws SQLException {
        return citajIzBaze(konekcijaNaBazu);
    }
}
