package com.automation.browserClient;

import com.automation.logger.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public final class OSUtils {

    private OSUtils() {
    }

    protected static boolean runCommandWithArgs(String... cmds) throws IOException, InterruptedException {
        Logger.out.debug("Running command: '{}'", Arrays.stream(cmds).collect(Collectors.joining(" ")));
        return Runtime.getRuntime().exec(cmds).waitFor() == 0;
    }

    protected static void runCommand(String command) throws IOException {
        Runtime.getRuntime().exec(command);
    }

    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    public static void killProcess(String name) {
        if (!isWindows()) {
            Logger.out.debug("Method is not implemented for this OS");
            return;
        }
        try {
            runCommandWithArgs("taskkill", "/F", "/T", "/IM", name);
        } catch (IOException | InterruptedException e) {
            Logger.out.trace(e.getMessage(), e);
        }
    }
}
