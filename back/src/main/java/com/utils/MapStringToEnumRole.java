package com.utils;
import com.enums.EnumRole;

public class MapStringToEnumRole{

    public static String map (String role){
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

}