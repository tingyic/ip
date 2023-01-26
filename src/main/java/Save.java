import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 * Saves and loads tasks in the hard disk.
 */
public class Save {
    private final File savedFile;

    /**
     * The constructor of Save.
     * Creates a saved file in the hard disk if it has not existed yet.
     * @param location The location where the saved file is saved to.
     * @throws DukeException If there is an error when creating the new file.
     * @throws IOException If there is an error while creating the new file.
     */
    public Save(String location) throws DukeException, IOException {
        String[] subPath = location.split("/");
        File savedTasks = new File(location);
        File path = new File(subPath[0]);

        if (!path.exists()) {
            throw new DukeException("Sorry! There was an error while creating /.data/ folder");
        }

        if (!savedTasks.exists()) {
            if (!savedTasks.createNewFile()) {
                throw new DukeException("Sorry! There was an error while creating savedTasks.txt file");
            }
        }

        this.savedFile = savedTasks;
    }

    /**
     * Loads the saved tasks from the saved file.
     * @return An array list of tasks from the saved file.
     * @throws FileNotFoundException If the saved file cannot be found.
     * @throws DukeException If the saved file cannot be found.
     */
    public ArrayList<Task> loadTasks() throws DukeException, FileNotFoundException {
        Scanner scanner = new Scanner(this.savedFile);
        ArrayList<Task> listOfTasks = new ArrayList<>();

        while (scanner.hasNext()) {
            String current = scanner.nextLine();
            String[] type = current.split(" ", 3);
            switch (type[0]) {
            case "T": {
                Task taskToAdd = new Todo(type[2], (type[1]).equals("1"));
                listOfTasks.add(taskToAdd);
                break;
            }
            case "E": {
                Task taskToAdd = new Event(type[2], from, to, type[1].equals("1"));
                listOfTasks.add(taskToAdd);
                break;
            }
            case "D": {
                Task taskToAdd = new Deadline(type[2], by, type[1]. equals(("1")));
                listOfTasks.add(taskToAdd);
                break;
            }
            default: {
                throw new DukeException("My apologies. There was an error when reading saved tasks from" +
                        "hard disk." + "\n Not to worry, I have created a new task list for you.");
            }
            }
        }
        return listOfTasks;
    }
}
