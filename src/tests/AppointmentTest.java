package tests;

import fontys.time.Appointment;
import fontys.time.Contact;
import fontys.time.Time;
import fontys.time.TimeSpan;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Ken on 21-3-2017.
 * Test for the appointment class
 */
class AppointmentTest {

    Contact ken = new Contact ("Ken");
    Contact max = new Contact ("Max");

    Appointment a1 = new Appointment("Board meeting", new TimeSpan(new Time(2000, 1, 1, 10, 0), new Time(2000, 1, 1, 12, 0)));
    Appointment a2 = new Appointment("Staff meeting", new TimeSpan(new Time(2000, 1, 1, 13, 0), new Time(2000, 1, 1, 15, 0)));
    Appointment overlapAppointment = new Appointment("Create overlap", new TimeSpan(new Time(2000, 1, 1, 11, 0), new Time(2000, 1, 1, 14, 0)));

    @Test
    void getSubject() {
        assertEquals("Board meeting", a1.getSubject());
    }

    @Test
    void getTimeSpan() {
        assertEquals(10, a1.getTimeSpan().getBeginTime().getHours());
    }

    @Test
    void invitees() {
        a1.addContact(ken);
        a1.addContact(max);

        int size = 0;
        Iterator it = a1.invitees();
        while(it.hasNext()) {
            size++;
            it.next();
        }

        assertEquals(2, size);
    }

    @Test
    void addContact() {
        ken.addAppointment(a2);
        assertEquals(true, a1.addContact(ken));
        ken.addAppointment(overlapAppointment);
        assertEquals(false, a2.addContact(ken));
    }

    @Test
    void removeContact() {


        int size = 0;
        Iterator it = a1.invitees();
        while(it.hasNext()) {
            size++;
            it.next();
        }
        assertEquals(0, size);

        assertEquals(true, a1.addContact(ken));

        int size1 = 0;
        Iterator it1 = a1.invitees();
        while(it1.hasNext()) {
            size1++;
            it1.next();
        }
        assertEquals(1, size1);

        a1.removeContact(ken);

        int size2 = 0;
        Iterator it2 = a1.invitees();
        while(it2.hasNext()) {
            size2++;
            it2.next();
        }
        assertEquals(0, size2);
    }

}