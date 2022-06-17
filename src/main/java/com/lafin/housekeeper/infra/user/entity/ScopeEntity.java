package com.lafin.housekeeper.infra.user.entity;

import com.lafin.housekeeper.shared.type.ScopeType;
import lombok.*;

import javax.persistence.*;

//@Entity
//@Table(name = "scope")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ScopeEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ScopeType type;

    private boolean accepted;
}
