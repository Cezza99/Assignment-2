////////////////////////////////////////////////////////////////////
// [CESARE] [OMODEI] [1187460]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import java.time.LocalDate;

public class User {
    private String ID;
    private String nome;
    private String cognome;
    private LocalDate data_nascita;

    public User(String id, String nome, String cognome, 
            LocalDate data_nascita) {
        this.ID = id;
        this.nome = nome;
        this.cognome = cognome;
        this.data_nascita = data_nascita;
    }

    public String getID() {
        return ID;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public LocalDate getDataNascita() {
        return data_nascita;
    }

}