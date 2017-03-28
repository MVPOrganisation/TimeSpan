package tests;

import fontys.time.DayInWeek;
import fontys.time.Time;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by max on 14/03/2017.
 *
 */
class testTimeClass {
    @org.junit.jupiter.api.Test
    void getDayInWeek() {
        Time time = new Time(2000, 1, 1, 10, 11);
        assertEquals(DayInWeek.TUE,time.getDayInWeek());

        Time time2 = new Time(2000, 1, 2, 10, 11);
        assertEquals(DayInWeek.WED,time2.getDayInWeek());

        Time time3 = new Time(2000, 1, 3, 10, 11);
        assertEquals(DayInWeek.THU,time3.getDayInWeek());

        Time time4 = new Time(2000, 1, 4, 10, 11);
        assertEquals(DayInWeek.FRI,time4.getDayInWeek());

        Time time5 = new Time(2000, 1, 5, 10, 11);
        assertEquals(DayInWeek.SAT,time5.getDayInWeek());

        Time time6 = new Time(2000, 1, 6, 10, 11);
        assertEquals(DayInWeek.SUN,time6.getDayInWeek());

        Time time7 = new Time(2000, 1, 7, 10, 11);
        assertEquals(DayInWeek.MON,time7.getDayInWeek());


    }

    @org.junit.jupiter.api.Test
    void getYear() {
        Time time = new Time(2000, 1, 2, 10, 11);

        assertEquals(2000,time.getYear());
    }

    @org.junit.jupiter.api.Test
    void getMonth() {
        Time time = new Time(2000, 1, 2, 10, 11);

        assertEquals(1,time.getMonth());
    }

    @org.junit.jupiter.api.Test
    void getDay() {
        Time time = new Time(2000, 1, 2, 10, 11);

        assertEquals(2,time.getDay());
    }

    @org.junit.jupiter.api.Test
    void getHours() {
        Time time = new Time(2000, 1, 2, 10, 11);

        assertEquals(10,time.getHours());
    }

    @org.junit.jupiter.api.Test
    void getMinutes() {
        Time time = new Time(2000, 1, 2, 10, 11);

        assertEquals(11,time.getMinutes());
    }

    @org.junit.jupiter.api.Test
    void plus() {
        Time time = new Time(2000, 1, 2, 10, 11);
        assertEquals(11,time.getMinutes());

        Time newTIme = (Time) time.plus(2);
        assertEquals(13, newTIme.getMinutes());
    }

    @org.junit.jupiter.api.Test
    void compareTo() {
        Time time = new Time(2000, 1, 2, 10, 11);
        Time timeTwo = new Time(2010, 1, 2, 10, 11);
        assertEquals(1,time.compareTo(timeTwo));
    }

    @org.junit.jupiter.api.Test
    void difference() {
        Time time = new Time(2000, 1, 2, 10, 10);
        Time timeTwo = new Time(2000, 1, 2, 10, 20);

        assertEquals(1, timeTwo.difference(time));
    }

    @org.junit.jupiter.api.Test
    void exceptionTesting() {
        Throwable exception = expectThrows(IllegalArgumentException.class, () -> {
            Time time = new Time(2000, 0, 2, 10, 11);
        });
        assertEquals("month must be within 1..12", exception.getMessage());

        Throwable exception2 = expectThrows(IllegalArgumentException.class, () -> {
            Time time = new Time(2000, 1, 0, 10, 11);
        });
        assertEquals("day must be within 1..31", exception2.getMessage());

        Throwable exception3 = expectThrows(IllegalArgumentException.class, () -> {
            Time time = new Time(2000, 1, 1, -1, 11);
        });
        assertEquals("hours must be within 0..23", exception3.getMessage());

        Throwable exception4 = expectThrows(IllegalArgumentException.class, () -> {
            Time time = new Time(2000, 1, 2, 10, 28967875);
        });
        assertEquals("minutes must be within 0..59", exception4.getMessage());
    }

    // this test though
    @org.junit.jupiter.api.Test
    void testString() {

       Time t = new Time(1,1,1,1,1);
       assertNotEquals(t.toString(), "sadsa");

    }
}