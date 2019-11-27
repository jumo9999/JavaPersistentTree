public class LinkedStack<E> implements Stack<E> {
    private Node<E> top;
    private static class Node<E>{
        private E elem;
        private Node<E> next;
        public Node(E elem, Node<E> next){
            this.elem = elem;
            this.next = next;
        }
    }
    public LinkedStack(){
        this.top = null;
    }

    @Override
    public boolean isEmpty(){
        return this.top==null;
    }
    @Override
    public E top(){
        if(this.isEmpty()){
            throw new NullPointerException();
        }
        return this.top.elem;
    }
    @Override
    public void pop(){
        if(this.isEmpty()){
            throw new NullPointerException();
        }
        this.top = this.top.next;
    }
    @Override
    public void push(E elem){
        this.top = new Node<>(elem, this.top);

    }
}
