package ra.presention;

import ra.businessImp.Employee;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class EmployeeManagement  {
    byte indexEmployee = 0;
    Employee[] employees = new Employee[10];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Employee employee = new Employee();

        EmployeeManagement employeeManagement = new EmployeeManagement();
        while (true) {
            System.out.println("********************MENU*********************");
            System.out.println("1. Nhập thông tin cho n nhân viên");
            System.out.println("2. Hiển thị thông tin nhân viên");
            System.out.println("3. Tính lương cho các nhân viên");
            System.out.println("4. Tìm kiếm nhân viên theo tên nhân viên");
            System.out.println("5. Cập nhật thông tin nhân viên");
            System.out.println("6. Xóa nhân viên theo mã nhân viên");
            System.out.println("7. Sắp xếp nhân viên theo lương tăng dần (Comparable)");
            System.out.println("8. Sắp xếp nhân viên theo tên nhân viên giảm dần (Comparator)");
            System.out.println("9. Sắp xếp nhân vên theo năm sinh tăng dần (Comparator)");
            System.out.println("10. Thoát");

            byte choice = Byte.parseByte(employee.inputFromUser(scanner, "\\d+"));

            switch (choice) {
                case 1:
                    employeeManagement.inputInfoEmployee(scanner, employee);
                    break;
                case 2:
                    employeeManagement.displayAllEmployee();
                    break;
                case 3:
                    employee.calSalary(employeeManagement.employees);
                    break;
                case 4:
                    employeeManagement.searchByEmployeeName(employee, scanner);
                    break;
                case 5:
                    employeeManagement.updateEmployeeInfo(scanner, employee);
                    break;
                case 6:
                    employeeManagement.deleteEmployeeById(scanner, employee);
                    break;
                case 7:
                    employeeManagement.sortBySalary(employee);
                    break;
                case 8:

                    break;
                case 9:

                    break;
                case 10:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Your choice out of range");
                    break;
            }
        }
    }



    public void deleteEmployeeById(Scanner scanner, Employee employee){
        System.out.println("Input employeeId");
        String employeeId = employee.inputFromUser(scanner, "E\\w{3}");
        boolean isExist = false;

        for (int i = 0; i < employees.length ; i++) {
            //quet cho den khi tim dc employeeId
            if( employees[i] != null && employees[i].getEmployeeId().equals(employeeId)){
                    //dich ghi de vi tri hien tai bang vi tri ke ben
                for (int j = i; j < employees.length -1; j++) {
                    employees[j] = employees[j+1];
                }
                //khi di ghi de xong, phan tu cuoi cung neu ko = null, no se mang gia tri cua phan tu gan cuoi.
                employees[employees.length -1] = null;
                isExist = true;
                System.out.println("Delete successfully");
                break;
            }
        }
        if(!isExist){
            System.out.println("Employee Id not found");
        }
    }

    public void updateEmployeeInfo(Scanner scanner, Employee employee){
        System.out.println("Input Employee Id");
        String employeeId = employee.inputFromUser(scanner, "E\\w{3}");

        boolean isExist = false;
        for (int i = 0; i < employees.length; i++) {
            if( employees[i] != null && employees[i].getEmployeeId().equals(employeeId)){
                displayInfoUpdate(scanner, employees[i]);
                isExist = true;
            }
        }

        if(!isExist){
            System.out.println("Employee Id not found");
        }

    }
    public void displayInfoUpdate(Scanner scanner, Employee employee){



        do{
            System.out.println("Which info you want to update?");
            System.out.println("********************UPDATE_INFO*********************");
            System.out.println("1. Employee Name");
            System.out.println("2. Rate");
            System.out.println("3. Commission");
            System.out.println("4. status");
            System.out.println("5. Escape");
            byte choice = Byte.parseByte(employee.inputFromUser(scanner, "\\d+"));
            boolean isOut = false;
            switch (choice){
                case 1:
                    System.out.println("Input Employee Name");
                    String employeeName = employee.inputFromUser(scanner, "\\w+");
                    employee.setEmployeeName(employeeName);
                    System.out.println("Update Name Successfully");
                    break;
                case 2:
                    System.out.println("Input Employee Rate");
                    float rate = Float.parseFloat(employee.inputFromUser(scanner, "\\w+"));
                    employee.setRate(rate);
                    System.out.println("Update Rate Successfully");
                    break;
                case 3:
                    System.out.println("Input Employee Commission");
                    float commission = Float.parseFloat(employee.inputFromUser(scanner, "\\w+"));
                    employee.setCommission(commission);
                    System.out.println("Update Commission Successfully");
                    break;
                case 4:
                    System.out.println("Input Employee Status");
                    boolean status = Boolean.parseBoolean(employee.inputFromUser(scanner, "\\w+"));
                    employee.setStatus(status);
                    System.out.println("Update Status Successfully");
                    break;
                case 5:
                    isOut = true;
                    break;
                default:
                    System.out.println("Your choice out of range");
                    break;
            }
            if(isOut){
                break;
            }
        }while (true);


    }



    public void searchByEmployeeName(Employee employee, Scanner scanner){

        System.out.println("Input employee Name");

        String inputName = employee.inputFromUser(scanner, "\\w+");
        boolean isExist = false;
        for (int i = 0; i < employees.length; i++) {
            if(employees[i] != null && employees[i].getEmployeeName().contains(inputName)){
                System.out.println(employees[i].toString());
                isExist = true;
            }
        }
        if(!isExist){
            System.out.println("Employee Name is not found");
        }

    }

    public void displayAllEmployee(){
        for (int i = 0; i < employees.length; i++) {
            if(employees[i] != null){
                System.out.println(employees[i].toString());
            }
        }


    }

    public void inputInfoEmployee(Scanner scanner, Employee employee){
        System.out.println("How many Employee you want to add");
        byte amount = Byte.parseByte(employee.inputFromUser(scanner, "\\d+"));

        for (byte i = 0; i < amount; i++) {
            employees[indexEmployee] = new Employee();
            employees[indexEmployee].inputData(scanner,employees);
            indexEmployee++;
        }
        System.out.println("Add successfully");

    }

    public void sortBySalary(Employee employee){

        boolean checkNull = false;
        byte minIndex = 0;

        byte lengthOfArr = (byte) employees.length;
        for (byte i = 0; i < lengthOfArr; i++) {
            if(employees[i] != null && employees[i].getSalary() <= 0){
                checkNull = true;
                break;
            } else {
                minIndex = i;
                for (byte j = 0; j < lengthOfArr; j++) {
                    if(employees[i] != null &  employees[minIndex].getSalary() > employees[j].getSalary()){
                        minIndex = j;
                    }
                }

                employee = employees[i];
                employees[i] = employees[minIndex];
                employees[minIndex] = employee;
            }

        }
        if(checkNull){
            System.out.println("Salary is not calculate yet!");
        }


    }




}