import java.sql.SQLException;

import com.ideas2it.employeemanagement.view.EmployeeView;

/**
 * Doing CRUD operation in database
 * @author Sharon V
 * @created 21-03-2021
 */
public class EmployeeManagement {
    public static void main(String[] args) {
        private Scanner scanner = new Scanner(System.in);
	private EmployeeView employeeView = new EmployeeView();
        String option = "0";
	do {
            try {
                employeeView.start();
            } catch(SQLException e) {
                System.out.println("Something wrong in database connection\n"
                + "Press 1 to Try again...\nPress other key to Exit....");
                option = scanner.nextLine();
            }
        } while("1".equals(option));
    }
}