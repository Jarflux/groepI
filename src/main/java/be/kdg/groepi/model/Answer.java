package be.kdg.groepi.model;

import be.kdg.groepi.utils.CompareUtil;
import javax.persistence.*;

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
    private boolean fIsCorrect;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stop_id", nullable = false)
    private Stop fStop;

    public Answer() {
    }

    public Answer(String fAnswerText, boolean fIsCorrect, Stop fStop) {
        this.fAnswerText = fAnswerText;
        this.fIsCorrect = fIsCorrect;
        this.fStop = fStop;
        this.fStop.getAnswers().add(this);
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

    public boolean getIsCorrect() {
        return fIsCorrect;
    }

    public void setIsCorrect(boolean fIsCorrect) {
        this.fIsCorrect = fIsCorrect;
    }

    public Stop getStop() {
        return fStop;
    }

    public void setStop(Stop fStop) {
        this.fStop = fStop;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        Answer answer = (Answer) o;
        if (!CompareUtil.compareString(fAnswerText, answer.getAnswerText())) {
            return false;
        }
        if (fIsCorrect != answer.getIsCorrect()) {
            return false;
        }
        /*if (!CompareUtil.compareList(this.fAnswers, answer.getAnswers())) {
         return false;
         }*/
        if (!fStop.equals(answer.getStop())) {
            return false;
        }
        return true;
    }
}
