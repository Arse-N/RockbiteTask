package org.rockbite.rockbitetask.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;


@Builder
@Entity
@Getter
@Table(name="items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    /**
     * icon of item, must be image url
     */
    @Column(nullable = false)
    private String icon;

    /**
     * maxCap is the maximum capacity in single warehouse
     */
    @Column(name = "max_capacity", nullable = false)
    private Short maxCap;

    public Item() {
    }

    public Item(Long id, String name, String description, String icon, Short maxCap) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.maxCap = maxCap;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                ", maxCap=" + maxCap +
                '}';
    }
}
