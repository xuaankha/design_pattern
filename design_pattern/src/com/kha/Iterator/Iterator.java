public interface Iterator<T> {
    boolean hasNext();
    T next();
}
@Component
public class ListIterator<T> implements Iterator<T> {
    private List<T> list;
    private int position = 0;

    public ListIterator(List<T> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return position < list.size();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        T item = list.get(position);
        position++;
        return item;
    }
}
@Autowired
private List<Data> dataList;

public void iterateList() {
    Iterator<Data> iterator = new ListIterator<>(dataList);
    while (iterator.hasNext()) {
        Data data = iterator.next();
        // Xử lý phần tử data
    }
}
@Component
public class DataCollection implements Iterable<Data> {
    @Autowired
    private List<Data> dataList;

    @Override
    public Iterator<Data> iterator() {
        return new ListIterator<>(dataList);
    }
}
@Autowired
private DataCollection dataCollection;

public void iterateCollection() {
    for (Data data : dataCollection) {
        // Xử lý phần tử data
    }
}