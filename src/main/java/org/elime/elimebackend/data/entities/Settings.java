package org.elime.elimebackend.data.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.elime.elimebackend.security.logging.Auditable;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
@Table(name = "settings")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)

public class Settings extends Auditable {

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "max_daily_cakes", nullable = false)
    private int maxDailyCakes;

    @Column(name = "max_daily_trays", nullable = false)
    private int maxDailyTrays;

    @Column(name = "lead_time_days", nullable = false)
    private int leadTimeDays;
}
