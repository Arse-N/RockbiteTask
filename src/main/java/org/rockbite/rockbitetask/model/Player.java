package org.rockbite.rockbitetask.model;

import jakarta.persistence.*;
import lombok.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Warehouse> warehouses;

    public Player() {
    }

    public Player(Long id, String name, String lastName, List<Warehouse> warehouses) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.warehouses = warehouses;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\''+
                '}';
    }
}