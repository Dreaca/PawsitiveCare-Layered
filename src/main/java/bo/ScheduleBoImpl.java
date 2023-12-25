package bo;

import dao.DaoFactory;
import dao.custom.Impl.ScheduleDaoImpl;
import dao.custom.ScheduleDao;
import dto.ScheduleDto;

import java.sql.SQLException;

public class ScheduleBoImpl implements ScheduleBo{
    private ScheduleDao scheduleDao = (ScheduleDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.SCHEDULE);
    @Override
    public String getNextShedId() throws SQLException {
        return scheduleDao.getNextShedId();
    }

    @Override
    public void saveScheduleItem(ScheduleDto dto) throws SQLException, ClassNotFoundException {
        scheduleDao.save(dto);
    }
}
