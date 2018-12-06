package Hero;


public class Hero {
    private int id;
    private String name;
    private String loc; // location
    private String sex;
    private int birth;
    private int death;
    private int power;
    public int getage(){
        return death-birth;
    }


    public Hero(int id, String name, String loc, String sex, int birth, int death, int power) {
        this.id = id;
        this.name = name;
        this.loc = loc;
        this.sex = sex;
        this.birth = birth;
        this.death = death;
        this.power = power;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", loc='" + loc + '\'' +
                ", sex='" + sex + '\'' +
                ", birth=" + birth +
                ", death=" + death +
                ", power=" + power +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getBirth() {
        return birth;
    }

    public void setBirth(int birth) {
        this.birth = birth;
    }

    public int getDeath() {
        return death;
    }

    public void setDeath(int death) {
        this.death = death;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
