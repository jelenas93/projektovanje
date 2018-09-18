package projektovanje.dbDAO;

import projektovanje.bin.plata.Plata;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dto.DTONalog;
import projektovanje.dto.DTOPlata;
import projektovanje.dto.DTOZaposleni;
import projektovanje.dto.IDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.UIManager.getString;

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
        ps.setBoolean(5,lokalniZaposleni.getAktivan());
        ps.setString(6,lokalniZaposleni.getNalog().getKorisnickiNalog());
        ps.executeUpdate();
        return true;
    }


    @Override
    public List<DTOZaposleni> citajIzBaze(Connection konekcijaNaBazu) throws SQLException {
        Statement s = konekcijaNaBazu.createStatement();
        ResultSet rs = s.executeQuery("select * from zaposleni");
        ArrayList<DTOZaposleni> povratnaVrijednost = new ArrayList<>();
        while (rs.next()){
            DTONalog nalogDTO = (DTONalog) new DBDAONalog().pretraziBazu(konekcijaNaBazu,rs.getString(7));
            Integer hlpVar = rs.getInt(2);
            DTOPlata plataDTO = (DTOPlata) new DBDAOPlata().pretraziBazu(konekcijaNaBazu, hlpVar.toString());
            Zaposleni procitaniZaposleni = new Zaposleni(rs.getInt(1), plataDTO.getPlata(),
                    rs.getString(3),rs.getString(4),rs.getString(5),
                    rs.getBoolean(6), nalogDTO.getNalog());
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
        ps.setBoolean(6,lokalniDTOZaposleni.getZaposleni().getAktivan());
        ps.setInt(7,lokalniZaposleni.getIdZaposlenog());
        ps.executeUpdate();
        return true;
    }

    public Boolean izmjeniInformacijeOZaposlenom(IDTO noviZaposleni, Connection konekcijaNaBazu) throws SQLException {
        DTOZaposleni lokalniDTOZaposleni = (DTOZaposleni) noviZaposleni;
        Zaposleni lokalniZaposleni = lokalniDTOZaposleni.getZaposleni();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("update Zaposleni" +
                "   set " +
                "   ime = ?," +
                "   prezime = ?," +
                "   JMBG = ?," +
                "   aktivan = ?" +
                "   where idZaposlenog = ?");
        ps.setString(1,lokalniZaposleni.getIme());
        ps.setString(2,lokalniZaposleni.getPrezime());
        ps.setString(3,lokalniZaposleni.getJMBG());
        ps.setBoolean(4,lokalniDTOZaposleni.getZaposleni().getAktivan());
        ps.setInt(5,lokalniZaposleni.getIdZaposlenog());
        ps.executeUpdate();
        return true;
    }

    public Boolean dajOtkaz(IDTO noviZaposleni, Connection konekcijaNaBazu) throws SQLException {
        DTOZaposleni lokalniDTOZaposleni = (DTOZaposleni) noviZaposleni;
        Zaposleni lokalniZaposleni = lokalniDTOZaposleni.getZaposleni();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("update Zaposleni" +
                "   set " +
                "   aktivan = ?" +
                "   where idZaposlenog = ?");
        ps.setBoolean(1,false);
        ps.setInt(2,lokalniZaposleni.getIdZaposlenog());
        ps.executeUpdate();
        return true;
    }

    public Boolean azurirajZaposlenogPlatuZaposlenog(IDTO noviZaposleni, Connection konekcijaNaBazu) throws SQLException {
        DTOZaposleni lokalniDTOZaposleni = (DTOZaposleni) noviZaposleni;
        Zaposleni lokalniZaposleni = lokalniDTOZaposleni.getZaposleni();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("update Zaposleni" +
                "   set " +
                "   idPlate = ?" +
                "   where idZaposlenog = ?");
        ps.setInt(1,lokalniDTOZaposleni.getZaposleni().getPlata().getIDPlate());
        ps.setInt(2,lokalniZaposleni.getIdZaposlenog());
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
            DTONalog nalogDTO = (DTONalog) new DBDAONalog().pretraziBazu(konekcijaNaBazu,rezultat.getString(7));
            Integer hlpVar = rezultat.getInt(2);
            DTOPlata plataDTO = (DTOPlata) new DBDAOPlata().pretraziBazu(konekcijaNaBazu, hlpVar.toString());
            int id = rezultat.getInt(1);
            String ime = rezultat.getString(3);
            String prezime = rezultat.getString(4);
            String JMBG = rezultat.getString(5);
            Boolean aktivan = rezultat.getBoolean(6);
            povratnaVrijednost = new DTOZaposleni(new Zaposleni(id,plataDTO.getPlata(),ime,prezime,JMBG,aktivan, nalogDTO.getNalog()));
        }
        return povratnaVrijednost;
    }

    public IDTO procitajZaposlenogAkoJeAktivan(Connection konekcijaNaBazu, String parametarPretrage) throws SQLException {
        DTOZaposleni povratnaVrijednost = null;
        int idZaposlenog = Integer.valueOf(parametarPretrage);
        PreparedStatement s = konekcijaNaBazu.prepareStatement("select * from zaposleni where idZaposlenog = ? and aktivan = true");
        s.setInt(1,idZaposlenog);
        ResultSet rezultat = s.executeQuery();
        if(rezultat.next()){
            DTONalog nalogDTO = (DTONalog) new DBDAONalog().pretraziBazu(konekcijaNaBazu,rezultat.getString(7));
            Integer hlpVar = rezultat.getInt(2);
            DTOPlata plataDTO = (DTOPlata) new DBDAOPlata().pretraziBazu(konekcijaNaBazu, hlpVar.toString());
            int id = rezultat.getInt(1);
            String ime = rezultat.getString(3);
            String prezime = rezultat.getString(4);
            String JMBG = rezultat.getString(5);
            Boolean aktivan = rezultat.getBoolean(6);
            povratnaVrijednost = new DTOZaposleni(new Zaposleni(id,plataDTO.getPlata(),ime,prezime,JMBG,aktivan, nalogDTO.getNalog()));
        }
        return povratnaVrijednost;
    }

    public List<? extends IDTO> procitajSveAktivneZaposlene(Connection konekcijaNaBazu) throws SQLException {
        List<DTOZaposleni> povratnaVrijednost = new ArrayList<>();

        PreparedStatement s = konekcijaNaBazu.prepareStatement("select * from zaposleni where aktivan = ?");
        s.setBoolean(1, true);
        ResultSet rezultat = s.executeQuery();
        while(rezultat.next()){
            try {
                DTONalog nalogDTO = (DTONalog) new DBDAONalog().pretraziBazu(konekcijaNaBazu, rezultat.getString(7));
                Integer hlpVar = rezultat.getInt(2);
                DTOPlata plataDTO = (DTOPlata) new DBDAOPlata().pretraziBazu(konekcijaNaBazu, hlpVar.toString());
                int id = rezultat.getInt(1);
                String ime = rezultat.getString(3);
                String prezime = rezultat.getString(4);
                String JMBG = rezultat.getString(5);
                Boolean aktivan = rezultat.getBoolean(6);
                Zaposleni zaposleni = new Zaposleni(id, plataDTO.getPlata(), ime, prezime, JMBG, aktivan, nalogDTO.getNalog());
                System.out.println(zaposleni);
                povratnaVrijednost.add(new DTOZaposleni(zaposleni));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return povratnaVrijednost;
    }

    public IDTO pretraziZaposlenogPoNalogu(Connection konekcijaNaBazu, String parametarPretrage) throws SQLException {
        DTOZaposleni povratnaVrijednost = null;
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("select * from zaposleni where korisnickoIme = ?");
        ps.setString(1 , parametarPretrage);
        ResultSet rezultat = ps.executeQuery();
        if(rezultat.next()){
            DTONalog nalogDTO = (DTONalog) new DBDAONalog().pretraziBazu(konekcijaNaBazu,rezultat.getString(7));
            Integer hlpVar = rezultat.getInt(2);
            DTOPlata plataDTO = (DTOPlata) new DBDAOPlata().pretraziBazu(konekcijaNaBazu, hlpVar.toString());
            int id = rezultat.getInt(1);
            String ime = rezultat.getString(3);
            String prezime = rezultat.getString(4);
            String JMBG = rezultat.getString(5);
            Boolean aktivan = rezultat.getBoolean(6);
            povratnaVrijednost = new DTOZaposleni(new Zaposleni(id,plataDTO.getPlata(),ime,prezime,JMBG,aktivan, nalogDTO.getNalog()));
        }
        return povratnaVrijednost;
    }

    @Override
    public List<DTOZaposleni> ispisi(Connection konekcijaNaBazu) throws SQLException {
        return citajIzBaze(konekcijaNaBazu);
    }
}
