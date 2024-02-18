package Objects;

public class TelefonoPaciente {
    private int cedula;
    private int telefono;

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Telefono Paciente" +
                "\nCédula: " + cedula +
                "\nTeléfono: " + telefono+"\n";
    }
}
