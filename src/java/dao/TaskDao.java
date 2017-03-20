/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Task;
import java.util.List;

/**
 *
 * @author Yakov
 */
public interface TaskDao {

    /* Возвращает список всех задач * @return Список задач */
    List<Task> listAllTasks();

    /* Получение задачи по идентификатору * @param taskId * @return Искомая задача */
    Task getTaskById(Integer taskId);

    /* Добавление новой задачи * @param task Новая задача * @return Добавленная задача с установленным идентификатором */
    Task addTask(Task task);

    /* Обновление существующей задачи * @param task Задача с идентификатором и новыми полями * @return Обновленная задача */
    Task updateTask(Task task);

    /* Удаление существующей задачи * @param taskId Идентификатор задачи */
    boolean deleteTask(Integer taskId);
}
