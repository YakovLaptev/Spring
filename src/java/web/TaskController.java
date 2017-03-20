package web;

import dao.TaskDao;
import domain.Task;
import exception.NotFoundException;
import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Yakov
 */
@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskDao taskDao;

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND,
            reason = "Entity with specified id not found")
    public void handleNotFoundException(
            NotFoundException ex,
            HttpServletResponse response) throws IOException {
    }

    @RequestMapping(method = RequestMethod.GET)
    public String listAllTasks(Model ui) {
        ui.addAttribute("taskList", taskDao.listAllTasks());
        return "task/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getTaskById(
            @PathVariable("id") Integer taskId,
            Model ui) throws NotFoundException {
        Task task = taskDao.getTaskById(taskId);
        if (task == null) {
            throw new NotFoundException();
        }
        ui.addAttribute("task", task);
        return "task/show";
    }

    @RequestMapping(params = "addForm", method = RequestMethod.GET)
    public String addTaskForm(Model ui) {
        ui.addAttribute("task", new Task());
        return "task/create";
    }

    @RequestMapping(params = "addForm", method = RequestMethod.POST)
    public String addTask(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("due_date")
            @DateTimeFormat(pattern = "dd-MM-yyyy") Date dueDate) {
        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        task.setDueDate(dueDate);
        task = taskDao.addTask(task);
        return "redirect:/tasks/" + task.getId();
    }

    @RequestMapping(value = "/{id}", 
                    params = "delete", 
                    method = RequestMethod.GET)
    public String deleteTask(@PathVariable("id") Integer taskId) throws NotFoundException {
        if (!taskDao.deleteTask(taskId)) {
            throw new NotFoundException();
        }
        return "redirect:/tasks";
    }
}
