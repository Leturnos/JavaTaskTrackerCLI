import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Task> allTasks = new ArrayList<>();

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
                                if (!status.equals("TODO") && !status.equals("IN_PROGRESS") && !status.equals("DONE"))
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
                                    long optDescriptionUpdate = Long.parseLong(tmp);

                                    for (Task task : allTasks) {
                                        if (task.getId() == optDescriptionUpdate) {
                                            System.out.println(task);
                                            System.out.println("insert new description\n");
                                            String descriptionUpdate = scanner.nextLine();
                                            task.setDescription(descriptionUpdate);
                                            System.out.println("task updated: " + task);
                                            task.setUpdatedAt(LocalDateTime.now());
                                        }
                                    }
                                } catch (NumberFormatException e) {
                                    System.err.println(e);
                                }
                                break;

                            case "3":
                                System.out.println("insert id to Delete task");
                                String tmp2 = scanner.nextLine();
                                try {
                                    long optTaskDelete = Long.parseLong(tmp2);

                                    Iterator<Task> iterator = allTasks.iterator();
                                    while (iterator.hasNext()) {
                                        Task task = iterator.next();
                                        if (task.getId() == optTaskDelete) {
                                            iterator.remove(); // safe remove
                                            System.out.println("task deleted: " + task);
                                        }
                                    }
                                } catch (NumberFormatException e) {
                                    System.err.println(e);
                                }
                                break;

                            case "4":
                                break;

                            default:
                                System.out.println("invalid option");
                                break;
                        }
                    } break;

                case "2" :
                    System.out.println("insert id to Update status of task");
                    String tmp3 = scanner.nextLine();
                    try {
                        long optStatusUpdate = Long.parseLong(tmp3);

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
                                }
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.err.println(e);
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
                    break;

                default:
                    System.out.println("invalid option");
                    break;
            }
        } while(!opt.equals("7"));
    }
}