package me.javapacker.app.impl;

import me.javapacker.app.api.PackerApplication;
import me.javapacker.config.PackerConfiguration;
import me.javapacker.file.api.PackerFilesManager;
import me.javapacker.file.impl.FactoryPackerFiles;
import me.javapacker.steps.api.PackerRepository;
import me.javapacker.steps.api.PackerStep;
import me.javapacker.steps.impl.*;

public class JavaPacker extends PackerApplication {

    public static JavaPacker INSTANCE;
    private PackerRepository packerRepository;

    @Override
    public void enableApp() {
        INSTANCE = this;

        try {
            PackerConfiguration.INSTANCE.readConfiguration();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.packerRepository = new PackerRepositoryImpl();
        final PackerFilesManager packerFilesManager = FactoryPackerFiles.createPackerFilesManager(PackerConfiguration.INSTANCE.getInputPath(), PackerConfiguration.INSTANCE.getOutputPath());
        final PackerStep packerStep = new S01ReadingJar(packerFilesManager);
        packerStep.linkWith(new S02EncryptClasses(packerFilesManager))
                .linkWith(new S03TrashingResource(packerFilesManager))
                .linkWith(new S04SavingKey(packerFilesManager))
                .linkWith(new S05JoinLoader(packerFilesManager))
                .linkWith(new S06AttachManifest(packerFilesManager));
        this.packerRepository.setPackerStep(packerStep);
        this.packerRepository.packApplication();
    }


}
