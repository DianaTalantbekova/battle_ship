import java.util.ArrayList;
import java.util.List;

class Ship {
    private int size;
    private List<String> positions;
    private boolean[] hits;
    private boolean sunk;

    public Ship(int size) {
        this.size = size;
        this.positions = new ArrayList<>();
        this.hits = new boolean[size];
        this.sunk = false;
    }

    public boolean isSunk() {
        return sunk;
    }

    public boolean checkHit(String position) {
        int index = positions.indexOf(position);

        if (index != -1 && !hits[index]) {
            hits[index] = true;

            if (isSunk()) {
                sunk = true;
            }

            return true;
        }

        return false;
    }

    public void placeShip(List<String> positions) {
        this.positions = positions;
    }
}