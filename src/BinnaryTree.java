public interface BinnaryTree<E> {
    boolean isEmpty();
    E root();
    BinnaryTree<E> left();
    BinnaryTree<E> right();
}
