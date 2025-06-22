public class FactoryMethod {
    public static void main(String[] args) {
        System.out.println("Document Management System");
        System.out.println("==========================\n");
    
        DocumentFactory wordFactory = new WordDocumentFactory();
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        DocumentFactory excelFactory = new ExcelDocumentFactory();
        
        System.out.println("Processing Word Document:");
        wordFactory.processDocument();
        
        System.out.println("Processing PDF Document:");
        pdfFactory.processDocument();
        
        System.out.println("Processing Excel Document:");
        excelFactory.processDocument();
        
        System.out.println("Creating individual documents:");
        Document wordDoc = wordFactory.createDocument();
        Document pdfDoc = pdfFactory.createDocument();
        Document excelDoc = excelFactory.createDocument();
        
        System.out.println("Created: " + wordDoc.getType());
        System.out.println("Created: " + pdfDoc.getType());
        System.out.println("Created: " + excelDoc.getType());
    }
}