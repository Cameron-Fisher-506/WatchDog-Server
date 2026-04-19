package za.co.watchdog.common.domain.usecase;

public interface UseCase<I, O> {
    public O execute(I input);
}
