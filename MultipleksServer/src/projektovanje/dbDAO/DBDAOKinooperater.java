package projektovanje.dbDAO;

import projektovanje.bin.zaposleni.Kinooperater;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dto.DTOKinooperater;
import projektovanje.dto.DTOZaposleni;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBDAOKinooperater implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) throws SQLException {
        PreparedStatement upisKinooperatera = konekcijaNaBazu.prepareStatement("insert into Kinooperater values (?)");
        DTOKinooperater lokalniKinooperater = (DTOKinooperater) dtoInstanca;
        upisKinooperatera.setInt(1, lokalniKinooperater.getKinooperater().getIdKinooperatera());
        upisKinooperatera.executeUpdate();
        return true;
    }

    @Override
    public List<DTOKinooperater> citajIzBaze(Connection konekcijaNaBazu) throws SQLException {
        List<DTOKinooperater> listaKinooperatera = new ArrayList<DTOKinooperater>();
        PreparedStatement citanjeSvihKinooperatera = konekcijaNaBazu.prepareStatement("select * from Kinooperater");
        ResultSet resultSet = citanjeSvihKinooperatera.executeQuery();
        while(resultSet.next()){
            Kinooperater kinooperater = new Kinooperater(resultSet.getInt(1));
            DTOKinooperater dtoKinooperater = new DTOKinooperater(kinooperater);
            listaKinooperatera.add(dtoKinooperater);
        }
        return listaKinooperatera;
    }

    @Override
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu) throws SQLException {
        DBDAOZaposleni zaposleniDAO = new DBDAOZaposleni();
        DTOKinooperater kinooperaterDTO = (DTOKinooperater)list;
        DTOZaposleni zaposleniDTO = new DTOZaposleni((Zaposleni)kinooperaterDTO.getKinooperater());
        zaposleniDAO.azurirajBazu(zaposleniDTO, konekcijaNaBazu);
        return true;
    }


    @Override
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) throws SQLException {
        DTOKinooperater lokalniKinooperater;
        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement("select * from Kinooperater where idZaposlenog = ?");
        preparedStatement.setInt(1, Integer.parseInt(parametarPretrage));
        ResultSet resultSet = preparedStatement.executeQuery();
        Kinooperater kino = new Kinooperater();
        if (resultSet.next()){
            kino.setIdKinooperatera(resultSet.getInt(1));
            lokalniKinooperater = new DTOKinooperater(kino);
        }else{
            lokalniKinooperater = null;
        }
        return lokalniKinooperater;
    }

    @Override
    public List<DTOKinooperater> ispisi(Connection konekcijaNaBazu) throws SQLException {
        return citajIzBaze(konekcijaNaBazu);
    }
}
