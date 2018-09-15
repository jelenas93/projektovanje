package projektovanje.dbDAO;

import projektovanje.dto.DTOKarta;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class DBDAOKarta implements IDBDAO {

    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) throws java.sql.SQLException {
        PreparedStatement preparedStatement;
        DTOKarta lokalniDTO = (DTOKarta)dtoInstanca;
        if(lokalniDTO.getKarta().getRezervisana())
        {
            preparedStatement = konekcijaNaBazu.prepareStatement("insert into Karta values(default, ?, ?, true, ?)");
            preparedStatement.setDate(1, new java.sql.Date(lokalniDTO.getKarta().getDatumIzdavanja().getTime()));
            preparedStatement.setDouble(2, lokalniDTO.getKarta().getCijena());
            preparedStatement.setString(3, lokalniDTO.getKarta().getKlijent().getKorisnickiNalog().getKorisnickiNalog());
        }else{
            preparedStatement = konekcijaNaBazu.prepareStatement("insert into Karta values(default, ?, ?, false, null)");
            preparedStatement.setDate(1, new java.sql.Date(lokalniDTO.getKarta().getDatumIzdavanja().getTime()));
            preparedStatement.setDouble(2, lokalniDTO.getKarta().getCijena());
        }
        preparedStatement.executeQuery();

        return true;
    }

    @Override
    public List<DTOKarta> citajIzBaze(Connection konekcijaNaBazu) {
        return null;
    }

    @Override

    public Boolean azurirajBazu(IDTO dtoKarta, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<DTOKarta> pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) {
        return null;
    }

    @Override
    public List<DTOKarta> ispisi(Connection konekcijaNaBazu) {
        return null;
    }
}
