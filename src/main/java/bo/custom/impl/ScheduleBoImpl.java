package bo.custom.impl;

import bo.custom.ScheduleBo;
import dao.DaoFactory;
import dao.custom.*;
import dto.ScheduleDto;
import entity.Schedule;
import entity.VetSchedule;
import entity.VetScheduleJoin;
import entity.Veterinarian;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ScheduleBoImpl implements ScheduleBo {
    private ScheduleDao scheduleDao = (ScheduleDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.SCHEDULE);
    private VetDao vetDao = (VetDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.VET);
    private VetScheduleDao VSdao = (VetScheduleDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.VS);
    QueryDao queryDao = (QueryDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.QUERY);
    @Override
    public String getNextShedId() throws SQLException {
        return scheduleDao.getNextShedId();
    }

    @Override
    public boolean saveScheduleItem(ScheduleDto dto) throws SQLException, ClassNotFoundException {
        String vetId = vetDao.getVetId(dto.getVetName());
        boolean flag = false;
        Transaction.setAutoCommit(false);
        boolean save1 = scheduleDao.save(new Schedule(dto.getScheduleId(), dto.getDate(), dto.getDuration(), dto.getTime()));
        boolean save = VSdao.save(new VetSchedule(dto.getScheduleId(), vetId));
        if (save1) {
            if (save){
                Transaction.commit();
                Transaction.setAutoCommit(true);
                flag = true;
            }
        }
        else{
            Transaction.rollback();
            Transaction.setAutoCommit(true);
            flag = false;
        }
        return flag;
    }

    @Override
    public List<ScheduleDto> loadScheduleList() throws SQLException, ClassNotFoundException {
        List<VetScheduleJoin> scheduleData = queryDao.getScheduleData();
        ArrayList<ScheduleDto> list = new ArrayList<>();
        for (VetScheduleJoin v: scheduleData) {
            list.add(
                    new ScheduleDto(
                            v.getScheduleId(),
                            v.getDate(),
                            v.getTime(),
                            v.getDuration(),
                            v.getVetName()
                    )
            );
        }
        return list;
    }

    @Override
    public ArrayList<String> getAllVetNames() throws SQLException, ClassNotFoundException {
        List<Veterinarian> list = vetDao.getAll();
        ArrayList<String> names = new ArrayList<>();
        for (Veterinarian a: list) {
            names.add(
                    a.getName()
            );
        }
        return names ;
    }

    @Override
    public boolean deleteScheduleItem(String vetName, LocalDate date, String duration, LocalTime time) throws SQLException, ClassNotFoundException {
        System.out.println("delete button pressed");
        List<VetScheduleJoin> scheduleData = queryDao.getScheduleData();
        String schedId = "";
        for (VetScheduleJoin v: scheduleData) {
            if (v.getVetName().equals(vetName)&
            v.getDate().equals(date)&
            v.getTime().equals(time)&v.getDuration().equals(duration)){
                schedId = v.getScheduleId();
                System.out.println(schedId);
                break;
            }
        }
        return scheduleDao.delete(schedId);
    }
}
