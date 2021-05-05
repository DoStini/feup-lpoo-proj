package com.shootemup.g53.model.util;

import java.util.Objects;

//https://stackoverflow.com/questions/8905528/how-to-write-write-a-set-for-unordered-pair-in-java
public class UnorderedPair<T> {
    private final T first;
    private final T second;

    public UnorderedPair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UnorderedPair)) return false;
        UnorderedPair<?> that = (UnorderedPair<?>) o;
        return (first.equals(that.first) && second.equals(that.second)) ||
                (second.equals(that.first) && first.equals(that.second)) ;
    }

    @Override
    public int hashCode() {
        int hashFirst = first.hashCode();
        int hashSecond = second.hashCode();
        int maxHash = Math.max(hashFirst, hashSecond);
        int minHash = Math.min(hashFirst, hashSecond);

        return minHash * 31 + maxHash;
    }
}
