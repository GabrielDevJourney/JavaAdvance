package TheEmployeeAnalyzer;

import java.util.*;
import java.util.stream.Stream;

public class Analyzer {

	public void numberOfWorkingEmployeesOver5yrs(Department department) {
		Stream<Employee> employeeStream = department.getEmployees().stream();
		long count = employeeStream.filter(employee -> employee.getWorkingYears() > 5)
				.count();
		System.out.println(count);
	}

	public void employeesNamesWithSalaryAbove1200(Department department) {
		Stream<Employee> employeeStream = department.getEmployees().stream();

		employeeStream.filter(employee -> employee.getSalary() > 1200)
				.map(Employee::getName)
				.forEach(System.out::println);

	}

	public void oldestEmployee(Department department) {
		Stream<Employee> employeeStream = department.getEmployees().stream();

		employeeStream.max(Comparator.comparing(Employee::getAge))
				.ifPresent(employee -> System.out.println(employee.getName()));
	}

	public void firstEmployeeOver35(Department department) {
		Stream<Employee> employeeStream = department.getEmployees().stream();

		employeeStream.filter(employee -> employee.getAge() > 35)
				.findFirst()
				.ifPresent(employee -> System.out.println(employee.getName()));
	}

	public void averageSalaryDepartment(Department department) {
		Stream<Employee> employeeStream = department.getEmployees().stream();

		int[] salaries = employeeStream.mapToInt(Employee::getSalary).toArray();

		int averageSalary = (int) Arrays.stream(salaries).average().orElse(0);

		System.out.println(averageSalary);
	}

	public void commonFirstNameBetweenDepartments(Department department1, Department department2) {
		Stream<Employee> employeeStream1 = department1.getEmployees().stream();
		Stream<Employee> employeeStream2 = department2.getEmployees().stream();

		List<String> employeeStream1Names = employeeStream1
				.map(employee -> employee.getName().split(" ")[0])
				.toList();

		List<String> employeeStream2Names = employeeStream2
				.map(employee -> employee.getName().split(" ")[0])
				.toList();

		List<String> equalFirstNames = employeeStream1Names.stream()
				.filter(employeeStream2Names::contains)
				.toList();

		equalFirstNames.forEach(System.out::println);

	}


}
