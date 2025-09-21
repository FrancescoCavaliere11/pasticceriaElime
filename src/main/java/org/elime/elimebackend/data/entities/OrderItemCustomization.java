package org.elime.elimebackend.data.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.elime.elimebackend.security.logging.Auditable;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Data
@Table(name = "order_item_customizations")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class OrderItemCustomization extends Auditable {

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "text_decoration")
    private String textDecoration;

    @Column(name = "weight", nullable = false)
    private int weight;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "description")
    private String description;

    @Column(name = "color_hexadecimal")
    private String colorHexadecimal;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_item_id", nullable = false)
    private OrderItem orderItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cream_id", nullable = false)
    private Cream cream;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "decoration_id")
    private Decoration decoration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shape_id")
    private Shape shape;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dough_id", nullable = false)
    private Dough dough;
}
