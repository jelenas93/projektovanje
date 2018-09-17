package projektovanje.dbDAO;

import projektovanje.bin.film.Ponuda;
import projektovanje.bin.oprema.Artikal;
import projektovanje.bin.racun.Stavka;
import projektovanje.dto.DTOPonuda;
import projektovanje.dto.DTOStavka;
import projektovanje.dto.DTOZaposleni;
import projektovanje.dto.IDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBDAOPonuda implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) throws SQLException {
        DTOPonuda lokalniDTOPonuda = (DTOPonuda) dtoInstanca;
        Ponuda lokalniPonuda = lokalniDTOPonuda.getPonuda();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("insert into Ponuda values (default,?,?)");
        ps.setDate(1,new Date(lokalniPonuda.getDatumPonude().getTime()));
        ps.setInt(2,lokalniPonuda.getZaposleni().getIdZaposlenog());
        ps.executeUpdate();
        return true;
    }

    @Override
    public List<DTOPonuda> citajIzBaze(Connection konekcijaNaBazu) throws SQLException {
        Statement s = konekcijaNaBazu.createStatement();
        ResultSet rs = s.executeQuery("select * from Ponuda");
        ArrayList<DTOPonuda> povratnaVrijednost = new ArrayList<>();
        while (rs.next()){
            Integer idPonude = rs.getInt(1);
            java.util.Date datumPonude = new java.util.Date(rs.getDate(2).getTime());
            Integer idZaposlenog = rs.getInt(3);
            DTOZaposleni zaposleni = (DTOZaposleni) new DBDAOZaposleni().pretraziBazu(konekcijaNaBazu,String.valueOf(idZaposlenog));
            Ponuda ponuda = new Ponuda(idPonude,null,datumPonude,zaposleni.getZaposleni());
            povratnaVrijednost.add(new DTOPonuda(ponuda));
        }
        return povratnaVrijednost;
    }

    @Override
    public Boolean azurirajBazu(IDTO noviPonuda, Connection konekcijaNaBazu) throws SQLException {
        DTOPonuda lokalniDTOPonuda = (DTOPonuda) noviPonuda;
        Ponuda lokalniPonuda = lokalniDTOPonuda.getPonuda();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("update Ponuda" +
                "   set datumPonude = ?," +
                "   idZaposlenog = ?" +
                "   where idPonude = ?");
        ps.setDate(1,new Date(lokalniPonuda.getDatumPonude().getTime()));
        ps.setInt(2,lokalniPonuda.getZaposleni().getIdZaposlenog());
        ps.setInt(3,lokalniPonuda.getIdPonude());
        ps.executeUpdate();
        return true;
    }

    @Override
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) throws SQLException {
        DTOPonuda povratnaVrijednost = null;
        int idPonude = Integer.valueOf(parametarPretrage);
        PreparedStatement s = konekcijaNaBazu.prepareStatement("select * from Ponuda where idPonude = ?");
        s.setInt(1,idPonude);
        ResultSet rezultat = s.executeQuery();
        if(rezultat.next()){
            int id = rezultat.getInt(1);
            java.util.Date datumPonude = new java.util.Date(rezultat.getDate(2).getTime());
            int idZaposlenog = rezultat.getInt(3);
            DTOZaposleni zaposleni = (DTOZaposleni) new DBDAOZaposleni().pretraziBazu(konekcijaNaBazu,String.valueOf(idZaposlenog));
            povratnaVrijednost = new DTOPonuda(new Ponuda(id,null,datumPonude,zaposleni.getZaposleni()));
        }
        return povratnaVrijednost;
    }

    @Override
    public List<DTOPonuda> ispisi(Connection konekcijaNaBazu) throws SQLException {
        return citajIzBaze(konekcijaNaBazu);
    }
}
