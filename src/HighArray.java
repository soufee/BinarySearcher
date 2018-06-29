import java.util.Arrays;

public class HighArray<T> {
    private T[] elements;
    private int nElems;

    public int getSize() {
        return nElems;
    }

    public HighArray() {
        elements = (T[]) new Object[16];
        nElems = 0;
    }

    public int find(T searchKey) {
        int i;
        for (i = 0; i < nElems; i++)
            if (elements[i] == searchKey) break;
        if (i == nElems) return -1;
        else return i;
    }

    public void insert(T value) {
        elements[nElems] = value;
        nElems++;
        if (nElems == elements.length) {
            T[] newArray = (T[]) new Object[elements.length * 2];
            for (int i = 0; i < elements.length; i++) {
                newArray[i] = elements[i];
            }
            elements = newArray;
        }
    }

    public boolean delete(T value) {
        int index = find(value);
        if (index != -1) {
            System.arraycopy(elements, index + 1, elements, index, nElems - index);
            nElems--;
            return true;
        } else return false;
    }

    public void display() {
        for (int i = 0; i < nElems; i++) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }

}

