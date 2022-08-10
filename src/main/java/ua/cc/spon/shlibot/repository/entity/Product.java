package ua.cc.spon.shlibot.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Product entity.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "list_id")
    private UserList list;

}
