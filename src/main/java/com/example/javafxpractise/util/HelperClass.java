package com.example.javafxpractise.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperClass {
    public static LocalDate convertStringToLocalDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");

        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }
}
