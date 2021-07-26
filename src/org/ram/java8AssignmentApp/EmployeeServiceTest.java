package org.ram.java8AssignmentApp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

interface EmployeeService{
	
	//program1
	public void displayEmployeeFromParticularDepartment(String departmentName);
	public double totalSalaryOfAllEmployees();
	public Employee displayEmployeeWithMaximumSalary();
	public void sortEmployeeByDepartmentInIncreaasingAndBySalariesInDecreasingOrder();
	
	//program2
	public void showEmployeePerDeparmentInMap();
	public void countTotalEmployeePerDepartment();
	public void countAverageSalariesPerDepartment();
	public List<Employee> first5EmployeeFromTheList();
	public void employeeFrom2ndIndexTo5thIndex();
	
}

public class EmployeeServiceTest {
	public static void main(String[] args) {
		
		//storing employee information in array
		Employee employee[]= {
				new Employee("Sachin", "Tendulakr", "Sales", 15400),
				new Employee("M.S.", "Dhoni", "IT", 85613),
				new Employee("Virat", "Kohli", "Finance", 36415),
				new Employee("Anil", "Kumble", "Sales", 69451),
				new Employee("Irfan", "Pathan", "IT", 42156),
				new Employee("Harbhajan", "Singh", "IT", 23514),
				new Employee("Saurabh", "Ganguli", "Finance", 56946)
				};
		
		//converting array into list
		List<Employee> empList=Arrays.asList(employee);
		
		//using consumer interface to print employee information
		Consumer<Employee> consumer=e->{
			System.out.println(e.getFirstName()+" "+e.getLastName()+", "
								+e.getDepartmentName() +", "+e.getSalary());
		};
		
		//implementing the abstract methods of EmployeeService
		EmployeeService employeeService=new EmployeeService() {
			
		//Create a method to print the total Salaries of all employees	
			@Override
			public double totalSalaryOfAllEmployees() {
				double sumSalary= empList.stream().mapToDouble(i->i.getSalary()).sum();
				System.out.println("Total salary of all Employees is: "+sumSalary);
				System.out.println("---------------------------------------------------------");
				return sumSalary;
				
			}
			
			//Create a method to sort and Print the List of employees by Department  
			//and then sort based on the decreasing order of their salaries 
 			@Override
			public void sortEmployeeByDepartmentInIncreaasingAndBySalariesInDecreasingOrder() {
				
 				//sorting department wise
 			List<Employee>sortedDeptList = empList.stream().sorted((dept1,dep2)->
 				dept1.getDepartmentName().compareTo(dep2.getDepartmentName()))
 					.collect(Collectors.toList());
 			
 			//sorting decreasing salary wise		
 			List<Employee>sortedSalaryList =empList.stream().sorted((sal1,sal2)->
 			(sal1.getSalary()<sal2.getSalary())?1:
 					(sal1.getSalary()>sal2.getSalary())?-1:0)
 				.collect(Collectors.toList());
 			
 			System.out.println("Sorting Employee Record Department wise");
 			for(Employee e:sortedDeptList) {
 				consumer.accept(e); 				
 			}
 			System.out.println("****************");
 			System.out.println("Sorting Employee Record decreasing salary wise");
 			for(Employee e:sortedSalaryList) {
 				consumer.accept(e); 				
 			}
 			System.out.println("---------------------------------------------------------");
			}
 			
			
 			//Create a method to print the list of employees per department
 			//in a Map structure. So the output should be like below
 			//Map("IT", List (Emp1, Emp2)); Map("Sales", List (Emp3, Emp4)); 
			@Override
			public void showEmployeePerDeparmentInMap() {
				System.out.println("list of employees per department in a Map structure");
				List<Employee> tempList=new ArrayList<>();
				Map<String, List<Employee>> map=empList.stream()
						.collect(Collectors.groupingBy(Employee::getDepartmentName));
				
				System.out.println(map);
				
				System.out.println("---------------------------------------------------------");
			}
			
			/*Create a method to print the first 5 employees of the list
			(You have to create more employees in the original list to achieve this)*/
			@Override
			public List<Employee> first5EmployeeFromTheList() {
				System.out.println("first 5 employees of the list");
				List<Employee> list=empList.stream().limit(5)
						.collect(Collectors.toList());
				System.out.println(list);
				System.out.println("---------------------------------------------------------");
				return list;
				
			}
			
			/*Create a method to print the employees from 2nd index to 5th index 
			 * (You have to create more employees in the original list to achieve this)
			 * */
			@Override
			public void employeeFrom2ndIndexTo5thIndex() {
				System.out.println("employees from 2nd index to 5th index");
		                IntStream.range(2,6).mapToObj(o->empList.get(o)).forEach(System.out::println);
		                System.out.println("---------------------------------------------------------");
			}
			
			/*Create a method to print the employee with the highest salary
			 * */
			@Override
			public Employee displayEmployeeWithMaximumSalary() {
				System.out.println("Employee With Highest Salary is: ");
				
			Employee e= empList.stream().max((s1,s2)->
			(s1.getSalary()<s2.getSalary())?-1:
					(s1.getSalary()>s2.getSalary())?1:0).get();
			System.out.println("maximum salary of "+e.getFirstName()+" "+e.getLastName()+
					" salary is :"+e.getSalary());
			
				System.out.println("---------------------------------------------------------");
				return e;
			}
			
			/*Create a method with one parameter department and print 
			 *the list of employees in that department
			 * */
			@Override
			public void displayEmployeeFromParticularDepartment(String departmentName) {
				//parameter for department is IT
				System.out.println("Employee who works in "+departmentName+" department is: ");
			
				List<Employee> list= empList.stream().filter(dept->dept
					.getDepartmentName()==departmentName)
			.collect(Collectors.toList());
					
			System.out.println(list);
				System.out.println("---------------------------------------------------------");
			}
			
			/*Create a method to count total employees per department
			 * */
			@Override
			public void countTotalEmployeePerDepartment() {
				System.out.println("Employees working in each deparment.");
				Map<String, Long> map= empList.stream()
						.collect(Collectors.groupingBy(Employee::getDepartmentName,
								Collectors.counting()));
				System.out.println(map);
				System.out.println("---------------------------------------------------------");
			}
			
			/*Create a method to print average salaries per department
			 * */
			@Override
			public void countAverageSalariesPerDepartment() {
				System.out.println("Average salaries per deparment is");
				Map<String, Double> map= empList.stream()
						.collect(Collectors.groupingBy(Employee::getDepartmentName,
								Collectors
								.averagingDouble(i->i.getSalary())));
				System.out.println(map);
				System.out.println("---------------------------------------------------------");
			}
		};
		
		
		//methods of program-1
		employeeService.displayEmployeeFromParticularDepartment("IT");
		employeeService.totalSalaryOfAllEmployees();
		employeeService.displayEmployeeWithMaximumSalary();
		employeeService.sortEmployeeByDepartmentInIncreaasingAndBySalariesInDecreasingOrder();
		
		//methods of program-2
		employeeService.showEmployeePerDeparmentInMap();
		employeeService.countTotalEmployeePerDepartment();
		employeeService.countAverageSalariesPerDepartment();
		employeeService.first5EmployeeFromTheList();
		employeeService.employeeFrom2ndIndexTo5thIndex();
	}
}
