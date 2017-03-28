package fontys.time;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Ken on 21-3-2017.
 */
public class Appointment {
    private String subject;
    private ITimeSpan timeSpan;
    private ArrayList<Contact> invitees;

    public Appointment(String subject, ITimeSpan timeSpan) {
        this.subject = subject;
        this.timeSpan = timeSpan;
        invitees = new ArrayList<Contact>();
    }

    /**
     * The subject of the appointment.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * The time the appointment will take.
     */
    public ITimeSpan getTimeSpan() {
        return timeSpan;
    }

    /**
     * All the people who will be attending the appointment.
     */
    public Iterator<Contact> invitees(){
        return invitees.iterator();
    }

    public boolean addContact(Contact c) {
        boolean hasOverlap = false;

        for (Iterator<Appointment> it = c.appointments(); it.hasNext(); ) {
            Appointment a = it.next();

            if(a.getTimeSpan().intersectionWith(getTimeSpan()) != null) {
                hasOverlap = true;
            }
        }

        if(hasOverlap) {
           return false;
        } else {
            invitees.add(c);
            return true;
        }
    }

    public void removeContact(Contact c){
        invitees.remove(c);
    }
}