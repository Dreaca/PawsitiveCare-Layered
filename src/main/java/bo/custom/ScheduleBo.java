package bo.custom;

import dto.ScheduleDto;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public interface ScheduleBo extends SuperBO{
    String getNextShedId() throws SQLException;

    boolean saveScheduleItem(ScheduleDto dto) throws SQLException, ClassNotFoundException;

    List<ScheduleDto> loadScheduleList() throws SQLException, ClassNotFoundException;

    ArrayList<String> getAllVetNames() throws SQLException, ClassNotFoundException;

    boolean deleteScheduleItem(String vetName, LocalDate date, String duration, LocalTime time) throws SQLException, ClassNotFoundException;

    String getScheduleId(String vetName, String duration, LocalDate date, LocalTime time) throws SQLException;

    boolean updateScheduleItem(ScheduleDto dto) throws SQLException, ClassNotFoundException;
}
