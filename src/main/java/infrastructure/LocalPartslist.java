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
            localPartlist.add(calculateBoardsUnderStarFronBack(carport));
            localPartlist.add(calculateBoardsUnderStarSide(carport));
            localPartlist.add(calculateBoardsOverStarFronBack(carport));
            localPartlist.add(calculateBoardsOverStarSide(carport));
            localPartlist.add(calculateRaftsStraps(carport));
            localPartlist.add(calculateRaftsRoof(carport));
            localPartlist.add(calculatePolesFoundation(carport));
            localPartlist.add(calculateBoardsWaterStarSide(carport));
            localPartlist.add(calculateBoardsWaterStartEnd(carport));
            localPartlist.add(calculateRoofingRoffingBig(carport));
            localPartlist.add(calculateRoofingRoffingSmall(carport));
            localPartlist.add(calculateBottomScrewsRoof(carport));
            localPartlist.add(calculatePerforatedTapeWindshield(carport));
            localPartlist.add(calculateUniversalRaftersRight(carport));
            localPartlist.add(calculateUniversalRaftersLeft(carport));
            localPartlist.add(calculateScrewStarWaterBoard(carport));
            localPartlist.add(calculateFittingScrewsUniversalFittings(carport));
            localPartlist.add(calculateBoardBoltPole(carport));
            localPartlist.add(calculateSquareDiscsPole(carport));
            if(carport.hasShed()){
                localPartlist.add(calculateLathsDoor(carport));
                localPartlist.add(calculateRulesShedGables(carport));
                localPartlist.add(calculateRulesShedSide(carport));
                localPartlist.add(calculateRaftsShedStraps(carport));
                localPartlist.add(calculateBoardsShedCladding(carport));
                localPartlist.add(calculateScrewOuterCladding400(carport));
                localPartlist.add(calculateScrewInnerCladding300(carport));
                localPartlist.add(calculateBarnDoorHandleShed(carport));
                localPartlist.add(calculateHingeShed(carport));
                localPartlist.add(calculateAngleBracketShed(carport));
            }
        } else {

            localPartlist.add(calculateBoardsWindshield(carport));
            localPartlist.add(calculateBoardsStarSide(carport));
            localPartlist.add(calculateRaftsCustomRoof(carport));
            localPartlist.add(calculatePolesFoundation(carport));
            localPartlist.add(calculateRaftsStrapsPeak(carport));
            localPartlist.add(calculateBoardsWindshieldsBoard(carport));
            localPartlist.add(calculateBoardsWindshieldsGavel(carport));
            localPartlist.add(calculateBoardsRoofBattens(carport));
            localPartlist.add(calculateLathsRoof(carport));
            localPartlist.add(calculateLathsTopLath(carport));
            localPartlist.add(calculateDobbeltRoofBattens(carport));
            localPartlist.add(calculateBackStoneTopLath(carport));
            localPartlist.add(calculateTopLayerRafters(carport));
            localPartlist.add(calculateBackstoneFittingsBackStone(carport));
            localPartlist.add(calculateRoofBinderRoofTiles(carport));
            localPartlist.add(calculateUniversalRaftersRightPeak(carport));
            localPartlist.add(calculateUniversalRaftersLeftPeak(carport));
            localPartlist.add(calculateScrewStarWaterBoardPeak(carport));
            localPartlist.add(calculateFittingScrewsUniversalFittingsPeak(carport));
            localPartlist.add(calculateScrewRoofBattens(carport));
            localPartlist.add(calculateBoardBoltPolePeak(carport));
            localPartlist.add(calculateSquareDiscsPolePeak(carport));
            if(carport.hasShed()){
                localPartlist.add(calculateBoardsWaterStartEndPeak(carport));
                localPartlist.add(calculateRaftsShedStrapsPeak(carport));
                localPartlist.add(calculateRulesShedSidePeak(carport));
                localPartlist.add(calculateRulesShedGablesPeak(carport));
                localPartlist.add(calculateBoardsShedCladdingPeak(carport));
                localPartlist.add(calculateLathsDoorPeak(carport));
                localPartlist.add(calculateBarnDoorHandleShed(carport));
                localPartlist.add(calculateHingeShed(carport));
                localPartlist.add(calculateAngleBracketShed(carport));
                localPartlist.add(calculateScrewOuterCladding200(carport));
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
        return new Part(material, amount, des);
    }

    private Part calculateScrewOuterCladding200(Carport carport) {
        Material material = null;

        int amount = (int) Math.ceil(((carport.getLength() * carport.getWidth()))  / 10000 / 7);
        String des = "til montering af yderste beklædning";

        for(Options o: optionsMaterials){
            if(o.getType() == Options.Type.Screw && o.getUsage() == Material.Usage.OuterCladding200){
                material = o;
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
        return new Part(material, amount, des);
    }

    private Part calculateBoardsShedCladdingPeak(Carport carport) {
        Material material = null;

        int amount = (int) Math.ceil(carport.getWidth()/2 - 32);
        double length = carport.getShed().getLength();
        String des = "til beklædning af skur 1 på 2";

        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Boards && t.getUsage() == Material.Usage.ShedCladding){
                t.setLength(length);
                material = t;
                break;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateRulesShedGablesPeak(Carport carport) {
        Material material = null;

        int amount = 6;
        double length = carport.getWidth();
        String des = "Løsholter i gavle af skur";

        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Rules && t.getUsage() == Material.Usage.ShedGables){
                t.setLength(length);
                material = t;
                break;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateRulesShedSidePeak(Carport carport) {
        Material material = null;

        int amount = (int) Math.floor(carport.getWidth()/90);
        double length = carport.getShed().getLength() + 15;
        String des = "Løsholter i siderne af skur";

        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Rules && t.getUsage() == Material.Usage.ShedSide){
                t.setLength(length);
                material = t;
                break;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateRaftsShedStrapsPeak(Carport carport) {
        Material material = null;

        int amount = 1;
        double length = carport.getWidth() + 120;
        String des = "Remme i sider, sadles ned i stolper ( skur del, deles)";

        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Rafts && t.getUsage() == Material.Usage.ShedStraps){
                t.setLength(length);
                material = t;
                break;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateSquareDiscsPolePeak(Carport carport) {
        Material material = null;

        int amount = (int) Math.ceil((carport.getLength()/89) * 2 + 6);

        String des = "Til montering af rem på stolper";

        for(Options o: optionsMaterials){
            if(o.getType() == Options.Type.SquareDiscs && o.getUsage() == Material.Usage.Pole){
                material = o;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateBoardBoltPolePeak(Carport carport) {
        Material material = null;

        int amount = (int) Math.ceil((carport.getLength()/89) * 2 + 6);

        String des = "Til montering af rem på stolper";

        for(Options o: optionsMaterials){
            if(o.getType() == Options.Type.BoardBolt && o.getUsage() == Material.Usage.Pole){
                material = o;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateScrewRoofBattens(Carport carport) {
        Material material = null;

        int amount = (int) Math.ceil((carport.getWidth() * carport.getLength() / 10000 / 12));

        String des = "til taglægter";

        for(Options o: optionsMaterials){
            if(o.getType() == Options.Type.Screw && o.getUsage() == Material.Usage.RoofBattens){
                material = o;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateFittingScrewsUniversalFittingsPeak(Carport carport) {
        Material material = null;

        int amount = 1;

        String des = "Til montering af universalbeslag + toplægte";

        for(Options o: optionsMaterials){
            if(o.getType() == Options.Type.FittingScrews && o.getUsage() == Material.Usage.UniversalFittings){
                material = o;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateScrewStarWaterBoardPeak(Carport carport) {
        Material material = null;

        int amount = 1;

        String des = "Til montering af Stern, vindskeder, vindkryds & vand bræt";

        for(Options o: optionsMaterials){
            if(o.getType() == Options.Type.Screw && o.getUsage() == Material.Usage.StarWaterBoard){
                material = o;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateUniversalRaftersLeftPeak(Carport carport) {
        Material material = null;

        int amount = (int) Math.ceil(carport.getLength() / 89);

        String des = "Til montering af spær på rem";

        for(Options o: optionsMaterials){
            if(o.getType() == Options.Type.Universal && o.getUsage() == Material.Usage.RaftersLeft){
                material = o;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateUniversalRaftersRightPeak(Carport carport) {
        Material material = null;

        int amount = (int) Math.ceil(carport.getLength() / 89);

        String des = "Til montering af spær på rem";

        for(Options o: optionsMaterials){
            if(o.getType() == Options.Type.Universal && o.getUsage() == Material.Usage.RaftersRight){
                material = o;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateRoofBinderRoofTiles(Carport carport) {
        Material material = null;

        int amount = 2;

        String des = "til montering af tagsten, alle ydersten + hver anden fastgøres";

        for(Options o: optionsMaterials){
            if(o.getType() == Options.Type.RoofBinder && o.getUsage() == Material.Usage.RoofTiles){
                material = o;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateBackstoneFittingsBackStone(Carport carport) {
        Material material = null;

        int amount = (int) Math.ceil((carport.getWidth() * carport.getLength() / 10000 / 1.23));

        String des = "Til montering af rygsten";

        for(Options o: optionsMaterials){
            if(o.getType() == Options.Type.BackstoneFittings && o.getUsage() == Material.Usage.BackStone){
                material = o;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateTopLayerRafters(Carport carport) {
        Material material = null;

        int amount = (int) Math.ceil(carport.getLength() / 89);

        String des = "monteres på toppen af spæret (til toplægte)";

        for(Options o: optionsMaterials){
            if(o.getType() == Options.Type.Rafters && o.getUsage() == Material.Usage.TopLayer){
                material = o;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateBackStoneTopLath(Carport carport) {
        Material material = null;

        int amount = (int) Math.ceil((carport.getWidth() * carport.getLength() / 10000 / 1.23));

        String des = "monteres på toplægte med medfølgende beslag se tagstens vejledning";

        for(Options o: optionsMaterials){
            if(o.getType() == Options.Type.BackStone && o.getUsage() == Material.Usage.TopLath){
                material = o;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateDobbeltRoofBattens(Carport carport) {
        Material material = null;

        int amount = (int) Math.ceil(carport.getWidth() - 60);

        String des = "monteres på taglægter 6 rækker af 24 sten på hver side af taget";

        for(Options o: optionsMaterials){
            if(o.getType() == Options.Type.Dobbelt && o.getUsage() == Material.Usage.RoofBattens){
                material = o;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateLathsTopLath(Carport carport) {
        Material material = null;

        int amount = 2;
        double length = carport.getWidth() + 100;
        String des = "toplægte til montering af rygsten lægges i toplægte holder";

        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Laths && t.getUsage() == Material.Usage.TopLath){
                t.setLength(length);
                material = t;
                break;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateLathsRoof(Carport carport) {
        Material material = null;

        int amount = (int) Math.floor((carport.getLength() * carport.getWidth()) / 10000 / 1.23);
        double length = carport.getLength() - 150;
        String des = "til montering på spær, 7 rækker lægter på hver skiftevis 1 hel & 1 halv lægte";

        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Laths && t.getUsage() == Material.Usage.Roof){
                t.setLength(length);
                material = t;
                break;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateBoardsRoofBattens(Carport carport) {
        Material material = null;

        int amount = 3;
        double length = carport.getWidth() * 2;
        String des = "til montering oven på tagfodslægte";

        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Boards && t.getUsage() == Material.Usage.RoofBattens){
                t.setLength(length);
                material = t;
                break;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateBoardsWindshieldsGavel(Carport carport) {
        Material material = null;

        int amount = (int) Math.floor(carport.getLength()/19);
        double length = carport.getShed().getLength() + 15;
        String des = "beklædning af gavle 1 på 2";

        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Boards && t.getUsage() == Material.Usage.WindshieldsGavel){
                t.setLength(length);
                material = t;
                break;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateBoardsWindshieldsBoard(Carport carport) {
        Material material = null;

        int amount = 2;
        double length = carport.getLength() - 150;
        String des = "Vand bræt på vindskeder";

        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Boards && t.getUsage() == Material.Usage.WindshieldsBoard){
                t.setLength(length);
                material = t;
                break;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateRaftsStrapsPeak(Carport carport) {
        Material material = null;

        int amount = 2;
        double length = carport.getWidth() + 120;
        String des = "Remme i sider, sadles ned i stolper Carport del";

        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Rafts && t.getUsage() == Material.Usage.Straps){
                t.setLength(length);
                material = t;
                break;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateRaftsCustomRoof(Carport carport) {
        Material material = null;

        int amount = (int) Math.floor(carport.getLength()/89);
        double length = carport.getWidth();
        String des = "byg-selv spær (skal samles) " + amount + " stk.";

        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Rafts && t.getUsage() == Material.Usage.CustomRoof){
                t.setLength(length);
                material = t;
                break;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateBoardsWaterStartEndPeak(Carport carport) {
        Material material = null;

        int amount = 1;
        double length = carport.getLength() - 190;
        String des = "Sternbrædder til siderne Skur del ( deles )";

        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Boards && t.getUsage() == Material.Usage.WaterStartEnd){
                t.setLength(length);
                material = t;
                break;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateBoardsStarSide(Carport carport) {
        Material material = null;

        int amount = 2;
        double length = carport.getLength() - 130;
        String des = "Sternbrædder til siderne Carport del";

        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Boards && t.getUsage() == Material.Usage.StarSide){
                t.setLength(length);
                material = t;
                break;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateBoardsWindshield(Carport carport) {
        Material material = null;

        int amount = 2;
        double length = carport.getWidth() + 120;
        String des = "Vindskeder på rejsning";

        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Boards && t.getUsage() == Material.Usage.Windshield){
                t.setLength(length);
                material = t;
                break;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateAngleBracketShed(Carport carport) {
        Material material = null;

        int amount = (int) Math.ceil(((carport.getShed().getLength() * carport.getShed().getWidth())) / 10000  * 3);
        String des = "Til montering af løsholter i skur";

        for(Options o: optionsMaterials){
            if(o.getType() == Options.Type.AngleBracket && o.getUsage() == Material.Usage.Shed){
                material = o;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateHingeShed(Carport carport) {
        Material material = null;

        int amount = 2;
        String des = "Til skurdør";

        for(Options o: optionsMaterials){
            if(o.getType() == Options.Type.Hinge && o.getUsage() == Material.Usage.Shed){
                material = o;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateBarnDoorHandleShed(Carport carport) {
        Material material = null;

        int amount = 1;
        String des = "Til lås på dør i skur";

        for(Options o: optionsMaterials){
            if(o.getType() == Options.Type.BarnDoorHandle && o.getUsage() == Material.Usage.Shed){
                material = o;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateScrewInnerCladding300(Carport carport) {
        Material material = null;

        int amount = (int) Math.ceil(((carport.getLength() * carport.getWidth()))  / 10000  /20);
        String des = "til montering af inderste beklædning";

        for(Options o: optionsMaterials){
            if(o.getType() == Options.Type.Screw && o.getUsage() == Material.Usage.InnerCladding300){
                material = o;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateScrewOuterCladding400(Carport carport) {
        Material material = null;

        int amount = (int) Math.ceil(((carport.getLength() * carport.getWidth()))  / 10000 /20);
        String des = "til montering af yderste beklædning";

        for(Options o: optionsMaterials){
            if(o.getType() == Options.Type.Screw && o.getUsage() == Material.Usage.OuterCladding400){
                material = o;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateBoardsShedCladding(Carport carport) {
        Material material = null;

        int amount = (int) Math.ceil(carport.getLength() - carport.getWidth() + 20);
        double length = carport.getShed().getLength();
        String des = "til beklædning af skur 1 på 2";

        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Boards && t.getUsage() == Material.Usage.ShedCladding){
                t.setLength(length);
                material = t;
                break;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateRaftsShedStraps(Carport carport) {
        Material material = null;

        int amount = 1;
        double length = carport.getWidth() - 180;
        String des = "Remme i sider, sadles ned i stolper ( skur del, deles)";

        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Rafts && t.getUsage() == Material.Usage.ShedStraps){
                t.setLength(length);
                material = t;
                break;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateRulesShedSide(Carport carport) {
        Material material = null;

        int amount = (int) Math.floor(carport.getWidth()/150);
        double length = carport.getShed().getLength() + 30;
        String des = "løsholter til skur sider";

        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Rules && t.getUsage() == Material.Usage.ShedSide){
                t.setLength(length);
                material = t;
                break;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateRulesShedGables(Carport carport) {
        Material material = null;

        int amount = (int) Math.floor(carport.getLength()/65);
        double length = carport.getShed().getLength() + 60;
        String des = "løsholter til skur gavle";

        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Rules && t.getUsage() == Material.Usage.ShedGables){
                t.setLength(length);
                material = t;
                break;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateLathsDoor(Carport carport) {
        Material material = null;

        int amount = 1;
        double length = 420;
        String des = "til z på bagside af dør";

        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Laths && t.getUsage() == Material.Usage.Door){
                t.setLength(length);
                material = t;
                break;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateSquareDiscsPole(Carport carport) {
        Material material = null;

        int amount = (int) Math.ceil(((carport.getLength() * carport.getWidth()))  / 10000 /3.9);
        String des = "Til montering af rem på stolper";

        for(Options o: optionsMaterials){
            if(o.getType() == Options.Type.SquareDiscs && o.getUsage() == Material.Usage.Pole){
                material = o;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateBoardBoltPole(Carport carport) {
        Material material = null;

        int amount = (int) Math.ceil(((carport.getLength() * carport.getWidth()))  / 10000 /2.5);
        String des = "Til montering af rem på stolper";

        for(Options o: optionsMaterials){
            if(o.getType() == Options.Type.BoardBolt && o.getUsage() == Material.Usage.Pole){
                material = o;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateFittingScrewsUniversalFittings(Carport carport) {
        Material material = null;

        int amount = (int) Math.ceil(((carport.getLength() * carport.getWidth()))  / 10000 /15);
        String des = "Til montering af universalbeslag + hulbånd";

        for(Options o: optionsMaterials){
            if(o.getType() == Options.Type.FittingScrews && o.getUsage() == Material.Usage.UniversalFittings){
                material = o;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateScrewStarWaterBoard(Carport carport) {
        Material material = null;

        int amount = 1;
        String des = "Til montering af stern&vandbrædt";

        for(Options o: optionsMaterials){
            if(o.getType() == Options.Type.Screw && o.getUsage() == Material.Usage.StarWaterBoard){
                material = o;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateUniversalRaftersLeft(Carport carport) {
        Material material = null;

        int amount = (int) Math.ceil(carport.getLength()/55);
        String des = "Til montering af spær på rem";

        for(Options o: optionsMaterials){
            if(o.getType() == Options.Type.Universal && o.getUsage() == Material.Usage.RaftersLeft){
                material = o;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateUniversalRaftersRight(Carport carport) {
        Material material = null;

        int amount = (int) Math.ceil(carport.getLength()/55);
        String des = "Til montering af spær på rem";

        for(Options o: optionsMaterials){
            if(o.getType() == Options.Type.Universal && o.getUsage() == Material.Usage.RaftersRight){
                material = o;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculatePerforatedTapeWindshield(Carport carport) {
        Material material = null;

        int amount = (int) Math.ceil(((carport.getLength() * carport.getWidth())) / 10000 /20);
        String des = "Til vindkryds på spær";

        for(Options o: optionsMaterials){
            if(o.getType() == Options.Type.PerforatedTape && o.getUsage() == Material.Usage.Windshield){
                material = o;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateBottomScrewsRoof(Carport carport) {
        Material material = null;

        int amount = (int) Math.ceil(((carport.getLength() * carport.getWidth()) / 10000 /15));
        String des = "Skruer til tagplader";

        for(Options o: optionsMaterials){
            if(o.getType() == Options.Type.Screw && o.getUsage() == Material.Usage.Roof){
                material = o;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateRoofingRoffingSmall(Carport carport) {
        Material material = null;

        int amount = 6;
        double length = carport.getLength()/2 - 30;
        String des = "tagplader monteres på spær";

        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Roofing && t.getUsage() == Material.Usage.RoffingSmall){
                t.setLength(length);
                material = t;
                break;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateRoofingRoffingBig(Carport carport) {
        Material material = null;

        int amount = 6;
        double length = carport.getWidth();
        String des = "tagplader monteres på spær";

        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Roofing && t.getUsage() == Material.Usage.RoffingBig){
                t.setLength(length);
                material = t;
                break;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateBoardsWaterStartEnd(Carport carport) {
        Material material = null;

        int amount = 2;
        double length = carport.getLength()/2 - 30;
        String des = "vandbrædt på stern i forende";

        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Boards && t.getUsage() == Material.Usage.WaterStartEnd){
                t.setLength(length);
                material = t;
                break;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateBoardsWaterStarSide(Carport carport) {
        Material material = null;

        int amount = 4;
        double length = carport.getWidth() - 60;
        String des = "vandbrædt på stern i sider";

        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Boards && t.getUsage() == Material.Usage.WaterStarSide){
                t.setLength(length);
                material = t;
                break;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculatePolesFoundation(Carport carport) {
        Material material = null;
        int amount;
        if(carport.hasShed()){
            amount = 11;
        } else {
            amount = 7;
        }

        double length = 300;
        String des = "Stolper nedgraves 90 cm. i jord";

        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Poles && t.getUsage() == Material.Usage.Foundation){
                t.setLength(length);
                material = t;
                break;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateRaftsRoof(Carport carport) {
        Material material = null;

        int amount = (int) Math.floor(carport.getLength()/55);
        double length = carport.getWidth();
        String des = "Spær, monteres på rem";

        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Rafts && t.getUsage() == Material.Usage.Roof){
                t.setLength(length);
                material = t;
                break;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateRaftsStraps(Carport carport) {
        Material material = null;

        int amount = 2;
        double length = carport.getWidth();
        String des = "Remme i sider, sadles ned i stolper";

        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Rafts && t.getUsage() == Material.Usage.Straps){
                t.setLength(length);
                material = t;
                break;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateBoardsOverStarSide(Carport carport) {
        Material material = null;

        int amount = 4;
        double length = carport.getLength();
        String des = "oversternbrædder til siderne";

        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Boards && t.getUsage() == Material.Usage.OverStarSide){
                t.setLength(length);
                material = t;
                break;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateBoardsOverStarFronBack(Carport carport) {
        Material material = null;

        int amount = 2;
        double length = carport.getWidth();
        String des = "oversternbrædder til forenden";

        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Boards && t.getUsage() == Material.Usage.OverStarFronBack){
                t.setLength(length);
                material = t;
                break;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateBoardsUnderStarSide(Carport carport) {
        Material material = null;

        int amount = 4;
        double length = carport.getLength();
        String des = "understernbrædder til siderne";

        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Boards && t.getUsage() == Material.Usage.UnderStarSide){
                t.setLength(length);
                material = t;
                break;
            }
        }
        return new Part(material, amount, des);
    }

    private Part calculateBoardsUnderStarFronBack(Carport carport) {
        Material material = null;

        int amount = 4;
        double length = carport.getWidth();
        String des = "understernbrædder til for & bag ende";

        for(Tree t: treeMaterials){
            if(t.getType() == Tree.Type.Boards && t.getUsage() == Material.Usage.UnderStarFronBack){
                t.setLength(length);
                material = t;
                break;
            }
        }
        return new Part(material, amount, des);
    }


}
