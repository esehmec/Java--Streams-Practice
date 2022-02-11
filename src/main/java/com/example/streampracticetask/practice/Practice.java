package com.example.streampracticetask.practice;

import com.example.streampracticetask.bootstrap.DataGenerator;
import com.example.streampracticetask.model.*;
import com.example.streampracticetask.service.*;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class Practice {

    public static CountryService countryService;
    public static DepartmentService departmentService;
    public static EmployeeService employeeService;
    public static JobHistoryService jobHistoryService;
    public static JobService jobService;
    public static LocationService locationService;
    public static RegionService regionService;

    public Practice(CountryService countryService, DepartmentService departmentService,
                    EmployeeService employeeService, JobHistoryService jobHistoryService,
                    JobService jobService, LocationService locationService,
                    RegionService regionService) {

        Practice.countryService = countryService;
        Practice.departmentService = departmentService;
        Practice.employeeService = employeeService;
        Practice.jobHistoryService = jobHistoryService;
        Practice.jobService = jobService;
        Practice.locationService = locationService;
        Practice.regionService = regionService;

    }

    // You can use the services above for all the CRUD (create, read, update, delete) operations.
    // Above services have all the required methods.
    // Also, you can check all the methods in the ServiceImpl classes inside the service.impl package, they all have explanations.

    // Display all the employees
    public static List<Employee> getAllEmployees() {
        return employeeService.readAll();
    }

    // Display all the countries
    public static List<Country> getAllCountries() {
        return countryService.readAll();

    }

    // Display all the departments
    public static List<Department> getAllDepartments() {
        return departmentService.readAll();
    }

    // Display all the jobs
    public static List<Job> getAllJobs() {

        return jobService.readAll();
    }

    // Display all the locations
    public static List<Location> getAllLocations() {
        return locationService.readAll();
    }

    // Display all the regions
    public static List<Region> getAllRegions() {
        return regionService.readAll();
    }

    // Display all the job histories
    public static List<JobHistory> getAllJobHistories() {
        return jobHistoryService.readAll();
    }

    //8
    // Display all the employees' first names
    public static List<String> getAllEmployeesFirstName() {
        return employeeService.readAll().stream()
                .map(Employee::getFirstName)
                .collect(Collectors.toList());
    }

    // Display all the countries' names
    public static List<String> getAllCountryNames() {
        return countryService.readAll().stream()
                .map(Country::getCountryName)
                .collect(Collectors.toList());
    }

    // Display all the departments' managers' first names
    //10
    public static List<String> getAllDepartmentManagerFirstNames() {
        return departmentService.readAll().stream()
                .map(department -> department.getManager()
                        .getFirstName()).collect(Collectors.toList());
    }

    //11
    // Display all the departments where manager name of the department is 'Steven'
    public static List<Department> getAllDepartmentsWhichManagerFirstNameIsSteven() {
        return departmentService.readAll().stream()
                .filter(department -> department.getManager().getFirstName().equalsIgnoreCase("Steven"))
                .collect(Collectors.toList());


    }

    //12
    // Display all the departments where postal code of the location of the department is '98199'
    public static List<Department> getAllDepartmentsWhereLocationPostalCodeIs98199() {
        return departmentService.readAll().stream()
                .filter(department -> department.getLocation().getPostalCode().equals(98199))
                .collect(Collectors.toList());
    }

    //13
    // Display the region of the IT department
    public static Region getRegionOfITDepartment() {

        return null;
        //departmentService.readAll().stream().filter(department -> department.getDepartmentName().equals("IT"))
        //.map(department -> department.getLocation().getCountry().getRegion());

    }

    //14
    // Display all the departments where the region of department is 'Europe'
    public static List<Department> getAllDepartmentsWhereRegionOfCountryIsEurope() {
        return departmentService.readAll().stream()
                .filter(department -> department.getLocation().getCountry().getRegion().equals("Europe"))
                .collect(Collectors.toList());

    }

    //15
    // Display if there is any employee with salary less than 1000. If there is none, the method should return true
    public static boolean checkIfThereIsNoSalaryLessThan1000() {
        return employeeService.readAll().stream()
                .filter(employee -> employee.getSalary() < 1000)
                .findAny()
                .isEmpty();
    }

    //15
    // Check if the salaries of all the employees in IT department are greater than 2000 (departmentName: IT)
    public static boolean checkIfThereIsAnySalaryGreaterThan2000InITDepartment() {
        return employeeService.readAll().stream()
                .filter(employee -> employee.getDepartment().equals("IT"))
                .filter(employee -> employee.getSalary() > 2000)
                .findAny()
                .isEmpty();
    }

    //16
    // Display all the employees whose salary is less than 5000
    public static List<Employee> getAllEmployeesWithLessSalaryThan5000() {
        return employeeService.readAll().stream()
                .filter(employee -> employee.getSalary() < 5000)
                .collect(Collectors.toList());

    }

    //17
    // Display all the employees whose salary is between 6000 and 7000
    public static List<Employee> getAllEmployeesSalaryBetween() {
        return employeeService.readAll().stream()
                .filter(employee -> employee.getSalary() > 6000 && employee.getSalary() < 7000)
                .collect(Collectors.toList());
    }

    //18
    // Display the salary of the employee Grant Douglas (lastName: Grant, firstName: Douglas)
    public static Long getGrantDouglasSalary() throws Exception {
        try {
            //  return  employeeService.readAll().stream()
            //     .filter(employee -> employee.getFirstName().equals("Douglas") && employee.getLastName().equals("Grant"))
            //        .map(employee -> employee.getSalary().longValue());
        } catch (Exception e) {
            System.out.println("Exception thrown" + e);
        }

        return null;
    }

    //19
    // Display the maximum salary an employee gets
    public static Long getMaxSalary() {
        return employeeService.readAll().stream()
                .map(Employee::getSalary)
                .reduce(Long::max)
                .get();
    }

    //20 pass
    // Display the employee(s) who gets the maximum salary
    public static List<Employee> getMaxSalaryEmployee() {
        //List<Employee> highest = new ArrayList<>();
        return employeeService.readAll().stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .limit(1)
                .collect(Collectors.toList());
    }

    //21 pass
    // Display the max salary employee's job
    public static Job getMaxSalaryEmployeeJob() {
        return employeeService.readAll().stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .limit(1)
                .findFirst()
                .get().getJob();
    }

    //22
    // Display the max salary in Americas Region
    public static Long getMaxSalaryInAmericasRegion() {
        return null;
    }

    //23
    // Display the second maximum salary an employee gets
    public static Long getSecondMaxSalary() {
        return null;
    }

    //24
    // Display the employee(s) who gets the second maximum salary
    public static List<Employee> getSecondMaxSalaryEmployee() {
        return null;
    }

    //25
    // Display the minimum salary an employee gets
    public static Long getMinSalary() {
        return employeeService.readAll().stream()
                .map(employee -> employee.getSalary())
                .reduce(Long::min)
                .get();
    }

    //26
    // Display the employee(s) who gets the minimum salary
    public static List<Employee> getMinSalaryEmployee() {
        return employeeService.readAll().stream()
                //.map(employee -> employee.getSalary())
                .sorted(Comparator.comparing(Employee::getSalary))
                .limit(1).collect(Collectors.toList());


    }

    //27
    // Display the second minimum salary an employee gets
    public static Long getSecondMinSalary() {
        List<Employee> lowestPaid2 = employeeService.readAll().stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .limit(2)
                .collect(Collectors.toList());
        return lowestPaid2.get(1).getSalary();
    }

    //28
    // Display the employee(s) who gets the second minimum salary
    public static List<Employee> getSecondMinSalaryEmployee() {
        List<Employee> lowestPaid2 = employeeService.readAll().stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .limit(2)
                .collect(Collectors.toList());
        lowestPaid2.remove(0);
        return lowestPaid2;
    }

    //29
    // Display the average salary of the employees
    public static Double getAverageSalary() {
        long sum = employeeService.readAll().stream()
                .map(Employee::getSalary)
                .reduce((s1, s2) -> s1 + s2).get();
        int numberOfEmployees = employeeService.readAll().size();

        double average = (double) sum / numberOfEmployees;
        return average;
    }

    //30
    // Display all the employees who are making more than average salary
    public static List<Employee> getAllEmployeesAboveAverage() {
        return null;
    }

    //31
    // Display all the employees who are making less than average salary
    public static List<Employee> getAllEmployeesBelowAverage() {
        return null;
    }

    //32
    // Display all the employees separated based on their department id number
    public static Map<Long, List<Employee>> getAllEmployeesForEachDepartment() {
        return null;
    }

    //33
    // Display the total number of the departments
    public static Long getTotalDepartmentsNumber() {
        return null;
    }

    //34
    // Display the employee whose first name is 'Alyssa' and manager's first name is 'Eleni' and department name is 'Sales'
    public static Employee getEmployeeWhoseFirstNameIsAlyssaAndManagersFirstNameIsEleniAndDepartmentNameIsSales() {
        return null;
    }

    //35
    // Display all the job histories in ascending order by start date
    public static List<JobHistory> getAllJobHistoriesInAscendingOrder() {
        return null;
    }

    //36
    // Display all the job histories in descending order by start date
    public static List<JobHistory> getAllJobHistoriesInDescendingOrder() {
        return null;
    }

    //37
    // Display all the job histories where the start date is after 01.01.2005
    public static List<JobHistory> getAllJobHistoriesStartDateAfterFirstDayOfJanuary2005() {
        return null;
    }

    //38
    // Display all the job histories where the end date is 31.12.2007 and the job title of job is 'Programmer'
    public static List<JobHistory> getAllJobHistoriesEndDateIsLastDayOfDecember2007AndJobTitleIsProgrammer() {
        return null;
    }

    //39
    // Display the employee whose job history start date is 01.01.2007 and job history end date is 31.12.2007 and department's name is 'Shipping'
    public static Employee getEmployeeOfJobHistoryWhoseStartDateIsFirstDayOfJanuary2007AndEndDateIsLastDayOfDecember2007AndDepartmentNameIsShipping() {
        return null;
    }

    //40
    // Display all the employees whose first name starts with 'A'
    public static List<Employee> getAllEmployeesFirstNameStartsWithA() {
        return null;
    }

    //41
    // Display all the employees whose job id contains 'IT'
    public static List<Employee> getAllEmployeesJobIdContainsIT() {
        return null;
    }

    //42
    // Display the number of employees whose job title is Programmer and department name is 'IT'
    public static Long getNumberOfEmployeesWhoseJobTitleIsProgrammerAndDepartmentNameIsIT() {
        return null;
    }

    //43
    // Display all the employees whose department id is 50, 80, or 100
    public static List<Employee> getAllEmployeesDepartmentIdIs50or80or100() {
        return null;
    }

    //44
    // Display the initials of all the employees
    // Note: You can assume that there is no middle name
    public static List<String> getAllEmployeesInitials() {
        return null;
    }

    //45
    // Display the full names of all the employees
    public static List<String> getAllEmployeesFullNames() {
        return null;
    }

    //46
    // Display the length of the longest full name(s)
    public static Integer getLongestNameLength() {
        return null;
    }

    //47
    // Display the employee(s) with the longest full name(s)
    public static List<Employee> getLongestNamedEmployee() {
        return null;
    }

    //48
    // Display all the employees whose department id is 90, 60, 100, 120, or 130
    public static List<Employee> getAllEmployeesDepartmentIdIs90or60or100or120or130() {
        return null;
    }

    //49
    // Display all the employees whose department id is NOT 90, 60, 100, 120, or 130
    public static List<Employee> getAllEmployeesDepartmentIdIsNot90or60or100or120or130() {
        return null;
    }

}
