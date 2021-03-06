package io.mackness.Controllers;

import io.mackness.Entities.Book;
import io.mackness.Repositories.ReadingListRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class ReadingListController {

    private ReadingListRepository readingListRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    public ReadingListController(ReadingListRepository readingListRepository){
        this.readingListRepository = readingListRepository;
    }

    @RequestMapping(value="/readingList/{reader}", method=RequestMethod.GET)
    public String readersBooks(@PathVariable("reader") String reader, Model model){
        List<Book> readingList = readingListRepository.findByReader(reader);
        if(reader != null){
            model.addAttribute("books", readingList);
        }
        return "readingList";
    }

    @RequestMapping(value="/readingList/{reader}", method=RequestMethod.POST)
    public String addToReadingList(@PathVariable("reader") String reader, Book book, @RequestBody String requestString){
        logger.error(requestString);
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/readingList/{reader}";
    }
}
