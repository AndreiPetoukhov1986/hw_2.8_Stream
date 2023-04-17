package pro.sky.hwstream.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.hwstream.Employee;
import pro.sky.hwstream.service.EmployeeService;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping("/add")
    public Employee add(@RequestParam("fullName") String fullName, @RequestParam("department") int department, @RequestParam("salary") double salary) {
        return employeeService.add(fullName, department, salary);
    }

    @RequestMapping("/find")
    public Employee find(@RequestParam String fullName, @RequestParam int department, @RequestParam double salary) {
        return employeeService.find(fullName, department, salary);
    }

    @RequestMapping("/remove")
    public Employee remove(@RequestParam String fullName, @RequestParam int department, @RequestParam double salary) {
        return employeeService.remove(fullName, department, salary);
    }

    @RequestMapping("/")
    public List<Employee> List() {
        return employeeService.List();
    }

    @RequestMapping("/departments/max-salary")
    public Employee findEmployeeWithMaxSalaryFromDepartment(@RequestParam("departmentId") int department) {
        return employeeService.findEmployeeWithMaxSalaryFromDepartment(department);
    }

    @RequestMapping("/departments/min-salary")
    public Employee findEmployeeWithMinSalaryFromDepartment(@RequestParam("departmentId") int department) {
        return employeeService.findEmployeeWithMinSalaryFromDepartment(department);
    }

    @RequestMapping("/departments/all")
    public List<Employee> printAllEmployeesForDep(@RequestParam(value = "departmentId", required = false) Integer dep) {
        if (dep == null) {
            return employeeService.printDepEmployees();
        }
        return employeeService.printAllEmployeesForDep(dep);
    }
}