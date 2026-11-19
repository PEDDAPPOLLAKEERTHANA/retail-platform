
package retail_platform.retail_platform.inventory;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // Add new inventory
    @PostMapping
    public Inventory addInventory(@RequestBody Inventory inventory) {
        return inventoryService.addInventory(inventory);
    }

    // Get all inventory
    @GetMapping
    public List<Inventory> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    // Get inventory by ID
    @GetMapping("/{id}")
    public Inventory getInventoryById(@PathVariable Long id) {
        return inventoryService.getInventoryById(id);
    }

    // Get inventory by Product ID
    @GetMapping("/product/{productId}")
    public Inventory getInventoryByProductId(@PathVariable Long productId) {
        return inventoryService.getInventoryByProductId(productId);
    }

    // Update inventory
    @PutMapping("/{id}")
    public Inventory updateInventory(
            @PathVariable Long id,
            @RequestBody Inventory inventory) {
        return inventoryService.updateInventory(id, inventory);
    }

    // Increase stock
    @PutMapping("/add-stock/{productId}")
    public Inventory addStock(
            @PathVariable Long productId,
            @RequestParam Integer quantity) {
        return inventoryService.addStock(productId, quantity);
    }

    // Reduce stock
    @PutMapping("/reduce-stock/{productId}")
    public Inventory reduceStock(
            @PathVariable Long productId,
            @RequestParam Integer quantity) {
        return inventoryService.reduceStock(productId, quantity);
    }

    // Get low-stock inventory
    @GetMapping("/low-stock")
    public List<Inventory> getLowStockInventory() {
        return inventoryService.getLowStockInventory();
    }
}
