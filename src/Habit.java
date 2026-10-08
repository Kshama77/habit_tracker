public class Habit {
    private int id;
    private String name;
    private boolean completed;

    public Habit(int id, String name) {
        this.id = id;
        this.name = name;
        this.completed = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void markComplete() {
        completed = true;
    }

    public void markIncomplete() {
        completed = false;
    }

    @Override
    public String toString() {
        String status = completed ? "[Done]" : "[ ]";
        return id + ". " + status + " " + name;
    }
}
