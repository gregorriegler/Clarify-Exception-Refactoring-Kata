package codingdojo;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MessageEnricherTest {

    @Test
    void sampleTest() {
        MessageEnricher enricher = new MessageEnricher();
        SpreadsheetWorkbook worksheet = new SpreadsheetWorkbook();
        Exception e = new RuntimeException("Terrible problem");

        ErrorResult actual = enricher.enrichError(worksheet, e);

        Approvals.verify(actual);
    }

    @Test
    void expressionParseException() {
        MessageEnricher enricher = new MessageEnricher();
        SpreadsheetWorkbook worksheet = new SpreadsheetWorkbook();
        Exception e = new ExpressionParseException("Terrible problem");

        ErrorResult actual = enricher.enrichError(worksheet, e);

        Approvals.verify(actual);
    }

    @Test
    void circularReferenceException() {
        MessageEnricher enricher = new MessageEnricher();
        SpreadsheetWorkbook worksheet = new SpreadsheetWorkbook();
        Exception e = new RuntimeException("Circular Reference And something else");

        ErrorResult actual = enricher.enrichError(worksheet, e);

        Approvals.verify(actual);
    }

    @Test
    void vLookupException() {
        MessageEnricher enricher = new MessageEnricher();
        SpreadsheetWorkbook worksheet = new SpreadsheetWorkbook();
        Exception e = irrelevant_vLookup_irrelevant();

        ErrorResult actual = enricher.enrichError(worksheet, e);

        Approvals.verify(actual);
    }

    @Test
    void noMatchesFoundException() {
        MessageEnricher enricher = new MessageEnricher();
        SpreadsheetWorkbook worksheet = new SpreadsheetWorkbook();
        Exception e = new RuntimeException("No matches found");

        ErrorResult actual = enricher.enrichError(worksheet, e);

        Approvals.verify(actual);
    }

    private RuntimeException irrelevant_vLookup_irrelevant() {
        RuntimeException exception = new RuntimeException("Object reference not set to an instance of an object");
        exception.fillInStackTrace();
        return exception;
    }

}
