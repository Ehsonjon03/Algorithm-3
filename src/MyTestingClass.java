public class MyTestingClass {
    private int id;
    private String name;

    // Default constructor
    public MyTestingClass() {
        this.id = 0;
        this.name = "";
    }

    // Constructor to initialize the id and name
    public MyTestingClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and setters for id and name
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

    // Custom hashCode method (without using Objects.hash())
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    // Override equals method for key comparison
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MyTestingClass that = (MyTestingClass) obj;
        return id == that.id && (name != null ? name.equals(that.name) : that.name == null);
    }

    // toString method to represent the object as a string
    @Override
    public String toString() {
        return "MyTestingClass{id=" + id + ", name='" + name + "'}";
    }
}
