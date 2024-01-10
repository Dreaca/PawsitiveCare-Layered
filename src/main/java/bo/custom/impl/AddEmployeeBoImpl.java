package bo.custom.impl;

import bo.custom.AddEmployeeBo;
import dao.DaoFactory;
import dao.custom.EmployeeDao;
import dao.custom.LoginDao;
import dao.custom.Transaction;
import dto.EmployeeDto;
import dto.LoginFormDto;
import entity.Employee;
import entity.User;

import java.sql.SQLException;

public class AddEmployeeBoImpl implements AddEmployeeBo {
    private EmployeeDao employeeDao = (EmployeeDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.EMPLOYEE);
    private LoginDao loginDao = (LoginDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.LOGIN);

    @Override
    public boolean saveData(LoginFormDto lDto, EmployeeDto dto) throws SQLException, ClassNotFoundException {
        Transaction.setAutoCommit(false);
        if(!loginDao.save(new User(lDto.getUserID(),lDto.getUserName(),lDto.getPassword()))){
            Transaction.rollback();
            Transaction.setAutoCommit(true);
            return false;
        }
        if (!employeeDao.save(new Employee(dto.getEmpId(),dto.getEmpName()
        ,dto.getAddress(),dto.getContact(),dto.getSalary(),dto.getUserId(),dto.getNIC()))){
            Transaction.rollback();
            Transaction.setAutoCommit(true);
            return false;
        }
        Transaction.commit();
        Transaction.setAutoCommit(true);
        return true;
    }
}
