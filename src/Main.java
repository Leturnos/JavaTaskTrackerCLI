import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            FileWriter fileWriter = new FileWriter("TaskTracker.txt", true);
            FileReader fileReader = new FileReader("TaskTracker.txt");

            List<Task> allTasks = new ArrayList<>();

            try (BufferedReader br = new BufferedReader(fileReader)) {
                String line;
                while ((line = br.readLine()) != null) {
                    try {
                        line = line.trim();
                        if (line.isEmpty()){
                            continue;
                        }
                        line = line.substring(1, line.length() - 1); // remove { and }

                        String[] fields = line.split(","); // separate by comma

                        // formatt "key: value"
                        Map<String, String> map = new HashMap<>();
                        for (String field : fields) {
                            String[] keyValue = field.split(":", 2);
                            String key = keyValue[0].replace("\"", "").trim();
                            String value = keyValue[1].replace("\"", "").trim();
                            map.put(key, value);
                        }

                        Task task = new Task(
                                Long.parseLong(map.get("id")),
                                map.get("description"),
                                Status.valueOf(map.get("status")),
                                LocalDateTime.parse(map.get("createdAt"), Task.formatter),
                                LocalDateTime.parse(map.get("updatedAt"), Task.formatter)
                        );
                        allTasks.add(task);
                    } catch (Exception e) {
                        // any error in loading the file
                        System.err.println("Warning: Skipping corrupted or invalid line in TaskTracker.txt: \"" + line + "\"");
                    }
                long maxId = 0;
                for (Task task : allTasks) {
                    if (task.getId() > maxId) {
                        maxId = task.getId();
                    }
                }
                Task.initializeIdCounter(maxId); // Initializes the counter with the highest ID
            }
            }catch (IOException e) {
                e.printStackTrace();
            }

            Scanner scanner = new Scanner(System.in);

            String opt = "";
            do {
                System.out.println("""
                        ======================================
                        1- Add, Update, and Delete tasks
                        2- Mark a task as in progress or done
                        3- List all tasks
                        4- List all tasks that are done
                        5- List all tasks that are not done
                        6- List all tasks that are in progress
                        7- exit
                        ======================================""");
                opt = scanner.nextLine();

                switch (opt) {
                    case "1" :
                        String optCase1 = "";
                        while (!optCase1.equals("4")) {
                            System.out.println("What would you like?");
                            System.out.println("""
                                    1- Add
                                    2- Update
                                    3- Delete
                                    4- Exit""");
                            optCase1 = scanner.nextLine();
                            switch (optCase1) {
                                case "1":
                                    System.out.println("insert description");
                                    String description = scanner.nextLine();
                                    System.out.println("insert status(TODO, IN_PROGRESS, DONE)");
                                    String status = scanner.nextLine();
                                    if (description.contains(",")) {
                                        System.out.println("invalid description, dont use commas");
                                    }
                                    else if (!status.equals("TODO") && !status.equals("IN_PROGRESS") && !status.equals("DONE"))
                                        System.out.println("invalid status");
                                    else {
                                        Task task = new Task(description, status);
                                        allTasks.add(task);
                                        System.out.println("task added: " + task);
                                    }
                                    break;

                                case "2":
                                    System.out.println("insert id to Update description of task");
                                    String tmp = scanner.nextLine();
                                    try {
                                        boolean flag = false;
                                        long optDescriptionUpdate = Long.parseLong(tmp);

                                        for (Task task : allTasks) {
                                            if (task.getId() == optDescriptionUpdate) {
                                                System.out.println(task);
                                                System.out.println("insert new description\n");
                                                String descriptionUpdate = scanner.nextLine();
                                                if (descriptionUpdate.contains(",")){
                                                    System.out.println("invalid description, dont use commas");
                                                }
                                                else {
                                                    task.setDescription(descriptionUpdate);
                                                    System.out.println("task updated: " + task);
                                                    task.setUpdatedAt(LocalDateTime.now());
                                                    flag = true;
                                                }
                                            }
                                        }
                                        if (!flag) {
                                            System.out.println("Task with ID " + optDescriptionUpdate + " not found.");
                                        }
                                    } catch (NumberFormatException e) {
                                        System.err.println("Invalid format. Please enter a numeric ID.");
                                    }
                                    break;

                                case "3":
                                    System.out.println("insert id to Delete task");
                                    String tmp2 = scanner.nextLine();
                                    try {
                                        long optTaskDelete = Long.parseLong(tmp2);
                                        boolean flag = false;

                                        Iterator<Task> iterator = allTasks.iterator();
                                        while (iterator.hasNext()) {
                                            Task task = iterator.next();
                                            if (task.getId() == optTaskDelete) {
                                                iterator.remove(); // safe remove
                                                System.out.println("task deleted: " + task);
                                                flag = true;
                                            }
                                        }
                                        if (!flag) {
                                            System.out.println("Task with ID " + optTaskDelete + " not found.");
                                        }
                                    } catch (NumberFormatException e) {
                                        System.err.println("Invalid format. Please enter a numeric ID.");
                                    }
                                    break;

                                case "4":
                                    break;

                                default:
                                    System.out.println("invalid option, insert numbers 1-4");
                                    break;
                            }
                        } break;

                    case "2" :
                        System.out.println("insert id to Update status of task");
                        String tmp3 = scanner.nextLine();
                        try {
                            long optStatusUpdate = Long.parseLong(tmp3);
                            boolean flag = false;

                            for (Task task : allTasks) {
                                if (task.getId() == optStatusUpdate) {
                                    System.out.println(task);
                                    System.out.println("insert status(IN_PROGRESS, DONE)");
                                    String status = scanner.nextLine();
                                    if (!status.equals("IN_PROGRESS") && !status.equals("DONE"))
                                        System.out.println("invalid status");
                                    else {
                                        task.setStatus(Status.valueOf(status));
                                        System.out.println("task status updated: " + task);
                                        task.setUpdatedAt(LocalDateTime.now()); // update date
                                        flag = true;
                                    }
                                }
                                if (!flag) {
                                    System.out.println("Task with ID " + optStatusUpdate + " not found.");
                                }
                            }
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid format. Please enter a numeric ID.");
                        }
                        break;

                    case "3" :
                        if (allTasks.isEmpty()){
                            System.out.println("empty list");
                        }
                        else {
                            for (Task task : allTasks) {
                                System.out.println(task);
                            }
                        }
                        break;

                    case "4" :
                        if (allTasks.isEmpty()){
                            System.out.println("empty list");
                        }
                        else {
                            boolean flag = false;
                            for (Task task : allTasks) {
                                if (task.getStatus() == Status.DONE) {
                                    System.out.println(task);
                                    flag = true;
                                }
                            }
                            if (!flag) {
                                System.out.println("no tasks found");
                            }
                        }
                        break;

                    case "5" :
                        if (allTasks.isEmpty()){
                            System.out.println("empty list");
                        }
                        else {
                            boolean flag = false;
                            for (Task task : allTasks) {
                                if (task.getStatus() != Status.DONE) {
                                    System.out.println(task);
                                    flag = true;
                                }
                            } if (!flag) {
                                System.out.println("no tasks found");
                            }
                        }
                        break;

                    case "6" :
                        if (allTasks.isEmpty()){
                            System.out.println("empty list");
                        }
                        else {
                            boolean flag = false;
                            for (Task task : allTasks) {
                                if (task.getStatus() == Status.IN_PROGRESS) {
                                    System.out.println(task);
                                    flag = true;
                                }
                            } if (!flag) {
                                System.out.println("no tasks found");
                            }
                        }
                        break;

                    case "7":
                        System.out.println("finish");
                        fileReader.close();
                        fileWriter.close();

                        // overwriting the file
                        try (FileWriter writer = new FileWriter("TaskTracker.txt", false)) {
                            for (Task task : allTasks){
                                writer.write(task.toString() + System.lineSeparator());
                            }
                        } catch (IOException e) {
                            System.out.println("Error saving tasks: " + e.getMessage());
                        }
                        break;

                    default:
                        System.out.println("invalid option, insert numbers 1-7");
                        break;
                }
            } while(!opt.equals("7"));
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}