package app.core.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "transportations")
@ToString(exclude = "students")
@Builder
public class Transportation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private int numBus;
    @JsonIgnore
    @OneToMany
    private List<Station> stations;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="school_id")
    private School school;
//    @JsonIgnore
//    @OneToMany(mappedBy = "numBus")
//    private List<Student> students;
}
