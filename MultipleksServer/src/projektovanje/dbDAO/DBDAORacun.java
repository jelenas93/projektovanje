package projektovanje.dbDAO;

import projektovanje.dto.DTORacun;
import projektovanje.dto.IDTO;

import java.util.List;

public class DBDAORacun implements IDBDAO {
    @Override
    public Boolean upisiUBazu(List<? extends IDTO> dtoRacun) {
        return null;
    }

    @Override
    public List<DTORacun> citajIzBaze() {
        return null;
    }

    @Override
    public Boolean ispisiTabelu(List<? extends IDTO> dtoRacun) {
        return null;
    }

    @Override
    public Boolean azurirajBazu(List<? extends IDTO> dtoRacun) {
        return null;
    }

    @Override
    public List<DTORacun> pretraziBazu() {
        return null;
    }

    @Override
    public List<DTORacun> ispisi() {
        return null;
    }
}
