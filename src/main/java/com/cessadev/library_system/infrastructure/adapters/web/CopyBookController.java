package com.cessadev.library_system.infrastructure.adapters.web;

import com.cessadev.library_system.application.services.CopyBookService;
import com.cessadev.library_system.domain.CopyBook;
import com.cessadev.library_system.domain.dto.CopyBookDTO;
import com.cessadev.library_system.domain.dto.CreateCopyBookDTO;
import com.cessadev.library_system.domain.dto.UpdateStatusCopyBookDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/copybooks")
public class CopyBookController {

  private final CopyBookService copyBookService;

  public CopyBookController(CopyBookService copyBookService) {
    this.copyBookService = copyBookService;
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/{id}")
  public ResponseEntity<CopyBookDTO> getCopyById(@PathVariable Long id) {
    CopyBook copy = copyBookService.getCopyById(id);
    return ResponseEntity.ok(CopyBookDTO.fromEntity(copy));
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/by-book/{bookId}")
  public List<CopyBookDTO> getAvailableCopiesByBookId(@PathVariable Long bookId) {
    return copyBookService.getAvailableCopiesByBookId(bookId).stream()
            .map(CopyBookDTO::fromEntity)
            .collect(Collectors.toList());
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @PostMapping
  public ResponseEntity<CopyBookDTO> createCopyBook(@RequestBody CreateCopyBookDTO createCopyBookDTO) {
    CopyBook copy = copyBookService.createCopyBook(
            createCopyBookDTO.getCode(),
            createCopyBookDTO.getBookId());

    return ResponseEntity.created(URI.create("/api/v1/copybook/" + copy.getId()))
            .body(CopyBookDTO.fromEntity(copy));
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/available")
  public List<CopyBookDTO> getAvailableCopies() {
    return copyBookService.getAvailableCopies().stream()
            .map(CopyBookDTO::fromEntity)
            .collect(Collectors.toList());
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/code/{code}")
  public ResponseEntity<CopyBookDTO> getCopyByCode(@PathVariable String code) {
    CopyBook copy = copyBookService.getCopyByCode(code);
    return ResponseEntity.ok(CopyBookDTO.fromEntity(copy));
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/copies/available/{bookId}")
  public List<CopyBookDTO> getAvailableCopiesByBook(@PathVariable Long bookId) {
    return copyBookService.getAvailableCopiesByBookId(bookId).stream()
            .map(CopyBookDTO::fromEntity)
            .collect(Collectors.toList());
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @PutMapping("/{id}/status")
  public ResponseEntity<CopyBookDTO> updateCopyStatus(
          @PathVariable Long id,
          @RequestBody UpdateStatusCopyBookDTO updateStatusCopyBookDTO) {

    CopyBook updatedCopy = copyBookService.updateCopyStatus(id, updateStatusCopyBookDTO.getStatus());
    return ResponseEntity.ok(CopyBookDTO.fromEntity(updatedCopy));
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/{id}/available")
  public ResponseEntity<Boolean> isCopyAvailable(@PathVariable Long id) {
    boolean available = copyBookService.isCopyAvailable(id);
    return ResponseEntity.ok(available);
  }
}
