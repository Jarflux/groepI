package be.kdg.groepi.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Gregory
 * Date: 21/02/13
 * Time: 13:26
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "T_ANSWER")
public class Answer {
    @Id
    @GeneratedValue
    @Column(name = "answer_id")
    private Long fId;
    @Column(name = "answerText")
    private String fAnswerText;
    @Column(name = "isCorrect")
    private Boolean fIsCorrect;

    public Answer() {
    }

    public Answer(String fAnswerText, Boolean fIsCorrect) {
        this.fAnswerText = fAnswerText;
        this.fIsCorrect = fIsCorrect;
    }

    public Long getId() {
        return fId;
    }

    public void setId(Long fId) {
        this.fId = fId;
    }

    public String getAnswerText() {
        return fAnswerText;
    }

    public void setAnswerText(String fAnswerText) {
        this.fAnswerText = fAnswerText;
    }

    public Boolean getIsCorrect() {
        return fIsCorrect;
    }

    public void setIsCorrect(Boolean fIsCorrect) {
        this.fIsCorrect = fIsCorrect;
    }
}
