
public class testa {

	public static void main(String[] args) {
		ListaEncadeada<String> le = new ListaEncadeada<>();
		le.add("Primeiro");
		le.add("Dois");
		le.add("3");

		System.out.println(le.containsKey("Primeiro"));
		System.out.println(le.containsKey("Primeir"));
		System.out.println(le.containsKey("3"));
	}

}
