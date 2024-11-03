import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface QuizInterface extends Remote {
    List<String> getQuestions() throws RemoteException;
    boolean submitAnswer(int questionIndex, String answer) throws RemoteException;
    int getScore() throws RemoteException;
}
