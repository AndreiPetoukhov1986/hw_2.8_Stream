package pro.sky.hwstream.service;


import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import pro.sky.hwstream.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();

    @PostConstruct
    public void init() {
        employees.add(new Employee("Андрей Петухов", 1, 100_000));
        employees.add(new Employee("Кристина Одинцова", 1, 95_000));
        employees.add(new Employee("Александр Марьенко", 1, 90_000));
        employees.add(new Employee("Александр Ожигатов", 2, 92_000));
        employees.add(new Employee("Наталья Фурсова", 2, 93_000));
        employees.add(new Employee("Людмила Подоплелова", 2, 91_000));
    }

    public Employee add(String fullName, int department, double salary) {
        Employee employee = new Employee(fullName, department, salary);
        if (employees.contains(employee)) {
            throw new RuntimeException("в массиве уже есть такой сотрудник");
        }
        employees.add(employee);
        return employee;
    }

    public Employee remove(String fullName, int department, double salary) {
        Employee employee = new Employee(fullName, department, salary);
        if (employees.remove(employee)) {
            return employee;
        }
        throw new RuntimeException("сотрудник не найден");
    }


    public Employee find(String fullName, int department, double salary) {
        Employee employee = new Employee(fullName, department, salary);
        if (employees.contains(employee)) {
            return employee;
        }
        throw new RuntimeException("сотрудник не найден");
    }

    public List<Employee> List() {
        return Collections.unmodifiableList(employees);
    }

    public Employee findEmployeeWithMaxSalaryFromDepartment(int dep) {
        return employees.stream()
                .filter(e -> e.getDepartment() == dep)
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public Employee findEmployeeWithMinSalaryFromDepartment(int dep) {
        return employees.stream()
                .filter(e -> e.getDepartment() == dep)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public List<Employee> printDepEmployees() {
        return employees.stream()
                .sorted(Comparator.comparing(Employee::getDepartment))
                .collect(Collectors.toList());
    }

    public List<Employee> printAllEmployeesForDep(int dep) {
        return employees.stream()
                .filter(e -> e.getDepartment() == dep)
                .collect(Collectors.toList());
    }
}
