package org.rockbite.rockbitetask.model;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;


@Builder
@Entity
@Table(name = "warehouses")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column()
    private String description;

    @ManyToOne()
    @JoinColumn(name="player_id", nullable = false)
    private Player player;

//    @OneToMany(mappedBy = "warehouse_item", cascade = CascadeType.ALL)
//    private List<WarehouseItem> item;


    public Warehouse() {
    }

    public Warehouse(Long id, String name, String description, Player player) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.player = player;
    }


}

