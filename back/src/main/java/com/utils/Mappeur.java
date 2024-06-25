package com.utils;
import com.enums.EnumRole;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Mappeur {

    public static String mapStringToEnumRole(String role){
        String finalRole ="";
        try {
            finalRole = EnumRole.valueOf(role).getRole();
        }
        catch (Exception e){
            System.out.println("Erreur de conversion de Role");
        }
        finally {
            return finalRole ;
        }
    }


    public static String mapListToString (ArrayList<Integer> tables){
        if (tables == null || tables.isEmpty()) {
            return "";
        }
        else {
            return tables.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining("-"));
        }

    }

    public static ArrayList<Integer> mapStringToList(String str) {
        if (str == null || str.isEmpty()) {
            return new ArrayList<>();
        }


        return (ArrayList<Integer>) Stream.of(str.split("-"))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }
}