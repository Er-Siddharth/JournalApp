package com.AsiaAutmation.JournalApp.Service;

import com.AsiaAutmation.JournalApp.Entity.JournalEntry;
import com.AsiaAutmation.JournalApp.Entity.Users;
import com.AsiaAutmation.JournalApp.Repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JournalService {

    @Autowired
    JournalRepository journalRepository;
    @Autowired
    UserService userService;

    public JournalEntry addEntry(JournalEntry entry){
        return journalRepository.save(entry);
    }

    public JournalEntry addEntry(JournalEntry entry, String username){
        Users user= userService.getUserByUserName(username);
        if(user!=null){
            JournalEntry newEntry = journalRepository.save(entry);
            user.getEntries().add(newEntry);
            userService.saveUser(user);
            return entry;
        }
        else throw new RuntimeException("Cannot find user to add Entry");
    }

    public List<JournalEntry> getAllEntries(String username){
        Users user= userService.getUserByUserName(username);
//        if(user!=null){
            return user.getEntries();
//        }
//       else throw new UsernameNotFoundException("User not found");
    }

    @Transactional
    public void  deleteEntry(ObjectId id, String username) throws RuntimeException{
        Users user= userService.getUserByUserName(username);
        if(user!=null){
            journalRepository.deleteById(id);
            if(user.getEntries().removeIf(x-> x.getId().equals(id))) {
                userService.saveUser(user);
            }
            else throw new InvalidParameterException("Entry no longer exist");
        }
        else throw new UsernameNotFoundException("User not found");
    }

    @Transactional
    public JournalEntry updateEntry(JournalEntry entry, ObjectId id, String username)throws RuntimeException{
        Users user= userService.getUserByUserName(username);
        List <JournalEntry> existingEntry = user.getEntries().stream().filter(x-> x.getId().equals(id)).toList();
        if(existingEntry.isEmpty()) throw new RuntimeException("Entry no longer exist");
        else {
           JournalEntry existing = existingEntry.getFirst();
            existing.setTitle(entry.getTitle() != null && !entry.getTitle().isEmpty() ? entry.getTitle() : existing.getTitle());
            existing.setContent(entry.getContent() != null && !entry.getContent().isEmpty() ? entry.getContent() : existing.getContent());
            existing.setDate(entry.getDate() != null && !entry.getDate().equals("") ? entry.getDate() : existing.getDate());
           return this.addEntry(existing);
        }
    }

    public Optional<JournalEntry> findEntry(ObjectId id, String username) {
        Users user= userService.getUserByUserName(username);
        List<JournalEntry> entries = user.getEntries().stream().filter(x-> x.getId().equals(id)).toList();
        if(entries.size()>0) return Optional.of(entries.get(0));
        return journalRepository.findById(id);

    }
}
