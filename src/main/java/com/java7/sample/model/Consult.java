package com.java7.sample.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "consults")
public class Consult  implements Domain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consultId")
    private Long consultId;

    @Column(name = "date")
    private Date date;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "vetId")
    private Vet vet;

    @ManyToOne
    @JoinColumn(name = "petId")
    private Pet pet;

    public Long getConsultId() {
        return consultId;
    }

    public void setConsultId(Long consultId) {
        this.consultId = consultId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "Consult{" +
                "consultId=" + consultId +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}
