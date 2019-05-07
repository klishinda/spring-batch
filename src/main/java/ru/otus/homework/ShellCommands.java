package ru.otus.homework;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.bookRepository.BookRepository;
import ru.otus.homework.model.postgresql.Book;

import java.util.*;


@ShellComponent
public class ShellCommands {
    private BookRepository bookRepository;
    private JobLauncher jobLauncher;
    private Job job;

    public ShellCommands(BookRepository bookRepository, JobLauncher jobLauncher, Job job) {
        this.bookRepository = bookRepository;
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    // Testing connection with Postgresql
    @ShellMethod("Get book by id")
    private Book getBookById(@ShellOption Long id) {
        return bookRepository.findBookById(id);
    }

    @ShellMethod("Get all books")
    private List<String> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<String> str = new ArrayList<>();
        for (Book b : books) {
            str.add(b.getName() + b.getPages());
        }
        return str;
    }

    // Run batch
    @ShellMethod("Start converting")
    public void startJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        jobLauncher.run(job, new JobParameters());
    }
}
