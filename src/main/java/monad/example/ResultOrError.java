package monad.example;

import java.util.function.Function;

public class ResultOrError<R, E> {

    private final R result;

    private final E error;

    protected ResultOrError(R result, E error) {
        this.result = result;
        this.error = error;
    }

    public static <R, E> ResultOrError<R, E> createError(E error) {
        return new ResultOrError<R, E>(null, error);
    }

    public static <R, E> ResultOrError<R, E> createResult(R result) {
        return new ResultOrError<R, E>(result, null);
    }

    public <R2> ResultOrError<R2, E> bind(Function<R, ResultOrError<R2, E>> function) {
        return isResult() ? function.apply(result) : new ResultOrError<>(null, error);
    }

    public R getResult() {
        return result;
    }

    public E getError() {
        return error;
    }

    public boolean isResult() {
        return error == null;
    }

    public boolean isError() {
        return error != null;
    }
}
