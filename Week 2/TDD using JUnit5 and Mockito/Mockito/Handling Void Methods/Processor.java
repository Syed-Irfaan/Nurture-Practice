
public class Processor {
    private Logger logger;

    public Processor(Logger logger) {
        this.logger = logger;
    }

    public void process() {
        logger.log("Processing...");  
    }
}
