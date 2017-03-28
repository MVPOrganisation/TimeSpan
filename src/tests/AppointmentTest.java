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
        TimeSpan t = new TimeSpan(new Time(2000, 1, 1, 10, 0), new Time(2000, 1, 1, 12, 0));
        assertEquals(t, a1.getTimeSpan());
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
        assertEquals(false, a1.addContact(ken));
    }

    @Test
    void removeContact() {

    }

}