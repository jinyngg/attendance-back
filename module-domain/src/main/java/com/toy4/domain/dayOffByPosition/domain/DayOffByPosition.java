package com.toy4.domain.dayOffByPosition.domain;

import com.toy4.domain.BaseEntity;
import com.toy4.domain.position.domain.Position;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class DayOffByPosition extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", referencedColumnName = "id", nullable = false)
    private Position position;

    @Column(name = "amount", nullable = false)
    private int amount;
}