package domain.material.materials;

public abstract class Material {
  public enum Unit {
    Stk,
    Pk,
    Rulle,
    Sæt
  }

  public enum Usage {
    // Flat roof
    UnderStarFronBack,
    UnderStarSide,
    OverStarFronBack,
    OverStarSide,
    Door,
    ShedGables,
    ShedSide,
    Straps,
    ShedStraps,
    Roof,
    CustomRoof,
    Foundation,
    ShedCladding,
    WaterStarSide,
    WaterStartEnd,
    RoffingSmall,
    RoffingBig,
    FoffingSmall,
    Windshield,
    StarSide,
    ShedStarSide,
    GablesCladding,
    WindshieldsBoard,
    WindshieldsGavel,
    Crossings,
    RaftersRight,
    RaftersLeft,
    StarWaterBoard,
    UniversalFittings,
    Pole,
    InnerCladding,
    InnerCladding300,
    InnerCladding350,
    OuterCladding,
    OuterCladding200,
    OuterCladding400,
    Shed,
    RoofBattens,
    TopLath,
    Rafters,
    BackStone,
    RoofTiles,
    Star,
    TopLayer
  }

  protected final int id;
  protected final String name;
  protected final double price;
  protected final Enum<Usage> usage;
  protected final Enum<Unit> unit;

  protected Material(int id, String name, double price, Enum<Usage> usage, Enum<Unit> unit) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.usage = usage;
    this.unit = unit;
  }

  protected Material(String name, double price, Enum<Usage> usage, Enum<Unit> unit) {
    this.id = -1;
    this.name = name;
    this.price = price;
    this.usage = usage;
    this.unit = unit;
  }

  public Enum<Material.Usage> getUsage() {
    return usage;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public double getPrice() {
    return price;
  }

  public Enum<Unit> getUnit() {
    return unit;
  }

  public String getUnitString() {
    return unit.name();
  }

  public abstract String toString();

  public abstract String typeName();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Material material = (Material) o;

    return name != null ? name.equals(material.name) : material.name == null;
  }

  @Override
  public int hashCode() {
    return name != null ? name.hashCode() : 0;
  }
}
