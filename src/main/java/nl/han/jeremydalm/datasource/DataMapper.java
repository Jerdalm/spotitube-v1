package nl.han.jeremydalm.datasource;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataMapper<T> {
    T mapToDTO(ResultSet rs) throws SQLException;
}
