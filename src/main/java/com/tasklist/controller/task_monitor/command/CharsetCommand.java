package com.tasklist.controller.task_monitor.command;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Determines the system's charset.
 * Example output:
 * IBM866
 */
@Component
public class CharsetCommand extends Command<Charset> {

    static final String[] CHARSET_PREFIXES = new String[]{"", "windows-", "x-windows-", "IBM", "x-IBM"};

    public CharsetCommand() {
        super("chcp.com");
    }

    @Override
    public Charset execute() {
        init();

        String windowsCodePage = determineWindowsCodePage();
        Charset charset = findMatchingCharset(windowsCodePage);
        if (charset == null) charset = Charset.defaultCharset();
        return charset;
    }

    private String determineWindowsCodePage() {
        String windowsCodePage = null;
        try (InputStream in = process.getInputStream()) {
            windowsCodePage = new Scanner(in).skip(".*:").next();
        } catch (IOException e) {
            LOG.error("Error determining charset." + e.getMessage());
        }
        return windowsCodePage;
    }

    private Charset findMatchingCharset(String windowsCodePage) {
        Charset charset = null;
        for (String charsetPrefix : CHARSET_PREFIXES) {
            try {
                charset = Charset.forName(charsetPrefix + windowsCodePage);
                break;
            } catch (Throwable t) {
                LOG.warn(t.getMessage());
            }
        }
        return charset;
    }

}
