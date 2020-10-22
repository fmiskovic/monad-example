package monad.example.processors;

import io.vavr.control.Try;
import monad.example.ResultOrError;
import monad.example.errors.SimpleError;

import java.util.concurrent.atomic.AtomicReference;

public class NumberProcessor implements Processor<String, Long, SimpleError> {

    @Override
    public ResultOrError<Long, SimpleError> process(String number) {
        return toLong(number).bind(this::pow);
    }

    private ResultOrError<Long, SimpleError> toLong(String number) {
        AtomicReference<ResultOrError<Long, SimpleError>> ref = new AtomicReference<>();
        Try.of(() -> Long.parseLong(number))
                .onFailure(f ->
                        ref.set(ResultOrError.createError(new SimpleError(f.getMessage())))
                )
                .onSuccess(s ->
                        ref.set(ResultOrError.createResult(s))
                );

        return ref.get();
    }

    private ResultOrError<Long, SimpleError> pow(Long number) {
        if (number < 0) return createPositiveNumberError();
        return ResultOrError.createResult(number * number);
    }

    private ResultOrError<Long, SimpleError> createPositiveNumberError() {
        return ResultOrError.createError(new SimpleError("Argument must be positive number"));
    }
}
