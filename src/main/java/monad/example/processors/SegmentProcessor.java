package monad.example.processors;

import monad.example.ResultOrError;
import monad.example.errors.SimpleError;

import java.util.Objects;

public class SegmentProcessor implements Processor<String, String, SimpleError> {

    @Override
    public ResultOrError<String, SimpleError> process(String segment) {
        return trim(segment).bind(this::toUpperCase).bind(this::appendExclamation);
    }

    private ResultOrError<String, SimpleError> trim(String segment) {
        if (Objects.isNull(segment)) return createNullError();

        String trimmed = segment.trim();
        if (trimmed.isEmpty()) return createEmptyError();

        return ResultOrError.createResult(trimmed);
    }

    private ResultOrError<String, SimpleError> toUpperCase(String segment) {
        if (Objects.isNull(segment)) return createNullError();

        if (segment.matches("[a-zA-Z ]+")) return ResultOrError.createResult(segment.toUpperCase());

        return createLettersError();
    }

    private ResultOrError<String, SimpleError> appendExclamation(String segment) {
        if (Objects.isNull(segment)) return createNullError();

        if (segment.length() > 100) createLengthError();

        return ResultOrError.createResult(segment.concat("!"));
    }

    private ResultOrError<String, SimpleError> createNullError() {
        return ResultOrError.createError(new SimpleError("Invalid argument. Can not be null."));
    }

    private ResultOrError<String, SimpleError> createEmptyError() {
        return ResultOrError.createError(new SimpleError("Segment must contain non-space characters."));
    }

    private ResultOrError<String, SimpleError> createLettersError() {
        return ResultOrError.createError(new SimpleError("Segment must contain only letters."));
    }

    private ResultOrError<String, SimpleError> createLengthError() {
        return ResultOrError.createError(new SimpleError("segment must not exceed 100 characters."));
    }
}
