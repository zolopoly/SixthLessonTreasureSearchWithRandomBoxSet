package com.msaggik.sixthlessontreasuresearch;

public class Box {
    // поля
    private final float coordinateX;
    private final float coordinateY;
    private boolean found; // поле найден/не найден сундук сокровищ

    // конструктор
    public Box(float coordinateX, float coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        found = false;
    }

    // геттеры
    public float getCoordinateX() {
        return coordinateX;
    }

    public float getCoordinateY() {
        return coordinateY;
    }

    public boolean isFound() {
        return found;
    }

    // сеттер
    public void setFound(boolean found) {
        this.found = found;
    }
}
