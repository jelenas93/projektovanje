package projektovanje.dbDAO;

import projektovanje.bin.repertoar.Repertoar;
import projektovanje.bin.sala.Sala;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dto.DTORepertoar;
import projektovanje.dto.DTOZaposleni;
import projektovanje.dto.IDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBDAORepertoar implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoRepertoar, Connection konekcijaNaBazu) throws SQLException {
        DTORepertoar LokalniDtoRepertoar = (DTORepertoar) dtoRepertoar;
        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement("insert into Repertoar values(default, ?, ?, ?)");
        preparedStatement.setInt(1,LokalniDtoRepertoar.getRepertoar().getZaposleni().getIdZaposlenog());
        preparedStatement.setDate(2,new java.sql.Date(LokalniDtoRepertoar.getRepertoar().getDatumOd().getTime()));
        preparedStatement.setDate(3,new java.sql.Date(LokalniDtoRepertoar.getRepertoar().getDatumDo().getTime()));
        preparedStatement.executeUpdate();
        return true;

    }

    @Override
    public List<DTORepertoar> citajIzBaze(Connection konekcijaNaBazu) throws SQLException {
        Statement s = konekcijaNaBazu.createStatement();
        ResultSet rs = s.executeQuery("select * from Repertoar");
        ArrayList<DTORepertoar> povratnaVrijednost = new ArrayList<>();
        while (rs.next()){
            Integer idRepertoara = rs.getInt(1);
            Integer idZaposlenog = rs.getInt(2);
            java.util.Date datumOd = new java.util.Date(rs.getDate(3).getTime());
            java.util.Date datumDo = new java.util.Date(rs.getDate(4).getTime());
            DTOZaposleni idto =(DTOZaposleni) new DBDAOZaposleni().pretraziBazu(konekcijaNaBazu, String.valueOf(idZaposlenog));
            Repertoar procitaniRepertoar = new Repertoar(idRepertoara,null,idto.getZaposleni(),datumOd,datumDo);
            povratnaVrijednost.add(new DTORepertoar(procitaniRepertoar));
        }
        return povratnaVrijednost;
    }


    @Override
    public Boolean azurirajBazu(IDTO noviRepertoar, Connection konekcijaNaBazu) throws SQLException {
        DTORepertoar lokalniDTORepertoar = (DTORepertoar) noviRepertoar;
        Repertoar lokalniRepertoar = lokalniDTORepertoar.getRepertoar();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("update Repertoar" +
                "   set idZaposlenog = ?," +
                "   datumOd = ?," +
                "   datumDo = ?" +
                "   where idRepertoara = ?");
        ps.setInt(1,lokalniRepertoar.getZaposleni().getIdZaposlenog());
        ps.setDate(2,new java.sql.Date(lokalniRepertoar.getDatumOd().getTime()));
        ps.setDate(3,new java.sql.Date(lokalniRepertoar.getDatumDo().getTime()));
        ps.setInt(4,lokalniRepertoar.getIdRepertoara());
        ps.executeUpdate();
        return true;

    }

    @Override
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) throws SQLException {
        Integer idRepertoara = Integer.valueOf(parametarPretrage);
        DTORepertoar povratnaVrijednost = null;
        PreparedStatement s = konekcijaNaBazu.prepareStatement("select * from Repertoar where idRepertoara = ?");
        s.setInt(1,idRepertoara);
        ResultSet rezultat = s.executeQuery();
        if(rezultat.next()){
            int id = rezultat.getInt(1);
            int idZaposlenog = rezultat.getInt(2);
            java.util.Date datumOd = new java.util.Date(rezultat.getDate(3).getTime());
            java.util.Date datumDo = new java.util.Date(rezultat.getDate(4).getTime());
            DTOZaposleni zaposleni = (DTOZaposleni) new DBDAOZaposleni().pretraziBazu(konekcijaNaBazu,String.valueOf(idZaposlenog));
            povratnaVrijednost = new DTORepertoar(new Repertoar(id,null,zaposleni.getZaposleni(),datumOd,datumDo));
        }
        return povratnaVrijednost;
    }

    @Override
    public List<DTORepertoar> ispisi(Connection konekcijaNaBazu) throws SQLException {
        return citajIzBaze(konekcijaNaBazu);
    }

    public DTORepertoar dohvatiTrenutniRepertoar(Connection konekcijaNaBazu) throws SQLException {
        List<DTORepertoar> sviRepertoari = citajIzBaze(konekcijaNaBazu);
        java.util.Date trenutnoVrijeme = new java.util.Date();
        sviRepertoari.stream().filter(x-> x.getRepertoar().getDatumDo().after(trenutnoVrijeme) && x.getRepertoar().getDatumOd().before(trenutnoVrijeme));
        DTORepertoar trenutniRepertoar = sviRepertoari.get(1);
        return trenutniRepertoar;
    }
}
