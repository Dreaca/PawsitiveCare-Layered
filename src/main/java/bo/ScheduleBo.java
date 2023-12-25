package bo;

import dto.ScheduleDto;

import java.sql.SQLException;

public interface ScheduleBo extends SuperBO{
    String getNextShedId() throws SQLException;

    void saveScheduleItem(ScheduleDto dto) throws SQLException, ClassNotFoundException;
}
