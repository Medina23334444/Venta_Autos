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

// Busca un elemento en la LinkedList por su identificador.
    public E search(Object id) {
        // current es el nodo actual
        Nodo<E> current = head;

        while (current != null) {
            E element = current.getData();
            try {
                Integer elementId = (Integer) element.getClass().getMethod("getId").invoke(element);
                if (element != null && elementId.equals(id)) {
                    return element;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            current = current.getNext();
        }
        return null;
    }

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

    public Nodo<E> getNode(Integer post) throws VacioExpection {
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

//    public static void main(String[] args) throws VacioExpection {
//        LinkedList<Integer> numerics = new LinkedList<>();
//        for (int i = 0; i < 10; i++) {
//            Integer aux = (int) (Math.random() * 1000);
//            numerics.add(aux);
//        }
//
//        numerics.add(1, 1);
//
//        System.out.println(numerics.print());
//        System.out.println("-----------------------------------------------");
//        System.out.println("Tamanio de lista: " + numerics.getSize());
//        try {
//            System.out.println(numerics.getNode(1).getData().toString());
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
}
