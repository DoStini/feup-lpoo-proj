package com.shootemup.g53.model.util;

import com.shootemup.g53.model.util.objectpool.ObjectPool;
import com.shootemup.g53.model.util.objectpool.PoolableObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ObjectPoolTest {

    class TestClass implements PoolableObject {
        boolean active = false;
        @Override
        public boolean isActive() { return active; }

        @Override
        public void activate() { active = true; }

        @Override
        public void deactivate() { active = false; }

        @Override
        public PoolableObject clone() { return new TestClass(); }
    }

    private ObjectPool<TestClass> pool;
    private int maxSize = 20;

    @BeforeEach
    void setup() {
        pool = new ObjectPool<>(maxSize, new TestClass());
    }

    @Test
    void size() {
        Assertions.assertEquals(maxSize, pool.getPoolSize());
    }

    @Test
    void elements() {
        List<TestClass> objects = new ArrayList<>();
        for (int i = 0; i < maxSize; i++) {
            TestClass obj = pool.retrieve();
            Assertions.assertTrue( obj.isActive());
            objects.add(obj);
        }

        Assertions.assertNull(pool.retrieve());

        for (int i = 0; i < maxSize; i++) {
            TestClass obj = objects.get(i);
            pool.restore(obj);
            Assertions.assertFalse(obj.isActive());
        }
    }
}
