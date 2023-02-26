package com.eduardo.rdguez.zssn.service.impl

import com.eduardo.rdguez.zssn.domain.Item
import com.eduardo.rdguez.zssn.repository.ItemRepository
import com.eduardo.rdguez.zssn.service.ItemService
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import spock.lang.Unroll

import java.lang.Void as Should

@SpringBootTest
@ActiveProfiles("default")
@Unroll
class ItemServiceSpec extends Specification {
  private ItemService itemService

  def setup() {
    itemService = new ItemServiceImpl(Mock(ItemRepository))
  }

  @Rollback(true)
  Should "Spec 1 find item by name"() {
    given:
    ItemRepository itemRepository = Mock(ItemRepository)
    itemRepository.findByNameIgnoreCase(_ as String) >> Optional.of(itemFound)
    itemService.itemRepository = itemRepository
    when:
    Optional<Item> item = itemService.findItemByName(itemName)
    then:
    assert item.get().id
    assert item.get().points
    assert item.get().name.toLowerCase() == itemName
    where:
    itemName << [
      "water",
      "food",
      "medication",
      "ammunition",
    ]
    itemFound << [
      new Item(1, 'Water', 4),
      new Item(2, 'Food', 3),
      new Item(3, 'Medication', 2),
      new Item(4, 'Ammunition', 1),
    ]
  }
}
