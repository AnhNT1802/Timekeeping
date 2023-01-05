/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import helper.DateTimeHelper;
import java.util.Date;

/**
 *
 * @author NTA-PC
 */
public class Work {

    private int id;
    private Employee employee;
    private Date checkIn;
    private Date checkOut;

    public Date getCheckInDate() {
        return DateTimeHelper.removeTime(checkIn);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public float getWorkingHours() {
        if (checkOut != null) {
            long diff = Math.abs(checkOut.getTime() - checkIn.getTime());
//            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000);
            return (diffHours * 1.0f) + ((diffMinutes * 1.0f) / 60);
        } else {
            return -1;
        }
    }

}
