package com.shootemup.g53.model.util.objectpool;

import java.util.ArrayList;
import java.util.List;

public class ObjectPool<T extends PoolableObject> {
    private int cacheSize;
    private List<T> objects;

    public ObjectPool(int cacheSize, T templateObject) {
        this.cacheSize = cacheSize;
        this.objects = new ArrayList<T>();
        init(templateObject);
    }

    private void init(T template) {
        for (int i = 0; i < cacheSize; i++) {
            T sample = (T) template.clone();
            sample.deactivate();
            objects.add(sample);
        }
    }

    public int getPoolSize() {
        return objects.size();
    }

    public T retrieve() {
        for (T obj: objects) {
            if (!obj.isActive()) {
                obj.activate();
                return obj;
            }
        }
        return null;
    }

    public void restore(T object) {
        object.deactivate();
    }
}
