package br.com.supero.tasklist.api.dtos;

import br.com.supero.tasklist.domain.models.Status;
import br.com.supero.tasklist.domain.models.Task;

import javax.validation.constraints.NotBlank;

public class TaskDTO {

    private Long id;

    @NotBlank
    private String title;
    private Status status;
    @NotBlank
    private String description;
    private String creationDate;
    private String modificationDate;
    private String removedDate;
    private String conclusionDate;
    private boolean changeFields;

    public TaskDTO() {
    }

    public TaskDTO(Task entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.status = entity.getStatus();
        this.description = entity.getDescription();
        this.creationDate = entity.getCreationDate().toString();
        this.modificationDate = entity.getModificationDate() != null ? entity.getModificationDate().toString() : null;
        this.removedDate = entity.getRemovedDate() != null ? entity.getRemovedDate().toString() : null;
        this.conclusionDate = entity.getConclusionDate() != null ? entity.getConclusionDate().toString() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getRemovedDate() {
        return removedDate;
    }

    public void setRemovedDate(String removedDate) {
        this.removedDate = removedDate;
    }

    public String getConclusionDate() {
        return conclusionDate;
    }

    public void setConclusionDate(String conclusionDate) {
        this.conclusionDate = conclusionDate;
    }

    public boolean isChangeFields() {
        return changeFields;
    }

    public void setChangeFields(boolean changeFields) {
        this.changeFields = changeFields;
    }
}
