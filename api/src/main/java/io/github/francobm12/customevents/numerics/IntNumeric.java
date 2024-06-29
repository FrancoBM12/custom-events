package io.github.francobm12.customevents.numerics;

public class IntNumeric implements Numeric {

    private static final int INITIAL_VALUE = 0;

    private int amount;

    public IntNumeric() {
        this(INITIAL_VALUE);
    }

    public IntNumeric(int amount) {
        this.amount = amount;
    }

    @Override
    public int amount() {
        return this.amount;
    }

    @Override
    public int getAndIncrement() {
        return this.amount++;
    }

    @Override
    public int getAndDecrement() {
        return this.amount--;
    }

    @Override
    public int incrementAndGet() {
        return ++this.amount;
    }

    @Override
    public int decrementAndGet() {
        return --this.amount;
    }

    @Override
    public void increment() {
        this.amount++;
    }

    @Override
    public void decrement() {
        this.amount--;
    }

    @Override
    public void plus(int amount) {
        this.amount += amount;
    }

    @Override
    public void minus(int amount) {
        this.amount -= amount;
    }

    @Override
    public void divide(int amount) {
        this.amount /= amount;
    }

    @Override
    public void multiply(int amount) {
        this.amount *= amount;
    }
}
