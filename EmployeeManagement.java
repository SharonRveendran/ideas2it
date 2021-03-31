import com.ideas2it.employeemanagement.employee.view.EmployeeView;
import com.ideas2it.employeemanagement.project.view.ProjectView;


import java.util.Scanner;

/**
 * Doing CRUD operation in database
 * @author Sharon V
 * @created 21-03-2021
 */
public class EmployeeManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeView employeeView = new EmployeeView();
        ProjectView projectView = new ProjectView();
        System.out.println("\n\n.............. WELCOME TO EMPLOYEE MANAGEMENT SYSTEM ...............");
        String message = "\n1 : Employee Management\n\n2 : Project Management\n\n3 : Exit\n"
               + "------------------------";
        System.out.println("\n\nSelect your option");
        String option;
        do {
            System.out.println(message);
            option = scanner.nextLine();
            switch(option) {
                case "1":
                    System.out.println("\n.............. WELCOME TO EMPLOYEE MANAGEMENT ...............");
                    employeeView.start();
                    break;
                case "2":
                    System.out.println("\n.............. WELCOME TO PROJECT MANAGEMENT ...............");
                    projectView.start();
                    break;
                case "3":
                    System.out.println("\nThank you....");
                    option = "0";
                    break;
                default:
                    System.out.println("Enter valid option");
            }
        } while(!"0".equals(option));        
    }
}