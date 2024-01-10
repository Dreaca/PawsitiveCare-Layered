package bo.custom.impl;

import bo.custom.EmployeeBo;
import dao.DaoFactory;
import dao.custom.EmployeeDao;
import dao.custom.LoginDao;
import dto.EmployeeDto;
import dto.LoginFormDto;
import entity.Employee;
import entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBoImpl implements EmployeeBo {
private EmployeeDao dao = (EmployeeDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.EMPLOYEE);
    private LoginDao loginDao = (LoginDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.LOGIN);
    @Override
    public boolean saveUser(LoginFormDto dto) throws SQLException, ClassNotFoundException {
       return loginDao.save(new User(dto.getUserID(),dto.getUserName(),dto.getPassword()));
    }

    @Override
    public boolean saveEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return dao.save(new Employee(dto.getEmpId(),dto.getEmpName(),dto.getAddress(),dto.getContact(),dto.getSalary(),dto.getUserId(),dto.getNIC()));
    }

    @Override
    public boolean UpdateNIC(String userId, String nic) throws SQLException {
        return dao.UpdateNIC(userId,nic);
    }

    @Override
    public boolean updateName(String name, String userId) throws SQLException {
        return dao.updateName(name,userId);
    }

    @Override
    public boolean deleteEmployee(String empId) throws SQLException, ClassNotFoundException {
        boolean delete = dao.delete(empId);
        if (delete){
            String userId = dao.getEmployee(empId);
            return loginDao.delete(userId);
        }
        else return false;
    }

    @Override
    public List<EmployeeDto> getEmployees() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> all = dao.getAll();
        ArrayList<EmployeeDto> list = new ArrayList<>();
        for (Employee e: all) {
            list.add(new EmployeeDto(
               e.getEmployeeId(),
               e.getName(),
               e.getAddress(),
                    e.getContact(),
                    e.getSalary(),
                    e.getUserId(),
                    e.getNIC()
            ));
        }
        return list;
    }

    @Override
    public boolean UpdateUserId(String oldUserName, String newUserId) throws SQLException {
        return dao.updateName(oldUserName,newUserId);
    }

    @Override
    public String generateNextEmpId() throws SQLException {
        return dao.generateNextEmpId();
    }

    @Override
    public String generateNExtUserID() throws SQLException {
       return loginDao.generateNExtUserID();
    }
}
