public class Main {
	public static void main(String[] args) {
		WordHistogram histogram = new WordHistogram();
		histogram.analizeString("O Lorem a e Ipsum O qwe Lorem Ipsum O Loremqwe Ipsum O Lqworem qwe Ipsum O Lorem " +
				"Ipsum ");
		System.out.println();
		histogram.get("um");
	}
}
