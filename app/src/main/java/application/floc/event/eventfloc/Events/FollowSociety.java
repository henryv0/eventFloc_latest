package application.floc.event.eventfloc.Events;

/**
 * Created by henryvo on 9/05/15.
 */
public class FollowSociety {
    private int societyID;
    private int studentID;

    public FollowSociety(int societyID, int studentID) {
        this.societyID = societyID;
        this.studentID = studentID;
    }

    public int getSocietyID() {
        return societyID;
    }

    public void setSocietyID(int societyID) {
        this.societyID = societyID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }
}

