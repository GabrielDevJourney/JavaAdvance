package TheEmployeeAnalyzer;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		Analyzer analyzer = new Analyzer();

		Department departmentTest = new Department();

		Department departmentTest2 = new Department();

		//*CREATE EMPLOYEES
		Employee employee1 = new Employee(10,1000,"gabriel",23);
		Employee employee2 = new Employee(12,1200,"ana",36);
		Employee employee3 = new Employee(15,1500,"lara isavel",45);
		Employee employee4 = new Employee(8,2000,"joao",28);
		Employee employee5 = new Employee(6,4000,"hugo",23);

		Employee employee6 = new Employee(10,900,"thiago",24);
		Employee employee7 = new Employee(12,1200,"jonny",54);
		Employee employee8 = new Employee(15,1500,"luis",38);
		Employee employee9 = new Employee(8,2000,"lara sofia",19);
		Employee employee10 = new Employee(6,4000,"marina",23);

		//*ADD EMPLOYEES TO LIST
		departmentTest.addEmployee(employee1);
		departmentTest.addEmployee(employee2);
		departmentTest.addEmployee(employee3);
		departmentTest.addEmployee(employee4);
		departmentTest.addEmployee(employee5);

		departmentTest2.addEmployee(employee6);
		departmentTest2.addEmployee(employee7);
		departmentTest2.addEmployee(employee8);
		departmentTest2.addEmployee(employee9);
		departmentTest2.addEmployee(employee10);

		//*USE ANALYZER METHODS PASSING NECESSARY ARGS

		analyzer.numberOfWorkingEmployeesOver5yrs(departmentTest);
		analyzer.employeesNamesWithSalaryAbove1200(departmentTest);
		analyzer.oldestEmployee(departmentTest);
		analyzer.firstEmployeeOver35(departmentTest);
		analyzer.averageSalaryDepartment(departmentTest);

		analyzer.commonFirstNameBetweenDepartments(departmentTest,departmentTest2);
	}
}
