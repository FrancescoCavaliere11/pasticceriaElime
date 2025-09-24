package org.elime.elimebackend.data.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.elime.elimebackend.security.logging.Auditable;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Entity
@Table(name = "creams")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Cream extends Auditable {

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "active", nullable = false)
    private boolean active;

    @OneToMany(mappedBy = "cream", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItemCustomization> orderItemCustomizations;
}
