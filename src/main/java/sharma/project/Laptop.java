package sharma.project;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "laptops")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Laptop {
    @Id
    @Column(name = "cwid")
    private int cwid;

    @Column(name = "brand")
    private String brand;

    @OneToOne
    @JoinColumn(name = "cwid")
    private Student student;

    public Laptop(int cwid, String brand){
        this.cwid = cwid;
        this.brand = brand;
    }
}
