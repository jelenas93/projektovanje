package projektovanje.dbDAO;

import projektovanje.bin.film.Zanr;
import projektovanje.dto.DTOZanr;
import projektovanje.dto.IDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBDAOZanr implements IDBDAO {

    @Override
    public Boolean upisiUBazu(IDTO zanr, Connection konekcijaNaBazu) throws SQLException {
        DTOZanr lokalniDTOZanr = (DTOZanr) zanr;
        Zanr lokalniZanr = lokalniDTOZanr.getZanr();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("insert into Zanr values (default,?)");
        ps.setString(1,lokalniZanr.getNazivZanra());
        ps.executeUpdate();
        return true;
    }

    @Override
    public List<? extends IDTO> citajIzBaze(Connection konekcijaNaBazu) throws SQLException {
        PreparedStatement s = konekcijaNaBazu.prepareStatement("select * from Zanr");
        ResultSet rs = s.executeQuery();
        ArrayList<DTOZanr> povratnaVrijednost = new ArrayList<>();
        while (rs.next()){
            Zanr procitaniZanr = new Zanr(rs.getInt(1),rs.getString(2));
            povratnaVrijednost.add(new DTOZanr(procitaniZanr));
        }
        return povratnaVrijednost;
    }

    @Override
    public Boolean azurirajBazu(IDTO noviZanr, Connection konekcijaNaBazu) throws SQLException {
        DTOZanr lokalniDTOZanr = (DTOZanr) noviZanr;
        Zanr lokalniZanr = lokalniDTOZanr.getZanr();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("update Zanr" +
                "   set nazivZanra = ?" +
                "   where idZanra = ?");
        ps.setString(1,lokalniZanr.getNazivZanra());
        ps.setInt(2,lokalniZanr.getIdZanra());
        ps.executeUpdate();
        return true;
    }

    @Override
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) throws SQLException {
        DTOZanr povratnaVrijednost = null;
        int idZanra = Integer.valueOf(parametarPretrage);
        PreparedStatement s = konekcijaNaBazu.prepareStatement("select * from Zanr where idZanra = ?");
        s.setInt(1,idZanra);
        ResultSet rezultat = s.executeQuery();
        if(rezultat.next()){
            int id = rezultat.getInt(1);
            String naziv = rezultat.getString(2);
            povratnaVrijednost = new DTOZanr(new Zanr(id,naziv));
        }
        return povratnaVrijednost;
    }

    public IDTO pretraziZanrPoImenu(Connection konekcijaNaBazu, String parametarPretrage) throws SQLException {
        DTOZanr povratnaVrijednost = null;
        //int idZanra = Integer.valueOf(parametarPretrage);
        PreparedStatement s = konekcijaNaBazu.prepareStatement("select * from Zanr where nazivZanra = ?");
        s.setString(1,parametarPretrage);
        ResultSet rezultat = s.executeQuery();
        if(rezultat.next()){
            int id = rezultat.getInt(1);
            String naziv = rezultat.getString(2);
            povratnaVrijednost = new DTOZanr(new Zanr(id,naziv));
        }
        return povratnaVrijednost;
    }

    @Override
    public List<? extends IDTO> ispisi(Connection konekcijaNaBazu) throws SQLException {
        return citajIzBaze(konekcijaNaBazu);
    }
}
