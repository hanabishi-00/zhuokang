package hdy.eva.entity;

public class evaModelTur20200001Leaf {
    private Integer id;

    private String name;

    private Integer father;

    private Byte weight;

    private Boolean state;

    private String stanDescription;

    private String points;

    public evaModelTur20200001Leaf(Integer id, String name, Integer father, Byte weight, Boolean state, String stanDescription, String points) {
        this.id = id;
        this.name = name;
        this.father = father;
        this.weight = weight;
        this.state = state;
        this.stanDescription = stanDescription;
        this.points = points;
    }

    public evaModelTur20200001Leaf() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getFather() {
        return father;
    }

    public void setFather(Integer father) {
        this.father = father;
    }

    public Byte getWeight() {
        return weight;
    }

    public void setWeight(Byte weight) {
        this.weight = weight;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getStanDescription() {
        return stanDescription;
    }

    public void setStanDescription(String stanDescription) {
        this.stanDescription = stanDescription == null ? null : stanDescription.trim();
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points == null ? null : points.trim();
    }
}