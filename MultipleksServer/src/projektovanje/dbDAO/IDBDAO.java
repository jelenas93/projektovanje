package projektovanje.dbDAO;

import projektovanje.dao.IDAO;
import projektovanje.dto.IDTO;

import java.util.List;

public interface IDBDAO extends IDAO {
    public Boolean upisiUBazu(List<? extends IDTO> list); //poradi na argumetima
    public List<? extends IDTO> citajIzBaze(); //poradi na argumentima
    public Boolean ispisiTabelu(List<? extends IDTO> list); //poradi na argumentima
    public Boolean azurirajBazu(List<? extends IDTO> list); //poradi na argumentima
    public List<? extends IDTO> pretraziBazu(); //poradi na argumentima
}
