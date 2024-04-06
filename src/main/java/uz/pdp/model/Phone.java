package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// first
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false, unique = true)
    private String macAddress;

    @Column(nullable = false)
    private String info;

}
