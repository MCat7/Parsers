package com.gson.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gson.domain.Staff;

import java.io.*;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.*;

public class GsonExStaffs {
    public static void main(String[] args) {

        ArrayList<Staff> staffs = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting()
                //.excludeFieldsWithoutExposeAnnotation()
                .excludeFieldsWithModifiers(Modifier.STATIC, Modifier.TRANSIENT)
                .create();
        Gson gsRead = new Gson();
        staffs.add(createStaffObject("Richard"));
        staffs.add(createStaffObject("John"));
        staffs.add(createStaffObject("Philip"));
        // Staff staff = createStaffObject();


        // String json = gson.toJson(staff);
        try (FileWriter writer = new FileWriter("json/students.json")) {
            gson.toJson(staffs, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Reader reader = new FileReader("json/student.json")) {
            ArrayList staffsRead = gsRead.fromJson(reader, ArrayList.class);
            //Staff staffsRead = gson.fromJson(reader, Staff.class);

            System.out.println(staffsRead);
            System.out.println(staffs.size());

            for (Object st :
                    staffsRead) {
                System.out.println(st);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Staff createStaffObject(String name) {
        Staff staff = new Staff();
        staff.setName(name);
        staff.setAge(35);
        staff.setPosition(new String[]{"Founder", "SEO", "coder"});
        Map<String, BigDecimal> salary = new HashMap() {{ // вопрос про двойные "{"
            put("2010", new BigDecimal(10000));
            put("2012", new BigDecimal(12000));
            put("2018", new BigDecimal(14000));

        }};
        staff.setSalary(salary);
        staff.setSkills(Arrays.asList("java", "python", "node", "kotlin"));
        return staff;
    }

}
