package infrastructure;

import domain.material.materials.Material;
import domain.material.materials.Options;
import domain.material.materials.Tree;
import domain.material.MaterielRepository;
import infrastructure.exceptions.DBException;

import java.util.List;

public class DBMaterial implements MaterielRepository {
    private final Database database;
    
    public DBMaterial(Database database) {
        this.database = database;
    }
    
    
    @Override
    public List<Material> getAllMaterials() throws DBException {
        return null;
    }
    
    @Override
    public Material getMaterialById(int id) throws DBException {
        return null;
    }
    
    @Override
    public List<Tree> getAllTrees() throws DBException {
        return null;
    }
    
    @Override
    public Tree getTreeByName(String name) throws DBException {
        return null;
    }
    
    @Override
    public Tree updateTree(Tree tree) throws DBException {
        return null;
    }
    
    @Override
    public List<Options> getAllOptions() throws DBException {
        return null;
    }
    
    @Override
    public Options getOptionByName(String name) throws DBException {
        return null;
    }
    
    @Override
    public Options updateOption(Options option) throws DBException {
        return null;
    }
    
    @Override
    public boolean deleteMaterialById(int id) throws DBException {
        return false;
    }
    
    @Override
    public Tree createTree(Tree tree) throws DBException {
        return null;
    }
    
    @Override
    public Options createOption(Options option) throws DBException {
        return null;
    }
}
