package monad.example.processors;

import monad.example.ResultOrError;
import monad.example.errors.SimpleError;
import monad.example.processors.SegmentProcessor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SegmentProcessorTest {

    private final SegmentProcessor processor = new SegmentProcessor();

    @Test
    public void testSuccessResult() {
        // given
        String segment = " The quick brown fox jumps over the lazy dog ";
        // when
        ResultOrError<String, SimpleError> resultOrError = processor.process(segment);
        // expected
        assertFalse(resultOrError.isError());
        assertEquals("THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG!", resultOrError.getResult());
    }

    @Test
    public void testFailResult() {
        // given
        String segment = "   ";
        // when
        ResultOrError<String, SimpleError> resultOrError = processor.process(segment);
        // expected
        assertTrue(resultOrError.isError());
        System.out.println(resultOrError.getError().getMessage());
    }

}
