package element;
public class node {
    private int index;
    private String name;
    private float value=0;
    private int father;
    private int weight=1;

    public node(){

    }

    public node(int ind, String nam, int fa){
        index=ind;
        name=nam;
        value=0;
        father=fa;
    }

    public node(int ind, String nam,  int fa, float val){
        index=ind;
        name=nam;
        father=fa;
        value=val;
    }

    public node(int ind, String nam,  int fa, int w){
        index=ind;
        name=nam;
        father=fa;
        weight=w;
    }

    public void init_value(){//初始化value值
        value=0;
    }

    public void write_value(float v){//写入中间节点值
        if (value==0){//逻辑：若原值为0，代表刚完成初始化或之前项目未出现扣分
            value=v;//直接写入v值
        }
        else if (v>value){//否则仅当传入的扣分值大于原值时进行更新
            value=v;
        }
    }

    public int get_index(){
        return index;
    }

    public String get_name(){
        return name;
    }

    public float get_value(){
        return value;
    }

    public int get_father(){
        return father;
    }

    public int get_weight(){
        return weight;
    }

}
