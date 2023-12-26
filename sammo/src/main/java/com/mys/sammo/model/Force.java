package com.mys.sammo.model;

import com.mys.sammo.model.enums.Faith;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Force {

    @Id
    @GeneratedValue
    @Column(name = "force_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "leader_id")
    private General leader;

    @Enumerated(EnumType.STRING)
    private Faith faith;

    @OneToMany(mappedBy="force")
    private List<General> generals;

    public static Force raise(General leader, Faith faith) {

        Force force = new Force();
        force.leader = leader;
        force.faith = faith;
        force.generals = new ArrayList<>();
        force.generals.add(leader);
        leader.appoint(force);
        return force;
    }

    public void join(General general) {

        generals.add(general);
        general.appoint(this);
    }
}
