package data;

import domein.OVChipkaart;
import domein.Reiziger;

import java.sql.SQLException;
import java.util.List;

public interface OVChipDAO {
    boolean save(OVChipkaart ovchipkaart) throws SQLException;
    boolean update(OVChipkaart ovchipkaart) throws SQLException;
    boolean delete(OVChipkaart ovchipkaart) throws SQLException;
    List<OVChipkaart> findAll() throws SQLException;

    List<OVChipkaart> findByReiziger(Reiziger reiziger) throws SQLException;
}

