import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class QuizApplication {
    // Store quiz questions along with multiple-choice options and correct answers
    private Question[] questions;
    private int currentQuestionIndex;
    private int score;

    // Timer for each question
    private Timer timer;

    public QuizApplication(Question[] questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
    }

    public void startQuiz() {
        displayCurrentQuestion();
    }

    private void displayCurrentQuestion() {
        Question currentQuestion = questions[currentQuestionIndex];
        System.out.println("Question " + (currentQuestionIndex + 1) + ": " + currentQuestion.getQuestion());
        System.out.println("Options:");
        for (int i = 0; i < currentQuestion.getOptions().length; i++) {
            System.out.println((i + 1) + ". " + currentQuestion.getOptions()[i]);
        }

        // Start the timer
        timer = new Timer(30000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Time's up! Move to the next question
                submitAnswer(-1);
            }
        });
        timer.start();
    }

    public void submitAnswer(int selectedOption) {
        // Stop the timer
        timer.stop();

        Question currentQuestion = questions[currentQuestionIndex];
        if (selectedOption == currentQuestion.getCorrectAnswerIndex()) {
            score++;
        }

        currentQuestionIndex++;

        if (currentQuestionIndex < questions.length) {
            displayCurrentQuestion();
        } else {
            displayResult();
        }
    }

    private void displayResult() {
        System.out.println("Quiz finished! Your score is " + score + " out of " + questions.length);
        for (int i = 0; i < questions.length; i++) {
            Question question = questions[i];
            System.out.println("Question " + (i + 1) + ": " + question.getQuestion());
            System.out.println("Your answer: " + (question.getCorrectAnswerIndex() == question.getUserAnswerIndex() ? "Correct" : "Incorrect"));
        }
    }
}

class Question {
    private String question;
    private String[] options;
    private int correctAnswerIndex;
    private int userAnswerIndex;

    public Question(String question, String[] options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public int getUserAnswerIndex() {
        return userAnswerIndex;
    }

    public void setUserAnswerIndex(int userAnswerIndex) {
        this.userAnswerIndex = userAnswerIndex;
    }
}