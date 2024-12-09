import java.util.Comparator;

public class TaskComparator implements Comparator<TodoList.TodoItem> {

	public int compare(TodoList.TodoItem task1, TodoList.TodoItem task2){
		//go trough queue and see where is the first task with equal level and add it there
		if(task1.getPriorityLevel() < task2.getPriorityLevel()) return 1;
		else if (task1.getPriorityLevel() > task2.getPriorityLevel()) return -1;
		return 0;
	}

}
