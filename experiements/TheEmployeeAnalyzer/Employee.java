package TheEmployeeAnalyzer;

public class Employee {
	private final int workingYears;
	private final int salary;
	private final String name;
	private final int age;

	public Employee(int workingYears, int salary, String name, int age) {
		this.workingYears = workingYears;
		this.salary = salary;
		this.name = name;
		this.age = age;
	}

	public int getWorkingYears() {
		return workingYears;
	}

	public int getSalary() {
		return salary;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}
}
