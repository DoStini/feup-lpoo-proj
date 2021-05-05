package com.shootemup.g53.view.element;

import com.shootemup.g53.model.element.Element;
import com.shootemup.g53.ui.Gui;

public interface ElementView<T extends Element> {
    void draw(Gui gui, T element);
}
