package com.mys.sammo.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class General {

    @Id
    @GeneratedValue
    @Column(name = "general_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String name;

    @ManyToOne
    @JoinColumn(name = "force_id")
    private Force force;

    @ManyToOne
    @JoinColumn(name = "territory_id")
    private Territory position;

    public static General showUp(Member member, String name, Territory position) {

        General general = new General();
        general.member = member;
        general.name = name;
        position.assignedBy(general);
        return general;
    }

    public void moveIn(Territory position) {

        this.position = position;
    }

    public void appoint(Force force) {

        this.force = force;
    }
}
