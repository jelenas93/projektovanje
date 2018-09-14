package projektovanje.dbDAO;

import projektovanje.dao.IDAO;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IDBDAO extends IDAO {
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu)throws SQLException; //poradi na argumetima
    public List<? extends IDTO> citajIzBaze(Connection konekcijaNaBazu)throws SQLException; //poradi na argumentima
    public Boolean azurirajBazu(List<? extends IDTO> list, Connection konekcijaNaBazu)throws SQLException; //poradi na argumentima
    public List<? extends IDTO> pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage)throws SQLException; //poradi na argumentima
}
