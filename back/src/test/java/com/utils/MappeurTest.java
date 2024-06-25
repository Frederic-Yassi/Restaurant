package com.utils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MappeurTest {
    @Test
    public void mapStringToEnumRoleTest(){

        String value1 = "ADMIN" ;
        String value2 = "CLIENT" ;
        String value3 = "test" ;


        assertEquals("Admin",Mappeur.mapStringToEnumRole(value1));
        assertEquals("Client",Mappeur.mapStringToEnumRole(value2));
        assertEquals("",Mappeur.mapStringToEnumRole(value3));

    }

    @Test
    public void mapStringToListTest(){

        String value1 = "" ;
        String value2 = "3-4" ;
        String value3 = "5-8-15-27" ;


        ArrayList<Integer> result1 = Mappeur.mapStringToList(value1) ;
        ArrayList<Integer> result2 = Mappeur.mapStringToList(value2) ;
        ArrayList<Integer> result3 = Mappeur.mapStringToList(value3) ;

        ArrayList<Integer> expectedList1 = new ArrayList<>() ;
        ArrayList<Integer> expectedList2 = new ArrayList<>(Arrays.asList(3,4)) ;
        ArrayList<Integer> expectedList3 = new ArrayList<>(Arrays.asList(5,8,15,27)) ;



        assertAll("ArrayList elements comparison",
                () -> assertEquals(expectedList1.size(), result1.size(), "List sizes are not equal"),
                () -> {
                    for (int i = 0; i < expectedList1.size(); i++) {
                        assertEquals(expectedList1.get(i), result1.get(i), "Elements at index " + i + " are not equal");
                    }
                }
        );

        assertAll("ArrayList elements comparison",
                () -> assertEquals(expectedList2.size(), result2.size(), "List sizes are not equal"),
                () -> {
                    for (int i = 0; i < expectedList2.size(); i++) {
                        assertEquals(expectedList2.get(i), result2.get(i), "Elements at index " + i + " are not equal");
                    }
                }
        );

        assertAll("ArrayList elements comparison",
                () -> assertEquals(expectedList3.size(), result3.size(), "List sizes are not equal"),
                () -> {
                    for (int i = 0; i < expectedList3.size(); i++) {
                        assertEquals(expectedList3.get(i), result3.get(i), "Elements at index " + i + " are not equal");
                    }
                }
        );


    }

    @Test
    public void mapListToStringTest(){

        ArrayList<Integer> liste1 = new ArrayList<>() ;
        ArrayList<Integer> liste2 = new ArrayList<>(Arrays.asList(3,4)) ;
        ArrayList<Integer> liste3 = new ArrayList<>(Arrays.asList(5,8,15,27)) ;


        String result1 = Mappeur.mapListToString(liste1) ;
        String result2 = Mappeur.mapListToString(liste2) ;
        String result3 = Mappeur.mapListToString(liste3) ;


        String expectedString1 = "" ;
        String expectedString2 = "3-4" ;
        String expectedString3 = "5-8-15-27" ;

        assertEquals(expectedString1, result1, "String  are not equal") ;
        assertEquals(expectedString2, result2, "String  are not equal") ;
        assertEquals(expectedString3, result3, "String  are not equal") ;



    }
}