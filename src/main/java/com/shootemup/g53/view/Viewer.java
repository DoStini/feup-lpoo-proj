package com.shootemup.g53.view;


import com.shootemup.g53.model.Model;
import com.shootemup.g53.ui.Gui;

public abstract class Viewer<M extends Model> {
    protected Gui gui;
    public abstract void draw(M model);

}