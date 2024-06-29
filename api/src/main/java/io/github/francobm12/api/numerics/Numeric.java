package io.github.francobm12.api.numerics;

public interface Numeric {

    int amount();

    int getAndIncrement();
    int getAndDecrement();

    int incrementAndGet();
    int decrementAndGet();

    void increment();
    void decrement();

    void plus(int amount);
    void minus(int amount);
    void divide(int amount);
    void multiply(int amount);

}
