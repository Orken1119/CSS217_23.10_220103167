import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

public class EnumerationIteratorAdapter<T> implements Iterator<T> {
    private final Enumeration<T> enumeration;

    public EnumerationIteratorAdapter(Enumeration<T> enumeration) {
        this.enumeration = enumeration;
    }

    @Override
    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }

    @Override
    public T next() {
        return enumeration.nextElement();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported");
    }

    public static void main(String[] args) {
        String studentId = "220103167";
        System.out.println("Student ID: " + studentId);

        Vector<String> vector = new Vector<>();
        vector.add(studentId);
        vector.add("Item 1");
        vector.add("Item 2");
        vector.add("Item 3");

        Enumeration<String> enumeration = vector.elements();
        Iterator<String> iter = new EnumerationIteratorAdapter<>(enumeration);

        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}
