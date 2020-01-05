import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayCollection<T> implements Collection<T> {

    private T[] m = (T[]) new Object[1];

    private int size;

    @Override
    public int size() {
        // BEGIN (write your solution here)
        return size;
        // END
    }

    @Override
    public boolean isEmpty() {
        // BEGIN (write your solution here)
        return this.size() == 0;
        // END
    }

    @Override
    public boolean contains(final Object o) {
        // BEGIN (write your solution here)
        int i = 0;
        
        while (size > i) {
            if (m[i++].equals(o)) return true;
        }
        
        return false;
        // END
    }

    @Override
    public Iterator<T> iterator() {
        // BEGIN (write your solution here)
        return new ElementsIterator();
        // END
    }

    @Override
    public Object[] toArray() {
        // BEGIN (write your solution here)
        return Arrays.copyOf(m, size);
        // END
    }

    @Override
    /*This method may prove to be too difficult.
    he test is not covered.*/
    public <T1> T1[] toArray(T1[] a) {
        // BEGIN (write your solution here)
        a = (T1[]) Arrays.copyOf(m, size, a.getClass());

        return a;
        // END
    }

    @Override
    public boolean add(final T t) {
        // BEGIN (write your solution here)
        if (m.length == size) {
            m = Arrays.copyOf(m, size * 2);
            m[size++] = t;
        } else m[size++] = t;

        return true;
        // END
    }

    @Override
    public boolean remove(final Object o) {
        // BEGIN (write your solution here)
        if (!this.contains(o)) return false;

        int i = size - 1;

        while (i > 0) {
            if (m[i].equals(o)) break;
            i--;
        }

        int k = 0;

        for (int j = 0; j < size; j++) {
            if (i == j) continue;
            m[k++] = m[j];
        }

        size--;

        return true;
        // END
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        // BEGIN (write your solution here)
        for (final Object elem : c) {
            if (!this.contains(elem)) return false;
        }

        return true;
        // END
    }

    @Override
    public boolean addAll(final Collection<? extends T> c) {
        // BEGIN (write your solution here)
        for (final T t : c) {
            this.add(t);
        }

        return true;
        // END
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        // BEGIN (write your solution here)
        for (final Object elem : c) {
            this.remove(elem);
        }

        return true;
        // END
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        // BEGIN (write your solution here)
        final Collection<T> toRemove = new ArrayCollection<>();

        for (final T elem : this) {
            if (!c.contains(elem)) toRemove.add(elem);
        }

        this.removeAll(toRemove);

        return toRemove.size() > 0; 
        // END
    }

    @Override
    public void clear() {
        // BEGIN (write your solution here)
        m = (T[]) new Object[1];

        size = 0;
        // END
    }

    private class ElementsIterator implements Iterator<T> {
        // BEGIN (write your solution here)
        private int i = 0;

        @Override
        public T next() {
            if (!hasNext()) {throw new NoSuchElementException();}
            return ArrayCollection.this.m[i++];
        }
        
        @Override
        public boolean hasNext() {
            return ArrayCollection.this.size() > i;
        }
        // END
    }
}
