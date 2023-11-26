package Controller;

import Controller.TDAListas.LinkedList;
import Controller.Venta_Autos.DataAccessObject;
import Model.Marca;

public class MarcaController extends DataAccessObject<Marca> {

    private LinkedList<Marca> marcas = new LinkedList<>();
    private Marca marca;

    public MarcaController() {
        super(Marca.class);
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
    public static void main(String[] args) {
        // Se instancia un controlador de marcas
        MarcaController marcaController = new MarcaController();
        int nuevoId = marcaController.generarId();
        // Se establecen los atributos de la marca
        marcaController.getMarca().setId(nuevoId);
        marcaController.getMarca().setNombre("Porge");
        marcaController.getMarca().setEstado(true);
        // Se guarda la marca en la base de datos
        marcaController.save();
    }
}
