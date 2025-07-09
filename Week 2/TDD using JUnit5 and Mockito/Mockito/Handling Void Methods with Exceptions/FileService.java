
public class FileService {
    private FileWriter writer;

    public FileService(FileWriter writer) {
        this.writer = writer;
    }

    public void save(String content) {
        try {
            writer.write(content);
        } catch (Exception e) {
            System.out.println("Write failed: " + e.getMessage());
        }
    }
}
