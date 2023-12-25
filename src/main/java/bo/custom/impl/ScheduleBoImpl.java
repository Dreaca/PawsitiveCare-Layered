package bo.custom.impl;

import bo.custom.ScheduleBo;
import dao.DaoFactory;
import dao.custom.ScheduleDao;
import dao.custom.VetDao;
import dao.custom.VetScheduleDao;
import dto.ScheduleDto;
import dto.VetDto;
import entity.Schedule;
import entity.VetSchedule;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleBoImpl implements ScheduleBo {
    private ScheduleDao scheduleDao = (ScheduleDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.SCHEDULE);
    private VetDao vetDao = (VetDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.VET);
    private VetScheduleDao VSdao = (VetScheduleDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.VS);
    @Override
    public String getNextShedId() throws SQLException {
        return scheduleDao.getNextShedId();
    }

    @Override
    public boolean saveScheduleItem(ScheduleDto dto) throws SQLException, ClassNotFoundException {
        boolean save1 = scheduleDao.save(new Schedule(dto.getScheduleId(), dto.getDate(), dto.getDuration(), dto.getTime()));
        String vetId = vetDao.getVetId(dto.getVetName());
        boolean save = VSdao.save(new VetSchedule(dto.getScheduleId(), vetId));
        if(save&&save1){
            return true;
        }
        else return false;
    }

    @Override
    public List<ScheduleDto> loadScheduleList() throws SQLException, ClassNotFoundException {
        ArrayList<Schedule> all = scheduleDao.getAll();
        ArrayList<VetSchedule> vetSchedules = VSdao.getAll();
        ArrayList<ScheduleDto> list = new ArrayList<>();

        for (VetSchedule v: vetSchedules) {
            VetDto search = vetDao.search(v.getVetid());
        for (Schedule a: all) {
            list.add(
                    new ScheduleDto(
                            a.getScheduleId(),
                            a.getDate(),
                            a.getDuration(),
                            a.getTime(),
                            search.getVetName()
                    )
            );
            }
        }
        return list;
    }

    @Override
    public ArrayList<String> getAllVetNames() throws SQLException, ClassNotFoundException {
        List<VetDto> list = vetDao.getAll();
        ArrayList<String> names = new ArrayList<>();
        for (VetDto a: list) {
            names.add(
                    a.getVetName()
            );
        }
        return names ;
    }
}
