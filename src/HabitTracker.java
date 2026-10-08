import java.util.ArrayList;
import java.util.Scanner;

public class HabitTracker {
    private static final ArrayList<Habit> habits = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static int nextId = 1;

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("================================");
        System.out.println("       HABIT TRACKER");
        System.out.println("================================");

        while (running) {
            showMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    addHabit();
                    break;
                case 2:
                    viewHabits();
                    break;
                case 3:
                    markHabitComplete();
                    break;
                case 4:
                    markHabitIncomplete();
                    break;
                case 5:
                    editHabit();
                    break;
                case 6:
                    deleteHabit();
                    break;
                case 7:
                    showProgress();
                    break;
                case 8:
                    running = false;
                    System.out.println("Thank you for using HabitTracker!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\n----------- MENU -----------");
        System.out.println("1. Add Habit");
        System.out.println("2. View Habits");
        System.out.println("3. Mark Habit Complete");
        System.out.println("4. Mark Habit Incomplete");
        System.out.println("5. Edit Habit");
        System.out.println("6. Delete Habit");
        System.out.println("7. View Progress");
        System.out.println("8. Exit");
        System.out.println("----------------------------");
    }

    private static void addHabit() {
        System.out.print("Enter habit name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Habit name cannot be empty.");
            return;
        }

        habits.add(new Habit(nextId++, name));
        System.out.println("Habit added successfully.");
    }

    private static void viewHabits() {
        if (habits.isEmpty()) {
            System.out.println("No habits found.");
            return;
        }

        System.out.println("\nYour Habits:");
        for (Habit habit : habits) {
            System.out.println(habit);
        }
    }

    private static void markHabitComplete() {
        viewHabits();

        if (habits.isEmpty()) {
            return;
        }

        int id = readInt("Enter habit ID to mark complete: ");
        Habit habit = findHabit(id);

        if (habit != null) {
            habit.markComplete();
            System.out.println("Habit marked as complete.");
        } else {
            System.out.println("Habit not found.");
        }
    }

    private static void markHabitIncomplete() {
        viewHabits();

        if (habits.isEmpty()) {
            return;
        }

        int id = readInt("Enter habit ID to mark incomplete: ");
        Habit habit = findHabit(id);

        if (habit != null) {
            habit.markIncomplete();
            System.out.println("Habit marked as incomplete.");
        } else {
            System.out.println("Habit not found.");
        }
    }

    private static void editHabit() {
        viewHabits();

        if (habits.isEmpty()) {
            return;
        }

        int id = readInt("Enter habit ID to edit: ");
        Habit habit = findHabit(id);

        if (habit != null) {
            System.out.print("Enter new habit name: ");
            String newName = scanner.nextLine().trim();

            if (!newName.isEmpty()) {
                habit.setName(newName);
                System.out.println("Habit updated successfully.");
            } else {
                System.out.println("Habit name cannot be empty.");
            }
        } else {
            System.out.println("Habit not found.");
        }
    }

    private static void deleteHabit() {
        viewHabits();

        if (habits.isEmpty()) {
            return;
        }

        int id = readInt("Enter habit ID to delete: ");
        Habit habit = findHabit(id);

        if (habit != null) {
            habits.remove(habit);
            System.out.println("Habit deleted successfully.");
        } else {
            System.out.println("Habit not found.");
        }
    }

    private static void showProgress() {
        if (habits.isEmpty()) {
            System.out.println("No habits to track.");
            return;
        }

        int completed = 0;

        for (Habit habit : habits) {
            if (habit.isCompleted()) {
                completed++;
            }
        }

        int total = habits.size();
        double percentage = (completed * 100.0) / total;

        System.out.println("\n----------- PROGRESS -----------");
        System.out.println("Total habits: " + total);
        System.out.println("Completed: " + completed);
        System.out.println("Remaining: " + (total - completed));
        System.out.printf("Completion rate: %.1f%%%n", percentage);
        System.out.println("--------------------------------");
    }

    private static Habit findHabit(int id) {
        for (Habit habit : habits) {
            if (habit.getId() == id) {
                return habit;
            }
        }

        return null;
    }

    private static int readInt(String message) {
        while (true) {
            System.out.print(message);

            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
