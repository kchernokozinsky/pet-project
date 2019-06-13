package cracker.level;

import cracker.model.*;

import java.util.ArrayList;

public class FirstLevel extends AbstractLevel {
	@Override
	public void init() {
		map.setLives(10);
		Path path = new Path(new Position(0, 404), new Position(230, 404), new Position(230, 80), new Position(480,
				80),
				new Position(480, 580), new Position(720, 580), new Position(755, 570), new Position(755, 240),
				new Position(1024, 240));
		map.addPath(path);
		ArrayList<Mob> mobs = new ArrayList<>();
		for (int i = 0; i < 11; i++) {
			Mob mob1 = new Mob(MobType.SKELETON, path);
			mobs.add(mob1);
//        Mob mob2 = new Mob(MobType.SKELETON, path);
//        Mob mob3 = new Mob(MobType.SLIME, path);
		}
		Wave wave = new Wave(mobs, 10000L, 0);
		map.addWave(wave);
	}
}