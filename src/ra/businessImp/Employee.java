package ra.businessImp;

import ra.business.IEmployee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee implements IEmployee  {

    private String employeeId;
    private String employeeName;
    private Date birthday;
    private float rate;
    private float commission;
    private double salary;
    private boolean status;


    public void calSalary(Employee[] employees){

        for (int i = 0; i < employees.length; i++) {
            if( employees[i] != null){
                double salary =  employees[i].getRate() * BASIC_SALARY + employees[i].getCommission();
                employees[i].setSalary(salary);
            }
        }
        System.out.println("Update salary successfully");

    }



    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", birthday=" + birthday +
                ", rate=" + rate +
                ", commission=" + commission +
                ", salary=" + salary +
                ", status=" + (status ?  "Dang lam" : "Nghi viec") +
                '}';
    }

    @Override
    public void inputData(Scanner scanner, Employee[] employees) {
        this.employeeId = checkInputEmployeeId(scanner, employees);
        this.employeeName = inputEmployeeName(scanner);
        this.birthday = checkDateOfBirth(scanner, employees);
        this.rate = inputRate(scanner);
        this.commission = inputCommission(scanner);
        this.status = inputStatus(scanner);


    }
    public boolean inputStatus(Scanner scanner){
        System.out.println("input employee Status ");
        return Boolean.parseBoolean(inputFromUser(scanner, "(true|false)"));
    }

    public float inputCommission(Scanner scanner){
        System.out.println("input employee commission ");
        return Float.parseFloat(inputFromUser(scanner, "\\d+"));
    }

    public String inputEmployeeName(Scanner scanner){
        System.out.println("input employee name");
        return inputFromUser(scanner, "\\w{6,50}");
    }
    public Float inputRate(Scanner scanner){
        System.out.println("input employee Rate");
        return Float.parseFloat(inputFromUser(scanner, "\\d+"));
    }
    public Date checkDateOfBirth(Scanner scanner, Employee[] employees){
        System.out.println("input date of birth");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        do {
            try {
                return sdf.parse(scanner.nextLine());
            } catch (ParseException e) {
                System.err.println("input in valid");
            }catch (Exception ex){
                System.err.println("Loi he thong");
            }
        }while(true);


    }

    public String checkInputEmployeeId(Scanner scanner, Employee[] employees){

        boolean isExist = false;
        for (int i = 0; i < employees.length; i++) {
            if(employees[i] != null && employees[i].getEmployeeId() != null && employees[i].getEmployeeId().equals(employeeId)){
                isExist = true;
                break;
            }
        }
        do {
            System.out.println("input employee id");
            String employeeId = inputFromUser(scanner, "E\\w{3}");
            if(isExist){
                System.out.println("Employee Id is exists");

            }else{
                return employeeId;
            }
        }while (true);


    }



    public String inputFromUser(Scanner scanner, String regex){
        while (true){
            String value = scanner.nextLine();
            if(isValid(regex, value)){
                return value;
            }
            else{
                System.out.println("Your input is invalid!");
            }
        }

    }
    public boolean isValid(String regex, String value){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);

        return matcher.matches();
    }


    @Override
    public void displayData(Employee[] employees) {
        for (int i = 0; i < employees.length; i++) {
            if(employees[i] != null){
                System.out.println(employees[i].toString());
            }
        }
    }


    public Employee() {
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getCommission() {
        return commission;
    }

    public void setCommission(float commission) {
        this.commission = commission;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


}
