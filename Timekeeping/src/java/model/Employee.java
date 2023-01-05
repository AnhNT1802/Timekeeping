/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author NTA-PC
 */
public class Employee {

    private int id;
    private String name;
    private ArrayList<Work> works = new ArrayList<>();
    private ArrayList<LeaveRequest> leaves = new ArrayList<>();

    public ArrayList<LeaveRequest> getLeaves() {
        return leaves;
    }

    public int getTotalLeaves() {
        int sum = 0;
        for (LeaveRequest leave : leaves) {
            sum += leave.getTotalDays();
        }
        return sum;
    }

    public void setLeaves(ArrayList<LeaveRequest> leaves) {
        this.leaves = leaves;
    }

    public int getNumberOfWorkingDays() {
        return works.size();
    }

    public float getNumberOfWorkingHours() {
        float sum = 0;
        for (Work work : works) {
            sum += work.getWorkingHours();
        }
        return sum;
    }

    public ArrayList<Work> getWorks() {
        return works;
    }

    public void setWorks(ArrayList<Work> works) {
        this.works = works;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
