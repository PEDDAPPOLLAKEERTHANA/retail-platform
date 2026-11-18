
package retail_platform.retail_platform.supplier;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    // Add Supplier
    public Supplier addSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    // Get All Suppliers
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    // Get Supplier By ID
    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Supplier not found with ID: " + id));
    }

    // Update Supplier
    public Supplier updateSupplier(Long id, Supplier updatedSupplier) {
        Supplier existingSupplier = getSupplierById(id);

        existingSupplier.setName(updatedSupplier.getName());
        existingSupplier.setEmail(updatedSupplier.getEmail());
        existingSupplier.setPhone(updatedSupplier.getPhone());
        existingSupplier.setAddress(updatedSupplier.getAddress());

        return supplierRepository.save(existingSupplier);
    }

    // Delete Supplier
    public void deleteSupplier(Long id) {
        Supplier existingSupplier = getSupplierById(id);
        supplierRepository.delete(existingSupplier);
    }
}
