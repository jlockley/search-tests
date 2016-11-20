package com.ec.entry;

/**
 * Hello world!
 *
 */
//TODO MAKE MULTI THREADED

public class App 
{
    public static void main( String[] args )
    {
        Conductor conductor = new Conductor();
        conductor.beginCupidSearch();
        conductor.startAnalytics();
        
    }
}
