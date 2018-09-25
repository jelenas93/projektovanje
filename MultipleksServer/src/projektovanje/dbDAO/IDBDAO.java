package projektovanje.dbDAO;

import projektovanje.dao.IDAO;
import projektovanje.dto.IDTO;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IDBDAO extends IDAO {
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu)throws SQLException, IOException; //poradi na argumetima
    public List<? extends IDTO> citajIzBaze(Connection konekcijaNaBazu) throws SQLException, IOException; //poradi na argumentima
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu)throws SQLException; //poradi na argumentima
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) throws SQLException, IOException; //poradi na argumentima
    public default int zadnjiUmetnutiId(Connection konekcijaNaBazu) throws SQLException {
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("select last_insert_id()");
        int lastId = -1;
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            lastId = rs.getInt("last_insert_id()");
        }
        return lastId;
    }
}
