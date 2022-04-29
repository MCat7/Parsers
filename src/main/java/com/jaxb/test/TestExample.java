package com.jaxb.test;

import com.jaxb.model.Department;
import com.jaxb.model.Employee;
import com.jaxb.model.Organisation;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TestExample {
    public static final String XML_FILE = "F:\\!!!учёба\\IBA\\Strelchuk\\ПрилИС\\Лабораторные\\Parsers\\src\\main\\java\\com\\dept-info.xml";

    public static void main(String[] args) {
        Employee emp1 = new Employee("emp01", "Nick", null);
        Employee emp2 = new Employee("emp02", "Mike", "emp01");
        Employee emp3 = new Employee("emp03", "Cris", null);

        Employee emp4 = new Employee("emp04", "Люся", null);
        Employee emp5 = new Employee("emp05", "Мася", "emp04");
        Employee emp6 = new Employee("emp06", "Тася", "emp04");

        List<Employee> listD01 = new ArrayList<>();
        listD01.add(emp1);
        listD01.add(emp2);
        listD01.add(emp3);

        List<Employee> listD02 = new ArrayList<>();
        listD02.add(emp4);
        listD02.add(emp5);
        listD02.add(emp6);

        Department dept1 = new Department("D01", "ACCOUNTING", "Vilnius");
        Department dept2 = new Department("D02", "MANAGERS", "Vilnius");
        List<Department> listDeps = new ArrayList<>();
        listDeps.add(dept1);
        listDeps.add(dept2);
        dept1.setEmployees(listD01);
        dept2.setEmployees(listD02);

        Organisation org = new Organisation("Ширли Мырли");
        org.setDepartments(listDeps);


        try {
            JAXBContext context = JAXBContext.newInstance(Organisation.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            //  m.marshal(dept, System.err);

            File outFile = new File(XML_FILE);
            m.marshal(org
                    , outFile);
            System.err.println("Write to file: " + outFile.getAbsolutePath());

            Unmarshaller unm = context.createUnmarshaller();

           /* Department deptFromFile = (Department) unm.unmarshal(new FileReader(XML_FILE));
            List<Employee> emps = deptFromFile.getEmployees();*/

            Organisation orgFromFile = (Organisation) unm.unmarshal(new FileReader(XML_FILE));
            List<Department> dep = orgFromFile.getDepartments();

            System.out.println("ORGANISATION: " + orgFromFile.getOrgName());
            for (Department department :
                    dep) {
                System.out.println("DEPARTMENT: " +department.getDeptName());
                for (Employee emp :
                        department.getEmployees()) {
                    System.out.println("EMPLOYEE: " + emp.getEmpName());
                }

            }

        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
