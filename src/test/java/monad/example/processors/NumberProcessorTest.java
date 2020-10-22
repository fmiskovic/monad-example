package monad.example.processors;

import monad.example.ResultOrError;
import monad.example.errors.SimpleError;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NumberProcessorTest {

    private final NumberProcessor processor = new NumberProcessor();

    @Test
    public void testSuccessResult() {
        // given
        String segment = "5";
        // when
        ResultOrError<Long, SimpleError> resultOrError = processor.process(segment);
        // expected
        assertFalse(resultOrError.isError());
        assertEquals(25, resultOrError.getResult());
    }

    @Test
    public void testSuccessFail() {
        // given
        String segment = "Not a number";
        // when
        ResultOrError<Long, SimpleError> resultOrError = processor.process(segment);
        // expected
        assertTrue(resultOrError.isError());
        System.out.println(resultOrError.getError().getMessage());
    }
}
