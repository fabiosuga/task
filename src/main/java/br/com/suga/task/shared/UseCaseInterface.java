package br.com.suga.task.shared;

public interface UseCaseInterface<I, O> {
    O execute(I input);
}