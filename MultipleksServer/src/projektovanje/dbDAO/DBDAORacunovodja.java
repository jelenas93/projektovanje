package projektovanje.dbDAO;

import projektovanje.dto.DTORacuovodja;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.util.List;

public class DBDAORacunovodja implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) {
        return null;
    }

    @Override
    public List<DTORacuovodja> citajIzBaze(Connection konekcijaNaBazu) {
        return null;
    }

    @Override
<<<<<<< HEAD
    public Boolean azurirajBazu(IDTO dtoRacuovodja, Connection konekcijaNaBazu) {
=======
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu) {
>>>>>>> 119457b79ea8ebc88d4b6e2705a6ba9d3cd5687a
        return null;
    }

    @Override
    public List<DTORacuovodja> pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) {
        return null;
    }

    @Override
    public List<DTORacuovodja> ispisi(Connection konekcijaNaBazu) {
        return null;
    }
}
