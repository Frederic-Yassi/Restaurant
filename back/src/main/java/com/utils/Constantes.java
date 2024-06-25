package com.utils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Constantes {

    public static final ArrayList<LocalTime> Creneaux = new ArrayList<>(Arrays.asList(
            LocalTime.of(11,00),
            LocalTime.of(11,15),
            LocalTime.of(11,30),
            LocalTime.of(11,45),
            LocalTime.of(12,00),
            LocalTime.of(12,30),
            LocalTime.of(12,45),
            LocalTime.of(13,00),
            LocalTime.of(13,15),
            LocalTime.of(13,30),

            LocalTime.of(19,00),
            LocalTime.of(19,30),
            LocalTime.of(19,45),
            LocalTime.of(20,00),
            LocalTime.of(20,00),
            LocalTime.of(20,30),
            LocalTime.of(20,45),
            LocalTime.of(21,00),
            LocalTime.of(21,00),
            LocalTime.of(21,30)
    ));

    public static final int NumberOftable = 40 ;
}
