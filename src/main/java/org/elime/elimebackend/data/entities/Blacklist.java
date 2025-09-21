package org.elime.elimebackend.data.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.elime.elimebackend.security.logging.Auditable;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Table(name = "blacklist")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Blacklist extends Auditable {

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "expiration")
    private Date expiration;

    @Column(name = "token", nullable = false)
    private String token;
}
