package org.elime.elimebackend.data.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.elime.elimebackend.security.logging.Auditable;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Entity
@Data
@Table(name = "shapes")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Shape extends Auditable {

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "active", nullable = false)
    private boolean active;

    @OneToMany(mappedBy = "shape", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItemCustomization> orderItemCustomizations;
}
