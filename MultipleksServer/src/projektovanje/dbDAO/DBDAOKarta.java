package projektovanje.dbDAO;

import projektovanje.bin.karta.Karta;
import projektovanje.bin.nalog.Nalog;
import projektovanje.dto.DTOKarta;
import projektovanje.dto.DTONalog;
import projektovanje.dto.IDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBDAOKarta implements IDBDAO {

    @Override
    public Boolean upisiUBazu(IDTO dtoInstanca, Connection konekcijaNaBazu) throws java.sql.SQLException {
        PreparedStatement preparedStatement;
        DTOKarta lokalniDTO = (DTOKarta)dtoInstanca;
            preparedStatement = konekcijaNaBazu.prepareStatement("insert into Karta values(default, ?, ?, ?, ?)");
            preparedStatement.setDate(1, new java.sql.Date(lokalniDTO.getKarta().getDatumIzdavanja().getTime()));
            preparedStatement.setDouble(2, lokalniDTO.getKarta().getCijena());
            preparedStatement.setBoolean(3,lokalniDTO.getKarta().getRezervisana());
            preparedStatement.setString(4, lokalniDTO.getKarta().getNalog().getKorisnickiNalog());
        preparedStatement.executeUpdate();

        return true;
    }

    @Override
    public List<DTOKarta> citajIzBaze(Connection konekcijaNaBazu) throws SQLException {

        List<DTOKarta> listaKarti = new ArrayList<>();

        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement("select * from Karta");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Karta karta = new Karta();
            karta.setIdKarte(resultSet.getInt(1));
            karta.setDatumIzdavanja(new java.util.Date(resultSet.getDate(2).getTime()));
            karta.setCijena(resultSet.getDouble(3));
            karta.setRezervisana(resultSet.getBoolean(4));
            String korisnickoIme = resultSet.getString(5);
            DTONalog dtoNalog = (DTONalog) new DBDAONalog().pretraziBazu(konekcijaNaBazu,korisnickoIme);
            Nalog nalog = dtoNalog.getNalog();
            karta.setNalog(nalog);
            DTOKarta dtoKarta = new DTOKarta(karta);
            listaKarti.add(dtoKarta);
        }
        return listaKarti;
    }

    @Override

    public Boolean azurirajBazu(IDTO dtoKarta, Connection konekcijaNaBazu) throws SQLException {
        DTOKarta lokalnaKarta = (DTOKarta) dtoKarta;
        PreparedStatement preparedStatement;
        if(lokalnaKarta.getKarta().getRezervisana()) {
            preparedStatement = konekcijaNaBazu.prepareStatement("" +
                    "update Karta" +
                    "   set datumIzdavanja = ?," +
                    "       cijena = ?," +
                    "       rezervisana = ?," +
                    "       korisnickoIme = ?" +
                    "   where idKarte = ?");
            preparedStatement.setDate(1, new java.sql.Date(lokalnaKarta.getKarta().getDatumIzdavanja().getTime()));
            preparedStatement.setDouble(2, lokalnaKarta.getKarta().getCijena());
            preparedStatement.setBoolean(3, lokalnaKarta.getKarta().getRezervisana());
            preparedStatement.setString(4, lokalnaKarta.getKarta().getNalog().getKorisnickiNalog());
            preparedStatement.setInt(5, lokalnaKarta.getKarta().getIdKarte());
        }else{
            preparedStatement = konekcijaNaBazu.prepareStatement("" +
                    "update Karta" +
                    "   set datumIzdavanja = ?," +
                    "       cijena = ?," +
                    "       rezervisana = ?," +
                    "       korisnickoIme = ?" +
                    "   where idKarte = ?");
            preparedStatement.setDate(1, new java.sql.Date(lokalnaKarta.getKarta().getDatumIzdavanja().getTime()));
            preparedStatement.setDouble(2, lokalnaKarta.getKarta().getCijena());
            preparedStatement.setInt(5, lokalnaKarta.getKarta().getIdKarte());
            preparedStatement.setBoolean(3, false);
            preparedStatement.setNull(4, Types.VARCHAR);
        }
        preparedStatement.executeUpdate();
        return true;
    }

    @Override
    public IDTO pretraziBazu(Connection konekcijaNaBazu, String parametarPretrage) throws SQLException {
        DTOKarta lokalniKarta;
        PreparedStatement preparedStatement = konekcijaNaBazu.prepareStatement("select * from Karta where idKarte = ?");
        preparedStatement.setInt(1, Integer.parseInt(parametarPretrage));
        ResultSet resultSet = preparedStatement.executeQuery();
        Karta karta = new Karta();
        if (resultSet.next()){
            karta.setIdKarte(resultSet.getInt(1));
            karta.setDatumIzdavanja(new java.util.Date(resultSet.getDate(2).getTime()));
            karta.setCijena(resultSet.getDouble(3));
            karta.setRezervisana(resultSet.getBoolean(4));
            String korisnickoIme = resultSet.getString(5);
            DTONalog dtoNalog = (DTONalog) new DBDAONalog().pretraziBazu(konekcijaNaBazu,korisnickoIme);
            Nalog nalog = dtoNalog.getNalog();
            karta.setNalog(nalog);
            lokalniKarta = new DTOKarta(karta);
            }else{
            lokalniKarta = null;
        }
        return lokalniKarta;
    }

    @Override
    public List<DTOKarta> ispisi(Connection konekcijaNaBazu) throws SQLException {
        return citajIzBaze(konekcijaNaBazu);
    }
}
