package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    final private Long ownerId = 1l;
    final String name = "Kar";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        ownerMapService.save(Owner.builder().id(ownerId).lastMame(name).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();
        assertEquals(1,ownerSet.size());
    }

    @Test
    void findById() {
    Owner owner = ownerMapService.findById(ownerId);
    assertEquals(ownerId, owner.getId());

    }

    @Test
    void saveExistingId() {
        Long id = 2l;
        Owner owner2 = Owner.builder().id(id).build();
        Owner savedOwner = ownerMapService.save(owner2);
        assertEquals(id,owner2.getId());
    }

    @Test
    void saveNoId() {
        Owner owner3 = Owner.builder().build();
        Owner ownerSaved3 = ownerMapService.save(Owner.builder().build());
        assertNotNull(ownerSaved3);         // dlaczego jak podstawimy tu owner3 to test fail ?
        assertNotNull(ownerSaved3.getId());

    }


    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);
        assertEquals(0, ownerMapService.findAll().size());


    }

    @Test
    void findByLastName() {
        Owner owner  = ownerMapService.findByLastName(name);
        assertNotNull(owner);
        assertEquals(name, owner.getLastName());
        assertEquals(ownerId, owner.getId());

    }

    @Test
    void findByLastNameNotFound() {
        Owner smith  = ownerMapService.findByLastName("foo");
        assertNull(smith);
    }
}






