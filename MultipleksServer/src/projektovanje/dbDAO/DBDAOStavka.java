package projektovanje.dbDAO;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.oprema.Artikal;
import projektovanje.bin.plata.Plata;
import projektovanje.bin.racun.Stavka;
import projektovanje.dto.DTOArtikal;
import projektovanje.dto.DTOStavka;
import projektovanje.dto.IDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBDAOStavka implements IDBDAO {

    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) throws SQLException {
        DTOStavka lokalnaDTOStavka = (DTOStavka) dtoInstanca;
        Stavka lokalnaStavka = lokalnaDTOStavka.getStavka();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("insert into Stavka values (default,?,?,?,?)");
        ps.setInt(1,lokalnaStavka.getKolicina());
        ps.setDouble(2,lokalnaStavka.getUkupaCijena());
        ps.setInt(3,lokalnaStavka.getArtikal().getIdArtikla());
        ps.setInt(4,lokalnaStavka.getIdRacuna());
        ps.executeUpdate();
        return true;
    }

    @Override
    public List<? extends IDTO> citajIzBaze(Connection konekcijaNaBazu) throws SQLException {
        Statement s = konekcijaNaBazu.createStatement();
        ResultSet rs = s.executeQuery("select * from Stavka");
        ArrayList<DTOStavka> povratnaVrijednost = new ArrayList<>();
        DBDAOArtikal artikalDao = new DBDAOArtikal();
        while (rs.next()){
            Integer idArtikla = rs.getInt(4);
            DTOArtikal dtoArtikal = (DTOArtikal) artikalDao.pretraziBazu(konekcijaNaBazu,String.valueOf(idArtikla));
            Stavka procitanaStavka = new Stavka(rs.getInt(1),rs.getInt(2),
                    rs.getDouble(3),dtoArtikal.getArtikal(),rs.getInt(5));
            povratnaVrijednost.add(new DTOStavka(procitanaStavka));
        }
        return povratnaVrijednost;
    }

    @Override
    public Boolean azurirajBazu(IDTO novaStavka, Connection konekcijaNaBazu) throws SQLException {
        DTOStavka lokalnaDTOStavka = (DTOStavka) novaStavka;
        Stavka lokalnaStavka = lokalnaDTOStavka.getStavka();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("update Stavka" +
                "   set kolicina = ?," +
                "   ukupnacijena = ?," +
                "   idArtikla = ?" +
                "   where idStavke = ?");
        ps.setInt(1,lokalnaStavka.getKolicina());
        ps.setDouble(2,lokalnaStavka.getUkupaCijena());
        ps.setInt(3,lokalnaStavka.getArtikal().getIdArtikla());
        ps.setInt(4,lokalnaStavka.getIdStavke());
        ps.executeUpdate();
        return true;
    }

    @Override
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) throws SQLException {
        DTOStavka povratnaVrijednost = null;
        int idStavke = Integer.valueOf(parametarPretrage);
        PreparedStatement s = konekcijaNaBazu.prepareStatement("select * from Stavka where idStavke = ?");
        s.setInt(1,idStavke);
        ResultSet rezultat = s.executeQuery();
        if(rezultat.next()){
            int id = rezultat.getInt(1);
            int kolicina = rezultat.getInt(2);
            Double ukupnaCijena = rezultat.getDouble(3);
            int idArtikla = rezultat.getInt(4);
            int idRacuna = rezultat.getInt(5);
            DTOArtikal dtoArtikal =(DTOArtikal) new DBDAOArtikal().pretraziBazu(konekcijaNaBazu, String.valueOf(idArtikla));
            povratnaVrijednost = new DTOStavka(new Stavka(id,kolicina,ukupnaCijena,dtoArtikal.getArtikal(),idArtikla));
        }
        return povratnaVrijednost;
    }

    @Override
    public List<? extends IDTO> ispisi(Connection konekcijaNaBazu) throws SQLException {
        return citajIzBaze(konekcijaNaBazu);
    }

    public List<Stavka> dohvatiSveStavkeSaRacuna(Integer idRacuna,Connection konekcijaNaBazu) throws SQLException {
        List<Stavka> povratnaVrijednost = new ArrayList<>();
        PreparedStatement s = konekcijaNaBazu.prepareStatement("select * from Stavka where idRacuna = ?");
        s.setInt(1,idRacuna);
        ResultSet rezultat = s.executeQuery();
        DBDAOArtikal artDao = new DBDAOArtikal();
        while(rezultat.next()){
            int id = rezultat.getInt(1);
            int kolicina = rezultat.getInt(2);
            Double ukupnaCijena = rezultat.getDouble(3);
            int idArtikla = rezultat.getInt(4);
            DTOArtikal dtoArtikal =(DTOArtikal) artDao.pretraziBazu(konekcijaNaBazu,String.valueOf(idArtikla));
            povratnaVrijednost.add(new Stavka(id,kolicina,ukupnaCijena,dtoArtikal.getArtikal(),idRacuna));
        }
        return povratnaVrijednost;
    }
}
