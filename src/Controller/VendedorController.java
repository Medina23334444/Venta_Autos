/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Controller.TDAListas.Expection.VacioExpection;
import Controller.TDAListas.LinkedList;
import Controller.Venta_Autos.DataAccessObject;
import Model.Vendedor;

/**
 *
 * @author Usuario
 */
public class VendedorController extends DataAccessObject<Vendedor> {

    private Vendedor vendedor = new Vendedor();
    private LinkedList<Vendedor> listVendedor = new LinkedList<>();
    private Integer index = -1;

    public VendedorController() {
        super(Vendedor.class);
    }

    public Boolean saved() {
        return save(vendedor);
    }

    public Boolean update1(Integer i) {
        return update(vendedor, i);
    }

    private int busquedaBinaria1(LinkedList<Vendedor> lista, String text, String campo) throws VacioExpection {
        int infe = 0;
        int sup = lista.getSize() - 1;

        while (infe <= sup) {
            int indice = (infe + sup) / 2;
            Vendedor midVendedor = lista.get(indice);
            int resultado = midVendedor.comparar(midVendedor, text, campo);
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

    private boolean getForm(Vendedor vendedor, String text, String campo) {
        switch (campo.toLowerCase()) {
            case "correo":
                return vendedor.getCorreo().equalsIgnoreCase(text);
            case "dni":
                return vendedor.getDNI().equalsIgnoreCase(text);
            case "nombre":
                return vendedor.getNombre().equalsIgnoreCase(text);
            case "ruc":
                return vendedor.getRUC().equalsIgnoreCase(text);
            case "id":
                return Integer.toString(vendedor.getId()).equalsIgnoreCase(text);
            default:
                throw new IllegalArgumentException("Campo de comparación no válido");
        }
    }

    public Vendedor busquedaBinaria2(LinkedList<Vendedor> lista, String text, String campo, String tipo, Integer type) throws VacioExpection {
        LinkedList<Vendedor> listaOrdenada = ordenarLista(lista, campo, tipo);
        int index = busquedaBinaria1(listaOrdenada, text.toLowerCase(), campo);

        if (index != -1) {
            return listaOrdenada.get(index);
        } else {
            System.out.println("Elemento no encontrado");
            return null;
        }
    }

    public LinkedList<Vendedor> buscarLinealBinario(LinkedList<Vendedor> lista, String text, String campo, String tipo) throws VacioExpection {
        LinkedList<Vendedor> listaOrdenada = ordenarLista(lista, campo, tipo);
        LinkedList<Vendedor> result = new LinkedList<>();
        int infe = 0;
        int sup = listaOrdenada.getSize() - 1;
        int index = 0;
        while (infe <= sup && index < lista.getSize()) {
            Vendedor vendedorActual = lista.get(index);
            int resultado = vendedorActual.comparar(vendedorActual, text.toLowerCase(), campo);
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

    public LinkedList<Vendedor> ordenarLista(LinkedList<Vendedor> lista, String campo, String tipo) throws VacioExpection {
        LinkedList<Vendedor> listaOrdenada = new LinkedList<>();
        if (tipo.equalsIgnoreCase("MergeSort")) {
            listaOrdenada = mergeSort(lista, 0, campo);
        } else if (tipo.equalsIgnoreCase("QuickSort")) {
            listaOrdenada = quicksort(lista, 0, campo);
        }

        return listaOrdenada;
    }

    public int generatedId() {
        return generarId();
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

    /**
     * @return the listAutos
     */
    public LinkedList<Vendedor> getListAutos() {
        if (listVendedor.isEmpty()) {
            setListVendedor(listAll());
        }
        return listVendedor;
    }

    /**
     *
     * @return the vendedor
     */
    public Vendedor getVendedor() {
        //Verifica si el objeto Vendedor es nulo si no se crea otro objeto Vendedor
        if (vendedor == null) {
            vendedor = new Vendedor();
        }
        return vendedor;
    }

    /**
     * @param vendedor the vendedor to set
     */
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    /**
     * @param listVendedor the listVendedor to set
     */
    public void setListVendedor(LinkedList<Vendedor> listVendedor) {
        this.listVendedor = listVendedor;
    }

    public LinkedList<Vendedor> quicksort(LinkedList<Vendedor> lista, Integer type, String field) throws VacioExpection {

        Vendedor[] m = lista.toArray();
        quicksort(m, 0, m.length - 1, type, field);
        lista.toList(m);
        return lista;
    }

    private void quicksort(Vendedor[] m, int bajo, int alto, Integer type, String field) {
        if (bajo < alto) {
            int pivotIndex = particion(m, bajo, alto, type, field);
            quicksort(m, bajo, pivotIndex - 1, type, field);
            quicksort(m, pivotIndex + 1, alto, type, field);
        }
    }

    private int particion(Vendedor[] Venarray, int bajo, int alto, Integer type, String field) {
        Vendedor pivote = Venarray[alto];
        int i = bajo - 1;

        for (int j = bajo; j < alto; j++) {
            if (Venarray[j].comparar(pivote, field, type)) {
                i++;
               intercambio(Venarray, i, j);
            }
        }

        intercambio(Venarray, i + 1, alto);
        return i + 1;
    }

    private void intercambio(Vendedor[] Venarray, int i, int j) {
        Vendedor temp = Venarray[i];
        Venarray[i] = Venarray[j];
        Venarray[j] = temp;
    }

    public LinkedList<Vendedor> mergeSort(LinkedList<Vendedor> lista, Integer type, String field) throws VacioExpection {

        Vendedor[] m = lista.toArray();
        mergeSort(m, type, field);
        lista.toList(m);
        return lista;
    }

    private void mergeSort(Vendedor[] m, Integer type, String field) {
        int size = m.length;
        if (size <= 1) {
            return;
        }
        int mid = size / 2;
        Vendedor[] der = new Vendedor[mid];
        Vendedor[] izq = new Vendedor[size - mid];
        System.arraycopy(m, 0, der, 0, mid);
        System.arraycopy(m, mid, izq, 0, size - mid);
        mergeSort(der, type, field);
        mergeSort(izq, type, field);
        merge(m, der, izq, type, field);
    }

    private void merge(Vendedor[] result, Vendedor[] der, Vendedor[] izq, Integer type, String field) {
        int i = 0, j = 0, k = 0;

        while (i < der.length && j < izq.length) {
            if (der[i].comparar(izq[j], field, type)) {
                result[k++] = der[i++];
            } else {
                result[k++] = izq[j++];
            }
        }
        while (i < der.length) {
            result[k++] = der[i++];
        }
        while (j < izq.length) {
            result[k++] = izq[j++];
        }
    }

}
