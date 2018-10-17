package com.chamc.demo.op;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LazyCompute<T> {
 
    private final Supplier<T> supplier;
    
    //回收
    //private Supplier<T> supplier;
 
    private volatile T value;
 
    public T get() {
        if (value == null) {
            synchronized (this) {
                if (value == null) {
                    value = supplier.get();
                    //value = Objects.requireNonNull(supplier.get());
                    
                    //回收
                    //supplier = null;
                }
            }
        }
       return value;
    }
    
    public <R> LazyCompute<R> map(Function<T, R> mapper) {
        return new LazyCompute<>(() -> mapper.apply(this.get()));
    }
     
    public <R> LazyCompute<R> flatMap(Function<T, LazyCompute<R>> mapper) {
        return new LazyCompute<>(() -> mapper.apply(this.get()).get());
    }
     
    public LazyCompute<Optional<T>> filter(Predicate<T> predicate) {
        return new LazyCompute<>(() -> Optional.of(get()).filter(predicate));
    }
    
    public static <T> LazyCompute<T> of(Supplier<T> supplier) {
        return new LazyCompute<>(supplier);
    }
    
}
