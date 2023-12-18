
public class Parameter {
    private Algorithm algorithm;

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public float work() {
        return this.algorithm.work();
    }

    public String name() {
        return this.algorithm.name();
    }
}
