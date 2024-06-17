package com.projetetudiant.demo.Utils;

import com.projetetudiant.demo.Enum.EnumRole;

import java.util.Arrays;

public class MapstringToRoleEnum {

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
