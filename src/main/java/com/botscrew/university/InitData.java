package com.botscrew.university;

import com.botscrew.university.entity.Degree;
import com.botscrew.university.entity.Department;
import com.botscrew.university.entity.Lector;
import com.botscrew.university.repository.DegreeRepository;
import com.botscrew.university.repository.DepartmentRepository;
import com.botscrew.university.repository.LectorRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class InitData {
    private final DegreeRepository degreeRepository;
    private final DepartmentRepository departmentRepository;
    private final LectorRepository lectorRepository;

    public InitData(DegreeRepository degreeRepository, DepartmentRepository departmentRepository, LectorRepository lectorRepository) {
        this.degreeRepository = degreeRepository;
        this.departmentRepository = departmentRepository;
        this.lectorRepository = lectorRepository;
    }

    @Transactional
    public void init() {
        generateAndSaveDegree();
        generateAndSaveLectors();
        generateAndSaveDepartment();
        setLectorToDepartment();
    }

    private void generateAndSaveDegree() {

        Degree assistance = Degree.builder().name("assistance").build();
        Degree associateProfessor = Degree.builder().name("associate professor").build();
        Degree professor = Degree.builder().name("professor").build();

        degreeRepository.saveAll(Arrays.asList(assistance, associateProfessor, professor));
    }

    private void generateAndSaveLectors() {
        Lector nazar = Lector.builder()
                .fullName("Nazar Hladysh")
                .salary(1000D)
                .degree(degreeRepository.findByName("assistance"))
                .build();

        Lector valis = Lector.builder()
                .fullName("Vasil Hladysh")
                .salary(2500D)
                .degree(degreeRepository.findByName("professor"))
                .build();

        Lector ira = Lector.builder()
                .fullName("Ira Hladysh")
                .salary(1500D)
                .degree(degreeRepository.findByName("associate professor"))
                .build();

        Lector ivan = Lector.builder()
                .fullName("Ivan Ivanov")
                .salary(700D)
                .degree(degreeRepository.findByName("assistance"))
                .build();

        Lector mykola = Lector.builder()
                .fullName("Mykola Haranuk")
                .salary(3000D)
                .degree(degreeRepository.findByName("professor"))
                .build();

        Lector mike = Lector.builder()
                .fullName("Mike Tyson")
                .salary(2500D)
                .degree(degreeRepository.findByName("professor"))
                .build();

        lectorRepository.saveAll(Arrays.asList(nazar, valis, ira, ivan, mykola, mike));
    }

    private void generateAndSaveDepartment() {
        Department internetOfThings = Department.builder()
                .name("Internet of Things")
                .headOfDepartment(lectorRepository.findByFullName("Vasil Hladysh"))
                .build();
        Department computerScience = Department.builder()
                .name("Computer Science")
                .headOfDepartment(lectorRepository.findByFullName("Mykola Haranuk"))
                .build();
        Department cybersecurity = Department.builder()
                .name("Сybersecurity")
                .headOfDepartment(lectorRepository.findByFullName("Mike Tyson"))
                .build();

        departmentRepository.saveAll(Arrays.asList(internetOfThings, computerScience, cybersecurity));
    }

    private void setLectorToDepartment() {
        Department internetOfThings = departmentRepository.findByName("Internet of Things");
        Department computerScience = departmentRepository.findByName("Computer Science");
        Department cybersecurity = departmentRepository.findByName("Сybersecurity");

        Lector nazar = lectorRepository.findByFullName("Nazar Hladysh");
        Lector vasil = lectorRepository.findByFullName("Vasil Hladysh");
        Lector ira = lectorRepository.findByFullName("Ira Hladysh");
        Lector ivan = lectorRepository.findByFullName("Ivan Ivanov");
        Lector mykola = lectorRepository.findByFullName("Mykola Haranuk");
        Lector mike = lectorRepository.findByFullName("Mike Tyson");

        internetOfThings.setLectors(new HashSet<>(Arrays.asList(vasil, nazar, ivan, mike)));
        computerScience.setLectors(new HashSet<>(Arrays.asList(mykola, nazar, ira)));
        cybersecurity.setLectors(new HashSet<>(Arrays.asList(mike, nazar, mykola, ivan)));
    }
}
