package cracker.level;

import cracker.model.*;

import java.util.ArrayList;

public class FirstLevel extends AbstractLevel {
	@Override
	public void init() {
		map.setLives(10);
		map.setMoney(150);
		Path path =
				new Path(new Position(0, 445), new Position(255, 445), new Position(255, 120), new Position(505, 120),
						new Position(505, 625), new Position(775, 625), new Position(775, 280),
						new Position(1024, 280));
		map.addPath(path);
		addWave(2000, 10000, MobType.SKELETON, 10, path);
		addWave(13000, 10000, MobType.SLIME, 4, path);
		addWave(24000, 10000, MobType.GHOST, 10, path);
		addWave(35000, 10000, MobType.SKELETON, 20, path);
		addWave(46000, 20000, MobType.SLIME, 10, path);
		addWave(57000, 10000, MobType.SKELETON, 40, path);
		addWave(57000, 10000, MobType.GHOST, 15, path);
	}
	@Override
	public FirstLevel getLevel(){
		this.map.getWaves().clear();
		this.map.getTowers().clear();
		return new FirstLevel();
	}


}
