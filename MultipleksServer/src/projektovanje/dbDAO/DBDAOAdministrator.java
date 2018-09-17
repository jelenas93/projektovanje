package projektovanje.dbDAO;

import projektovanje.bin.zaposleni.Administrator;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dto.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBDAOAdministrator implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoAdministrator, Connection konekcijaNaBazu) throws java.sql.SQLException{
        Boolean uspjesno = false;
        PreparedStatement upisAdministratora = konekcijaNaBazu.prepareStatement("insert into Administrator values (?)");
        DTOAdministrator lokalniAdministrator = (DTOAdministrator) dtoAdministrator;
        upisAdministratora.setInt(1, lokalniAdministrator.getAdministrator().getIdAdministratora());
        upisAdministratora.executeUpdate();
        uspjesno = true;
        return uspjesno;
    }

    @Override
    public List<DTOAdministrator> citajIzBaze(Connection konekcijaNaBazu) throws java.sql.SQLException{
    List<DTOAdministrator> listaAdministratora = new ArrayList<DTOAdministrator>();
    PreparedStatement citanjeSvihAdministratora = konekcijaNaBazu.prepareStatement("select * from Administrator");
    ResultSet resultSet = citanjeSvihAdministratora.executeQuery();
    while(resultSet.next()){
        DBDAOZaposleni zaposleniDAO = new DBDAOZaposleni();
        DTOZaposleni zaposleniDTO;
        Integer hlpVar = resultSet.getInt(1);
        zaposleniDTO = (DTOZaposleni)zaposleniDAO.pretraziBazu(konekcijaNaBazu, hlpVar.toString());
        Administrator administrator = new Administrator(zaposleniDTO.getZaposleni());
        DTOAdministrator dtoAdministrator = new DTOAdministrator(administrator);
        listaAdministratora.add(dtoAdministrator);
    }
    return listaAdministratora;

    }

    @Override
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu) throws SQLException {
        DBDAOZaposleni zaposleniDAO = new DBDAOZaposleni();
        DTOAdministrator localVar = (DTOAdministrator) list;
        DTOZaposleni zaposleniDTO = new DTOZaposleni((Zaposleni)localVar.getAdministrator());
        zaposleniDAO.azurirajBazu(zaposleniDTO, konekcijaNaBazu);
        return true;
    }

    public List<? extends IDTO> ispisiSveAktivneAdministratore(Connection konekcijaNaBazu) throws SQLException {
        List<DTOAdministrator> povratnaVrijednost = new ArrayList<>();
        PreparedStatement s = konekcijaNaBazu.prepareStatement("select * from administrator");
        ResultSet rezultat = s.executeQuery();
        while(rezultat.next()){
            Integer id = rezultat.getInt(1);
            DTOZaposleni dtoZaposleni = (DTOZaposleni)new DBDAOZaposleni().procitajZaposlenogAkoJeAktivan(konekcijaNaBazu, id.toString());
            if(null != dtoZaposleni) {
                Administrator pomocnaVarijabla = new Administrator(dtoZaposleni.getZaposleni());
                povratnaVrijednost.add(new DTOAdministrator(pomocnaVarijabla));
            }
        }
        return povratnaVrijednost;
    }

    @Override
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) throws SQLException {
        DTOAdministrator lokalniAdministrator;

        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement("select * from Administrator where idZaposlenog = ?");
        preparedStatement.setInt(1, Integer.parseInt(parametarPretrage));
        ResultSet resultSet = preparedStatement.executeQuery();
        Administrator var = new Administrator();
        if (resultSet.next()){
            DBDAOZaposleni zaposleniDAO = new DBDAOZaposleni();
            DTOZaposleni zaposleniDTO;
            Integer hlpVar = resultSet.getInt(1);
            zaposleniDTO = (DTOZaposleni)zaposleniDAO.pretraziBazu(konekcijaNaBazu, hlpVar.toString());
            var = new Administrator(zaposleniDTO.getZaposleni());
            lokalniAdministrator = new DTOAdministrator(var);
        }else{
            lokalniAdministrator = null;
        }
        return lokalniAdministrator;
    }

    @Override
    public List<DTOAdministrator> ispisi(Connection konekcija) throws java.sql.SQLException {
        return citajIzBaze(konekcija);
    }
}
