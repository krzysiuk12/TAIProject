package pl.edu.agh.domain;

/**
 * Created by Krzysztof Kicinger on 2014-11-24.
 */
public enum Rating {

    VERY_BAD(1),
    BAD(2),
    OK(3),
    GOOD(4),
    VERY_GOOD(5);

    private final int value;

    private Rating(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
