package com.shootemup.g53.view;


import com.shootemup.g53.model.Model;

public abstract class Viewer<M extends Model> {
    public abstract void draw(M model);
}