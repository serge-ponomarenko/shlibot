package ua.cc.spon.shlibot.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Product entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "position")
    private int position;

    @Column(name = "checked")
    private boolean checked;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "creation_date")
    private Date creationDate;

    @OneToOne
    @JoinColumn(name="list_id")
    private UserList list;

}
