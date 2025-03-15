package Lab3.src.Cinema;

public class Film {
    private String name;
    private Integer duration;

    public Film(String name, int durationMins) {
        this.name = name;
        this.duration = durationMins;
    }

    public String getName() {
        return this.name;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public long getDurationMillis() {
        return this.duration * 60 * 1000;
    }
}
