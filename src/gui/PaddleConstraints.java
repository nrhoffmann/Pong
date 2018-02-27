package gui;

class PaddleConstraints {
    private int x;
    private int maxY, minY;

    public int getX() {
        return x;
    }

    public PaddleConstraints setX(int x) {
        this.x = x;
        return this;
    }

    public int getMaxY() {
        return maxY;
    }

    public PaddleConstraints setMaxY(int maxY) {
        this.maxY = maxY;
        return this;
    }

    public int getMinY() {
        return minY;
    }

    public PaddleConstraints setMinY(int minY) {
        this.minY = minY;
        return this;
    }
}
