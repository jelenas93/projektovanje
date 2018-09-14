package projektovanje.dbDAO;

import projektovanje.dto.DTOArtikal;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class DBDAOArtikal implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoArtikal, Connection konekcijaNaBazu) throws java.sql.SQLException{
        Boolean uspjesno = false;
        DTOArtikal artikal = (DTOArtikal)dtoArtikal;
        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement("insert into Artikal values(default, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, artikal.getArtikal().getNaziv());
        preparedStatement.setInt(2, artikal.getArtikal().getKolicinaNaStanju());
        preparedStatement.setDouble(3, artikal.getArtikal().getJedinicnaCijena());
        preparedStatement.setString(4, artikal.getArtikal().getTip());
        preparedStatement.setString(5, artikal.getArtikal().getBarKod());
        preparedStatement.setInt(6, artikal.getArtikal().getIdZaposlenog());
        preparedStatement.executeUpdate();
        uspjesno = true;
        return uspjesno;
    }

    @Override
    public List<DTOArtikal> citajIzBaze(Connection konekcijaNaBazu) {
        return null;
    }

    @Override
<<<<<<< HEAD
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu) {
=======
    public Boolean azurirajBazu(IDTO dtoArtikal, Connection konekcijaNaBazu) {
>>>>>>> a042473c47eaa2ea37df5bc0659bb776f1068b17
        return null;
    }

    @Override
    public List<DTOArtikal> pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) {
        return null;
    }

    @Override
    public List<DTOArtikal> ispisi(Connection konekcija) {
        return null;
    }
}
