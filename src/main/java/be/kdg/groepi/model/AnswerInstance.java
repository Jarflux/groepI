package be.kdg.groepi.model;

import be.kdg.groepi.utils.CompareUtil;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Gregory
 * Date: 21/02/13
 * Time: 13:26
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "T_ANSWER_INSTANCE")
public class AnswerInstance {
    @Id
    @GeneratedValue
    @Column(name = "answer_instance_id")
    private Long fId;
    @Column(name = "givenAnswer")
    private String fGivenAnswer;
    @Column(name = "correct")
    private boolean fCorrect;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "answer_id", nullable = false)
    private Answer fAnswer;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User fUser;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stop_instance_id", nullable = false)
    private StopInstance fStopInstance;


    public AnswerInstance() {
    }

    public AnswerInstance(Answer fAnswer, User fUser, StopInstance fStopInstance, String fGivenAnswer, boolean fCorrect) {
        this.fAnswer = fAnswer;
        this.fUser = fUser;
        this.fStopInstance = fStopInstance;
        this.fGivenAnswer = fGivenAnswer;
        this.fCorrect = fCorrect;
    }
/*
    public AnswerInstance(Stop fStop) {
        this.fStop = fStop;
    }*/
/*
    public AnswerInstance(List<String> answers, int correctAnswer, String correctAnswerDescription, Stop fStop) {
        this.fAnswers = answers;
        if (correctAnswer < 0 || correctAnswer >= answers.size()) {
            correctAnswer = 0;
        }
        this.fCorrectAnswer = correctAnswer;
        this.fCorrectAnswerDescription = correctAnswerDescription;
        this.fStop = fStop;
    }*/

    public Long getId() {
        return fId;
    }

    public void setId(Long fId) {
        this.fId = fId;
    }

    public String getGivenAnswer() {
        return fGivenAnswer;
    }

    public void setGivenAnswer(String fGivenAnswer) {
        this.fGivenAnswer = fGivenAnswer;
    }

    public boolean isCorrect() {
        return fCorrect;
    }

    public void setCorrect(boolean fCorrect) {
        this.fCorrect = fCorrect;
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

    public StopInstance getStopInstance() {
        return fStopInstance;
    }

    public void setStopInstance(StopInstance fStopInstance) {
        this.fStopInstance = fStopInstance;
    }

    public void answerQuestion(String givenAnswer, boolean correct) {
        this.fGivenAnswer = givenAnswer;
        this.fCorrect = correct;
    }

    @Override
    public boolean equals(Object o) {
        AnswerInstance answerInstance = (AnswerInstance) o;
//        if (this == answer) return false;

        if (!this.fGivenAnswer.equals(answerInstance.getGivenAnswer())) return false;

        if (this.fCorrect != answerInstance.isCorrect()) return false;

        if (!this.fAnswer.getId().equals(answerInstance.getAnswer().getId())) return false;

        if (this.fUser.getId() != (answerInstance.getUser().getId())) return false;

        return this.fStopInstance.getId().equals(answerInstance.getStopInstance().getId());

    }
}
