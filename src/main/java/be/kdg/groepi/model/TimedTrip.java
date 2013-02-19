/*package be.kdg.groepi.model;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "T_TIMEDTRIP")
@PrimaryKeyJoinColumn(name="id")
public class TimedTrip extends Trip
{
    @Column(name="start")
    private Date fStart;
    @Column(name="end")
    private Date fEnd;

    public TimedTrip() {
    }
    public TimedTrip(String title, String description, boolean available, Date fStart, Date fEnd) {
        super(title, description, available);
        this.fStart = fStart;
        this.fEnd = fEnd;
    }

    public Date getStart() {
        return fStart;
    }
    public void setStart(Date fStart) {
        this.fStart = fStart;
    }
    public Date getEnd() {
        return fEnd;
    }
    public void setEnd(Date fEnd) {
        this.fEnd = fEnd;
    }
}
*/
/*
Hibernate Inheritance: Table Per Subclass
http://viralpatel.net/blogs/hibernate-inheritance-table-per-subclass-annotation-xml-mapping/
Hibernate Inheritance: Table Per Class Hierarchy
http://viralpatel.net/blogs/hibernate-inheritence-table-per-hierarchy-mapping/
*/
