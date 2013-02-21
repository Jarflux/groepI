package be.kdg.groepi.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Gregory
 * Date: 21/02/13
 * Time: 13:25
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "T_TRIPPICTURE")
public class StopPictures {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long fId;
    @Column(name = "path")
    private String fPath;

    public StopPictures() {
    }

    public Long getId() {
        return fId;
    }

    public void setId(Long fId) {
        this.fId = fId;
    }

    public String getPath() {
        return fPath;
    }

    public void setPath(String fPath) {
        this.fPath = fPath;
    }
}
