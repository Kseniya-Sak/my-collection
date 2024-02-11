package my.collection;

public interface MyList<T> {
    boolean add(T element);
    T get(int index);
    T remove(int index);
    boolean addAll(MyList<? extends T> collection);
    int size();
    T[] toArray();
    @Override
    String toString();
    T set(int index, T newElement);

    public static <T extends Comparable<T>> void sort(MyList<T> list) {
        boolean flag = true;
        if (list.size() > 1) {
            T min = list.get(0);
            for (int i = 0; i < list.size(); i++) {
                for(int j = i + 1; j < list.size(); j++) {
                    T before = list.get(i);
                    T current = list.get(j);
                    if (before.compareTo(current) > 0) {
                        list.set(i, current);
                        list.set(j, before);
                        flag = false;
                    }
                }
                if (flag) {
                    return;
                }
                flag = true;
            }
        }
    }
}
