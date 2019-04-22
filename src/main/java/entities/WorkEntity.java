package entities;

import java.util.Objects;

public class WorkEntity implements Comparable<WorkEntity>{
    private int idWork;
    private String nameWork;
    private int costWork;

    public WorkEntity(){

    }

    public WorkEntity(int idWork){
        this.idWork = idWork;
    }

    public WorkEntity(int idWork, String nameWork, int costWork){
        this.idWork = idWork;
        this.nameWork = nameWork;
        this.costWork = costWork;
    }

    public int getIdWork() {
        return idWork;
    }

    public void setIdWork(int idWork) {
        this.idWork = idWork;
    }

    public String getNameWork() {
        return nameWork;
    }

    public void setNameWork(String nameWork) {
        this.nameWork = nameWork;
    }

    public int getCostWork() {
        return costWork;
    }

    public void setCostWork(int costWork) {
        this.costWork = costWork;
    }

    @Override
    public int compareTo(WorkEntity o) {
        return this.nameWork.compareTo(o.getNameWork());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkEntity that = (WorkEntity) o;
        return idWork == that.idWork;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idWork);
    }
}