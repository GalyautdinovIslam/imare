package ru.itis.imare.exceptions.notfound;

import ru.itis.imare.exceptions.NotFoundException;

import java.io.IOException;

public class FileNotFoundException extends NotFoundException {

    public FileNotFoundException() {
    }

    public FileNotFoundException(IOException e) {
        super(e);
    }
}
