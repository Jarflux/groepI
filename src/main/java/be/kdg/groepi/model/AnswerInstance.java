package be.kdg.groepi.model;

import be.kdg.groepi.utils.CompareUtil;

import javax.persistence.*;

@Entity
@Table(name = "T_ANSWER_INSTANCE")
public class AnswerInstance {

    @Id
    @GeneratedValue
    @Column(name = "answer_instance_id")
    private Long fId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "answer_id", nullable = false)
    private Answer fAnswer;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User fUser;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trip_instance_id", nullable = false)
    private TripInstance fTripInstance;

    public AnswerInstance() {
    }

    public AnswerInstance(Answer fAnswer, User fUser, TripInstance fTripInstance) {
        this.fAnswer = fAnswer;
        this.fUser = fUser;
        this.fTripInstance = fTripInstance;
    }

    public Long getId() {
        return fId;
    }

    public void setId(Long fId) {
        this.fId = fId;
    }

    public Answer getAnswer() {
        return fAnswer;
    }

    public void setAnswer(Answer fAnswer) {
        this.fAnswer = fAnswer;
    }

    public User getUser() {
        return fUser;
    }

    public void setUser(User fUser) {
        this.fUser = fUser;
    }

    public TripInstance getTripInstance() {
        return fTripInstance;
    }

    public void setTripInstance(TripInstance fTripInstance) {
        this.fTripInstance = fTripInstance;
    }

    public boolean isCorrect()
    {
        return this.fAnswer.getIsCorrect();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        AnswerInstance answerInstance = (AnswerInstance) o;

        if (!this.fAnswer.equals(answerInstance.getAnswer())) {
            return false;
        }
        if (!this.fUser.equals(answerInstance.getUser())) {
            return false;
        }
        if (!this.fTripInstance.equals(answerInstance.getTripInstance())) {
            return false;
        }
        return true;
    }
}
