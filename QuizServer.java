import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class QuizServer extends UnicastRemoteObject implements QuizInterface {
    private List<String> questions;
    private List<String> correctAnswers;
    private int score;

    public QuizServer() throws RemoteException {
        super();
        this.questions = new ArrayList<>();
        this.correctAnswers = new ArrayList<>();
        this.score = 0;

        
        questions.add("Qual é a capital do Brasil?");
        correctAnswers.add("Brasília");

        questions.add("Qual é o maior planeta do sistema solar?");
        correctAnswers.add("Júpiter");

        questions.add("Qual é a língua oficial do Japão?");
        correctAnswers.add("Japonês");

        questions.add("Quem escreveu 'Dom Casmurro'?");
        correctAnswers.add("Machado de Assis");

        questions.add("Qual é a fórmula química da água?");
        correctAnswers.add("H2O");

        questions.add("Qual é o continente onde fica o Egito?");
        correctAnswers.add("África");

        questions.add("Qual animal é conhecido como o 'Rei da Selva'?");
        correctAnswers.add("Leão");

        questions.add("Qual é o país mais populoso do mundo?");
        correctAnswers.add("China");

        questions.add("Qual é a capital da França?");
        correctAnswers.add("Paris");

        questions.add("Qual é a montanha mais alta do mundo?");
        correctAnswers.add("Everest");

        questions.add("Qual é o elemento químico com símbolo 'O'?");
        correctAnswers.add("Oxigênio");

        questions.add("Qual é a moeda oficial do Japão?");
        correctAnswers.add("Iene");

        questions.add("Qual é o maior oceano do mundo?");
        correctAnswers.add("Pacífico");

        questions.add("Em que ano o Brasil foi descoberto?");
        correctAnswers.add("1500");

        questions.add("Quem pintou a Mona Lisa?");
        correctAnswers.add("Leonardo da Vinci");

        questions.add("Qual é o país conhecido como o berço da civilização?");
        correctAnswers.add("Mesopotâmia");

        questions.add("Qual é o sistema solar?");
        correctAnswers.add("Um conjunto de planetas que orbitam ao redor do Sol");

        questions.add("Qual é a capital da Itália?");
        correctAnswers.add("Roma");

        questions.add("Qual é o maior deserto do mundo?");
        correctAnswers.add("Sahara");

        questions.add("Qual é o idioma mais falado do mundo?");
        correctAnswers.add("Mandarim");

        questions.add("Qual é a principal religião da Índia?");
        correctAnswers.add("Hinduísmo");
    }

    @Override
    public List<String> getQuestions() throws RemoteException {
        return questions;
    }

    @Override
    public boolean submitAnswer(int questionIndex, String answer) throws RemoteException {
        if (answer.equalsIgnoreCase(correctAnswers.get(questionIndex))) {
            score++;
            return true;
        }
        return false;
    }

    @Override
    public int getScore() throws RemoteException {
        return score;
    }

    public static void main(String[] args) {
        try {
            QuizServer server = new QuizServer();
            Registry registry = LocateRegistry.createRegistry(1098);
            registry.bind("QuizService", server);
            System.out.println("O servidor de quiz está em execução...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

