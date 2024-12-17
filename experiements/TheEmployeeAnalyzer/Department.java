package TheEmployeeAnalyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Department {

	private final List<Employee> employees;

	public Department() {
		this.employees = new ArrayList<>();
	}

	//return not possible to modify list
	public List<Employee> getEmployees() {
		return Collections.unmodifiableList(employees);
	}

	public void addEmployee(Employee employee) {
		employees.add(employee);
	}
}
