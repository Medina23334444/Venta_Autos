/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Controller.TDAListas.Expection.VacioExpection;
import Controller.TDAListas.LinkedList;
import Controller.Venta_Autos.DataAccessObject;
import Model.Venta;

/**
 *
 * @author Usuario
 */
public class VentaController extends DataAccessObject<Venta> {

    private Venta venta = new Venta();
    private LinkedList<Venta> listVentas = new LinkedList<>();
    private Integer index = -1;

    public VentaController() {
        super(Venta.class);
    }

    public Boolean saved() {
        return save(getVenta());
    }

    public Boolean update1(Integer i) {
        return update(getVenta(), i);
    }

    // Genera el identificador necesario para cada objeto Venta
    // retorna un entero
    public int generatedId() {
        return generarId();
    }

    /**
     *
     * @return the venta
     */
    public Venta getVenta() {
        //Verifica si el objeto  Venta es nulo si no se crea otro objeto Venta
        if (venta == null) {
            venta = new Venta();
        }
        return venta;
    }

    /**
     * @param venta the venta to set
     */
    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    /**
     * @return the listVentas
     */
    public LinkedList<Venta> getListVentas() {
        if (listVentas.isEmpty()) {
            setListVentas(listAll());
        }
        return listVentas;
    }

    /**
     * @param listVentas the listVentas to set
     */
    public void setListVentas(LinkedList<Venta> listVentas) {
        this.listVentas = listVentas;
    }

    /**
     * @return the index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    //Genera un nuevo código para identificar un elemento.
    public String generatedCode() {
        StringBuilder code = new StringBuilder();
        // Se obtiene la longitud actual de la lista y se incrementa en uno
        Integer length = listAll().getSize() + 1;
        Integer pos = Integer.toString(length).length();
        for (int i = 0; i < (10 - pos); i++) {
            code.append("0");
        }
        code.append(length.toString());
        // Se devuelve el código como una cadena de texto
        return code.toString();
    }

    public LinkedList<Venta> quicksort(LinkedList<Venta> lista, Integer type, String field) throws VacioExpection {

        Venta[] m = lista.toArray();
        quicksort(m, 0, m.length - 1, type, field);
        lista.toList(m);
        return lista;
    }

    private void quicksort(Venta[] m, int bajo, int alto, Integer type, String field) {
        if (bajo < alto) {
            int pivotIndex = partition(m, bajo, alto, type, field);
            quicksort(m, bajo, pivotIndex - 1, type, field);
            quicksort(m, pivotIndex + 1, alto, type, field);
        }
    }

    private int partition(Venta[] array, int bajo, int alto, Integer type, String field) {
        Venta pivote = array[alto];
        int i = bajo - 1;

        for (int j = bajo; j < alto; j++) {
            if (array[j].comparar(pivote, field, type)) {
                i++;
                intercambio(array, i, j);
            }
        }

        intercambio(array, i + 1, alto);
        return i + 1;
    }

    private void intercambio(Venta[] Ventarray, int i, int j) {
        Venta temp = Ventarray[i];
        Ventarray[i] = Ventarray[j];
        Ventarray[j] = temp;
    }

    public LinkedList<Venta> mergeSort(LinkedList<Venta> lista, Integer type, String field) throws VacioExpection {
        Venta[] m = lista.toArray();
        mergeSort(m, type, field);
        lista.toList(m);
        return lista;
    }

    private void mergeSort(Venta[] m, Integer type, String field) {
        int size = m.length;
        if (size <= 1) {
            return;
        }
        int medio = size / 2;
        Venta[] der = new Venta[medio];
        Venta[] izq = new Venta[size - medio];
        System.arraycopy(m, 0, der, 0, medio);
        System.arraycopy(m, medio, izq, 0, size - medio);
        mergeSort(der, type, field);
        mergeSort(izq, type, field);
        merge(m, der, izq, type, field);
    }

    private void merge(Venta[] resultado, Venta[] der, Venta[] izq, Integer type, String field) {
        int i = 0, j = 0, k = 0;

        while (i < der.length && j < izq.length) {
            if (der[i].comparar(izq[j], field, type)) {
                resultado[k++] = der[i++];
            } else {
                resultado[k++] = izq[j++];
            }
        }
        while (i < der.length) {
            resultado[k++] = der[i++];
        }
        while (j < izq.length) {
            resultado[k++] = izq[j++];
        }
    }

    public LinkedList<Venta> busquedaBinaria(LinkedList<Venta> lista, String text, String campo, String tipo, Integer type) throws VacioExpection {
        LinkedList<Venta> listaOrdenada = ordenarLista(lista, campo, tipo);
        LinkedList<Venta> marc = new LinkedList<>();
        int index = busquedaBinaria1(listaOrdenada, text.toLowerCase(), campo);
        if (index != -1) {
            while (index < listaOrdenada.getSize() && getForm(listaOrdenada.get(index), text, campo)) {
                marc.add(listaOrdenada.get(index));
                index++;
            }

        } else {
            System.out.println("Elemento no encontrado");
        }

        return marc;
    }

    private int busquedaBinaria1(LinkedList<Venta> lista, String text, String campo) throws VacioExpection {
        int infe = 0;
        int sup = lista.getSize() - 1;

        while (infe <= sup) {
            int indice = (infe + sup) / 2;
            Venta midVenta = lista.get(indice);
            int resultado = midVenta.comparar(midVenta, text, campo);
            if (resultado == 0) {
                int izquierda = indice - 1;
                while (izquierda >= 0 && getForm(lista.get(izquierda), text, campo)) {
                    indice = izquierda;
                    izquierda--;
                }
                return indice;
            } else if (resultado < 0) {
                sup = indice - 1;
            } else {
                infe = indice + 1;
            }
        }

        return -1;
    }

    private boolean getForm(Venta venta, String text, String campo) {
        switch (campo.toLowerCase()) {
            case "fecha":
                return venta.getFecha().equalsIgnoreCase(text);
            case "nroventa":
                return venta.getNroVenta().equalsIgnoreCase(text);
            case "nombre":
                return Double.toString(venta.getValorVenta()).equalsIgnoreCase(text);
            case "id":
                return Integer.toString(venta.getId()).equalsIgnoreCase(text);
            default:
                throw new IllegalArgumentException("Campo de comparación no válido");
        }
    }

    public LinkedList<Venta> buscarlinealBinario(LinkedList<Venta> lista, String text, String campo, String tipo) throws VacioExpection {
        LinkedList<Venta> listaOrdenada = ordenarLista(lista, campo, tipo);
        LinkedList<Venta> result = new LinkedList<>();
        int infe = 0;
        int sup = listaOrdenada.getSize() - 1;
        int index = 0;
        while (infe <= sup && index < lista.getSize()) {
            Venta ventaActual = lista.get(index);
            int resultado = ventaActual.comparar(ventaActual, text.toLowerCase(), campo);
            if (resultado == 0) {
                int izquierda = index - 1;
                while (izquierda >= 0 && getForm(lista.get(izquierda), text, campo)) {
                    index = izquierda;
                    izquierda--;
                }
                while (index < lista.getSize() && getForm(lista.get(index), text, campo)) {
                    result.add(lista.get(index));
                    index++;
                }
                return result;
            } else if (resultado < 0) {
                sup = index - 1;
            } else {
                infe = index + 1;
            }

            index++;
        }
        return result;
    }

    private LinkedList<Venta> ordenarLista(LinkedList<Venta> lista, String campo, String tipo) throws VacioExpection {
        LinkedList<Venta> listaOrdenada = new LinkedList<>();
        if (tipo.equalsIgnoreCase("MergeSort")) {
            listaOrdenada = mergeSort(lista, 0, campo);
        } else if (tipo.equalsIgnoreCase("QuickSort")) {
            listaOrdenada = quicksort(lista, 0, campo);
        }

        return listaOrdenada;
    }

}
