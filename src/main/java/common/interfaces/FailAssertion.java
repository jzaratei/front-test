package common.interfaces;

@FunctionalInterface
public interface FailAssertion {
    void fail(String message);
}
