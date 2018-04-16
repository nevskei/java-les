package com.simbirsoft.java.include;

import java.io.File;
import java.io.FilenameFilter;

public class ExtensionFilter implements FilenameFilter {
 
    private final String extension;

    public ExtensionFilter(String ext) {
        extension = ext;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(extension);
    }
}
