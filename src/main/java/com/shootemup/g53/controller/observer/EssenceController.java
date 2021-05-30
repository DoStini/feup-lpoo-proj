package com.shootemup.g53.controller.observer;

import java.util.ArrayList;
import java.util.List;

public class EssenceController implements EventSubject<EssenceObserver>{
    private List<EssenceObserver> essenceObservers = new ArrayList<>();
    private int amount = 0;
    @Override
    public void addObserver(EssenceObserver observer) {
        essenceObservers.add(observer);
    }

    @Override
    public void removeObserver(EssenceObserver observer) {
        essenceObservers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(EssenceObserver essenceObserver:essenceObservers){
            essenceObserver.setEssence(amount);
        }
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<EssenceObserver> getEssenceObservers() {
        return essenceObservers;
    }
}
