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
        System.out.println("\n\n........... Welcome to Employee Management System ...........");
        String message = "\n1 : Employee Management\n2 : Project Management";
        System.out.println("\n\nSelect your option");
        String option;
        do {
            System.out.println(message);
            option = scanner.nextLine();
            switch(option) {
                case "1":
                    employeeView.start();
                    break;
                case "2":
                    projectView.start();
                    break;
                default:
                    System.out.println("Enter valid option");
                    option = "0";
            }
        } while("0".equals(option));        
    }
}