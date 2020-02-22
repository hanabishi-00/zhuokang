package hdy.eva.entity;

public class evaModelTur20200001Mid {
    private Integer id;

    private String name;

    private Integer father;

    public evaModelTur20200001Mid(Integer id, String name, Integer father) {
        this.id = id;
        this.name = name;
        this.father = father;
    }

    public evaModelTur20200001Mid() {
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
}