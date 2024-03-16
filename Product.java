package final_attestation;

public class Product {
    private int id;
    public String name;
    private int weight;

    public Product(int id, String name, int weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        return String.format("id = %s, product = %s, name = %s", getId(), getClass().getSimpleName(), name);
    }
}
