package me.javapacker.steps.impl;

import me.javapacker.steps.api.PackerRepository;
import me.javapacker.steps.api.PackerStep;

import java.util.ArrayList;
import java.util.List;

public class PackerRepositoryImpl implements PackerRepository {

    private final List<PackerStep> packerSteps = new ArrayList<PackerStep>();
    private PackerStep packerStep;

    public void setPackerStep(PackerStep packerStep) {
        this.packerStep = packerStep;
    }

    public void packApplication() {
        if(!this.packerStep.completeStep())
        {

        }
    }
}
