package infrastructure;

import domain.carport.Carport;
import domain.material.materials.Material;
//import domain.material.materials.Tree;
import domain.material.materials.Options;
import domain.material.materials.Tree;
import domain.partslist.Part;
import domain.partslist.exceptions.PartslistServices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LocalPartslist implements PartslistServices {
    
    private final List<Tree> treeMaterials = new ArrayList<Tree>();
    private final List<Options> optionsMaterials = new ArrayList<Options>();
    
    
    public LocalPartslist(List<Material> materialsFromDb) {
        List<Material> materials = List.copyOf(materialsFromDb);
        
        for(Material m: materials){
            if(m instanceof Tree){
                treeMaterials.add((Tree) m);
            } else {
                optionsMaterials.add((Options) m);
            }
        }
    }
    
    @Override
    public List<Part> createPartsList() {
        List<Part> partslist = new ArrayList<>();
        return partslist;
    }

    @Override
    public List<Part> addToPartslist(Carport carport, List<Material> allMaterialsFromDB, List<Part> localPartlist) {

        Collections.reverse(allMaterialsFromDB);

        if(carport.getRoofType() == Carport.Roof.Flat){
            localPartlist.add(calculateBoardsUnderStarFronBack(carport, allMaterialsFromDB));
            localPartlist.add(calculateBoardsUnderStarSide(carport,allMaterialsFromDB));
            localPartlist.add(calculateBoardsOverStarFronBack(carport, allMaterialsFromDB));
            localPartlist.add(calculateBoardsOverStarSide(carport, allMaterialsFromDB));
            localPartlist.add(calculateRaftsStraps(carport, allMaterialsFromDB));
            localPartlist.add(calculateRaftsRoof(carport, allMaterialsFromDB));
            localPartlist.add(calculatePolesFoundation(carport, allMaterialsFromDB));
            localPartlist.add(calculateBoardsWaterStarSide(carport, allMaterialsFromDB));
            localPartlist.add(calculateBoardsWaterStartEnd(carport, allMaterialsFromDB));
            localPartlist.add(calculateRoofingRoffingBig(carport, allMaterialsFromDB));
            localPartlist.add(calculateRoofingRoffingSmall(carport, allMaterialsFromDB));
            localPartlist.add(calculateBottomScrewsRoof(carport, allMaterialsFromDB));
            localPartlist.add(calculatePerforatedTapeWindshield(carport, allMaterialsFromDB));
            localPartlist.add(calculateUniversalRaftersRight(carport, allMaterialsFromDB));
            localPartlist.add(calculateUniversalRaftersLeft(carport, allMaterialsFromDB));
            localPartlist.add(calculateScrewStarWaterBoard(carport, allMaterialsFromDB));
            localPartlist.add(calculateFittingScrewsUniversalFittings(carport, allMaterialsFromDB));
            localPartlist.add(calculateBoardBoltPole(carport, allMaterialsFromDB));
            localPartlist.add(calculateSquareDiscsPole(carport, allMaterialsFromDB));
            if(carport.hasShed()){
                localPartlist.add(calculateLathsDoor(carport, allMaterialsFromDB));
                localPartlist.add(calculateRulesShedGables(carport, allMaterialsFromDB));
                localPartlist.add(calculateRulesShedSide(carport, allMaterialsFromDB));
                localPartlist.add(calculateRaftsShedStraps(carport, allMaterialsFromDB));
                localPartlist.add(calculateBoardsShedCladding(carport, allMaterialsFromDB));
                localPartlist.add(calculateScrewOuterCladding400(carport, allMaterialsFromDB));
                localPartlist.add(calculateScrewInnerCladding300(carport, allMaterialsFromDB));
                localPartlist.add(calculateBarnDoorHandleShed(carport, allMaterialsFromDB));
                localPartlist.add(calculateHingeShed(carport, allMaterialsFromDB));
                localPartlist.add(calculateAngleBracketShed(carport, allMaterialsFromDB));
            }
        } else {

            localPartlist.add(calculateBoardsWindshield(carport, allMaterialsFromDB));
            localPartlist.add(calculateBoardsStarSide(carport, allMaterialsFromDB));
            localPartlist.add(calculateRaftsCustomRoof(carport, allMaterialsFromDB));
            localPartlist.add(calculatePolesFoundation(carport, allMaterialsFromDB));
            localPartlist.add(calculateRaftsStrapsPeak(carport, allMaterialsFromDB));
            localPartlist.add(calculateBoardsWindshieldsBoard(carport, allMaterialsFromDB));
            localPartlist.add(calculateBoardsWindshieldsGavel(carport, allMaterialsFromDB));
            localPartlist.add(calculateBoardsRoofBattens(carport, allMaterialsFromDB));
            localPartlist.add(calculateLathsRoof(carport, allMaterialsFromDB));
            localPartlist.add(calculateLathsTopLath(carport, allMaterialsFromDB));
            localPartlist.add(calculateDobbeltRoofBattens(carport, allMaterialsFromDB));
            localPartlist.add(calculateBackStoneTopLath(carport, allMaterialsFromDB));
            localPartlist.add(calculateTopLayerRafters(carport, allMaterialsFromDB));
            localPartlist.add(calculateBackstoneFittingsBackStone(carport, allMaterialsFromDB));
            localPartlist.add(calculateRoofBinderRoofTiles(carport, allMaterialsFromDB));
            localPartlist.add(calculateUniversalRaftersRightPeak(carport, allMaterialsFromDB));
            localPartlist.add(calculateUniversalRaftersLeftPeak(carport, allMaterialsFromDB));
            localPartlist.add(calculateScrewStarWaterBoardPeak(carport, allMaterialsFromDB));
            localPartlist.add(calculateFittingScrewsUniversalFittingsPeak(carport, allMaterialsFromDB));
            localPartlist.add(calculateScrewRoofBattens(carport, allMaterialsFromDB));
            localPartlist.add(calculateBoardBoltPolePeak(carport, allMaterialsFromDB));
            localPartlist.add(calculateSquareDiscsPolePeak(carport, allMaterialsFromDB));
            if(carport.hasShed()){
                localPartlist.add(calculateBoardsWaterStartEndPeak(carport, allMaterialsFromDB));
                localPartlist.add(calculateRaftsShedStrapsPeak(carport, allMaterialsFromDB));
                localPartlist.add(calculateRulesShedSidePeak(carport, allMaterialsFromDB));
                localPartlist.add(calculateRulesShedGablesPeak(carport, allMaterialsFromDB));
                localPartlist.add(calculateBoardsShedCladdingPeak(carport, allMaterialsFromDB));
                localPartlist.add(calculateLathsDoorPeak(carport, allMaterialsFromDB));
                localPartlist.add(calculateBarnDoorHandleShed(carport, allMaterialsFromDB));
                localPartlist.add(calculateHingeShed(carport, allMaterialsFromDB));
                localPartlist.add(calculateAngleBracketShed(carport, allMaterialsFromDB));
                localPartlist.add(calculateScrewOuterCladding200(carport, allMaterialsFromDB));
                localPartlist.add(calculateScrewInnerCladding350(carport));
            }
        }

        return localPartlist;
    }

    private Part calculateScrewInnerCladding350(Carport carport) {
        Material material = null;

        int amount = (int) Math.ceil(((carport.getLength() * carport.getWidth()))  / 10000  /12);
        String des = "til montering af inderste beklædning";
        
        for(Options o: optionsMaterials){
            if(o.getType() == Options.Type.Screw && o.getUsage() == Material.Usage.InnerCladding350){
                material = o;
            }
        }

//        for(Material curretElement: allMaterialsFromDB){
//            if(curretElement.getType().equals("Screw") && curretElement.getUsage().equals("InnerCladding350")){
//                material = new Material(curretElement.getId(), curretElement.getName(), curretElement.getPrice(), "Pk.", "InnerCladding350", "Screw");
//                break;
//            }
//        }

        return new Part(material, amount, des);
    }

    private Part calculateScrewOuterCladding200(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = (int) Math.ceil(((carport.getLength() * carport.getWidth()))  / 10000 / 7);
        String des = "til montering af yderste beklædning";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Screw") && curretElement.getUsage().equals("OuterCladding200")){
                material = new Material(curretElement.getId(), curretElement.getName(), curretElement.getPrice(), curretElement.getUnitString(),"OuterCladding200", "Screw");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateLathsDoorPeak(Carport carport) {
        Material material = null;

        int amount = 1;
        double length = carport.getLength() - 150;
        String des = "til z på bagside af dør";
        
        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Laths && t.getUsage() == Material.Usage.Door){
                t.setLength(length);
                material = t;
                break;
            }
        }

//        for(Material curretElement: allMaterialsFromDB){
//            if(curretElement.getType().equals("Laths") && curretElement.getUsage().equals("Door")){
//                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(), curretElement.getUnitString(), "Door", "Laths");
//                break;
//            }
//        }

        return new Part(material, amount, des);
    }

    private Part calculateBoardsShedCladdingPeak(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = (int) Math.ceil(carport.getWidth()/2 - 32);
        double length = carport.getShed().getLength();
        String des = "til beklædning af skur 1 på 2";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Boards") && curretElement.getUsage().equals("ShedCladding")){
                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(), curretElement.getUnitString(), "ShedCladding", "Boards");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateRulesShedGablesPeak(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = 6;
        double length = carport.getWidth();
        String des = "Løsholter i gavle af skur";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Rules") && curretElement.getUsage().equals("ShedGables")){
                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(), curretElement.getUnitString(), "ShedGables", "Rules");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateRulesShedSidePeak(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = (int) Math.floor(carport.getWidth()/90);
        double length = carport.getShed().getLength() + 15;
        String des = "Løsholter i siderne af skur";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Rules") && curretElement.getUsage().equals("ShedSide")){
                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(), curretElement.getUnitString(),"ShedSide", "Rules");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateRaftsShedStrapsPeak(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = 1;
        double length = carport.getWidth() + 120;
        String des = "Remme i sider, sadles ned i stolper ( skur del, deles)";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Rafts") && curretElement.getUsage().equals("ShedStraps")){
                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(), curretElement.getUnitString(),"ShedStraps", "Rafts");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateSquareDiscsPolePeak(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = (int) Math.ceil((carport.getLength()/89) * 2 + 6);

        String des = "Til montering af rem på stolper";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("SquareDiscs") && curretElement.getUsage().equals("Pole")){
                material = new Material(curretElement.getId(), curretElement.getName(), curretElement.getPrice(), curretElement.getUnitString(),"Pole", "SquareDiscs");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateBoardBoltPolePeak(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = (int) Math.ceil((carport.getLength()/89) * 2 + 6);

        String des = "Til montering af rem på stolper";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("BoardBolt") && curretElement.getUsage().equals("Pole")){
                material = new Material(curretElement.getId(), curretElement.getName(), curretElement.getPrice(), curretElement.getUnitString(),"Pole", "BoardBolt");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateScrewRoofBattens(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = (int) Math.ceil((carport.getWidth() * carport.getLength() / 10000 / 12));

        String des = "til taglægter";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Screw") && curretElement.getUsage().equals("RoofBattens")){
                material = new Material(curretElement.getId(), curretElement.getName(), curretElement.getPrice(), curretElement.getUnitString(),"RoofBattens", "Screw");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateFittingScrewsUniversalFittingsPeak(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = 1;

        String des = "Til montering af universalbeslag + toplægte";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("FittingScrews") && curretElement.getUsage().equals("UniversalFittings")){
                material = new Material(curretElement.getId(), curretElement.getName(), curretElement.getPrice(), curretElement.getUnitString(),"UniversalFittings", "FittingScrews");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateScrewStarWaterBoardPeak(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = 1;

        String des = "Til montering af Stern, vindskeder, vindkryds & vand bræt";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Screw") && curretElement.getUsage().equals("StarWaterBoard")){
                material = new Material(curretElement.getId(), curretElement.getName(), curretElement.getPrice(), curretElement.getUnitString(),"StarWaterBoard", "Screw");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateUniversalRaftersLeftPeak(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = (int) Math.ceil(carport.getLength() / 89);

        String des = "Til montering af spær på rem";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Universal") && curretElement.getUsage().equals("RaftersLeft")){
                material = new Material(curretElement.getId(), curretElement.getName(), curretElement.getPrice(), curretElement.getUnitString(),"RaftersLeft", "Universal");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateUniversalRaftersRightPeak(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = (int) Math.ceil(carport.getLength() / 89);

        String des = "Til montering af spær på rem";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Universal") && curretElement.getUsage().equals("RaftersRight")){
                material = new Material(curretElement.getId(), curretElement.getName(), curretElement.getPrice(), curretElement.getUnitString(),"RaftersRight", "Universal");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateRoofBinderRoofTiles(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = 2;

        String des = "til montering af tagsten, alle ydersten + hver anden fastgøres";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("RoofBinder") && curretElement.getUsage().equals("RoofTiles")){
                material = new Material(curretElement.getId(), curretElement.getName(), curretElement.getPrice(), curretElement.getUnitString(),"RoofTiles", "RoofBinder");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateBackstoneFittingsBackStone(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = (int) Math.ceil((carport.getWidth() * carport.getLength() / 10000 / 1.23));

        String des = "Til montering af rygsten";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("BackstoneFittings") && curretElement.getUsage().equals("BackStone")){
                material = new Material(curretElement.getId(), curretElement.getName(), curretElement.getPrice(), curretElement.getUnitString(),"BackStone", "BackstoneFittings");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateTopLayerRafters(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = (int) Math.ceil(carport.getLength() / 89);

        String des = "monteres på toppen af spæret (til toplægte)";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Rafters") && curretElement.getUsage().equals("TopLayer")){
                material = new Material(curretElement.getId(), curretElement.getName(), curretElement.getPrice(), curretElement.getUnitString(),"TopLayer", "Rafters");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateBackStoneTopLath(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = (int) Math.ceil((carport.getWidth() * carport.getLength() / 10000 / 1.23));

        String des = "monteres på toplægte med medfølgende beslag se tagstens vejledning";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("BackStone") && curretElement.getUsage().equals("TopLath")){
                material = new Material(curretElement.getId(), curretElement.getName(), curretElement.getPrice(), curretElement.getUnitString(),"TopLath", "BackStone");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateDobbeltRoofBattens(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = (int) Math.ceil(carport.getWidth() - 60);

        String des = "monteres på taglægter 6 rækker af 24 sten på hver side af taget";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Dobbelt") && curretElement.getUsage().equals("RoofBattens")){
                material = new Material(curretElement.getId(), curretElement.getName(), curretElement.getPrice(), curretElement.getUnitString(),"RoofBattens", "Dobbelt");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateLathsTopLath(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = 2;
        double length = carport.getWidth() + 100;
        String des = "toplægte til montering af rygsten lægges i toplægte holder";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Laths") && curretElement.getUsage().equals("TopLath")){
                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(), curretElement.getUnitString(),"TopLath", "Laths");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateLathsRoof(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = (int) Math.floor((carport.getLength() * carport.getWidth()) / 10000 / 1.23);
        double length = carport.getLength() - 150;
        String des = "til montering på spær, 7 rækker lægter på hver skiftevis 1 hel & 1 halv lægte";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Laths") && curretElement.getUsage().equals("Roof")){
                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(), curretElement.getUnitString(),"Roof", "Laths");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateBoardsRoofBattens(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = 3;
        double length = carport.getWidth() * 2;
        String des = "til montering oven på tagfodslægte";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Boards") && curretElement.getUsage().equals("RoofBattens")){
                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(), curretElement.getUnitString(),"RoofBattens", "Boards");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateBoardsWindshieldsGavel(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = (int) Math.floor(carport.getLength()/19);
        double length = carport.getShed().getLength() + 15;
        String des = "beklædning af gavle 1 på 2";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Boards") && curretElement.getUsage().equals("WindshieldsGavel")){
                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(), curretElement.getUnitString(),"WindshieldsGavel", "Boards");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateBoardsWindshieldsBoard(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = 2;
        double length = carport.getLength() - 150;
        String des = "Vand bræt på vindskeder";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Boards") && curretElement.getUsage().equals("WindshieldsBoard")){
                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(), curretElement.getUnitString(),"WindshieldsBoard", "Boards");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateRaftsStrapsPeak(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = 2;
        double length = carport.getWidth() + 120;
        String des = "Remme i sider, sadles ned i stolper Carport del";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Rafts") && curretElement.getUsage().equals("Straps")){
                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(), curretElement.getUnitString(),"Straps", "Rafts");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateRaftsCustomRoof(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = (int) Math.floor(carport.getLength()/89);
        double length = carport.getWidth();
        String des = "byg-selv spær (skal samles) " + amount + " stk.";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Rafts") && curretElement.getUsage().equals("CustomRoof")){
                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(), curretElement.getUnitString(),"CustomRoof", "Rafts");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateBoardsWaterStartEndPeak(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = 1;
        double length = carport.getLength() - 190;
        String des = "Sternbrædder til siderne Skur del ( deles )";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Boards") && curretElement.getUsage().equals("WaterStartEndPeak")){
                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(), curretElement.getUnitString(),"WaterStartEndPeak", "Boards");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateBoardsStarSide(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = 2;
        double length = carport.getLength() - 130;
        String des = "Sternbrædder til siderne Carport del";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Boards") && curretElement.getUsage().equals("StarSide")){
                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(),curretElement.getUnitString(), "StarSide", "Boards");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateBoardsWindshield(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = 2;
        double length = carport.getWidth() + 120;
        String des = "Vindskeder på rejsning";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Boards") && curretElement.getUsage().equals("Windshield")){
                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(), curretElement.getUnitString(),"Windshield", "Boards");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateAngleBracketShed(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = (int) Math.ceil(((carport.getShed().getLength() * carport.getShed().getWidth())) / 10000  * 3);
        String des = "Til montering af løsholter i skur";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("AngleBracket") && curretElement.getUsage().equals("Shed")){
                material = new Material(curretElement.getId(), curretElement.getName(), curretElement.getPrice(), curretElement.getUnitString(),"Shed", "AngleBracket");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateHingeShed(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = 2;
        String des = "Til skurdør";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Hinge") && curretElement.getUsage().equals("Shed")){
                material = new Material(curretElement.getId(), curretElement.getName(), curretElement.getPrice(), curretElement.getUnitString(),"Shed", "Hinge");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateBarnDoorHandleShed(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = 1;
        String des = "Til lås på dør i skur";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("BarnDoorHandle") && curretElement.getUsage().equals("Shed")){
                material = new Material(curretElement.getId(), curretElement.getName(), curretElement.getPrice(), curretElement.getUnitString(),"Shed", "BarnDoorHandle");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateScrewInnerCladding300(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = (int) Math.ceil(((carport.getLength() * carport.getWidth()))  / 10000  /20);
        String des = "til montering af inderste beklædning";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Screw") && curretElement.getUsage().equals("InnerCladding300")){
                material = new Material(curretElement.getId(), curretElement.getName(), curretElement.getPrice(), curretElement.getUnitString(),"InnerCladding300", "Screw");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateScrewOuterCladding400(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = (int) Math.ceil(((carport.getLength() * carport.getWidth()))  / 10000 /20);
        String des = "til montering af yderste beklædning";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Screw") && curretElement.getUsage().equals("OuterCladding400")){
                material = new Material(curretElement.getId(), curretElement.getName(), curretElement.getPrice(), curretElement.getUnitString(),"OuterCladding400", "Screw");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateBoardsShedCladding(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = (int) Math.ceil(carport.getLength() - carport.getWidth() + 20);
        double length = carport.getShed().getLength();
        String des = "til beklædning af skur 1 på 2";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Boards") && curretElement.getUsage().equals("ShedCladding")){
                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(), curretElement.getUnitString(),"ShedCladding", "Boards");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateRaftsShedStraps(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = 1;
        double length = carport.getWidth() - 180;
        String des = "Remme i sider, sadles ned i stolper ( skur del, deles)";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Rafts") && curretElement.getUsage().equals("ShedStraps")){
                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(), curretElement.getUnitString(),"ShedStraps", "Rafts");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateRulesShedSide(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = (int) Math.floor(carport.getWidth()/150);
        double length = carport.getShed().getLength() + 30;
        String des = "løsholter til skur sider";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Rules") && curretElement.getUsage().equals("ShedSide")){
                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(), curretElement.getUnitString(),"ShedSide", "Rules");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateRulesShedGables(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = (int) Math.floor(carport.getLength()/65);
        double length = carport.getShed().getLength() + 60;
        String des = "løsholter til skur gavle";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Rules") && curretElement.getUsage().equals("ShedGables")){
                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(), curretElement.getUnitString(),"ShedGables", "Rules");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateLathsDoor(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = 1;
        double length = 420;
        String des = "til z på bagside af dør";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Laths") && curretElement.getUsage().equals("Door")){
                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(), curretElement.getUnitString(),"Door", "Laths");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateSquareDiscsPole(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = (int) Math.ceil(((carport.getLength() * carport.getWidth()))  / 10000 /3.9);
        String des = "Til montering af rem på stolper";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("SquareDiscs") && curretElement.getUsage().equals("Pole")){
                material = new Material(curretElement.getId(), curretElement.getName(), curretElement.getPrice(), curretElement.getUnitString(),"Pole", "SquareDiscs");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateBoardBoltPole(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = (int) Math.ceil(((carport.getLength() * carport.getWidth()))  / 10000 /2.5);
        String des = "Til montering af rem på stolper";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("BoardBolt") && curretElement.getUsage().equals("Pole")){
                material = new Material(curretElement.getId(), curretElement.getName(), curretElement.getPrice(), curretElement.getUnitString(),"Pole", "BoardBolt");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateFittingScrewsUniversalFittings(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = (int) Math.ceil(((carport.getLength() * carport.getWidth()))  / 10000 /15);
        String des = "Til montering af universalbeslag + hulbånd";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("FittingScrews") && curretElement.getUsage().equals("UniversalFittings")){
                material = new Material(curretElement.getId(), curretElement.getName(), curretElement.getPrice(), curretElement.getUnitString(),"UniversalFittings", "FittingScrews");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateScrewStarWaterBoard(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = 1;
        String des = "Til montering af stern&vandbrædt";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Screw") && curretElement.getUsage().equals("StarWaterBoard")){
                material = new Material(curretElement.getId(), curretElement.getName(), curretElement.getPrice(), curretElement.getUnitString(),"StarWaterBoard", "Screw");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateUniversalRaftersLeft(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = (int) Math.ceil(carport.getLength()/55);
        String des = "Til montering af spær på rem";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Universal") && curretElement.getUsage().equals("RaftersLeft")){
                material = new Material(curretElement.getId(), curretElement.getName(), curretElement.getPrice(), curretElement.getUnitString(),"RaftersLeft", "Universal");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateUniversalRaftersRight(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = (int) Math.ceil(carport.getLength()/55);
        String des = "Til montering af spær på rem";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Universal") && curretElement.getUsage().equals("RaftersRight")){
                material = new Material(curretElement.getId(), curretElement.getName(), curretElement.getPrice(), curretElement.getUnitString(),"RaftersRight", "Universal");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculatePerforatedTapeWindshield(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = (int) Math.ceil(((carport.getLength() * carport.getWidth())) / 10000 /20);
        String des = "Til vindkryds på spær";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("PerforatedTape") && curretElement.getUsage().equals("Windshield")){
                material = new Material(curretElement.getId(), curretElement.getName(), curretElement.getPrice(), curretElement.getUnitString(),"Windshield", "PerforatedTape");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateBottomScrewsRoof(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = (int) Math.ceil(((carport.getLength() * carport.getWidth()) / 10000 /15));
        String des = "Skruer til tagplader";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Screws") && curretElement.getUsage().equals("Roof")){
                material = new Material(curretElement.getId(), curretElement.getName(), curretElement.getPrice(), curretElement.getUnitString(),"Roof", "Screws");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateRoofingRoffingSmall(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = 6;
        double length = carport.getLength()/2 - 30;
        String des = "tagplader monteres på spær";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Roofing") && curretElement.getUsage().equals("RoffingSmall")){
                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(), curretElement.getUnitString(),"RoffingSmall", "Roofing");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateRoofingRoffingBig(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = 6;
        double length = carport.getWidth();
        String des = "tagplader monteres på spær";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Roofing") && curretElement.getUsage().equals("RoffingBig")){
                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(), curretElement.getUnitString(),"RoffingBig", "Roofing");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateBoardsWaterStartEnd(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = 2;
        double length = carport.getLength()/2 - 30;
        String des = "vandbrædt på stern i forende";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Boards") && curretElement.getUsage().equals("WaterStartEnd")){
                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(), curretElement.getUnitString(),"WaterStartEnd", "Boards");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateBoardsWaterStarSide(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = 4;
        double length = carport.getWidth() - 60;
        String des = "vandbrædt på stern i sider";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Boards") && curretElement.getUsage().equals("WaterStarSide")){
                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(), curretElement.getUnitString(),"WaterStarSide", "Boards");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculatePolesFoundation(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;
        int amount;
        if(carport.hasShed()){
            amount = 11;
        } else {
            amount = 7;
        }

        double length = 300;
        String des = "Stolper nedgraves 90 cm. i jord";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Poles") && curretElement.getUsage().equals("Foundation")){
                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(), curretElement.getUnitString(),"Foundation", "Poles");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateRaftsRoof(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = (int) Math.floor(carport.getLength()/55);
        double length = carport.getWidth();
        String des = "Spær, monteres på rem";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Rafts") && curretElement.getUsage().equals("Roof")){
                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(), curretElement.getUnitString(),"Roof", "Rafts");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateRaftsStraps(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = 2;
        double length = carport.getWidth();
        String des = "Remme i sider, sadles ned i stolper";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Rafts") && curretElement.getUsage().equals("Straps")){
                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(), curretElement.getUnitString(),"Straps", "Rafts");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateBoardsOverStarSide(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = 4;
        double length = carport.getLength();
        String des = "oversternbrædder til siderne";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Boards") && curretElement.getUsage().equals("OverStarSide")){
                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(), curretElement.getUnitString(),"OverStarSide", "Boards");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateBoardsOverStarFronBack(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = 2;
        double length = carport.getWidth();
        String des = "oversternbrædder til forenden";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Boards") && curretElement.getUsage().equals("OverStarFronBack")){
                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(), curretElement.getUnitString(),"OverStarFronBack", "Boards");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateBoardsUnderStarSide(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = 4;
        double length = carport.getLength();
        String des = "understernbrædder til siderne";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Boards") && curretElement.getUsage().equals("UnderStarSide")){
                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(), curretElement.getUnitString(),"UnderStarSide", "Boards");
                break;
            }
        }

        return new Part(material, amount, des);
    }

    private Part calculateBoardsUnderStarFronBack(Carport carport, List<Material> allMaterialsFromDB) {
        Material material = null;

        int amount = 4;
        double length = carport.getWidth();
        String des = "understernbrædder til for & bag ende";

        for(Material curretElement: allMaterialsFromDB){
            if(curretElement.getType().equals("Boards") && curretElement.getUsage().equals("UnderStarFronBack")){
                material = new Material(curretElement.getId(), curretElement.getName(), length, curretElement.getPrice(), curretElement.getUnitString(),"UnderStarFronBack", "Boards");
                break;
            }
        }

        return new Part(material, amount, des);
    }


}
