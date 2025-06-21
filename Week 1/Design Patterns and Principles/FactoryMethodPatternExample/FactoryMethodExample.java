import java.util.*;

interface Document {
    void open();
}

class WordDocument implements Document {
    public void open() {
        System.out.println("Word document created...");
    }
}

class PdfDocument implements Document {
    public void open() {
        System.out.println("PDF document created...");
    }
}

class ExcelDocument implements Document {
    public void open() {
        System.out.println("Excel document created...");
    }
}

abstract class DocumentFactory {
    public abstract Document createDocument();
}

class WordDocumentFactory extends DocumentFactory {
    public Document createDocument() {
        return new WordDocument();
    }
}

class PdfDocumentFactory extends DocumentFactory {
    public Document createDocument() {
        return new PdfDocument();
    }
}

class ExcelDocumentFactory extends DocumentFactory {
    public Document createDocument() {
        return new ExcelDocument();
    }
}

public class FactoryMethodExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose document type (word/pdf/excel): ");
        String type = scanner.nextLine().toLowerCase();

        DocumentFactory factory = null;

        switch (type) {
            case "word":
                factory = new WordDocumentFactory();
                break;
            case "pdf":
                factory = new PdfDocumentFactory();
                break;
            case "excel":
                factory = new ExcelDocumentFactory();
                break;
            default:
                System.out.println("Invalid document type.");
                System.exit(0);
        }

        Document document = factory.createDocument();
        document.open();

        scanner.close();
    }
}
