package crud;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static EmployeeDAO employeeDAO = new EmployeeDAO();

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("Employee CRUD Application");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left by nextInt()

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    viewAllEmployees();
                    break;
                case 3:
                    updateEmployee();
                    break;
                case 4:
                    deleteEmployee();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        employeeDAO.closeConnection();
    }

    private static void addEmployee() {
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();
        System.out.print("Enter employee age: ");
        int age = scanner.nextInt();
        System.out.print("Enter employee salary: ");
        double salary = scanner.nextDouble();

        Employee employee = new Employee();
        employee.setName(name);
        employee.setAge(age);
        employee.setSalary(salary);

        employeeDAO.addEmployee(employee);
        System.out.println("Employee added successfully!");
    }

    private static void viewAllEmployees() {
        List<Employee> employees = employeeDAO.getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            System.out.println("Employee List:");
            for (Employee employee : employees) {
                System.out.println(employee.toString());
            }
        }
    }

    private static void updateEmployee() {
        System.out.print("Enter the employee ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character left by nextInt()

        Employee existingEmployee = findEmployeeById(id);
        if (existingEmployee == null) {
            System.out.println("Employee with ID " + id + " not found.");
            return;
        }

        System.out.print("Enter updated employee name: ");
        String name = scanner.nextLine();
        System.out.print("Enter updated employee age: ");
        int age = scanner.nextInt();
        System.out.print("Enter updated employee salary: ");
        double salary = scanner.nextDouble();

        existingEmployee.setName(name);
        existingEmployee.setAge(age);
        existingEmployee.setSalary(salary);

        employeeDAO.updateEmployee(existingEmployee);
        System.out.println("Employee updated successfully!");
    }

    private static void deleteEmployee() {
        System.out.print("Enter the employee ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character left by nextInt()

        Employee existingEmployee = findEmployeeById(id);
        if (existingEmployee == null) {
            System.out.println("Employee with ID " + id + " not found.");
            return;
        }

        employeeDAO.deleteEmployee(id);
        System.out.println("Employee deleted successfully!");
    }

    private static Employee findEmployeeById(int id) {
        List<Employee> employees = employeeDAO.getAllEmployees();
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }
}
