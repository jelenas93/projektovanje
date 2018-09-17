package projektovanje.dbDAO;

import projektovanje.bin.zaposleni.ProdavacHraneIPica;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dto.DTOProdavacHraneIPica;
import projektovanje.dto.DTOZaposleni;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBDAOProdavacHraneIPica implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) throws SQLException {
        PreparedStatement upisProdavacHraneIPicaa = konekcijaNaBazu.prepareStatement("insert into ProdavacHraneIPica values (?)");
        DTOProdavacHraneIPica lokalniProdavacHraneIPica = (DTOProdavacHraneIPica) dtoInstanca;
        upisProdavacHraneIPicaa.setInt(1, lokalniProdavacHraneIPica.getProdavacHraneIPica().getIdProdavacaHraneIPica());
        upisProdavacHraneIPicaa.executeUpdate();
        return true;}

    @Override
    public List<DTOProdavacHraneIPica> citajIzBaze(Connection konekcijaNaBazu) throws SQLException {
        List<DTOProdavacHraneIPica> listaProdavacHraneIPicaa = new ArrayList<DTOProdavacHraneIPica>();
        PreparedStatement citanjeSvihProdavacHraneIPicaa = konekcijaNaBazu.prepareStatement("select * from ProdavacHraneIPica");
        ResultSet resultSet = citanjeSvihProdavacHraneIPicaa.executeQuery();
        while(resultSet.next()){
            DBDAOZaposleni zaposleniDAO = new DBDAOZaposleni();
            DTOZaposleni zaposleniDTO;
            Integer hlpVar = resultSet.getInt(1);
            zaposleniDTO = (DTOZaposleni)zaposleniDAO.pretraziBazu(konekcijaNaBazu, hlpVar.toString());
            ProdavacHraneIPica ProdavacHraneIPica = new ProdavacHraneIPica(zaposleniDTO.getZaposleni());
            DTOProdavacHraneIPica dtoAdministrator = new DTOProdavacHraneIPica(ProdavacHraneIPica);
            listaProdavacHraneIPicaa.add(dtoAdministrator);
        }
        return listaProdavacHraneIPicaa;}

    @Override
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu) throws SQLException {
        DBDAOZaposleni zaposleniDAO = new DBDAOZaposleni();
        DTOProdavacHraneIPica ProdavacHraneIPicaDTO = (DTOProdavacHraneIPica)list;
        DTOZaposleni zaposleniDTO = new DTOZaposleni((Zaposleni)ProdavacHraneIPicaDTO.getProdavacHraneIPica());
        zaposleniDAO.azurirajBazu(zaposleniDTO, konekcijaNaBazu);
        return true;
    }

    public List<? extends IDTO> ispisiSveAktivneProdavceHraneIPica(Connection konekcijaNaBazu) throws SQLException {
        List<DTOProdavacHraneIPica> povratnaVrijednost = new ArrayList<>();
        PreparedStatement s = konekcijaNaBazu.prepareStatement("select * from ProdavacHraneIPica");
        ResultSet rezultat = s.executeQuery();
        while(rezultat.next()){
            Integer id = rezultat.getInt(1);
            DTOZaposleni dtoZaposleni = (DTOZaposleni)new DBDAOZaposleni().procitajZaposlenogAkoJeAktivan(konekcijaNaBazu, id.toString());
            if(null != dtoZaposleni) {
                ProdavacHraneIPica pomocnaVarijabla = new ProdavacHraneIPica(dtoZaposleni.getZaposleni());
                povratnaVrijednost.add(new DTOProdavacHraneIPica(pomocnaVarijabla));
            }
        }
        return povratnaVrijednost;
    }

    @Override
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) throws SQLException {
        DTOProdavacHraneIPica lokalniProdavacHraneIPica;
        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement("select * from ProdavacHraneIPica where idZaposlenog = ?");
        preparedStatement.setInt(1, Integer.parseInt(parametarPretrage));
        ResultSet resultSet = preparedStatement.executeQuery();
        ProdavacHraneIPica var = new ProdavacHraneIPica();
        if (resultSet.next()){
            DBDAOZaposleni zaposleniDAO = new DBDAOZaposleni();
            DTOZaposleni zaposleniDTO;
            Integer hlpVar = resultSet.getInt(1);
            zaposleniDTO = (DTOZaposleni)zaposleniDAO.pretraziBazu(konekcijaNaBazu, hlpVar.toString());
            var = new ProdavacHraneIPica(zaposleniDTO.getZaposleni());
            lokalniProdavacHraneIPica = new DTOProdavacHraneIPica(var);
        }else{
            lokalniProdavacHraneIPica = null;
        }
        return lokalniProdavacHraneIPica;
    }

    @Override
    public List<DTOProdavacHraneIPica> ispisi(Connection konekcijaNaBazu) throws SQLException {

        return citajIzBaze(konekcijaNaBazu);
    }
}
