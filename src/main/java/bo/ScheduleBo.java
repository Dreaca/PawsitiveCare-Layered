package bo;

import dto.ScheduleDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ScheduleBo extends SuperBO{
    String getNextShedId() throws SQLException;

    boolean saveScheduleItem(ScheduleDto dto) throws SQLException, ClassNotFoundException;

    List<ScheduleDto> loadScheduleList() throws SQLException, ClassNotFoundException;

    ArrayList<String> getAllVetNames() throws SQLException, ClassNotFoundException;
}
