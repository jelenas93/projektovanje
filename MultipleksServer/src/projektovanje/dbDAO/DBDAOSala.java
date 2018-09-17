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
        ps.setInt(2,lokalniSala.getBrojKolona());
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
            int brojVrsta = rs.getInt(2);
            int brojKolona = rs.getInt(3);
            System.out.println(brojKolona);
            Sala ret = new Sala(idSale);
            List<DTOSjediste> dtoSjedista = sjedisteDao.pretraziSjedistaZaSalu(ret,konekcijaNaBazu);
            ArrayList<Sjediste> sjedista = new ArrayList<>();
            dtoSjedista.forEach(x->sjedista.add(x.getSjediste()));
            ret.setBrojVrsta(brojVrsta);
            ret.setBrojKolona(brojKolona);
            ret.setSjedista(sjedista);
            povratnaVrijednost.add(new DTOSala(ret));
        }
        return povratnaVrijednost;

    }

    @Override

    public Boolean azurirajBazu(IDTO dtoSala, Connection konekcijaNaBazu) throws SQLException {
        DTOSala lokalniDTOSala = (DTOSala) dtoSala;
        Sala lokalniSala = lokalniDTOSala.getSala();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("update Sala" +
                "   set brojvrsta = ?," +
                "   brojkolona = ?" +
                "   where idSale = ?");
        ps.setInt(1,lokalniSala.getBrojVrsta());
        ps.setInt(2,lokalniSala.getBrojKolona());
        ps.setInt(3,lokalniSala.getIdSale());
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
            Sala sala = new Sala();
            sala.setIdSale(id);
            sala.setBrojVrsta(vrsta);
            sala.setBrojKolona(kolona);
            List<DTOSjediste> sjedistaDto = sjedistaDao.pretraziSjedistaZaSalu(sala,konekcijaNaBazu);
            ArrayList<Sjediste> sjedista = new ArrayList<>();
            sjedistaDto.forEach(x->sjedista.add(x.getSjediste()));
            sala.setSjedista(sjedista);
            povratnaVrijednost = new DTOSala(sala);
        }
        return povratnaVrijednost;
    }

    @Override
    public List<DTOSala> ispisi(Connection konekcijaNaBazu) throws SQLException {
        return citajIzBaze(konekcijaNaBazu);
    }
}
