package main.Java.lib;

import java.time.LocalDate;

public class Movimentacao {
    public Usuario usuario;
    public Livro livro;
    public LocalDate dataPrevistaDevolucao;
    public LocalDate dataEfetivaDevolucao;
    public Movimentacao(Usuario usuario, Livro livro, LocalDate dataPrevistaDevolucao, LocalDate dataEfetivaDevolucao) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.dataEfetivaDevolucao = dataEfetivaDevolucao;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Livro getLivro() {
        return livro;
    }
    public void setLivro(Livro livro) {
        this.livro = livro;
    }
    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }
    public void setDataPrevistaDevolucao(LocalDate dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }
    public LocalDate getDataEfetivaDevolucao() {
        return dataEfetivaDevolucao;
    }
    public void setDataEfetivaDevolucao(LocalDate dataEfetivaDevolucao) {
        this.dataEfetivaDevolucao = dataEfetivaDevolucao;
    }
    

}