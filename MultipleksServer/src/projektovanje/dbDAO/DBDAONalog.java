package projektovanje.dbDAO;

import projektovanje.bin.nalog.Nalog;
import projektovanje.dto.DTONalog;
import projektovanje.dto.DTOPlata;
import projektovanje.dto.IDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBDAONalog implements IDBDAO {
    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu)throws SQLException {
        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement("insert into Nalog values(?,?)");
        DTONalog lokalnaVarijabla = (DTONalog) dtoInstanca;
        preparedStatement.setString(1, lokalnaVarijabla.getNalog().getKorisnickiNalog());
        preparedStatement.setString(2, lokalnaVarijabla.getNalog().getLozinkaHash());
        preparedStatement.executeUpdate();
        return true;
    }

    @Override
    public List<DTONalog> citajIzBaze(Connection konekcijaNaBazu)throws SQLException {
        List<DTONalog> listaNaloga = new ArrayList<>();

        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement("select * from Nalog");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Nalog hlpVar = new Nalog(resultSet.getString(1), resultSet.getString(2));
            listaNaloga.add(new DTONalog(hlpVar));
        }
        return listaNaloga;
    }

    @Override
    public Boolean azurirajBazu(IDTO list, Connection konekcijaNaBazu) throws SQLException {
        DTONalog lokalniDTONalog = (DTONalog) list;
        Nalog lokalniZanr = lokalniDTONalog.getNalog();
        PreparedStatement ps = konekcijaNaBazu.prepareStatement("update Nalog" +
                "   set hashLozinke = ?" +
                "   where korisnickoIme = ?");
        ps.setString(1,lokalniZanr.getLozinkaHash());
        ps.setString(2,lokalniZanr.getKorisnickiNalog());
        ps.executeUpdate();

        return true;
    }


    @Override
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage)throws SQLException {
        DTONalog lokalniNalog = new DTONalog();

        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement("select * from Nalog where korisnickoIme = ?");
        preparedStatement.setString(1, parametarPretrage);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            Nalog hlpVar = new Nalog(resultSet.getString(1), resultSet.getString(2));
            lokalniNalog = new DTONalog(hlpVar);
            System.out.println(hlpVar);
            System.out.println(hlpVar.getLozinkaHash());
        }else{
            System.out.println("NEMA NALOGA");
            lokalniNalog = null;
        }
        return lokalniNalog;
    }

    @Override
    public List<DTONalog> ispisi(Connection konekcijaNaBazu)throws SQLException {
        return citajIzBaze(konekcijaNaBazu);
    }
}
