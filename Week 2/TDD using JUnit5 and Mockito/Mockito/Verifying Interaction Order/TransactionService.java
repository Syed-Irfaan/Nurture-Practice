
public class TransactionService {
    private TransactionManager manager;

    public TransactionService(TransactionManager manager) {
        this.manager = manager;
    }

    public void performTransaction() {
        manager.start();
        manager.log("Transaction started");
        manager.commit();
    }
}
