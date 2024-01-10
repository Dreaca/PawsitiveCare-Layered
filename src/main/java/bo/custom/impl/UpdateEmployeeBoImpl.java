package bo.custom.impl;

import bo.custom.EmployeeBo;
import bo.custom.LoginBo;
import bo.custom.UpdateEmployeeBo;
import com.mysql.cj.log.Log;
import com.mysql.cj.protocol.a.TracingPacketSender;
import com.sun.jdi.connect.Connector;
import dao.DaoFactory;
import dao.custom.EmployeeDao;
import dao.custom.LoginDao;
import dao.custom.Transaction;
import dto.EmployeeDto;
import entity.Employee;

import java.sql.SQLException;

public class UpdateEmployeeBoImpl implements UpdateEmployeeBo {
    EmployeeDao employeeDao = (EmployeeDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.EMPLOYEE);
    LoginDao loginDao = (LoginDao) DaoFactory.getInstance().getDAO(DaoFactory.DAOType.LOGIN);
    @Override
    public boolean updateData(String userID, String pass, EmployeeDto dto) throws SQLException, ClassNotFoundException {
        Transaction.setAutoCommit(false);
        if(!loginDao.updatePassword(userID,pass)){
            Transaction.rollback();
            Transaction.setAutoCommit(true);
            return false;
        }
        if(!employeeDao.update(new Employee(dto.getEmpId(),dto.getEmpName(),dto.getAddress(),dto.getContact(),dto.getSalary(),dto.getUserId(),dto.getNIC()))){
            Transaction.rollback();
            Transaction.setAutoCommit(true);
            return false;
        }
        Transaction.commit();
        Transaction.setAutoCommit(true);
        return true;
    }
}
