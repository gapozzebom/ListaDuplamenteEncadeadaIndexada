import java.util.Random;

public class ListaEncadeada<T extends Comparable<T>> {
	
	//classe n�
	private class Node {
    public T value;
    public long level;
	public Node previous;
    public Node next;
    public Node down;
    
    public Node(T value, long level, Node next, Node down, Node previous) {
      this.value = value;
      this.level = level;
      this.next = next;
      this.down = down;
	  this.previous = previous;
    }
  }
  
  private Node _head;
  private Random _random;
  private long _size;
  private double _p;
  
  //a fun��o tem 50% de chance de criar mais um n�vel na estrutura, mas n�o pode ter mais n�veis que o tamanho da lista
  private long _level() {
    long level = 0;
    while (level <= _size && _random.nextDouble() < _p) {
      level++;
    }
    
    return level;
  }
  
  //construtor
  public ListaEncadeada() {
    _head = new Node( null, 0, null, null, null);
    _random = new Random();
    _size = 0;
    _p = 0.5;
  }
  
  //fun��o que adiciona um item a estrutura
  public void add(T value) {
    //gera o n�mero de n�veis do elemento
	long level = _level();
	//se o nivel gerado for maior que o n�vel do head, ser� adicionado mais um n�vel no head
    if (level > _head.level) {
      _head = new Node( null, level, null, _head, null);
    }
    
    Node cur = _head;
    Node last = null;
    
	//navega na lista e insere o valor de forma ordenada
    while (cur != null) {
      if (cur.next == null || cur.next.value.compareTo(value) > 0) {
        if (level >= cur.level) {
          Node n = new Node( value, cur.level, cur.next, null, cur);
          if (last != null) {
            last.down = n;
          }
          
          cur.next = n;
          last = n;
        }
        
        cur = cur.down;
        continue;
      } else if (cur.next.value.equals(value)) {
        cur.next.value = value;
        return;
      }
      
      cur = cur.next;
    }
    //aumenta o tamanho da lista
    _size++;
  }
  
  //verifica se existe valor no index passado
  public boolean containsKey(T value) {
    return get(value) != null;
  }
  
  //fun��o que remove o valor do index passado
  public T remove(T key) {
    T value = null;
    
    Node cur = _head;
	//navega pela lista at� encontrar um valor >= ao passado
    while (cur != null) {
      if (cur.next == null || cur.next.value.compareTo(value) >= 0) {
        if (cur.next != null && cur.next.value.equals(value)) {
          value = cur.next.value;
          cur.next = cur.next.next;
        }
        
        cur = cur.down;
        continue;
      }
      
      cur = cur.next;
    }
    //reduz o tamanho da lista
    _size--;
    return value;
  }
  
  //fun��o que retorna o valor associado ao index
  public T get(T key) {
    Node cur = _head;
    while (cur != null) {
      if (cur.next == null || cur.next.value.compareTo(key) > 0) {
        cur = cur.down;
        continue;
      } else if (cur.next.value.equals(key)) {
        return cur.next.value;
      }
      
      cur = cur.next;
    }
    
    return null;
  }
  

}