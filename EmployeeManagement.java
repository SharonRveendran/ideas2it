import java.sql.SQLException;

import com.ideas2it.employeemanagement.view.EmployeeView;

/**
 * Doing CRUD operation in database
 * @author Sharon V
 * @created 15-03-2021
 */
public class EmployeeManagement {
    public static void main(String[] args) {
	EmployeeView employeeView = new EmployeeView();
	while(true) {
            try {
                employeeView.start();
                break;
            } catch(SQLException e) {
                System.out.println("Something wrong in database connection\nTry again...");
            }
        }
    }
}