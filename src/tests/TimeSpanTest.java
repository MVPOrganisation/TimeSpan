package tests;

import fontys.time.Time;
import fontys.time.TimeSpan;

import static org.junit.jupiter.api.Assertions.*;


class TimeSpanTest {
    @org.junit.jupiter.api.Test
    void getBeginTime() {
        Time begin = new Time(2000,1,1,1,10);
        Time end = new Time(2000,1,1,1,40);
        TimeSpan timeSpan = new TimeSpan(begin, end);
        assertEquals(begin,timeSpan.getBeginTime());

        Throwable exception = expectThrows(IllegalArgumentException.class, () -> {
            TimeSpan timeSpan1 = new TimeSpan(end,begin);
        });
        assertEquals("begin time "
                + end + " must be earlier than end time " + begin, exception.getMessage());

    }

    @org.junit.jupiter.api.Test
    void getEndTime() {
        Time begin = new Time(2000,1,1,1,10);
        Time end = new Time(2000,1,1,1,40);
        TimeSpan timeSpan = new TimeSpan(begin, end);
        assertEquals(end,timeSpan.getEndTime());
    }

    @org.junit.jupiter.api.Test
    void length() {
        Time time = new Time(2000, 1, 2, 10, 10);
        Time timeTwo = new Time(2000, 1, 2, 10, 20);

        TimeSpan ts = new TimeSpan(time, timeTwo);
        assertEquals(1, ts.length());
    }

    @org.junit.jupiter.api.Test
    void setBeginTime() {
        Time begin = new Time(2000,1,1,1,10);
        Time end = new Time(2000,1,1,1,40);
        TimeSpan timeSpan = new TimeSpan(begin, end);
        assertEquals(begin,timeSpan.getBeginTime());
        Time begin2 = new Time(2000,1,1,1,20);
        timeSpan.setBeginTime(begin2);
        assertEquals(begin2,timeSpan.getBeginTime());
    }

    @org.junit.jupiter.api.Test
    void setEndTime() {
        Time begin = new Time(2000,1,1,1,10);
        Time end = new Time(2000,1,1,1,40);
        TimeSpan timeSpan = new TimeSpan(begin, end);
        assertEquals(end,timeSpan.getEndTime());
        Time end2 = new Time(2000,1,1,1,50);
        timeSpan.setEndTime(end2);
        assertEquals(end2,timeSpan.getEndTime());
    }

    @org.junit.jupiter.api.Test
    void move() {
        Time begin = new Time(2000,1,1,1,10);
        Time end = new Time(2000,1,1,1,40);
        TimeSpan timeSpan = new TimeSpan(begin, end);
        timeSpan.move(10);
        assertEquals(20,timeSpan.getBeginTime().getMinutes());
        assertEquals(50,timeSpan.getEndTime().getMinutes());
    }

    @org.junit.jupiter.api.Test
    void changeLengthWith() {
        Time begin = new Time(2000,1,1,1,10);
        Time end = new Time(2000,1,1,1,40);
        TimeSpan timeSpan = new TimeSpan(begin, end);

        Throwable exception = expectThrows(IllegalArgumentException.class, () -> {
            timeSpan.changeLengthWith(-3423);
        });
        assertEquals("length of period must be positive", exception.getMessage());

        Time begin1 = new Time(2000,1,1,1,10);
        Time end1 = new Time(2000,1,1,1,40);
        TimeSpan timeSpan1 = new TimeSpan(begin, end);
        timeSpan1.changeLengthWith(10);
        assertEquals(50,timeSpan1.getEndTime().getMinutes());
    }

    @org.junit.jupiter.api.Test
    void isPartOf() {
        Time begin = new Time(2000,1,1,1,10);
        Time end = new Time(2000,1,1,1,40);
        TimeSpan timeSpan = new TimeSpan(begin, end);
        Time begin1 = new Time(2000,1,1,1,20);
        Time end1 = new Time(2000,1,1,1,30);
        TimeSpan timeSpan1 = new TimeSpan(begin, end);
        assertEquals(timeSpan.isPartOf(timeSpan1),true);
    }


    @org.junit.jupiter.api.Test
    void unionWith() {
        Time begin = new Time(2000,1,1,1,10);
        Time end = new Time(2000,1,1,1,40);
        TimeSpan timeSpan = new TimeSpan(begin, end);
        Time begin1 = new Time(2000,1,1,1,35);
        Time end1 = new Time(2000,1,1,1,55);
        TimeSpan timeSpan1 = new TimeSpan(begin1, end1);

        Time beginExpected = new Time(2000,1,1,1,10);
        Time endExpected = new Time(2000,1,1,1,55);
        TimeSpan expected = new TimeSpan(beginExpected, endExpected);
        TimeSpan actual = (TimeSpan)timeSpan.unionWith(timeSpan1);

        assertEquals(expected.getBeginTime().getMinutes(), actual.getBeginTime().getMinutes());
        assertEquals(expected.getEndTime().getMinutes(), actual.getEndTime().getMinutes());
    }

    @org.junit.jupiter.api.Test
    void intersectionWith() {
        TimeSpan ts1 = new TimeSpan(new Time(2000, 1, 1, 12, 10), new Time(2000, 1, 1, 12, 40));
        TimeSpan ts2 = new TimeSpan(new Time(2000, 1, 1, 12, 30), new Time(2000, 1, 1, 12, 50));

        TimeSpan expectedDif = new TimeSpan(new Time(2000, 1, 1, 12, 30), new Time(2000, 1, 1, 12, 40));
        TimeSpan actualDif = (TimeSpan)ts1.intersectionWith(ts2);

        assertEquals(expectedDif.getBeginTime().getMinutes(), actualDif.getBeginTime().getMinutes());
        assertEquals(expectedDif.getEndTime().getMinutes(), actualDif.getEndTime().getMinutes());
    }

    // this test though
    @org.junit.jupiter.api.Test
    void testString() {

        TimeSpan t = new TimeSpan(new Time(2000, 1, 1, 12, 10), new Time(2000, 1, 1, 12, 40));
        assertNotEquals("sadsa", t.toString());
        assertEquals("test", t.toString());
    }
}