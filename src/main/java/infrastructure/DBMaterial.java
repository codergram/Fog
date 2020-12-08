package infrastructure;

import domain.material.exceptions.MaterialNotFound;
import domain.material.materials.Generic;
import domain.material.materials.Material;
import domain.material.MaterielRepository;
import domain.material.materials.Options;
import domain.material.materials.Tree;
import domain.user.exceptions.UserNotFound;
import infrastructure.exceptions.DBException;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class DBMaterial implements MaterielRepository {
    private final Database database;
    private static final Logger log = getLogger(DBMaterial.class);
    
    public DBMaterial(Database database) {
        this.database = database;
    }
    
    
    @Override
    public List<Material> getAllMaterials() throws DBException {
        List<Material> materials = new ArrayList();
        
        List<String> treeTypes = new ArrayList();
        for(Tree.Type tree: Tree.Type.values()){
            treeTypes.add(tree.name());
        }
    
        List<String> optionsType = new ArrayList();
        for(Options.Type option: Options.Type.values()){
            optionsType.add(option.name());
        }
        
        String sqlQuery = "SELECT materiale.id, materiale.name, materiale.price, materiale.unit, usage.name AS \"Usage\", type.name AS \"Type\" FROM materiale\n" +
                "JOIN `usage` ON materiale.id = usage.material_id\n" +
                "JOIN type ON usage.type_id = type.id";
        
        try (Connection conn = database.getConnection()) {
            try(PreparedStatement s = conn.prepareStatement(sqlQuery)){
                ResultSet rs = s.executeQuery();
                
                while(rs.next()) {
                    Material tmpMat = null;
                    
                    int id = rs.getInt("materiale.id");
                    String matName = rs.getString("materiale.name");
                    double matPrice = rs.getDouble("materiale.price");
                    String matUnit = rs.getString("materiale.unit");
                    String matUsage = rs.getString("Usage");
                    String matType = rs.getString("Type");
                    boolean typeMatched = false;
                    
                    for(String str: treeTypes){
                        if(str.equalsIgnoreCase(matType)){
                            tmpMat = new Tree(id, matName, matPrice, Material.Usage.valueOf(matUsage), Tree.Type.valueOf(matType), Material.Unit.valueOf(matUnit));
                            typeMatched = true;
                            break;
                        }
                    }
                    
                    if(!typeMatched) {
                        for (String str : optionsType) {
                            if (str.equalsIgnoreCase(matType)) {
                                tmpMat = new Options(id, matName, matPrice, Material.Usage.valueOf(matUsage), Options.Type.valueOf(matType), Material.Unit.valueOf(matUnit));
                            }
                        }
                    }
                    
                    materials.add(tmpMat);
                }
            }} catch (SQLException e) {
            log.error(e.getMessage());
        }
        return materials;
    }
    
    @Override
    public Material getMaterialById(int id) throws DBException {
        return null;
    }

    @Override
    public Material getMaterialByName(String name) throws DBException {
        return null;
    }

    @Override
    public Material updateMaterial(Material material) throws DBException {
        return null;
    }

    @Override
    public boolean deleteMaterialById(int id) throws DBException {
        return false;
    }
    
    @Override
    public List<Material> getAllRawMaterials() throws DBException {
        List<Material> materials = new ArrayList();
    
        List<String> treeTypes = new ArrayList();
        for(Tree.Type tree: Tree.Type.values()){
            treeTypes.add(tree.name());
        }
    
        List<String> optionsType = new ArrayList();
        for(Options.Type option: Options.Type.values()){
            optionsType.add(option.name());
        }
    
        String sqlQuery = "SELECT materiale.id, materiale.name, materiale.price, materiale.unit FROM materiale";
    
        try (Connection conn = database.getConnection()) {
            try(PreparedStatement s = conn.prepareStatement(sqlQuery)){
                ResultSet rs = s.executeQuery();
            
                while(rs.next()) {
                    Material tmpMat = null;
                
                    int id = rs.getInt("materiale.id");
                    String matName = rs.getString("materiale.name");
                    double matPrice = rs.getDouble("materiale.price");
                    String matUnit = rs.getString("materiale.unit");
                    
                    tmpMat = new Generic(id, matName, matPrice, Material.Unit.valueOf(matUnit));
                
                    materials.add(tmpMat);
                }
            }} catch (SQLException e) {
            log.error(e.getMessage());
        }
        return materials;
    }
    
    @Override
    public void updateMaterial(int id, String name, double price, Material.Unit unit) throws DBException {
        try (Connection conn = database.getConnection()){
            String query = "UPDATE materiale SET name=?, price=?, unit=? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement( query );
        
            ps.setString(1,name);
            ps.setDouble(2,price);
            ps.setString(3,unit.name());
            ps.setInt(4,id);
        
            //Execute the SQL statement to update the DB
            ps.executeUpdate();
        
        } catch ( SQLException ex ) {
            throw new DBException(ex);
        }
    }
    
    @Override
    public Material createMateriel(Material material) throws DBException {
        return null;
    }
}
