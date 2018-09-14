package projektovanje.dao;

import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IDAO {
    public List<? extends IDTO> ispisi(Connection konekcijaNaBazu)throws SQLException;
}
