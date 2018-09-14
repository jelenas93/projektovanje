package projektovanje.dbDAO;

import projektovanje.bin.zaposleni.Administrator;
import projektovanje.dto.DTOAdministrator;
import projektovanje.dto.IDTO;

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
        Administrator administrator = new Administrator(resultSet.getInt(1));
        DTOAdministrator dtoAdministrator = new DTOAdministrator(administrator);
        listaAdministratora.add(dtoAdministrator);
    }
    return listaAdministratora;

    }

    @Override
<<<<<<< HEAD
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu) throws SQLException {
=======
<<<<<<< HEAD
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu) {
=======
    public Boolean azurirajBazu(IDTO dtoAdministrator, Connection konekcijaNaBazu){
>>>>>>> a042473c47eaa2ea37df5bc0659bb776f1068b17
>>>>>>> 119457b79ea8ebc88d4b6e2705a6ba9d3cd5687a
        return null;
    }

    @Override
    public List<DTOAdministrator> pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) {
        return null;
    }

    @Override
    public List<DTOAdministrator> ispisi(Connection konekcija) throws java.sql.SQLException {
        return citajIzBaze(konekcija);
    }
}