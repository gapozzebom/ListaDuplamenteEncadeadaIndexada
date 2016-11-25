
import java.util.Random;

public class SkipList<T extends Comparable<T>, U> {
	//classe nó
	private class Node {
    public T key;
    public U value;
    public long level;
    public Node next;
    public Node down;
    
    public Node(T key, U value, long level, Node next, Node down) {
      this.key = key;
      this.value = value;
      this.level = level;
      this.next = next;
      this.down = down;
    }
  }
  
  private Node _head;
  private Random _random;
  private long _size;
  private double _p;
  
  //a função tem 50% de chance de criar mais um nível na estrutura, mas não pode ter mais níveis que o tamanho da lista
  private long _level() {
    long level = 0;
    while (level <= _size && _random.nextDouble() < _p) {
      level++;
    }
    
    return level;
  }
  
  //construtor
  public SkipList() {
    _head = new Node(null, null, 0, null, null);
    _random = new Random();
    _size = 0;
    _p = 0.5;
  }
  
  //função que adiciona um item a estrutura
  public void add(T key, U value) {
    //gera o número de níveis do elemento
	long level = _level();
	//se o nivel gerado for maior que o nível do head, será adicionado mais um nível no head
    if (level > _head.level) {
      _head = new Node(null, null, level, null, _head);
    }
    
    Node cur = _head;
    Node last = null;
    
	//navega na lista e insere o valor de forma ordenada
    while (cur != null) {
      if (cur.next == null || cur.next.key.compareTo(key) > 0) {
        if (level >= cur.level) {
          Node n = new Node(key, value, cur.level, cur.next, null);
          if (last != null) {
            last.down = n;
          }
          
          cur.next = n;
          last = n;
        }
        
        cur = cur.down;
        continue;
      } else if (cur.next.key.equals(key)) {
        cur.next.value = value;
        return;
      }
      
      cur = cur.next;
    }
    //aumenta o tamanho da lista
    _size++;
  }
  
  //verifica se existe valor no index passado
  public boolean containsKey(T key) {
    return get(key) != null;
  }
  
  //função que remove o valor do index passado
  public U remove(T key) {
    U value = null;
    
    Node cur = _head;
	//navega pela lista até encontrar um valor >= ao passado
    while (cur != null) {
      if (cur.next == null || cur.next.key.compareTo(key) >= 0) {
        if (cur.next != null && cur.next.key.equals(key)) {
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
  
  //função que retorna o valor associado ao index
  public U get(T key) {
    Node cur = _head;
    while (cur != null) {
      if (cur.next == null || cur.next.key.compareTo(key) > 0) {
        cur = cur.down;
        continue;
      } else if (cur.next.key.equals(key)) {
        return cur.next.value;
      }
      
      cur = cur.next;
    }
    
    return null;
  }
}