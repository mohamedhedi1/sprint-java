package event;

public class ParticipantsActivites {
    private int id;
    private int idParticipant;
    private int idActivite;

    public ParticipantsActivites(int id, int idParticipant, int idActivite) {
        this.id = id;
        this.idParticipant = idParticipant;
        this.idActivite = idActivite;
    }

    public ParticipantsActivites(int idParticipant, int idActivite) {
        this.idParticipant = idParticipant;
        this.idActivite = idActivite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdParticipant() {
        return idParticipant;
    }

    public void setIdParticipant(int idParticipant) {
        this.idParticipant = idParticipant;
    }

    public int getIdActivite() {
        return idActivite;
    }

    public void setIdActivite(int idActivite) {
        this.idActivite = idActivite;
    }
}
