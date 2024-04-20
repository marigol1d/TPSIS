package com.business;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SsBeautyProductsApplicationTests {

	@Test
	void contextLoads() {
	}

@Autowired
    private ClientController clientController;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void testRegister() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = clientController.register(user);

        assertEquals(user.getUsername(), result.getUsername());
        verify(userRepository, times(1)).save(user);
    }

    @Test
public void testLogin() {
    User user = new User();
    user.setUsername("testUser");
    user.setPassword("testPassword");

    when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
    when(passwordEncoder.matches(user.getPassword(), user.getPassword())).thenReturn(true);

    User result = clientController.login(user);

    assertEquals(user.getUsername(), result.getUsername());
    verify(userRepository, times(1)).findByUsername(user.getUsername());
}

@Test
public void testCreateOrder() {
    Order order = new Order();
    order.setId(1L);

    when(orderRepository.save(any(Order.class))).thenReturn(order);

    Order result = clientController.createOrder(order);

    assertEquals(order.getId(), result.getId());
    verify(orderRepository, times(1)).save(order);
}

@Test
public void testUpdateOrder() {
    Order order = new Order();
    order.setId(1L);
    order.setStatus("Submitted");

    when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
    when(orderRepository.save(any(Order.class))).thenReturn(order);

    Order result = clientController.updateOrder(order.getId(), new OrderDto());

    assertEquals(order.getStatus(), result.getStatus());
    verify(orderRepository, times(1)).save(order);
}
    @Test
public void testSubmitOrder() {
    Order order = new Order();
    order.setId(1L);
    order.setStatus("Submitted");

    when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
    when(orderRepository.save(any(Order.class))).thenReturn(order);

    Order result = clientController.submitOrder(order.getId());

    assertEquals(order.getStatus(), result.getStatus());
    verify(orderRepository, times(1)).save(order);
}

@Test
public void testCancelOrderClient() {
    Order order = new Order();
    order.setId(1L);
    order.setStatus("Cancelled");

    when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
    when(orderRepository.save(any(Order.class))).thenReturn(order);

    Order result = clientController.cancelOrder(order.getId());

    assertEquals(order.getStatus(), result.getStatus());
    verify(orderRepository, times(1)).save(order);
}

@Test
public void testPayOrder() {
    Order order = new Order();
    order.setId(1L);
    order.setStatus("Paid");

    when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
    when(orderRepository.save(any(Order.class))).thenReturn(order);

    Order result = clientController.payOrder(order.getId());

    assertEquals(order.getStatus(), result.getStatus());
    verify(orderRepository, times(1)).save(order);
}

@Test
public void testLeaveReview() {
    Review review = new Review();
    review.setId(1L);
    review.setRating(5);

    when(reviewRepository.save(any(Review.class))).thenReturn(review);

    Review result = clientController.leaveReview(1L, review);

    assertEquals(review.getRating(), result.getRating());
    verify(reviewRepository, times(1)).save(review);
}
@Test
public void testCompleteOrderAdmin() {
    Order order = new Order();
    order.setId(1L);
    order.setStatus("Выполнен");

    when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
    when(orderRepository.save(any(Order.class))).thenReturn(order);

    Order result = adminController.completeOrder(order.getId());

    assertEquals(order.getStatus(), result.getStatus());
    verify(orderRepository, times(1)).save(order);
}

@Test
public void testProcessOrder() {
    Order order = new Order();
    order.setId(1L);
    order.setStatus("В процессе");

    when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
    when(orderRepository.save(any(Order.class))).thenReturn(order);

    Order result = adminController.processOrder(order.getId());

    assertEquals(order.getStatus(), result.getStatus());
    verify(orderRepository, times(1)).save(order);
}

@Test
public void testCancelOrderAdmin() {
    Order order = new Order();
    order.setId(1L);
    order.setStatus("Отменен");

    when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
    when(orderRepository.save(any(Order.class))).thenReturn(order);

    Order result = adminController.cancelOrder(order.getId());

    assertEquals(order.getStatus(), result.getStatus());
    verify(orderRepository, times(1)).save(order);
}

@Test
public void testCreateService() {
    Service service = new Service();
    service.setId(1L);
    service.setName("Test Service");

    when(serviceRepository.save(any(Service.class))).thenReturn(service);

    Service result = adminController.createService(service);

    assertEquals(service.getName(), result.getName());
    verify(serviceRepository, times(1)).save(service);
}

@Test
public void testUpdateService() {
    Service service = new Service();
    service.setId(1L);
    service.setName("Updated Test Service");

    when(serviceRepository.findById(service.getId())).thenReturn(Optional.of(service));
    when(serviceRepository.save(any(Service.class))).thenReturn(service);

    Service result = adminController.updateService(service.getId(), service);

    assertEquals(service.getName(), result.getName());
    verify(serviceRepository, times(1)).save(service);
}

@Test
public void testDeleteService() {
    Service service = new Service();
    service.setId(1L);

    doNothing().when(serviceRepository).deleteById(service.getId());

    adminController.deleteService(service.getId());

    verify(serviceRepository, times(1)).deleteById(service.getId());
}

@Test
public void testUpdateUser() {
    User user = new User();
    user.setId(1L);
    user.setUsername("UpdatedTestUser");

    when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
    when(userRepository.save(any(User.class))).thenReturn(user);

    User result = adminController.updateUser(user.getId(), user);

    assertEquals(user.getUsername(), result.getUsername());
    verify(userRepository, times(1)).save(user);
}

@Test
public void testDeleteUser() {
    User user = new User();
    user.setId(1L);

    doNothing().when(userRepository).deleteById(user.getId());

    adminController.deleteUser(user.getId());

    verify(userRepository, times(1)).deleteById(user.getId());
}
@Test
public void testGetServices() {
    List<Service> services = new ArrayList<>();
    services.add(new Service());

    when(serviceRepository.findAll()).thenReturn(services);

    List<Service> result = clientController.getServices();

    assertEquals(services.size(), result.size());
    verify(serviceRepository, times(1)).findAll();
}

@Test
public void testGetOrder() {
    Order order = new Order();
    order.setId(1L);

    when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));

    Order result = clientController.getOrder(order.getId());

    assertEquals(order.getId(), result.getId());
    verify(orderRepository, times(1)).findById(order.getId());
}
@Test
public void testFindAffordableServices() {
    List<Service> services = new ArrayList<>();
    services.add(new Service());

    when(serviceRepository.findAffordableServices(anyDouble())).thenReturn(services);

    List<Service> result = adminController.findAffordableServices(100.0);

    assertEquals(services.size(), result.size());
    verify(serviceRepository, times(1)).findAffordableServices(anyDouble());
}

@Test
public void testFindByNameContaining() {
    List<Service> services = new ArrayList<>();
    services.add(new Service());

    when(serviceRepository.findByNameContaining(anyString())).thenReturn(services);

    List<Service> result = adminController.findByNameContaining("Test");

    assertEquals(services.size(), result.size());
    verify(serviceRepository, times(1)).findByNameContaining(anyString());
}

@Test
public void testFindByAreaLessThanEqual() {
    List<Service> services = new ArrayList<>();
    services.add(new Service());

    when(serviceRepository.findByAreaLessThanEqual(anyInt())).thenReturn(services);

    List<Service> result = adminController.findByAreaLessThanEqual(100);

    assertEquals(services.size(), result.size());
    verify(serviceRepository, times(1)).findByAreaLessThanEqual(anyInt());
}

@Test
public void testFindGoodReviews() {
    List<Review> reviews = new ArrayList<>();
    reviews.add(new Review());

    when(reviewRepository.findGoodReviews(anyInt())).thenReturn(reviews);

    List<Review> result = adminController.findGoodReviews(4);

    assertEquals(reviews.size(), result.size());
    verify(reviewRepository, times(1)).findGoodReviews(anyInt());
}

@Test
public void testFindByOrderId() {
    List<Review> reviews = new ArrayList<>();
    reviews.add(new Review());

    when(reviewRepository.findByOrderId(anyLong())).thenReturn(reviews);

    List<Review> result = adminController.findByOrderId(1L);

    assertEquals(reviews.size(), result.size());
    verify(reviewRepository, times(1)).findByOrderId(anyLong());
}

@Test
public void testFindByRatingBetween() {
    List<Review> reviews = new ArrayList<>();
    reviews.add(new Review());

    when(reviewRepository.findByRatingBetween(anyInt(), anyInt())).thenReturn(reviews);

    List<Review> result = adminController.findByRatingBetween(1, 5);

    assertEquals(reviews.size(), result.size());
    verify(reviewRepository, times(1)).findByRatingBetween(anyInt(), anyInt());
}

}
