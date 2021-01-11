package me.javapacker.steps.api;

public interface PackerRepository {

    void packApplication();

    void setPackerStep(final PackerStep packerStep);

}
