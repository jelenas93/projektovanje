package projektovanje.dbDAO;

import projektovanje.dto.DTOKarta;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.util.List;

public class DBDAOKarta implements IDBDAO {

    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) {
        return null;
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
