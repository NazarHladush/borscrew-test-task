package com.botscrew.university.view;


import com.botscrew.university.entity.Degree;
import com.botscrew.university.entity.Department;
import com.botscrew.university.entity.Lector;
import com.botscrew.university.service.DegreeService;
import com.botscrew.university.service.DepartmentService;
import com.botscrew.university.service.LectorService;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class MyView {
    private static final Scanner input = new Scanner(System.in);

    final
    DepartmentService departmentService;

    final
    DegreeService degreeService;

    final
    LectorService lectorService;

    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;

    public MyView(DepartmentService departmentService,
                  DegreeService degreeService,
                  LectorService lectorService) {

        this.departmentService = departmentService;
        this.degreeService = degreeService;
        this.lectorService = lectorService;

        menu = new LinkedHashMap<>();
        methodsMenu = new LinkedHashMap<>();

        menu.put("1", "   1 - Who is head of department");
        menu.put("2", "   2 - Show department statistics.");
        menu.put("3", "   3 - Show the average salary for the department");
        menu.put("4", "   4 - Show count of employee for the department");
        menu.put("5", "   5 - Global search");

        menu.put("Q", "   Q - exit");


        methodsMenu.put("1", this::headOfDepartment);
        methodsMenu.put("2", this::departmentStatistics);
        methodsMenu.put("3", this::averageSalary);
        methodsMenu.put("4", this::countOfEmployee);
        methodsMenu.put("5", this::globalSearch);
    }

    private void headOfDepartment() {
        System.out.println("All departments: ");
        System.out.println(departmentService.getDepartmentsName());

        System.out.println("Input department`s name: ");
        String departmentName = input.nextLine();

        String headOfDepartment = departmentService.getHeadOfDepartment(departmentName);
        System.out.println(headOfDepartment);

    }

    private void departmentStatistics() {
        System.out.println("All departments: ");
        System.out.println(departmentService.getDepartmentsName());

        System.out.println("Input department`s name: ");
        String departmentName = input.nextLine();
        Map<String, Integer> departmentStatistics = departmentService.getDepartmentStatistics(departmentName);
        departmentStatistics.forEach((degreeName, count) -> System.out.printf("%s : %d%n", degreeName, count));
    }

    private void averageSalary() {
        System.out.println("All departments: ");
        System.out.println(departmentService.getDepartmentsName());

        System.out.println("Input department`s name: ");
        String departmentName = input.nextLine();

        double averageSalary = departmentService.getAverageSalary(departmentName);
        System.out.printf("Average salary of %s is %s%n", departmentName, averageSalary);
    }

    private void countOfEmployee() {
        System.out.println("All departments: ");
        System.out.println(departmentService.getDepartmentsName());

        System.out.println("Input department`s name: ");
        String departmentName = input.nextLine();

        long countOfEmployee = departmentService.getCountOfEmployee(departmentName);
        System.out.printf("Employee count of %s is %s%n", departmentName, countOfEmployee);
    }

    private void globalSearch() {
        System.out.println("Input value: ");
        String value = input.nextLine();

        List<Department> departmentContaining = departmentService.getDepartmentContaining(value);
        departmentContaining.forEach(department -> System.out.println(department.getName()));

        List<Lector> lectorContaining = lectorService.getLectorContaining(value);
        lectorContaining.forEach(lector -> System.out.println(lector.getFullName()));

        List<Degree> degreeContaining = degreeService.getDegreeContaining(value);
        degreeContaining.forEach(degree -> System.out.println(degree.getName()));

    }

    //-------------------------------------------------------------------------


    private void outputMenu() {
        System.out.println("\nMENU:");
        for (String key : menu.keySet())
            if (key.length() == 1) {
                System.out.println(menu.get(key));
            }
    }

    public void show() {
        String keyMenu;
        do {
            outputMenu();
            System.out.println("Please, select menu point.");
            keyMenu = input.nextLine().toUpperCase();

            if (keyMenu.matches("^\\d")) {
                methodsMenu.get(keyMenu).print();
            }

        } while (!keyMenu.equals("Q"));
    }
}
