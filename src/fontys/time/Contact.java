package fontys.time;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Ken on 21-3-2017.
 */
public class Contact {
    private String name;
    private ArrayList<Appointment> agenda;

    public Contact(String name) {
        this.name = name;
        this.agenda = new ArrayList<Appointment>();
    }

    /**
     *
     * The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param a The appointment to add to this contacts agenda
     * @return False if the appointment overlaps with another appointment already in this person agenda.
     */
    public boolean addAppointment(Appointment a){
        boolean hasOverlap = false;

        for (Appointment ap : agenda) {

            if(ap.getTimeSpan().intersectionWith(a.getTimeSpan()) != null) {
                hasOverlap = true;
            }
        }

        if(hasOverlap) {
            return false;
        } else {
            agenda.add(a);
            return true;
        }
    }

    /**
     *
     * @param a The appointment to remove
     */
    public void removeAppointment(Appointment a){
        agenda.remove(a);
    }

    public Iterator<Appointment> appointments(){
        return agenda.iterator();
    }
}