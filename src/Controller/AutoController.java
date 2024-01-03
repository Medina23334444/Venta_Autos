/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Controller.TDAListas.Expection.VacioExpection;
import Controller.TDAListas.LinkedList;
import Controller.Venta_Autos.DataAccessObject;
import Model.Auto;

/**
 *
 * @author Usuario
 */
public class AutoController extends DataAccessObject<Auto> {

    private Auto auto = new Auto();
    private LinkedList<Auto> listAutos = new LinkedList<>();
    private Integer index = -1;

    public AutoController() {
        super(Auto.class);
    }

    /**
     * @return the auto
     */
    public Auto getAuto() {
        //Verifica si el objeto Auto es nulo si no se crea otro objeto Auto
        if (auto == null) {
            auto = new Auto();
        }
        return auto;
    }

    /**
     * @param auto the auto to set
     */
    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public Boolean saved() {
        return save(auto);
    }

    public LinkedList<Auto> busquedaBinaria(LinkedList<Auto> lista, String text, String campo, String tipo, Integer type) throws VacioExpection {
        LinkedList<Auto> listaOrdenada = new LinkedList<>();
        if (tipo.equalsIgnoreCase("MergeSort")) {
            listaOrdenada = mergeSort(lista, 0, campo);
        } else if (tipo.equalsIgnoreCase("QuickSort")) {
            listaOrdenada = quicksort(lista, 0, campo);
        }
        LinkedList<Auto> marc = new LinkedList<>();
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

    public Auto busquedaBinaria2(LinkedList<Auto> lista, String text, String campo, String tipo, Integer type) throws VacioExpection {
        LinkedList<Auto> listaOrdenada = ordenarLista(lista, campo, tipo);
        int index = busquedaBinaria1(listaOrdenada, text.toLowerCase(), campo);
        if (index != -1) {
            return listaOrdenada.get(index);
        } else {
            System.out.println("Elemento no encontrado");
            return null;
        }
    }

    private int busquedaBinaria1(LinkedList<Auto> lista, String text, String campo) throws VacioExpection {
        int infe = 0;
        int sup = lista.getSize() - 1;

        while (infe <= sup) {
            int indice = (infe + sup) / 2;
            Auto midAuto = lista.get(indice);
            int resultado = midAuto.comparar(midAuto, text, campo);
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

    private boolean getForm(Auto auto, String text, String campo) {
        switch (campo.toLowerCase()) {
            case "modelo":
                return auto.getModelo().equalsIgnoreCase(text);
            case "color":
                return auto.getColor().equalsIgnoreCase(text);
            case "precio":
                return Double.toString(auto.getPrecio()).equalsIgnoreCase(text);
            case "descripcion":
                return auto.getDescripcion().equalsIgnoreCase(text);
            case "id":
                return Integer.toString(auto.getId()).equalsIgnoreCase(text);
            default:
                throw new IllegalArgumentException("Campo de comparación no válido");
        }
    }
    
      public LinkedList<Auto> buscarBinarioLineal(LinkedList<Auto> lista, String text, String campo, String tipo) throws VacioExpection {
        LinkedList<Auto> listaOrdenada = ordenarLista(lista, campo, tipo);
        LinkedList<Auto> result = new LinkedList<>();
        int infe = 0;
        int sup = listaOrdenada.getSize() - 1;
        int index = 0;
        while (infe <= sup && index < lista.getSize()) {
            Auto autoActual = lista.get(index);
            int resultado = autoActual.comparar(autoActual, text.toLowerCase(), campo);
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

    private LinkedList<Auto> ordenarLista(LinkedList<Auto> lista, String campo, String tipo) throws VacioExpection {
        LinkedList<Auto> listaOrdenada = new LinkedList<>();

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

    public Boolean update1(Integer i) {
        return update(auto, i);
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
    public LinkedList<Auto> getListAutos() {
        if (listAutos.isEmpty()) {
            setListAutos(listAll());
        }
        return listAutos;
    }

    /**
     * @param listAutos the listAutos to set
     */
    public void setListAutos(LinkedList<Auto> listAutos) {
        this.listAutos = listAutos;
    }

    public LinkedList<Auto> quicksort(LinkedList<Auto> lista, Integer type, String field) throws VacioExpection {

        Auto[] m = lista.toArray();
        quicksort(m, 0, m.length - 1, type, field);
        lista.toList(m);
        return lista;
    }

    private void quicksort(Auto[] m, int bajo, int alto, Integer type, String field) {
        if (bajo < alto) {
            int pivoteIn = particion(m, bajo, alto, type, field);
            quicksort(m, bajo, pivoteIn - 1, type, field);
            quicksort(m, pivoteIn + 1, alto, type, field);
        }
    }

    private int particion(Auto[] Autoarray, int bajo, int alto, Integer type, String field) {
        Auto pivote = Autoarray[alto];
        int i = bajo - 1;
        for (int j = bajo; j < alto; j++) {
            if (Autoarray[j].comparar(pivote, field, type)) {
                i++;
                intercambiar(Autoarray, i, j);
            }
        }
        intercambiar(Autoarray, i + 1, alto);
        return i + 1;
    }

    private void intercambiar(Auto[] Autoarray, int i, int j) {
        Auto temp = Autoarray[i];
        Autoarray[i] = Autoarray[j];
        Autoarray[j] = temp;
    }

    public LinkedList<Auto> mergeSort(LinkedList<Auto> lista, Integer type, String field) throws VacioExpection {
        Auto[] m = lista.toArray();
        mergeSort(m, type, field);
        lista.toList(m);
        return lista;
    }

    private void mergeSort(Auto[] m, Integer type, String field) {
        int size = m.length;
        if (size <= 1) {
            return;
        }
        int medio = size / 2;
        Auto[] der= new Auto[medio];
        Auto[] izq = new Auto[size - medio];

        System.arraycopy(m, 0, der, 0, medio);
        System.arraycopy(m, medio, izq, 0, size - medio);
        mergeSort(der, type, field);
        mergeSort(izq, type, field);
        merge(m, der, izq, type, field);
    }

    private void merge(Auto[] resultado, Auto[] der, Auto[] izq, Integer type, String field) {
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
}
