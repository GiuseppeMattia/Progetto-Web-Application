package unical.demacs.backend.model;

public enum Categoria {

    SMARTPHONE(1, "Smartphone"),
    PC(2, "PC"),
    CONSOLE(3, "Console"),
    ACCESSORI(4, "Accessori");

    private final int ID;
    private final String nome;

    Categoria(int ID, String nome) {
        this.ID = ID;
        this.nome = nome;
    }

    public int getID() {
        return ID;
    }

    public String getNome() {
        return nome;
    }
}
