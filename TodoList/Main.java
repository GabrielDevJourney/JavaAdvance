public class Main {
	public static void main(String[] args) throws TodoListException {

		TodoList ola = new TodoList("eu");

		TodoList.TodoItem item1 = ola.new TodoItem("item1", "adeus", Priority.HIGH);
		TodoList.TodoItem item2 = ola.new TodoItem("item2", "aqui", Priority.MEDIUM);
		TodoList.TodoItem item3 = ola.new TodoItem("item3", "ty", Priority.HIGH);
		TodoList.TodoItem item4 = ola.new TodoItem("item4", "no", Priority.LOW);

		ola.addTask(item1);
		ola.addTask(item2);
		ola.addTask(item3);
		ola.addTask(item4);

		ola.markTaskComlete(item1);
		ola.markTaskComlete(item2);

		System.out.println(ola.getAllTasks());
		ola.removeTask(item4);
		System.out.println();
		System.out.println(ola.getAllTasks());
		System.out.println();
		ola.showDetails();


		//*EXCEPTION TESTS
		ola.addTask(null);

		TodoList.TodoItem nonExistentItem = ola.new TodoItem("non-existent", "test", Priority.LOW);
		ola.markTaskComlete(nonExistentItem);

		ola.removeTask(null);

	}
}
