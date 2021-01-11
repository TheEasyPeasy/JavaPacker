package me.javapacker.steps.api;

import me.javapacker.file.api.PackerFilesManager;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class PackerStep {

    private PackerStep nextStep;
    private final PackerFilesManager packerFilesManager;
    private static Logger LOGGER = Logger.getLogger("PackerStep");

    public PackerStep(final PackerFilesManager packerFilesManager) {
        this.packerFilesManager = packerFilesManager;
    }

    public PackerStep linkWith(final PackerStep nextStep) {
        this.nextStep = nextStep;
        return nextStep;
    }

    public abstract boolean completeStep();

    protected boolean checkNext()
    {
        if(this.nextStep == null)
        {
            return true;
        }
        return this.nextStep.completeStep();
    }


    protected void logMessage(final Level level, final String message)
    {
        LOGGER.log(level, message);
    }

    public PackerFilesManager getPackerFilesManager() {
        return packerFilesManager;
    }
}
