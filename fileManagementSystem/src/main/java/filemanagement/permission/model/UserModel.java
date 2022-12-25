package filemanagement.permission.model;
public class UserModel {
    private String name;
    private int id;
    private String password;
    public String getName() { return name; }
    public int getId() { return id; }
    public String getPassword() {
        return password;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User[name=" + getName() + ", id=" + getId()+ ",  password=" + getPassword() + "]";
    }
}