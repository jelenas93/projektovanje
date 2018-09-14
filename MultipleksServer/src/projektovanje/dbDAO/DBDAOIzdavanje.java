package projektovanje.dbDAO;

import projektovanje.dto.DTOIzdavanje;
import projektovanje.dto.IDTO;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DBDAOIzdavanje implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoIzdavanje, Connection konekcijaNaBazu) throws java.sql.SQLException {
        Boolean uspjesno = false;

        DTOIzdavanje lokalniDtoIzdavanje = (DTOIzdavanje) dtoIzdavanje;
        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement(
                "insert into Izdavanje (?, ?, ?, ?, ?, ?)");
        preparedStatement.setInt(1,lokalniDtoIzdavanje.getIzdavanje().getKarta().getIdKarte());
        preparedStatement.setInt(2, lokalniDtoIzdavanje.getIzdavanje().getSjediste().getIdSjedista());
        preparedStatement.setInt(3, lokalniDtoIzdavanje.getIzdavanje().getSala().getIdSale());
        preparedStatement.setInt(4, lokalniDtoIzdavanje.getIzdavanje().getProjekcija().getIdProjekcije());
        preparedStatement.setInt(5, lokalniDtoIzdavanje.getIzdavanje().getFilm().getIdFilma());
        preparedStatement.setInt(6, lokalniDtoIzdavanje.getIzdavanje().getZaposleni().getIdZaposlenog());
        preparedStatement.executeQuery();

        uspjesno = true;
        return uspjesno;
    }

    @Override
    public List<DTOIzdavanje> citajIzBaze(Connection konekcijaNaBazu)throws java.sql.SQLException {
        List<DTOIzdavanje> listaIzdavanja = new ArrayList<>();

        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement("select * from Izdavanje");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            //TO-DO uradi formatiranje izlaza
        }
        return listaIzdavanja;
    }

    @Override
    public Boolean azurirajBazu(IDTO dtoIzdavanje, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<DTOIzdavanje> pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) {
        return null;
    }

    @Override
    public List<DTOIzdavanje> ispisi(Connection konekcijaNaBazu) {
        return null;
    }
}
