
package retail_platform.retail_platform.inventory;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    // Add new inventory
    public Inventory addInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    // Get all inventory records
    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    // Get inventory by ID
    public Inventory getInventoryById(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Inventory not found with ID: " + id));
    }

    // Get inventory by Product ID
    public Inventory getInventoryByProductId(Long productId) {
        return inventoryRepository.findByProductId(productId)
                .orElseThrow(() ->
                        new RuntimeException("Inventory not found for Product ID: " + productId));
    }

    // Update inventory
    public Inventory updateInventory(Long id, Inventory updatedInventory) {
        Inventory existingInventory = getInventoryById(id);

        existingInventory.setReorderLevel(updatedInventory.getReorderLevel());

        return inventoryRepository.save(existingInventory);
    }

    // Increase stock
    public Inventory addStock(Long productId, Integer quantity) {

        if (quantity == null || quantity <= 0) {
            throw new RuntimeException("Quantity must be greater than zero");
        }

        Inventory inventory = getInventoryByProductId(productId);

        inventory.setAvailableQuantity(
                inventory.getAvailableQuantity() + quantity
        );

        return inventoryRepository.save(inventory);
    }

    // Reduce stock
    public Inventory reduceStock(Long productId, Integer quantity) {

        if (quantity == null || quantity <= 0) {
            throw new RuntimeException("Quantity must be greater than zero");
        }

        Inventory inventory = getInventoryByProductId(productId);

        if (inventory.getAvailableQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock available");
        }

        inventory.setAvailableQuantity(
                inventory.getAvailableQuantity() - quantity
        );

        return inventoryRepository.save(inventory);
    }

    // Get low-stock inventory
    public List<Inventory> getLowStockInventory() {
        return inventoryRepository.findAll()
                .stream()
                .filter(inventory ->
                        inventory.getAvailableQuantity() <= inventory.getReorderLevel())
                .toList();
    }
}
