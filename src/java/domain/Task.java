
package domain;

import java.util.Date;

/**
 *
 * @author Yakov
 */
public class Task {
    /* Идентификатор задачи */ 
    private Integer id; 
    /* Название задачи */ 
    private String name; 
    /* Описание задачи */ 
    private String description; 
    /* Срок выполнения */ 
    private Date dueDate; 

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

}
