package tests;
import fontys.time.Appointment;
import fontys.time.Contact;
import fontys.time.Time;
import fontys.time.TimeSpan;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Ken on 21-3-2017.
 */
class ContactTest {

    Contact ken = new Contact ("Ken");
    Contact max = new Contact ("Max");

    Appointment a1 = new Appointment("Board meeting", new TimeSpan(new Time(2000, 1, 1, 10, 0), new Time(2000, 1, 1, 12, 0)));
    Appointment a2 = new Appointment("Staff meeting", new TimeSpan(new Time(2000, 1, 1, 13, 0), new Time(2000, 1, 1, 15, 0)));
    Appointment overlapAppointment = new Appointment("Create overlap", new TimeSpan(new Time(2000, 1, 1, 11, 0), new Time(2000, 1, 1, 14, 0)));


    @Test
    void getName() {
        assertEquals("Max", max.getName());
    }

    @Test
    void addAppointment() {
        int size = 0;
        Iterator it = ken.appointments();
        while(it.hasNext()) {
            size++;
            it.next();
        }
        assertEquals(0, size);

        assertEquals(true, ken.addAppointment(a1));
        assertEquals(true, ken.addAppointment(a2));
        assertEquals(false, ken.addAppointment(overlapAppointment));

    }

    @Test
    void removeAppointment() {
        int size = 0;
        Iterator it = ken.appointments();
        while(it.hasNext()) {
            size++;
            it.next();
        }
        assertEquals(0, size);

        assertEquals(true, ken.addAppointment(a1));

        int size1 = 0;
        Iterator it1 = ken.appointments();
        while(it1.hasNext()) {
            size1++;
            it1.next();
        }
        assertEquals(1, size1);

        ken.removeAppointment(a1);

        int size2 = 0;
        Iterator it2 = ken.appointments();
        while(it2.hasNext()) {
            size2++;
            it2.next();
        }
        assertEquals(0, size2);
    }

}