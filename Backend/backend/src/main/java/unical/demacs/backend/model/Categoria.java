package unical.demacs.backend.model;

public class Categoria {

    private int ID;
    private String nome;

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getID() {
        return ID;
    }

    public String getNome() {
        return nome;
    }
}
