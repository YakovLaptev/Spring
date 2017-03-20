package dao;

import domain.Task;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Yakov
 */
@Repository
public class TaskDaoImpl implements TaskDao {

    @Autowired
    private DataSource datasource;
    private Connection connection;

    public Connection getConnection() {
        try {
            connection = datasource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(TaskDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(TaskDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Task> listAllTasks() {
        try {
            ArrayList<Task> taskList = new ArrayList<Task>();
            String query = "SELECT * FROM task";
            PreparedStatement stmt = getConnection().prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setDueDate(resultSet.getDate("due_date"));
                taskList.add(task);
            }
            return taskList;
        } catch (Exception e) {
            throw new RuntimeException("An error has occurred in listAllTasks method", e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public Task addTask(Task task) {
        try {
            String query = "INSERT INTO task (`name`,`description`,`due_date`) VALUES (?, ?, ?)";
            PreparedStatement stmt = getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, task.getName());
            stmt.setString(2, task.getDescription());
            stmt.setTimestamp(3, new Timestamp(task.getDueDate().getTime()));
            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.next()) {
                task.setId(resultSet.getInt(1));
                return task;
            }
            throw new Exception("Task not added to db");
        } catch (Exception e) {
            throw new RuntimeException("An error has occurred in addTask method", e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public Task getTaskById(Integer taskId) {
        try {
            Task task = new Task();
            String sql = "SELECT * FROM task WHERE id=?";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1, taskId);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                task.setId(resultSet.getInt("id"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setDueDate(resultSet.getDate("due_date"));
                return task;
            }
            throw new Exception("Task not added to db");
        } catch (Exception e) {
            throw new RuntimeException("An error has occurred in getTaskById method", e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public Task updateTask(Task task) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteTask(Integer taskId) {
        try {
            String sql = "DELETE FROM task WHERE id=?";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1, taskId);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new RuntimeException("An error has occurred in deleteTask method", e);
        } finally {
            closeConnection();
        }
    }
}
