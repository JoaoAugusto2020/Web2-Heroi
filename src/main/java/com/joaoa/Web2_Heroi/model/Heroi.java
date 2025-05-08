package com.joaoa.Web2_Heroi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "heroi")
public class Heroi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHeroi;

    @Size(min = 3, max = 50, message= "Nome deve conter pelo menos 3 e no máximo 50 caracteres")
    @NotBlank(message= "Nome é um campo obrigatório")
    @Column(name = "nome", nullable = false)
    private String nome;

    @Min(value = 1, message= "Pontos de vida deve ser positivo")
    @NotNull(message= "Este campo é obrigatório")
    @Column(name = "vida", nullable = false)
    private Integer vida;

    @Min(value = 1, message= "Pontos de defesa deve ser positivo")
    @NotNull(message= "Este campo é obrigatório")
    @Column(name = "defesa", nullable = false)
    private Integer defesa;

    @Min(value = 1, message= "Pontos de ataque deve ser positivo")
    @NotNull(message= "Este campo é obrigatório")
    @Column(name = "ataque", nullable = false)
    private Integer ataque;

    @NotBlank(message= "Universo é um campo obrigatório")
    @Column(name = "universo")
    private String universo;

    @NotNull(message= "Informe um ano válida")
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
