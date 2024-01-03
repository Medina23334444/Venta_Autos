package Controller;

import Controller.TDAListas.Expection.VacioExpection;
import Controller.TDAListas.LinkedList;
import Controller.Venta_Autos.DataAccessObject;
import Model.Marca;

public class MarcaController extends DataAccessObject<Marca> {

    private LinkedList<Marca> marcas = new LinkedList<>();
    private Marca marca;

    public MarcaController() {
        super(Marca.class);
    }

    public LinkedList<Marca> quicksort(LinkedList<Marca> lista, Integer type, String field) throws VacioExpection {

        Marca[] m = lista.toArray();
        quicksort(m, 0, m.length - 1, type, field);
        lista.toList(m);
        return lista;
    }

    private void quicksort(Marca[] m, int low, int high, Integer type, String field) {
        if (low < high) {
            int pivotIndex = partition(m, low, high, type, field);
            quicksort(m, low, pivotIndex - 1, type, field);
            quicksort(m, pivotIndex + 1, high, type, field);
        }
    }

    private int partition(Marca[] array, int low, int high, Integer type, String field) {
        Marca pivote = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j].comparar(pivote, field, type)) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, high);
        return i + 1;
    }

    private void swap(Marca[] array, int i, int j) {
        Marca temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public LinkedList<Marca> mergeSort(LinkedList<Marca> lista, Integer type, String field) throws VacioExpection {

        Marca[] m = lista.toArray();
        mergeSort(m, type, field);
        lista.toList(m);

        return lista;
    }

    private void mergeSort(Marca[] m, Integer type, String field) {
        int size = m.length;
        if (size <= 1) {
            return;
        }
        int mid = size / 2;
        Marca[] left = new Marca[mid];
        Marca[] right = new Marca[size - mid];
        System.arraycopy(m, 0, left, 0, mid);
        System.arraycopy(m, mid, right, 0, size - mid);
        mergeSort(left, type, field);
        mergeSort(right, type, field);
        merge(m, left, right, type, field);
    }

    private void merge(Marca[] result, Marca[] left, Marca[] right, Integer type, String field) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i].comparar(right[j], field, type)) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }
        while (i < left.length) {
            result[k++] = left[i++];
        }
        while (j < right.length) {
            result[k++] = right[j++];
        }
    }

    public LinkedList<Marca> busquedaBinaria(LinkedList<Marca> lista, String text, String campo, String tipo, Integer type) throws VacioExpection {
        LinkedList<Marca> listaOrdenada = ordenarLista(lista, campo, tipo);
        LinkedList<Marca> marc = new LinkedList<>();
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

    private int busquedaBinaria1(LinkedList<Marca> lista, String text, String campo) throws VacioExpection {
        int infe = 0;
        int sup = lista.getSize() - 1;

        while (infe <= sup) {
            int indice = (infe + sup) / 2;
            Marca midMarca = lista.get(indice);
            int resultado = midMarca.comparar(midMarca, text, campo);
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

    private boolean getForm(Marca marca, String text, String campo) {
        switch (campo.toLowerCase()) {
            case "nombre":
                return marca.getNombre().equalsIgnoreCase(text);
            case "id":
                return Integer.toString(marca.getId()).equalsIgnoreCase(text);
            default:
                throw new IllegalArgumentException("Campo de comparación no válido");
        }
    }

    public LinkedList<Marca> buscarBinarioLineal(LinkedList<Marca> lista, String text, String campo, String tipo) throws VacioExpection {
        LinkedList<Marca> listaOrdenada = ordenarLista(lista, campo, tipo);
        LinkedList<Marca> result = new LinkedList<>();
        int infe = 0;
        int sup = listaOrdenada.getSize() - 1;
        int index = 0;
        while (infe <= sup && index < lista.getSize()) {
            Marca marcaActual = lista.get(index);
            int resultado = marcaActual.comparar(marcaActual, text.toLowerCase(), campo);
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

    
    private LinkedList<Marca> ordenarLista(LinkedList<Marca> lista, String campo, String tipo) throws VacioExpection {
    LinkedList<Marca> listaOrdenada = new LinkedList<>();
    
    if (tipo.equalsIgnoreCase("MergeSort")) {
        listaOrdenada = mergeSort(lista, 0, campo);
    } else if (tipo.equalsIgnoreCase("QuickSort")) {
        listaOrdenada = quicksort(lista, 0, campo);
    }

    return listaOrdenada;
}
    /**
     * @return the marcas
     */
    public LinkedList<Marca> getMarcas() {
        if (marcas.isEmpty()) {
            marcas = this.listAll();
        }
        return marcas;
    }

    /**
     * @param marcas the marcas to set
     */
    public void setMarcas(LinkedList<Marca> marcas) {
        this.marcas = marcas;
    }

    /**
     * @return the marca
     */
    public Marca getMarca() {
        //Verifica si el objeto Marca es nulo si es asi crea un objeto Marca
        if (marca == null) {
            marca = new Marca();
        }
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Boolean save() {
        this.marca.setId(generarId());
        return this.save(marca);
    }

// Realizamos un main paar guardar las marcas
    public static void main(String[] args) throws VacioExpection {
        MarcaController mc = new MarcaController();
////        System.out.println(mc.quicksort(mc.listAll(), 1, "id").print());
//        //  System.out.println(mc.mergeSort(mc.listAll(), 0, "nombre").print());
//        //LinkedList<Marca> dm = mc.quicksort(mc.listAll(), 1, "id");
//        System.out.println(mc.buscarUnificado(mc.listAll(), "A", "nombre", "mergesort"));
//    }
    }
}
