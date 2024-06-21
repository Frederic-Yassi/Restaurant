package com.utils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Constantes {

    public static final ArrayList<Creneau> Creneaux = new ArrayList<>(Arrays.asList(
            new Creneau(LocalTime.of(11,00)),
            new Creneau(LocalTime.of(11,15)),
            new Creneau(LocalTime.of(11,30)),
            new Creneau(LocalTime.of(11,45)),
            new Creneau(LocalTime.of(12,00)),
            new Creneau(LocalTime.of(12,30)),
            new Creneau(LocalTime.of(12,45)),
            new Creneau(LocalTime.of(13,00))
    ));

    public static final int NumberOftable = 40 ;
}
