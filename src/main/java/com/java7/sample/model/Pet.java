package com.java7.sample.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pets")
public class Pet implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "race")
    private String race;

    @Column(name = "dateOfBirth")
    private Date dateOfBirth;

    @Column(name = "isVaccinated")
    private Boolean isVaccinated;

    @Column(name = "ownerName")
    private String ownerName;

    @OneToMany(mappedBy = "pet")
    private Set<Consult> consults = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getIsVaccinated() {
        return isVaccinated;
    }

    public void setIsVaccinated(Boolean isVaccinated) {
        this.isVaccinated = isVaccinated;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Set<Consult> getConsults() {
        return consults;
    }

    public void setConsults(Set<Consult> consults) {
        this.consults = consults;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", race='" + race + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", isVaccinated=" + isVaccinated +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pet pet = (Pet) o;

        if (id != null ? !id.equals(pet.id) : pet.id != null) return false;
        if (race != null ? !race.equals(pet.race) : pet.race != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(pet.dateOfBirth) : pet.dateOfBirth != null) return false;
        if (isVaccinated != null ? !isVaccinated.equals(pet.isVaccinated) : pet.isVaccinated != null) return false;
        return ownerName != null ? ownerName.equals(pet.ownerName) : pet.ownerName == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (race != null ? race.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (isVaccinated != null ? isVaccinated.hashCode() : 0);
        result = 31 * result + (ownerName != null ? ownerName.hashCode() : 0);
        return result;
    }
}
