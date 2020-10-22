package monad.example.processors;

import monad.example.ResultOrError;

/**
 * Functional interface - processor
 *
 * @param <I> input argument
 * @param <R> result
 * @param <E> error
 */
@FunctionalInterface
public interface Processor<I, R, E> {

    public ResultOrError<R, E> process(I input);
}
