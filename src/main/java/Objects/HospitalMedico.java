package Objects;

public class HospitalMedico {
    private int id_Hospital;
    private int cedula;

    public int getId_Hospital() {
        return id_Hospital;
    }

    public void setId_Hospital(int id_Hospital) {
        this.id_Hospital = id_Hospital;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    @Override
    public String toString() {
        return "Hospital-Medico" +
                "\nId Hospital:" + id_Hospital +
                "\nCédula" + cedula;
    }

    //Método pendiente de revisión
    public String toString2() {
        return "Hospital-Medico" +
                "\nId Hospital:" + id_Hospital +
                "\nCédula" + cedula;
    }
}
