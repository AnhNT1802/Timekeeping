/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import helper.DateTimeHelper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employee;
import model.LeaveRequest;
import model.Work;

/**
 *
 * @author NTA-PC
 */
public class EmployeeDBContext extends DBContext {

    public ArrayList<Employee> getEmployees(Date begin, Date end) {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            String sql = "SELECT EMP.ID, EMP.Name, ISNULL(TS.ID, -1) WorkID, TS.CheckIn, TS.CheckOut\n"
                    + "FROM Employee EMP\n"
                    + "LEFT JOIN (SELECT * FROM Timesheet WHERE\n"
                    + "CheckIn >= ? AND CheckIn < ?) TS\n"
                    + "ON EMP.ID = TS.EmployeeID";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, DateTimeHelper.getTimeStamp(DateTimeHelper.removeTime(begin)));
            statement.setTimestamp(2, DateTimeHelper.getTimeStamp(DateTimeHelper.removeTime(end)));

            ResultSet resultSet = statement.executeQuery();
            Employee currentEmployee = new Employee();
            currentEmployee.setId(-1);

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                if (id != currentEmployee.getId()) {
                    currentEmployee = new Employee();
                    currentEmployee.setId(id);
                    currentEmployee.setName(resultSet.getString("Name"));
                    employees.add(currentEmployee);
                }
                int workId = resultSet.getInt("WorkID");
                if (workId != -1) {
                    Work work = new Work();
                    work.setEmployee(currentEmployee);
                    work.setId(workId);
                    work.setCheckIn(DateTimeHelper.getDateFrom(resultSet.getTimestamp("CheckIn")));
                    work.setCheckOut(DateTimeHelper.getDateFrom(resultSet.getTimestamp("CheckOut")));
                    currentEmployee.getWorks().add(work);
                }
            }
        } catch (SQLException exception) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, exception);
        }

        LeaveRequest leave = new LeaveRequest();
        leave.setId(1);
        leave.setReason(1);
        leave.setEmployee(employees.get(1));
        Date date = new Date();
        date = DateTimeHelper.removeTime(date);
        leave.setFrom(DateTimeHelper.addDays(date, 3));
        leave.setTo(DateTimeHelper.addDays(date, 5));
        employees.get(1).getLeaves().add(leave);

        return employees;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        Date today = new Date();
//        Date today = new SimpleDateFormat("dd/MM/yyyy").parse("02/08/2022");
        today = DateTimeHelper.removeTime(today);
        int dayOfMonth = DateTimeHelper.getDayOfMonth(today);
        Date begin = DateTimeHelper.addDays(today, -1 * (dayOfMonth - 1));
        Date end = DateTimeHelper.addDays(DateTimeHelper.addMonths(begin, 1), -1);
        EmployeeDBContext employeeDB = new EmployeeDBContext();
        ArrayList<Employee> employees = employeeDB.getEmployees(begin, DateTimeHelper.addDays(end, 1));
        Employee employee = employees.get(0);
        System.out.println(employee.getName());
        System.out.println(employee.getNumberOfWorkingDays());
    }
    
}
