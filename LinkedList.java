class LinkedList {
    Node head;
    public LinkedList() {
      this.head = null;}
  
    public void add(String data) {
      Node newNode = new Node(data);
      if (this.head == null) {
        this.head = newNode;} 
        else {
        Node current = this.head;
        while (current.next != null) {
          current = current.next;
        }
        current.next = newNode;}}
  
    public String[] toArray() {
      int size = 0;
      Node current = this.head;
      while (current != null) {
        size++;
        current = current.next;}
      String[] array = new String[size];
      int index = 0;
      current = this.head;
      while (current != null) {
        array[index] = current.data;
        index++;
        current = current.next;
      }return array;}

    public void print(){
      Node current = this.head;
      while (current != null) {
        System.out.println(current.data);
        current = current.next;
      }
    }
    }
  