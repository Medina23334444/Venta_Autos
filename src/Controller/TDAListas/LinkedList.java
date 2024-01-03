/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.TDAListas;

import Controller.TDAListas.Expection.VacioExpection;

/**
 *
 * @author Usuario
 * @param <E>
 */
public class LinkedList<E> {

    private Nodo<E> head;
    private Nodo<E> last;
    private Integer size;

    public LinkedList() {
        head = null;
        last = null;
        size = 0;
    }

    public E[] toArray() {
        Class clazz = null;
        E[] matriz = null;
        if (this.size > 0) {
            clazz = head.getData().getClass();
            matriz = (E[]) java.lang.reflect.Array.newInstance(clazz, size);
            Nodo<E> aux = head;
            for (int i = 0; i < size; i++) {
                matriz[i] = aux.getData();
                aux = aux.getNext();

            }
        }
        return matriz;
    }

    public LinkedList<E> toList(E[] m) {
        clear();
        for (int i = 0; i < m.length; i++) {
            this.add(m[i]);
        }
        return this;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    // Metodos
    public Boolean isEmpty() {
        return head == null || size == 0;
    }

    protected void addFist(E data) {
        if (isEmpty()) {
            Nodo<E> aux = new Nodo<>(data, null);
            head = aux;
            last = aux;
        } else {
            Nodo<E> headOld = head;
            Nodo<E> aux = new Nodo<>(data, headOld);
            head = aux;
        }
        size++;
    }

    protected void addLast(E data) {
        if (isEmpty()) {
            addFist(data);
        } else {
            Nodo<E> aux = new Nodo<>(data, null);
            last.setNext(aux);
            last = aux;
            size++;
        }

    }

    public void add(E data) {
        addLast(data);
    }

    public void add(E data, Integer post) throws VacioExpection {
        if (post == 0) {
            addFist(data);
        } else if (post == size - 1) {
            addLast(data);
        } else {
            Nodo<E> search_preview = getNode(post - 1);
            Nodo<E> search = getNode(post);
            Nodo<E> aux = new Nodo<>(data, search);
            search_preview.setNext(aux);
            size++;
        }
    }

    public E get(Integer index) throws VacioExpection {
        if (isEmpty()) {
            throw new VacioExpection("Error, Lista vacia");
        } else if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Error, Esta fuera del limite de la lista");
        } else if (index == 0) {
            return getFirst();
        } else if (index == (size - 1)) {
            return getLast();
        } else {
            Nodo<E> search = getNode(index);
            return search.getData();
        }
    }

    public E search(E data) {
        Nodo<E> current = head;
        while (current != null) {
            if (current.getData().equals(data)) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

//    public E get(Integer index) throws VacioExpection {
//        if (isEmpty()) {
//            throw new VacioExpection("LLLL");
//        } else if (index.intValue() < 0 || index.intValue() >= size) {
//            throw new IndexOutOfBoundsException("mmmm");
//        } else if (index.intValue() == 0) {
//            return getFirst();
//        } else if (index.intValue() == (size - 1)) {
//            return getLast();
//        } else {
//            Nodo<E> search = getNode(index);
//            return search.getData();
//        }
//    }
    public void update(E data, Integer post) throws VacioExpection {
        if (isEmpty()) {
            throw new VacioExpection("Error, Lista vacia");
        } else if (post < 0 || post >= size) {
            throw new IndexOutOfBoundsException("Error, Esta fuera del limite de la lista");
        } else if (post == 0) {
            head.setData(data);
        } else if (post == (size - 1)) {
            last.setData(data);
        } else {
            Nodo<E> search = head;
            Integer cont = 0;
            while (cont < post) {
                cont++;
                search = search.getNext();
            }
            search.setData(data);
        }
    }

    private Nodo<E> getNode(Integer post) throws VacioExpection {
        if (isEmpty()) {
            throw new VacioExpection("Error, Lista vacia");
        } else if (post < 0 || post >= size) {
            throw new IndexOutOfBoundsException("Error, Esta fuera del limite de la lista");
        } else if (post == 0) {
            return head;
        } else if (post == (size - 1)) {
            return last;
        } else {
            Nodo<E> search = head;
            Integer cont = 0;
            while (cont < post) {
                cont++;
                search = search.getNext();
            }
            return search;
        }
    }

    public E getFirst() throws VacioExpection {

        if (isEmpty()) {
            throw new VacioExpection("Lista vacia");
        } else {
            return head.getData();
        }

    }

    public E getLast() throws VacioExpection {

        if (isEmpty()) {
            throw new VacioExpection("Lista vacia");
        } else {
            return last.getData();
        }

    }

    /// Revisar este metodo
    public void clear() {
        head = null;
        last = null;
        size = 0;
    }

    public E deleteFirst() throws VacioExpection {
        if (isEmpty()) {
            throw new VacioExpection("Lista Vacia");
        } else {
            E element = head.getData();
            Nodo<E> aux = head.getNext();
            head = aux;
            if (size.intValue() == 1) {
                last = null;
            }

            size--;
            return element;
        }

    }

    public E deleteLast() throws VacioExpection {
        if (isEmpty()) {
            throw new VacioExpection("Lista Vacia");
        } else {
            E element = head.getData();
            Nodo<E> aux = getNode(size - 2);
            if (aux == null) {
                last = null;
                if (size == 2) {
                    last = head;
                } else {
                    head = null;
                }
            } else {
                last = null;
                head = aux;
                last.setNext(null);
            }

            size--;
            return element;
        }

    }

    public E delete(Integer post) throws VacioExpection {
        if (isEmpty()) {
            throw new VacioExpection("Error, Lista vacia");
        } else if (post < 0 || post >= size) {
            throw new IndexOutOfBoundsException("Error, Esta fuera del limite de la lista");
        } else if (post == 0) {
            return deleteFirst();
        } else if (post == (size - 1)) {
            return deleteLast();
        } else {
            Nodo<E> preview = getNode(post - 1);
            Nodo<E> actually = getNode(post);
            E element = preview.getData();
            Nodo<E> next = actually.getNext();
            actually = null;
            preview.setNext(next);
            size--;
            return element;
        }
    }

    public static void imprimir(Integer[] m) {
        System.out.println("******************");
        System.out.print("\t");
        for (int i = 0; i < m.length; i++) {
            System.out.print(m[i] + "\t");
        }
        System.out.println();
        System.out.println("******************");
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        if (isEmpty()) {
            sb.append(":Lista vacia");
        } else {
            Nodo<E> aux = head;
            while (aux != null) {
                sb.append(aux.getData().toString()).append("\n");
                aux = aux.getNext();
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return print();
    }

    public static void main(String[] args) throws VacioExpection {
        Integer[] in = new Integer[50];
        Integer[] sac = new Integer[50];
        Integer[] se = new Integer[50];
        Integer[] m = new Integer[50];
        for (int i = 0; i < m.length; i++) {
            Integer aux = (int) (Math.random() * 100);
            m[i] = aux;
            in[i] = aux;
            se[i] = aux;
            sac[i] = aux;

        }
        LinkedList.imprimir(m);
        // Burbuja
        Integer intercambios = 0;
        for (int i = 1; i < m.length - 1; i++) {
            for (int j = m.length - 1; j > 0; j--) {
                if (m[j - 1] > m[j]) {
                    Integer aux = m[j - 1];
                    m[j - 1] = m[j];
                    m[j] = aux;
                    intercambios++;
                }

            }

        }
        LinkedList.imprimir(m);
        System.out.println("Intecambios " + intercambios);
        // Insercion

        System.out.println("INSERCION");
        LinkedList.imprimir(in);
        Integer n = in.length;
        Integer intercambioIns = 0;
        for (int i = 1; i < n; i++) {
            int j = i - 1;
            Integer t = in[i];
            while (j >= 0 && t < in[j]) {
                in[j + 1] = in[j];
                j = j - 1;
                intercambioIns++;
            }
            in[j + 1] = t;
        }
        LinkedList.imprimir(in);
        System.out.println("Intercambios " + intercambioIns);

        // seleccion
        System.out.println("SELECCION");
        Integer intercambioSe = 0;
        LinkedList.imprimir(se);

        for (int i = 0; i < n - 1; i++) {
            int k = i;
            int t = se[i];
            for (int j = i + 1; j < n; j++) {
                if (se[j] < t) {
                    t = se[j];
                    k = j;
                    intercambioSe++;
                }
            }
            se[k] = se[i];
            se[i] = t;
        }

        LinkedList.imprimir(se);
        System.out.println("Intercambios " + intercambioSe);

        // sacudida
        System.out.println("Sacudida");
        Integer interSac = 0;
        LinkedList.imprimir(sac);
        int i, j, der, izq, aux = 0;
        izq = 1;
        der = n - 1;
        j = n - 1;

        do {
            for (i = der; i >= izq; i--) {
                if (sac[i - 1] > sac[i]) {
                    aux = sac[i];
                    sac[i] = sac[i - 1];
                    sac[i - 1] = aux;
                    j = i;
                    interSac++;
                }
            }
            izq = j + 1;
            for (i = izq; i <= der; i++) {
                if (sac[i - 1] > sac[i]) {
                    aux = sac[i];
                    sac[i] = sac[i - 1];
                    sac[i - 1] = aux;
                    j = i;
                    interSac++;
                }
                der = j - 1;
            }
        } while (izq <= der);
        LinkedList.imprimir(sac);
        System.out.println("Inserciones" + interSac);
    }

}
