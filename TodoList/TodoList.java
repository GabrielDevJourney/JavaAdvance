import java.util.*;

public class TodoList {

	private String name;
	private Queue<TodoItem> tasks;

	public TodoList(String listName) {
		this.name = listName;
		this.tasks = new PriorityQueue<>(new TaskComparator());
	}

	public TodoItem getNextTask() {
		return tasks.peek();
	}

	public List<TodoItem> getAllTasks() {
		List<TodoItem> items = new ArrayList<TodoItem>();
		for (TodoItem item : tasks) {
			items.add(item);
		}
		return items;
	}

	public List<TodoItem> getIncompleteTasks() {
		List<TodoItem> items = new ArrayList<TodoItem>();
		for (TodoItem item : tasks) {
			if (!item.isComplete) {
				items.add(item);
			}
		}
		return items;
	}

	public List<TodoItem> getCompleteTasks() {
		List<TodoItem> items = new ArrayList<TodoItem>();
		for (TodoItem item : tasks) {
			if (item.isComplete) {
				items.add(item);
			}
		}
		return items;
	}

	public void markTaskComlete(TodoItem item) throws TodoListException {
		if(tasks.contains(item)){
			try {
				item.setComplete(true);
			} catch (NullPointerException exception) {
				throw new TodoListException("Task must exist in the todo list to be marked complete/incomplete!");
			}
		}else{
			throw new TodoListException("Task doesn't exist!");
		}
	}

	public void markTaskIncomlete(TodoItem item) throws TodoListException {
		if(tasks.contains(item)){
			try {
				item.setComplete(false);
			} catch (NullPointerException exception) {
				throw new TodoListException("Task must exist in the todo list to be marked complete/incomplete!");
			}
		}else{
			throw new TodoListException("Task doesn't exist!");
		}

	}

	public void addTask(TodoItem task) throws TodoListException {
		try{
			tasks.add(task);
		} catch (NullPointerException exception) {
			throw new TodoListException("Cannot add a null task to the todo list!");
		}catch (UnsupportedOperationException exception ){
			throw new TodoListException("Task type not supported in this todo list!");
		}
	}

	public void removeTask(TodoItem task) throws TodoListException {
		try{
			tasks.remove(task);
		}catch (NullPointerException exception){
			throw new TodoListException("Cant remove null task from list!");
		}catch (UnsupportedOperationException exception){
			throw new TodoListException("Task removal not supported for this list");
		}
	}

	//display task name, priority, status
	public void showDetails() {
		Iterator<TodoItem> currentIterator = tasks.iterator();
		System.out.println("\u001b[1m=== Task List ===\u001b[0m\n");

		while (currentIterator.hasNext()) {
			TodoItem currentItem = currentIterator.next();
			System.out.println("\u001b[1m" + "---------------" + "\u001b[0m");
			System.out.println("\u001b[1m📝 Task: \u001b[0m" + currentItem.name);


			// Priority print with color matching urgency red - high / yellow - medium / low - green
			String priorityColor = currentItem.priorityLevel == 3 ? "\u001b[31m" :
					currentItem.priorityLevel == 2 ? "\u001b[33m" :
							"\u001b[32m";

			System.out.println(priorityColor + "⚡ Priority: " + currentItem.priorityLevel + "\u001b[0m");


			// Completion status with icon of done o not
			String statusColor = currentItem.isComplete ? "\u001b[32m" : "\u001b[31m";
			String statusSymbol = currentItem.isComplete ? "✓" : "✗";
			System.out.println(statusColor + statusSymbol + " Status: " + (currentItem.isComplete ? "Complete" : "Pending") + "\u001b[0m");

			System.out.println(); //space between tasks
		}
		System.out.println("\u001b[1m" + "---------------" + "\u001b[0m");
	}




	//*                     CLASS TODO ITEM                 *//
	class TodoItem {
		private String name;
		private String description;
		private int priorityLevel;
		private boolean isComplete = false;
		private Priority priority;


		public TodoItem(String name, String description, Priority priority) {
			this.name = name;
			this.description = description;
			this.priorityLevel = priority.getPropertyLevel();
			this.priority = priority;
		}

		public void setComplete(boolean complete) {
			isComplete = complete;
		}

		public int getPriorityLevel() {
			return priorityLevel;
		}

		@Override
		public String toString() {
			//todo print all info
			return "Task name: " + this.name + "\n" + "Description: " + this.description + "\n" + "Priority level: " + this.priorityLevel + "\n" + "Completed: " + this.isComplete + "\n";
		}
	}
}
