package infrastructure;

import api.Utils;
import domain.items.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBItems implements ItemRepository {
    
    @Override
    public Item findItem(int id) throws ItemNotFound {
        try (Connection conn = Database.getConnection()) {
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Items WHERE id=?;")){
                ps.setInt(1,id);
                ResultSet rs = ps.executeQuery();
            
                if(rs.next()) {
                    int iid = rs.getInt(1);
                    String name = rs.getString(2);
                    
                    return new Item(iid, name);
                }
                
                throw new ItemNotFound(id);
            }} catch (SQLException e) {
            throw new ItemNotFound("No items found!");
        }
    }
    
    @Override
    public Iterable<Item> findAllItems() throws ItemNotFound {
        List<Item> tmpList = new ArrayList<>();
        
        try (Connection conn = Database.getConnection()) {
            try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Items;")){
                ResultSet rs = ps.executeQuery();
            
                while(rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                
                    tmpList.add(new Item(id, name));
                }
                
                return tmpList;
                
            }} catch (SQLException e) {
            throw new ItemNotFound("No items found!");
        }
    }
    
    @Override
    public Item createItem(String itemName) throws InvalidItem {
        try (Connection conn = Database.getConnection()) {
            String sql = "INSERT INTO Items(name) VALUE (?);";
        
            try(PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                ps.setString(1, Utils.removeHtml(itemName));
                ps.executeUpdate();
            
                ResultSet rs = ps.getGeneratedKeys();
                
                if (rs.next()) {
                    return new Item(rs.getInt(1), Utils.removeHtml(itemName));
                }
                
                throw new InvalidItem();
            }} catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
