package de.fynder.m4b_tagger.utils;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Chapter {

    public static final int TYPE_CHAPTER = 1;  // Binary 00001
    public static final int TYPE_TRACK = 2;  // Binary 00010
    public static final int TYPE_COMMERCIAL = 4;  // Binary 00100
    public static final int TYPE_SILENCE = 8;  // Binary 01000


    public double startMilliSeconds;
    public double durationMilliSeconds;
    public String title;
    public int type;

}
