
public interface TransactionManager {
    void start();
    void commit();
    void log(String message);
}
