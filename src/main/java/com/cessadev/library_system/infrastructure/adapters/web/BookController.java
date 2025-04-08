package com.cessadev.library_system.infrastructure.adapters.web;

import com.cessadev.library_system.application.services.BookService;
import com.cessadev.library_system.application.services.CategoryService;
import com.cessadev.library_system.domain.Book;
import com.cessadev.library_system.domain.Category;
import com.cessadev.library_system.domain.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

  private final BookService bookService;
  private final CategoryService categoryService;

  public BookController(BookService bookService, CategoryService categoryService) {
    this.bookService = bookService;
    this.categoryService = categoryService;
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping
  public List<BookWithCategoryDTO> getAllBooks() {
    return bookService.getAllBooks().stream()
            .map(BookWithCategoryDTO::fromEntity)
            .collect(Collectors.toList());
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/{id}")
  public ResponseEntity<BookWithCategoryDTO> getBookById(@PathVariable Long id) {
    return bookService.getBookById(id)
            .map(BookWithCategoryDTO::fromEntity)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/by-category/{categoryId}")
  public List<BookWithCategoryDTO> getBooksByCategory(@PathVariable Long categoryId) {
    return bookService.getByCategoryId(categoryId).stream()
            .map(BookWithCategoryDTO::fromEntity)
            .collect(Collectors.toList());
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @PostMapping
  public ResponseEntity<BookWithCategoryDTO> createBook(
          @Valid @RequestBody CreateBookDTO createBookDTO) {
    Category category = categoryService.getCategoryById(createBookDTO.getCategoryId())
            .orElseThrow(() -> new IllegalArgumentException("Category not found"));

    Book book = createBookDTO.toEntityWithCategory(category);
    Book savedBook = bookService.createBook(book);

    return ResponseEntity
            .created(URI.create("/api/v1/book/" + savedBook.getId()))
            .body(BookWithCategoryDTO.fromEntity(savedBook));
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteBook(@PathVariable Long id) {
    try {
      bookService.deleteBook(id);
      return ResponseEntity.noContent().build();
    } catch (IllegalStateException e) {
      Map<String, Object> details = new HashMap<>();
      details.put("bookId", id);
      details.put("timestamp", new Date());

      ErrorResponse error = new ErrorResponse(
              "DELETE_RESTRICTED",
              e.getMessage(),
              details
      );

      return ResponseEntity
              .status(HttpStatus.BAD_REQUEST)
              .body(error);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }


  @CrossOrigin(origins = "http://localhost:4200")
  @PutMapping("/{id}")
  public ResponseEntity<BookWithCategoryDTO> updateBook(
          @PathVariable Long id,
          @Valid @RequestBody UpdateBookByIdDTO updateBookByIdDTO) {

    Book existingBook = bookService.getBookById(id)
            .orElseThrow(() -> new IllegalArgumentException("Book not found"));

    existingBook.setTitle(updateBookByIdDTO.getTitle());
    existingBook.setAuthor(updateBookByIdDTO.getAuthor());
    existingBook.setDescription(updateBookByIdDTO.getDescription());
    existingBook.setYearPublication(String.valueOf(updateBookByIdDTO.getYearPublication()));

    if (updateBookByIdDTO.getCategoryId() != null) {
      Category category = categoryService.getCategoryById(updateBookByIdDTO.getCategoryId())
              .orElseThrow(() -> new IllegalArgumentException("Category not found"));
      existingBook.setCategory(category);
    }

    Book updatedBook = bookService.updateBook(existingBook);
    return ResponseEntity.ok(BookWithCategoryDTO.fromEntity(updatedBook));
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/isbn/{isbn}")
  public ResponseEntity<BookWithCategoryDTO> getBookByIsbn(@PathVariable String isbn) {
    return bookService.getBookByIsbn(isbn)
            .map(BookWithCategoryDTO::fromEntity)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }
}
