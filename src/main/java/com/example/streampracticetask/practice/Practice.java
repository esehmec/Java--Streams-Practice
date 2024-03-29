package com.example.streampracticetask.practice;

import com.example.streampracticetask.bootstrap.DataGenerator;
import com.example.streampracticetask.model.*;
import com.example.streampracticetask.service.*;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.CookieHandler;
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
    // done
    // Display all the employees // done
    public static List<Employee> getAllEmployees() {
        return employeeService.readAll();
    }
    // done
    // Display all the countries
    public static List<Country> getAllCountries() {
        return countryService.readAll();

    }
    // done
    // Display all the departments
    public static List<Department> getAllDepartments() {
        return departmentService.readAll();
    }
    // done
    // Display all the jobs
    public static List<Job> getAllJobs() {
        return jobService.readAll();
    }
    // done
    // Display all the locations
    public static List<Location> getAllLocations() {
        return locationService.readAll();
    }
    // done
    // Display all the regions
    public static List<Region> getAllRegions() {
        return regionService.readAll();
    }

    // Display all the job histories
    public static List<JobHistory> getAllJobHistories() {
        return jobHistoryService.readAll();
    }
    // done
    //8
    // Display all the employees' first names
    public static List<String> getAllEmployeesFirstName() {
        return employeeService.readAll().stream()
                .map(Employee::getFirstName)
                .collect(Collectors.toList());
    }
    // done
    // Display all the countries' names
    public static List<String> getAllCountryNames() {
        return countryService.readAll().stream()
                .map(Country::getCountryName)
                .collect(Collectors.toList());
    }
    // done
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
    // done
    //12
    // Display all the departments where postal code of the location of the department is '98199'
    public static List<Department> getAllDepartmentsWhereLocationPostalCodeIs98199() {
        return departmentService.readAll().stream()
                .filter(department -> department.getLocation().getPostalCode().equals("98199"))
                .collect(Collectors.toList());
    }
    // done
    //13
    // Display the region of the IT department
    public static Region getRegionOfITDepartment() {
        return departmentService.readAll().stream()
                .filter(department -> department.getDepartmentName().equals("IT"))
                .map(d -> d.getLocation().getCountry().getRegion()).collect(Collectors.toList())
                .get(0);
    }
    // done
    //14
    // Display all the departments where the region of department is 'Europe'
    public static List<Department> getAllDepartmentsWhereRegionOfCountryIsEurope() {
        return departmentService.readAll().stream()
                .filter(department -> department.getLocation().getCountry().getRegion().getRegionName().equals("Europe"))
                .collect(Collectors.toList());

    }
    // done
    //15
    // Display if there is any employee with salary less than 1000. If there is none, the method should return true
    public static boolean checkIfThereIsNoSalaryLessThan1000() {
        return employeeService.readAll().stream()
                .filter(employee -> employee.getSalary() < 1000)
                .findAny()
                .isEmpty();
    }
    // done
    //16
    // Check if the salaries of all the employees in IT department are greater than 2000 (departmentName: IT)
    public static boolean checkIfThereIsAnySalaryGreaterThan2000InITDepartment() {
        return employeeService.readAll().stream()
                .filter(employee -> employee.getDepartment().equals("IT"))
                .filter(employee -> employee.getSalary() > 2000)
                .findAny()
                .isEmpty();
    }
    // done
    //17
    // Display all the employees whose salary is less than 5000
    public static List<Employee> getAllEmployeesWithLessSalaryThan5000() {
        return employeeService.readAll().stream()
                .filter(employee -> employee.getSalary() < 5000)
                .collect(Collectors.toList());

    }
    // done
    //18
    // Display all the employees whose salary is between 6000 and 7000
    public static List<Employee> getAllEmployeesSalaryBetween() {
        return employeeService.readAll().stream()
                .filter(employee -> employee.getSalary() > 6000 && employee.getSalary() < 7000)
                .collect(Collectors.toList());
    }

    //19 failed
    // Display the salary of the employee Grant Douglas (lastName: Grant, firstName: Douglas)
    public static Long getGrantDouglasSalary() {
        return employeeService.readAll().stream()
                .filter(e -> e.getFirstName().equals("Douglas") && e.getLastName().equals("Grant"))
                .map(employee -> employee.getSalary())
                .collect(Collectors.toList()).get(0);
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
        return employeeService.readAll().stream()
                .filter(e ->e.getDepartment().getLocation().getCountry().getRegion().getRegionName().equals("Americas"))
                .map(Employee::getSalary)
                .reduce(Long::max)
                .get();
    }

    //23
    // Display the second maximum salary an employee gets
    public static Long getSecondMaxSalary() {
        return employeeService.readAll().stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .limit(2)
                .collect(Collectors.toList())
                .get(1).getSalary();
    }
    //fail
    //24
    // Display the employee(s) who gets the second maximum salary
    public static List<Employee> getSecondMaxSalaryEmployee() {
        Long secongHighestSalary = employeeService.readAll().stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .limit(2)
                .collect(Collectors.toList())
                .get(1).getSalary();

        return employeeService.readAll().stream()
                .filter(employee -> employee.getSalary() == secongHighestSalary)
                .collect(Collectors.toList());



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
        Long secondLowest = employeeService.readAll().stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .map(e ->e.getSalary())
                .distinct()
                .limit(2)
                .collect(Collectors.toList()).get(1);
        return employeeService.readAll().stream()
                .filter(e -> e.getSalary() == secondLowest)
                .collect(Collectors.toList());

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
        long sum = employeeService.readAll().stream()
                .map(Employee::getSalary)
                .reduce((s1, s2) -> s1 + s2).get();
        int numberOfEmployees = employeeService.readAll().size();

        double average = (double) sum / numberOfEmployees;

        return employeeService.readAll().stream()
                .filter(employee -> employee.getSalary() > average)
                .collect(Collectors.toList());
    }

    //31
    // Display all the employees who are making less than average salary
    public static List<Employee> getAllEmployeesBelowAverage() {
        long sum = employeeService.readAll().stream()
                .map(Employee::getSalary)
                .reduce((s1, s2) -> s1 + s2).get();
        int numberOfEmployees = employeeService.readAll().size();

        double average = (double) sum / numberOfEmployees;

        return employeeService.readAll().stream()
                .filter(employee -> employee.getSalary() < average)
                .collect(Collectors.toList());

    }

    //32
    // Display all the employees separated based on their department id number
    public static Map<Long, List<Employee>> getAllEmployeesForEachDepartment() {
        return employeeService.readAll().stream()
                .collect(Collectors.groupingBy(employee -> employee.getDepartment().getId()));
    }

    //33
    // Display the total number of the departments
    public static Long getTotalDepartmentsNumber() {
        return departmentService.readAll().stream()
                .count();
    }

    //34
    // Display the employee whose first name is 'Alyssa' and manager's first name is 'Eleni' and department name is 'Sales'
    public static Employee getEmployeeWhoseFirstNameIsAlyssaAndManagersFirstNameIsEleniAndDepartmentNameIsSales() {
        List<Employee> alyssa = employeeService.readAll().stream()
                .filter(e -> e.getFirstName().equals("Alyssa"))
                .filter(e -> e.getManager().getFirstName().equals("Eleni"))
                .filter(e -> e.getDepartment().getDepartmentName().equals("Sales")).collect(Collectors.toList());

        return alyssa.get(0);
    }

    //35
    // Display all the job histories in ascending order by start date
    public static List<JobHistory> getAllJobHistoriesInAscendingOrder() {
        return jobHistoryService.readAll().stream()
                .sorted(Comparator.comparing(JobHistory::getStartDate))
                .collect(Collectors.toList());
    }

    //36
    // Display all the job histories in descending order by start date
    public static List<JobHistory> getAllJobHistoriesInDescendingOrder() {
        return jobHistoryService.readAll().stream()
                .sorted(Comparator.comparing(JobHistory::getStartDate).reversed())
                .collect(Collectors.toList());
    }

    //37
    // Display all the job histories where the start date is after 01.01.2005
    public static List<JobHistory> getAllJobHistoriesStartDateAfterFirstDayOfJanuary2005() {
        return jobHistoryService.readAll().stream()
                .sorted(Comparator.comparing(JobHistory::getStartDate))
                .filter(jobHistory -> jobHistory.getStartDate().isAfter(LocalDate.of(2005, 01, 01)))
                .collect(Collectors.toList());
    }

    //38
    // Display all the job histories where the end date is 31.12.2007 and the job title of job is 'Programmer'
    public static List<JobHistory> getAllJobHistoriesEndDateIsLastDayOfDecember2007AndJobTitleIsProgrammer() {
        return jobHistoryService.readAll().stream()
                .sorted(Comparator.comparing(JobHistory::getStartDate))
                .filter(jobHistory -> jobHistory.getEndDate().isEqual(LocalDate.of(2007, 12, 31)))
                .filter(jobHistory -> jobHistory.getJob().getJobTitle().equals("Programmer"))
                .collect(Collectors.toList());
    }

    //39
    // Display the employee whose job history start date is 01.01.2007 and job history end date is 31.12.2007 and department's name is 'Shipping'
    public static Employee getEmployeeOfJobHistoryWhoseStartDateIsFirstDayOfJanuary2007AndEndDateIsLastDayOfDecember2007AndDepartmentNameIsShipping() {


          //      readAll().stream().filter(e-> e.getHireDate().isEqual(LocalDate.of(2007, 01, 01)));
          Employee employee = jobHistoryService.readAll().stream()
                   .filter(jobHistory -> jobHistory.getStartDate().isEqual(LocalDate.of(2007,01,01)))
                   .filter(jobHistory -> jobHistory.getEndDate().isEqual(LocalDate.of(2007, 12, 31)))
                   .filter(jobHistory -> jobHistory.getEmployee().getDepartment().getDepartmentName().equals("Shipping"))
                   .collect(Collectors.toList()).get(0).getEmployee();
        return employee;
    }

    //40
    // Display all the employees whose first name starts with 'A'
    public static List<Employee> getAllEmployeesFirstNameStartsWithA() {
        return employeeService.readAll().stream()
                .filter(e -> e.getFirstName().startsWith("A"))
                .collect(Collectors.toList());
    }

    //41
    // Display all the employees whose job id contains 'IT'
    public static List<Employee> getAllEmployeesJobIdContainsIT() {
        return employeeService.readAll().stream()
                .filter(e -> e.getJob().getId().contains("IT"))
                .collect(Collectors.toList());
    }

    //42
    // Display the number of employees whose job title is Programmer and department name is 'IT'
    public static Long getNumberOfEmployeesWhoseJobTitleIsProgrammerAndDepartmentNameIsIT() {
        return employeeService.readAll().stream()
                .filter(e -> e.getJob().getJobTitle().equals("Programmer"))
                .filter(e -> e.getDepartment().getDepartmentName().equals("IT"))
                .count();
    }

    //43
    // Display all the employees whose department id is 50, 80, or 100
    public static List<Employee> getAllEmployeesDepartmentIdIs50or80or100() {
        return employeeService.readAll().stream()
                .filter(e -> e.getDepartment().getId() == 50 || e.getDepartment().getId() == 80 || e.getDepartment().getId() == 100)
                .collect(Collectors.toList());
    }

    //44
    // Display the initials of all the employees
    // Note: You can assume that there is no middle name
    public static List<String> getAllEmployeesInitials() {

        return employeeService.readAll().stream()
                .map(e -> e.getFirstName().substring(0, 1) + e.getLastName().substring(0, 1))
                .collect(Collectors.toList());
    }

    //45
    // Display the full names of all the employees
    public static List<String> getAllEmployeesFullNames() {
        return employeeService.readAll().stream()
                .map(e -> e.getFirstName() + " " + e.getLastName())
                .collect(Collectors.toList());
    }

    //46
    // Display the length of the longest full name(s)
    public static Integer getLongestNameLength() {
        List<String> fullNames = employeeService.readAll().stream()
                .map(e -> e.getFirstName() + " " + e.getLastName())
                .sorted(Comparator.comparing(e -> e.length()))
                .collect(Collectors.toList());
        return fullNames.get(fullNames.size() - 1).length();
    }

    //47
    // Display the employee(s) with the longest full name(s)
    public static List<Employee> getLongestNamedEmployee() {
        int longest = 0;
       List<Employee> longestNames = employeeService.readAll().stream()
                .sorted(Comparator.comparing(employee -> employee.getFirstName() + employee.getLastName()))
                .distinct()
                .collect(Collectors.toList());

        return null;
    }

    //48
    // Display all the employees whose department id is 90, 60, 100, 120, or 130
    public static List<Employee> getAllEmployeesDepartmentIdIs90or60or100or120or130() {
        return employeeService.readAll().stream()
                .filter(e -> e.getDepartment().getId() == 90 || e.getDepartment().getId() == 60 ||
                        e.getDepartment().getId() == 100 || e.getDepartment().getId() == 120 || e.getDepartment().getId() == 130)
                .collect(Collectors.toList());
    }

    //49
    // Display all the employees whose department id is NOT 90, 60, 100, 120, or 130
    public static List<Employee> getAllEmployeesDepartmentIdIsNot90or60or100or120or130() {
        return employeeService.readAll().stream()
                .filter(e -> e.getDepartment().getId() != 90 && e.getDepartment().getId() != 60 &&
                        e.getDepartment().getId() != 100 && e.getDepartment().getId() != 120 && e.getDepartment().getId() != 130)
                .collect(Collectors.toList());
    }

}
