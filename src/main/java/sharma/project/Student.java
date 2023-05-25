package sharma.project;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Persister;

@Entity
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Student {
    @Id
    @Column(name = "cwid")
    private int cwid;

    @Column(name = "name")
    private String name;

    @Column(name = "major")
    private String major;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private Laptop laptop;

    public Student(int cwid, String name, String major) {
        this.cwid = cwid;
        this.name = name;
        this.major = major;
    }

    public String getLaptopBrand() {
        return (laptop != null) ? laptop.getBrand() : null;
    }
}
