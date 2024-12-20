package com.AsiaAutmation.JournalApp.Controller;

import com.AsiaAutmation.JournalApp.Entity.JournalEntry;
import com.AsiaAutmation.JournalApp.Service.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.internal.CustomizerRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/JournalApp")
public class JournalController {

    @Autowired
    JournalService journalService;

    @PostMapping("/add")
    public ResponseEntity<?> addUserEntry(@RequestBody JournalEntry entry) {
        try {
            String username  = SecurityContextHolder.getContext().getAuthentication().getName();
            entry.setDate(LocalDateTime.now());
            return new ResponseEntity<>(journalService.addEntry(entry, username), HttpStatus.CREATED);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllEntries")
    public ResponseEntity<?> getAllEntries() {
        try {
            String username  = SecurityContextHolder.getContext().getAuthentication().getName();
            List<JournalEntry> entries = journalService.getAllEntries(username);
            if (entries != null && !entries.isEmpty()) {
                return new ResponseEntity<>(entries, HttpStatus.FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId myId) {
        String username  = SecurityContextHolder.getContext().getAuthentication().getName();
        try{
            journalService.deleteEntry(myId, username);
            return ResponseEntity.ok().body("Deleted");
        }
        catch(UsernameNotFoundException ex){
            return new ResponseEntity<>("Invalid User",HttpStatus.BAD_REQUEST);
        }
        catch(InvalidParameterException ex){
            return new ResponseEntity<>("Invalid JournalEntry",HttpStatus.NO_CONTENT);
        }
        catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEntry(@RequestBody JournalEntry entry, @PathVariable ObjectId id){
        try {
            String username  = SecurityContextHolder.getContext().getAuthentication().getName();
            return ResponseEntity.ok().body(journalService.updateEntry(entry, id, username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<?> findEntry(@PathVariable ObjectId myId) {
        String username  = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<JournalEntry> entry = journalService.findEntry(myId,username);
        if (entry.isPresent()) {
            return ResponseEntity.ok().body(entry.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
