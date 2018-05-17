package com.puzia.bookstore.service;

import com.puzia.bookstore.db.entity.Book;
import com.puzia.bookstore.db.entity.Order;
import com.puzia.bookstore.db.repository.AuthorJpaRepository;
import com.puzia.bookstore.db.repository.BookJpaRepository;
import com.puzia.bookstore.db.repository.OrderJpaRepository;
import com.puzia.bookstore.db.repository.UserJpaRepository;
import com.puzia.bookstore.domain.shipping.ShippingOption;
import com.puzia.bookstore.domain.shipping.ShippingOptionFactory;
import com.puzia.bookstore.dto.BookDto;
import com.puzia.bookstore.dto.OrderDto;
import com.puzia.bookstore.exception.InvalidOrderDataException;
import com.puzia.bookstore.exception.UnsufficientBookQuantityException;
import com.puzia.bookstore.service.model.BookOrderDetails;
import com.puzia.bookstore.service.model.NewOrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Service
public class OrderService {

    private final BookJpaRepository bookJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final OrderJpaRepository orderJpaRepository;
    private final ShippingOptionFactory shippingOptionFactory;
    private final AuthorJpaRepository authorJpaRepository;

    @Autowired
    public OrderService(BookJpaRepository bookJpaRepository,
                        UserJpaRepository userJpaRepository,
                        OrderJpaRepository orderJpaRepository,
                        ShippingOptionFactory shippingOptionFactory,
                        AuthorJpaRepository authorJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
        this.userJpaRepository = userJpaRepository;
        this.orderJpaRepository = orderJpaRepository;
        this.shippingOptionFactory = shippingOptionFactory;
        this.authorJpaRepository = authorJpaRepository;
    }

    public int addNewOrder(int userId, NewOrderDetails newOrderDetails) {
        Map<Integer, Book> booksById = newOrderDetails.getOrderedBooks().stream()
                .map(BookOrderDetails::getBookId)
                .map(bookJpaRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toMap(Book::getId, Function.identity()));

        validateRequestedBooks(booksById, newOrderDetails.getOrderedBooks());
        ShippingOption shippingOption = shippingOptionFactory.getShippingOptionById(newOrderDetails.getShippingOptionId());
        BigDecimal booksTotalAmount = calculateBooksTotalAmount(booksById, newOrderDetails.getOrderedBooks());
        BigDecimal totalAmount = booksTotalAmount.add(shippingOption.getPrice());

        // TODO distract each book quantity !!!!~!!!#!@!@#21rF

        return orderJpaRepository.saveAndFlush(
                new Order(
                        null,
                        userJpaRepository.getOne(userId),
                        new ArrayList<>(booksById.values()),
                        shippingOption.getPrice(),
                        totalAmount
                )
        ).getId();
    }

    public List<OrderDto> getOrderList() {
        return orderJpaRepository.findAll().stream()
                .map(order -> new OrderDto(
                        order.getId(),
                        order.getUser().getFirstName(),
                        order.getUser().getLastName(),
                        prepareBookDtoList(order.getBooks()),
                        order.getShippingCost(),
                        order.getTotalCost()
                ))
                .collect(toList());
    }

    private List<BookDto> prepareBookDtoList(List<Book> books) {
        return books.stream()
                .map(book -> new BookDto(
                        book.getId(),
                        book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName(),
                        book.getName(),
                        book.getPrice(),
                        book.getQuantity()
                ))
                .collect(toList());
    }

    private void validateRequestedBooks(Map<Integer, Book> booksById, List<BookOrderDetails> requestBooksDetails) {
        boolean missingBookDto = requestBooksDetails.stream()
                .map(BookOrderDetails::getBookId)
                .anyMatch(bookId -> !Optional.ofNullable(booksById.get(bookId)).isPresent());
        if (missingBookDto) {
            throw new InvalidOrderDataException();
        }

        // TODO split validations to 2 methods
        Optional<BookOrderDetails> orderNotPossibleElement = requestBooksDetails.stream()
                .filter(bookOrderDetails -> !isOrderPossible(bookOrderDetails, booksById.get(bookOrderDetails.getBookId())))
                .findFirst();

        if (orderNotPossibleElement.isPresent()) {
            BookOrderDetails orderDetails = orderNotPossibleElement.get();
            throw new UnsufficientBookQuantityException(booksById.get(orderDetails.getBookId()));
        }
    }

    private boolean isOrderPossible(BookOrderDetails bookOrderDetails, Book book) {
        return bookOrderDetails.getQuantity() <= book.getQuantity();
    }

    private BigDecimal calculateBooksTotalAmount(Map<Integer, Book> booksById, List<BookOrderDetails> bookOrderDetailsList) {
        Map<Integer, BigDecimal> priceByBookId = booksById.values().stream()
                .collect(toMap(Book::getId, Book::getPrice));
        BigDecimal total = BigDecimal.ZERO;
        for (BookOrderDetails details : bookOrderDetailsList) {
            total = total.add(priceByBookId.get(details.getBookId()).multiply(BigDecimal.valueOf(details.getQuantity())));
        }
        return total;
    }

}
