package projektovanje.dbDAO;

import projektovanje.bin.zaposleni.Racunovodja;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dto.DTORacunovodja;
import projektovanje.dto.DTOZaposleni;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBDAORacunovodja implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) throws SQLException {
        PreparedStatement upisRacunovodja = konekcijaNaBazu.prepareStatement("insert into Racunovodja values (?)");
        DTORacunovodja lokalniRacunovodja = (DTORacunovodja) dtoInstanca;
        upisRacunovodja.setInt(1, lokalniRacunovodja.getRacunovodja().getIdRacunovodje());
        upisRacunovodja.executeUpdate();
        return true;}

    @Override
    public List<DTORacunovodja> citajIzBaze(Connection konekcijaNaBazu) throws SQLException {
        List<DTORacunovodja> listaRacunovodja = new ArrayList<DTORacunovodja>();
        PreparedStatement citanjeSvihRacunovodja = konekcijaNaBazu.prepareStatement("select * from Racunovodja");
        ResultSet resultSet = citanjeSvihRacunovodja.executeQuery();
        while(resultSet.next()){
            DBDAOZaposleni zaposleniDAO = new DBDAOZaposleni();
            DTOZaposleni zaposleniDTO;
            Integer hlpVar = resultSet.getInt(1);
            zaposleniDTO = (DTOZaposleni)zaposleniDAO.pretraziBazu(konekcijaNaBazu, hlpVar.toString());
            Racunovodja Racunovodja = new Racunovodja(zaposleniDTO.getZaposleni());
            DTORacunovodja dtoAdministrator = new DTORacunovodja(Racunovodja);
            listaRacunovodja.add(dtoAdministrator);
        }
        return listaRacunovodja;
    }

    public List<? extends IDTO> ispisiSveAktivneRacunovodje(Connection konekcijaNaBazu) throws SQLException {
        List<DTORacunovodja> povratnaVrijednost = new ArrayList<>();
        PreparedStatement s = konekcijaNaBazu.prepareStatement("select * from Racunovodja");
        ResultSet rezultat = s.executeQuery();
        while(rezultat.next()){
            Integer id = rezultat.getInt(1);
            DTOZaposleni dtoZaposleni = (DTOZaposleni)new DBDAOZaposleni().procitajZaposlenogAkoJeAktivan(konekcijaNaBazu, id.toString());
            if(null != dtoZaposleni) {
                Racunovodja pomocnaVarijabla = new Racunovodja(dtoZaposleni.getZaposleni());
                povratnaVrijednost.add(new DTORacunovodja(pomocnaVarijabla));
            }
        }
        return povratnaVrijednost;
    }

    @Override
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu) throws SQLException {
        DBDAOZaposleni zaposleniDAO = new DBDAOZaposleni();
        DTORacunovodja RacunovodjaDTO = (DTORacunovodja)list;
        DTOZaposleni zaposleniDTO = new DTOZaposleni((Zaposleni)RacunovodjaDTO.getRacunovodja());
        zaposleniDAO.azurirajBazu(zaposleniDTO, konekcijaNaBazu);
        return true;
    }

    @Override
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) throws SQLException {
        DTORacunovodja lokalniRacunovodja;
        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement("select * from Racunovodja where idZaposlenog = ?");
        preparedStatement.setInt(1, Integer.parseInt(parametarPretrage));
        ResultSet resultSet = preparedStatement.executeQuery();
        Racunovodja var;
        if (resultSet.next()){
            DBDAOZaposleni zaposleniDAO = new DBDAOZaposleni();
            DTOZaposleni zaposleniDTO;
            Integer hlpVar = resultSet.getInt(1);
            zaposleniDTO = (DTOZaposleni)zaposleniDAO.pretraziBazu(konekcijaNaBazu, hlpVar.toString());
            var = new Racunovodja(zaposleniDTO.getZaposleni());
            lokalniRacunovodja = new DTORacunovodja(var);
        }else{
            lokalniRacunovodja = null;
        }
        return lokalniRacunovodja;
    }

    @Override
    public List<DTORacunovodja> ispisi(Connection konekcijaNaBazu) throws SQLException {

        return citajIzBaze(konekcijaNaBazu);
    }
}
