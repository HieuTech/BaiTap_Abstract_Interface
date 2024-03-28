package ra.business;

import ra.businessImp.Employee;

import java.util.Scanner;

public interface IEmployee {
    public static final double BASIC_SALARY = 1300000;

     public void inputData(Scanner scanner, Employee[] employees);

     public void displayData(Employee[] employees);

}
