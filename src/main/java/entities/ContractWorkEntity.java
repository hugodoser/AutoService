package entities;

public class ContractWorkEntity {
    private int idContract;
    private int idWork;

    public int getIdContract() {
        return idContract;
    }

    public void setIdContract(int idContract) {
        this.idContract = idContract;
    }

    public int getIdWork() {
        return idWork;
    }

    public void setIdWork(int idWork) {
        this.idWork = idWork;
    }

    public ContractWorkEntity(){

    }

    public ContractWorkEntity(int idWork, int idContract){
        this.idWork = idWork;
        this.idContract = idContract;
    }
}