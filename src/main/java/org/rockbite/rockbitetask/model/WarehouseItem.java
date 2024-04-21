package org.rockbite.rockbitetask.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Data
@Table(name = "warehouse_items")
public class WarehouseItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "item_count")
    private Short count;


    public WarehouseItem() {
    }

    public WarehouseItem(Long id, Warehouse warehouse, Item item, Short count) {
        this.id = id;
        this.warehouse = warehouse;
        this.item = item;
        this.count = count;
    }

}
