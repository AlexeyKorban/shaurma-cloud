package shaurmas.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import shaurmas.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
