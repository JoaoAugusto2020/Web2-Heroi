package com.joaoa.Web2_Heroi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "heroi")
public class Heroi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHeroi;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "vida", nullable = false)
    private Integer vida;

    @Column(name = "defesa", nullable = false)
    private Integer defesa;

    @Column(name = "ataque", nullable = false)
    private Integer ataque;

    @Column(name = "universo")
    private String universo;

    @Column(name = "anoOrigem")
    private Integer anoOrigem;

    public Long getIdHeroi() {
        return idHeroi;
    }

    public void setIdHeroi(Long idHeroi) {
        this.idHeroi = idHeroi;
    }

    public Integer getAnoOrigem() {
        return anoOrigem;
    }

    public void setAnoOrigem(Integer anoOrigem) {
        this.anoOrigem = anoOrigem;
    }

    public String getUniverso() {
        return universo;
    }

    public void setUniverso(String universo) {
        this.universo = universo;
    }

    public Integer getAtaque() {
        return ataque;
    }

    public void setAtaque(Integer ataque) {
        this.ataque = ataque;
    }

    public Integer getDefesa() {
        return defesa;
    }

    public void setDefesa(Integer defesa) {
        this.defesa = defesa;
    }

    public Integer getVida() {
        return vida;
    }

    public void setVida(Integer vida) {
        this.vida = vida;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
