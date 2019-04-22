package entities;

public class ClientEntity {
    private int idClient;
    private String clientFIO;

    public ClientEntity(){

    }

    public ClientEntity(int idClient){
        this.idClient = idClient;
    }

    public ClientEntity(int idClient, String ClientFIO) {
        this.idClient = idClient;
        this.clientFIO = ClientFIO;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getClientFIO() {
        return clientFIO;
    }

    public void setClientFIO(String clientFIO) {
        this.clientFIO = clientFIO;
    }
}