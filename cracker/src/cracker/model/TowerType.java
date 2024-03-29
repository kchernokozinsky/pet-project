package cracker.model;

public enum TowerType {
	ARROW("Arrow Tower", 40, 150, 500, 50, ProjectileType.ARROW),
	BOMB("Bomb Tower", 600, 400, 6000, 200, ProjectileType.BOMB),
	MAGIC("Magic Tower", 300, 200, 1000, 100, ProjectileType.MAGIC);

	private final ProjectileType projectileType;
	private final String name;
	private final double damage;
	private final double range;
	private final long reload;
	private final long cost;

	TowerType(String name, double damage, double range, long reload, long cost, ProjectileType projectileType) {
		this.name = name;
		this.damage = damage;
		this.range = range;
		this.reload = reload;
		this.cost = cost;
		this.projectileType = projectileType;
	}

	public static TowerType findTowerType(String id) {
		for (TowerType type : values()) {
			if (("add" + type.toString() + "button").equalsIgnoreCase(id)) {
				return type;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public double getDamage() {
		return damage;
	}

	public double getRange() {
		return range;
	}

	public double getReload() {
		return reload;
	}

	public long getCost() {
		return cost;
	}

	public ProjectileType getProjectileType() {
		return projectileType;
	}
}
