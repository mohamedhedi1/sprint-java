/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportconnectfx.entities;

/**
 *
 * @author manar
 */
public class Commentaire{
        private int id ; 
        private String pseudo ; 
       private String contenu ; 
        private Blog idblog_id ; 

    public Commentaire() {
    }

    public Commentaire(int id, String pseudo, String contenu, Blog idblog_id) {
        this.id = id;
        this.pseudo = pseudo;
        this.contenu = contenu;
        this.idblog_id = idblog_id;
    }

    public int getId() {
        return id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getContenu() {
        return contenu;
    }

    public Blog getIdblog_id() {
        return idblog_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setIdblog_id(Blog idblog_id) {
        this.idblog_id = idblog_id;
    }

    @Override
    public String toString() {
        return "Commentaire{  idblog_id=" + idblog_id + '}';
    }


        
    
}
