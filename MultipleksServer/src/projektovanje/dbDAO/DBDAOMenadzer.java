package projektovanje.dbDAO;

import projektovanje.bin.zaposleni.Menadzer;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dto.DTOMenadzer;
import projektovanje.dto.DTOZaposleni;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBDAOMenadzer implements IDBDAO {

    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) throws SQLException {
        PreparedStatement upisMenadzera = konekcijaNaBazu.prepareStatement("insert into Menadzer values (?)");
        DTOMenadzer lokalniMenadzer = (DTOMenadzer) dtoInstanca;
        upisMenadzera.setInt(1, lokalniMenadzer.getMenadzer().getIdMenadzera());
        upisMenadzera.executeUpdate();
        return true;}

    @Override
    public List<DTOMenadzer> citajIzBaze(Connection konekcijaNaBazu) throws SQLException {
        List<DTOMenadzer> listaMenadzera = new ArrayList<DTOMenadzer>();
        PreparedStatement citanjeSvihMenadzera = konekcijaNaBazu.prepareStatement("select * from Menadzer");
        ResultSet resultSet = citanjeSvihMenadzera.executeQuery();
        while(resultSet.next()){
            DBDAOZaposleni zaposleniDAO = new DBDAOZaposleni();
            DTOZaposleni zaposleniDTO;
            Integer hlpVar = resultSet.getInt(1);
            zaposleniDTO = (DTOZaposleni)zaposleniDAO.pretraziBazu(konekcijaNaBazu, hlpVar.toString());
            Menadzer menadzer = new Menadzer(zaposleniDTO.getZaposleni());
            DTOMenadzer dtoAdministrator = new DTOMenadzer(menadzer);
            listaMenadzera.add(dtoAdministrator);
        }
        return listaMenadzera;}

    @Override
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu) throws SQLException {
        DBDAOZaposleni zaposleniDAO = new DBDAOZaposleni();
        DTOMenadzer MenadzerDTO = (DTOMenadzer)list;
        DTOZaposleni zaposleniDTO = new DTOZaposleni((Zaposleni)MenadzerDTO.getMenadzer());
        zaposleniDAO.azurirajBazu(zaposleniDTO, konekcijaNaBazu);
        return true;
    }

    public List<? extends IDTO> ispisiSveAktivneMenadzere(Connection konekcijaNaBazu) throws SQLException {
        List<DTOMenadzer> povratnaVrijednost = new ArrayList<>();
        PreparedStatement s = konekcijaNaBazu.prepareStatement("select * from menadzer");
        ResultSet rezultat = s.executeQuery();
        while(rezultat.next()){
            Integer id = rezultat.getInt(1);
            DTOZaposleni dtoZaposleni = (DTOZaposleni)new DBDAOZaposleni().procitajZaposlenogAkoJeAktivan(konekcijaNaBazu, id.toString());
            if(null != dtoZaposleni) {
                Menadzer pomocnaVarijabla = new Menadzer(dtoZaposleni.getZaposleni());
                povratnaVrijednost.add(new DTOMenadzer(pomocnaVarijabla));
            }
        }
        return povratnaVrijednost;
    }

    @Override
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) throws SQLException {
        DTOMenadzer lokalniMenadzer;
        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement("select * from Menadzer where idZaposlenog = ?");
        preparedStatement.setInt(1, Integer.parseInt(parametarPretrage));
        ResultSet resultSet = preparedStatement.executeQuery();
        Menadzer var = new Menadzer();
        if (resultSet.next()){
            DBDAOZaposleni zaposleniDAO = new DBDAOZaposleni();
            DTOZaposleni zaposleniDTO;
            Integer hlpVar = resultSet.getInt(1);
            zaposleniDTO = (DTOZaposleni)zaposleniDAO.pretraziBazu(konekcijaNaBazu, hlpVar.toString());
            var = new Menadzer(zaposleniDTO.getZaposleni());
            lokalniMenadzer = new DTOMenadzer(var);
        }else{
            lokalniMenadzer = null;
        }
        return lokalniMenadzer;
    }

    @Override
    public List<DTOMenadzer> ispisi(Connection konekcijaNaBazu) throws SQLException {

        return citajIzBaze(konekcijaNaBazu);
    }
}
