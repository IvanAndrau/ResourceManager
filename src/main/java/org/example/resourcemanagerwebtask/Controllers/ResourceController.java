package org.example.resourcemanagerwebtask.Controllers;

import org.example.resourcemanagerwebtask.Models.Resource;
import org.example.resourcemanagerwebtask.Services.ResourceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resources")
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping
    public List<Resource> getResources(Long userId) {
        return resourceService.GetAll(userId);
    }

    @PostMapping
    public Resource createResource(@RequestBody Resource resource, Long userId) {
        return resourceService.Add(resource, userId);
    }

    @PatchMapping
    public void updateResource(@RequestBody Resource resource, Long userId) {
        //edit resource
    }

    @DeleteMapping
    public void deleteResource(@RequestParam Long resourceId, Long userId) {
        //delete certain resource
    }


}
/*
*     @Autowired
    private BookService bookService;

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "book-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "book-form";
    }

    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "book-form";
        }
        bookService.addBook(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}"              )//@PathVariable extracts values from the path
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        boolean isDeleted = bookService.deleteBookById(id);
        if (isDeleted) {
            return ResponseEntity.ok("Book deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }
    }

    @GetMapping("/{id}")
    public String viewBook(@PathVariable int id, Model model) {
        Book book = bookService.getBookById(id);
        if(book == null) {
            return "redirect:/books";
        }
        model.addAttribute("book", book);
        return "book-detail";
    }
* */