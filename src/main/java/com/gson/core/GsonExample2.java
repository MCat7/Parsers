package com.gson.core;

import com.google.gson.Gson;
import com.gson.domain.Staff;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class GsonExample2 {
    public static void main(String[] args) {
        Gson gson = new Gson();

        try (Reader reader = new FileReader("json/student.json")){
            Staff staff = gson.fromJson(reader, Staff.class);

            System.out.println(staff);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
