import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import javax.swing.*;

public class QuizClient {
    private QuizInterface quiz;
    private int currentQuestionIndex;
    private int score;
    private List<String> questions;

    private JFrame frame;
    private JLabel questionLabel;
    private JTextField answerField;
    private JButton submitButton;
    private JLabel scoreLabel;

    public QuizClient() {
        try {

            Registry registry = LocateRegistry.getRegistry("localhost", 1098);
            quiz = (QuizInterface) registry.lookup("QuizService");

            questions = quiz.getQuestions();
            currentQuestionIndex = 0;
            score = 0;

            
            frame = new JFrame("Quiz");
            questionLabel = new JLabel();
            answerField = new JTextField(20);
            submitButton = new JButton("Enviar Resposta");
            scoreLabel = new JLabel();

            // Configuração do layout
            frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
            frame.add(questionLabel);
            frame.add(answerField);
            frame.add(submitButton);
            frame.add(scoreLabel);

            // Ação do botão
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    submitAnswer();
                }
            });

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 200);
            frame.setVisible(true);
            displayQuestion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Erro ao conectar ao servidor: " + e.getMessage());
        }
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questions.size()) {
            questionLabel.setText("Pergunta " + (currentQuestionIndex + 1) + ": " + questions.get(currentQuestionIndex));
            answerField.setText("");
        } else {
            questionLabel.setText("Quiz finalizado! Sua pontuação final: " + score + " de " + questions.size());
            answerField.setEnabled(false);
            submitButton.setEnabled(false);
        }
    }

    private void submitAnswer() {
        String answer = answerField.getText();
        try {
            boolean correct = quiz.submitAnswer(currentQuestionIndex, answer);
            if (correct) {
                score++;
            }
            currentQuestionIndex++;
            displayQuestion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Erro ao enviar resposta: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new QuizClient();
    }
}
