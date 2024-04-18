package main.Java.lib;

public class Usuario {
    public String nome;
    public Integer matricula;
    public char genero;
    public Usuario(String nome, Integer matricula, char genero) {
        this.nome = nome;
        this.matricula = matricula;
        this.genero = genero;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Integer getMatricula() {
        return matricula;
    }
    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }
    public char getGenero() {
        return genero;
    }
    public void setGenero(char genero) {
        this.genero = genero;
    }    
}
