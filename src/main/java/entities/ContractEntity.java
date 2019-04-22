package entities;

import java.sql.Date;
import java.util.ArrayList;

public class ContractEntity {
    private int idContract;
    private Date dataContract;
    private Date dataExecutionContract;
    private String carNumber;
    private int idClient;
    private ArrayList<WorkEntity> workEntities;

    public ContractEntity(){
    }

    public ContractEntity(int idContract){
        this.idContract = idContract;
    }

    public ContractEntity(int idContract, Date dataContract, Date dataExecutionContract, String carNumber, int idClient) {
        this.idContract = idContract;
        this.dataContract = dataContract;
        this.dataExecutionContract = dataExecutionContract;
        this.carNumber = carNumber;
        this.idClient = idClient;
    }

    public ContractEntity(int idContract, Date dataContract, Date dataExecutionContract, String carNumber, int idClient, ArrayList<WorkEntity> workEntities) {
        this.idContract = idContract;
        this.dataContract = dataContract;
        this.dataExecutionContract = dataExecutionContract;
        this.carNumber = carNumber;
        this.idClient = idClient;
        this.workEntities = workEntities;
    }

    public int getIdContract() {
        return idContract;
    }

    public void setIdContract(int idContract) {
        this.idContract = idContract;
    }

    public Date getDataContract() {
        return dataContract;
    }

    public void setDataContract(Date dataContract) {
        this.dataContract = dataContract;
    }

    public Date getDataExecutionContract() {
        return dataExecutionContract;
    }

    public void setDataExecutionContract(Date dataExecutionContract) {
        this.dataExecutionContract = dataExecutionContract;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public ArrayList<WorkEntity> getWorkEntities() {
        return workEntities;
    }

    public void setWorkEntities(ArrayList<WorkEntity> workEntities) {
        this.workEntities = workEntities;
    }
}