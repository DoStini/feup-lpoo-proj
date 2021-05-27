package com.shootemup.g53.view.element.spaceship;

import com.shootemup.g53.model.element.Player;
import com.shootemup.g53.model.element.Spaceship;
import com.shootemup.g53.model.util.ColorOperation;
import com.shootemup.g53.model.util.Position;
import com.shootemup.g53.ui.Gui;
import com.shootemup.g53.view.element.ElementView;

import java.util.List;
import java.util.stream.IntStream;

public class PlayerView implements ElementView<Player> {
    protected int lineWidth;

    public PlayerView(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    @Override
    public void draw(Gui gui, Player element) {
        Position pos = element.getPosition();
        int mid = pos.getX();
        String actualColor = element.getColor();
        List<Integer> colors = ColorOperation.parseColor(actualColor);
        IntStream.range(0, colors.size()).forEach(i -> colors.set(i, (int) (colors.get(i) * 0.8)));

        String inverseColor = ColorOperation.parseColor(colors.get(0),colors.get(1),colors.get(2));
        String color;
        for (int i = 0; i <= element.getHeight(); i++) {
            if(i <= element.getHitHeight()) color = inverseColor;
            else color = actualColor;

            if (i > lineWidth-1) {
                gui.drawLine(color, pos.getLeft(i).getDown(i), lineWidth);
                gui.drawLine(color, pos.getRight(i-lineWidth).getDown(i), lineWidth);
            }
            else
                gui.drawLine(color, pos.getLeft(i).getDown(i), i*2);
        }

    }
}
