package br.com.vinicius.basiccrud.controllers;

import br.com.vinicius.basiccrud.dtos.ContactDto;
import br.com.vinicius.basiccrud.services.ContactService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<Page<ContactDto>> findAllContacts(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        //Endpoint to list all contacts
        return ResponseEntity.ok(contactService.findAllContacts(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDto> findContactById(@PathVariable Long id){
        //Endpoint to find contact by ID
        return ResponseEntity.ok(contactService.findContactById(id));
    }

    @GetMapping("/findBy")
    public ResponseEntity<Page<ContactDto>> findContactsByParameter(@RequestParam String parameter,
                                                                    @RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "10") int size){
        //Endpoint to find a contact by some parameters - by first name, last name, occupation or company
        return ResponseEntity.ok(contactService.findContactsByParameter(parameter, page, size));
    }

    @PostMapping
    public ResponseEntity<String> saveContact(@RequestBody @Valid ContactDto contactDto){
        //Endpoint to save a new contact
        return ResponseEntity.status(HttpStatus.CREATED).body(contactService.saveContact(contactDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateContact(@PathVariable @Valid Long id, @RequestBody ContactDto contactDto){
        //Endpoint to update an existing contact
        return ResponseEntity.ok(contactService.updateContact(id, contactDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteContact(@PathVariable Long id){
        //Endpoint to delete an existing contact
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}
