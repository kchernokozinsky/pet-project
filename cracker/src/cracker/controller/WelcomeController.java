package cracker.controller;

import cracker.level.AbstractLevel;
import cracker.level.FirstLevel;
import cracker.level.SecondLevel;
import cracker.level.ThirdLevel;
import cracker.model.Character;
import cracker.model.CharacterType;
import cracker.ui.CharacterView;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WelcomeController {
	ArrayList<CharacterView> characters;
	int characterNumber = 0;

	@FXML
	private ImageView charName;
	@FXML
	private ImageView charDescription;
	@FXML
	private ImageView gearWheel;
	@FXML
	private ImageView minimizeView;
	@FXML
	private ImageView crossView;
	@FXML
	private LevelController levelController;
	@FXML
	private AnchorPane welcomePane;
	@FXML
	private ImageView leftButton;
	@FXML
	private ImageView rightButton;
	@FXML
	private ImageView characterImage;
	@FXML
	private ImageView playButton;
	private AbstractLevel level;
	private int soundLevel = 1;
	private int musicLevel = 3;
	private MediaPlayer mediaPlayer;
	private MediaPlayer mediaPlayerOnClick;
	private SettingsController settingsController;

	@FXML
	public void changeVolume() {
		mediaPlayer.setVolume( musicLevel / 10.0);
		File file = new File("res/music/button6.wav");
		Media media = new Media(file.toURI().toString());
		mediaPlayerOnClick = new MediaPlayer(media);
		mediaPlayerOnClick.setVolume(soundLevel / 1000.0);
	}

	public ImageView getCharacterImage() {
		return characterImage;
	}

	public void setLevel(AbstractLevel level) {
		this.level = level;
	}

	@FXML
	public void onPlay() {
		if (level != null && level.isRunning())
			return;
		levelController.setCharacter(characters.get(characterNumber).getCharacter());
		level.init();
		levelController.init(level);

		levelController.setBinding();
		level.start();
		level.setMoveCallback(() -> levelController.sortChildren());
		FadeTransition fade = new FadeTransition();
		fade.setDuration(Duration.millis(1000));
		fade.setFromValue(100);
		fade.setToValue(0);
		fade.setCycleCount(1);
		fade.setNode(welcomePane);
		fade.play();
		ScheduledExecutorService executor = levelController.getLevel().getExecutor();
		executor.schedule(() -> Platform.runLater(() -> welcomePane.setVisible(false)), 1000, TimeUnit.MILLISECONDS);
	}

	public int getSoundLevel() {
		return soundLevel;
	}

	public void setSoundLevel(int soundLevel) {
		this.soundLevel = soundLevel;
	}

	public int getMusicLevel() {
		return musicLevel;
	}

	public void setMusicLevel(int musicLevel) {
		this.musicLevel = musicLevel;
	}

	public void setSettingsController(SettingsController settingsController) {
		this.settingsController = settingsController;
	}

	public void init() {
		level = new FirstLevel();
		levelController.setLevel(level);
		mediaInit();
		playButtonInit();
		closeButtonInit();
		minimizeButtonInit();
		characters = new ArrayList<>();
		Character knight = new Character(CharacterType.KNIGHT);
		Image knightImage = new Image("image/welcome/knight.gif");
		Character archer = new Character(CharacterType.ARCHER);
		Image archerImage = new Image("image/welcome/archer.gif");
		Character wizard = new Character(CharacterType.WIZARD);
		Image wizardImage = new Image("image/welcome/wizard.gif");
		CharacterView knightView = new CharacterView(knight, knightImage);
		CharacterView archerView = new CharacterView(archer, archerImage);
		CharacterView wizardView = new CharacterView(wizard, wizardImage);
		characters.add(knightView);
		characters.add(archerView);
		characters.add(wizardView);
		setOptionButton();
		changeVolume();

	}

	private void setOptionButton() {

		RotateTransition rt = new RotateTransition(Duration.millis(250), gearWheel);
		gearWheel.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				rt.setByAngle(10);
				rt.setCycleCount(1);
				rt.play();
			}
		});

		gearWheel.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				rt.setByAngle(-10);
				rt.setCycleCount(1);
				rt.play();

			}
		});

	}

	public void setLevelController(LevelController levelController) {
		this.levelController = levelController;
	}

	public void onExitedLeftButton() {
		Image image = new Image("image/welcome/left-arrow-button.png");
		leftButton.setImage(image);
	}

	public void onEnteredLeftButton() {
		buttonClickMedia();
		Image image = new Image("image/welcome/left-arrow-button-entered.png");
		leftButton.setImage(image);
	}

	public void onExitedRightButton() {
		Image image = new Image("image/welcome/right-arrow-btn.png");
		rightButton.setImage(image);
	}

	public void onEnteredRightButton() {
		buttonClickMedia();
		Image image = new Image("image/welcome/right-arrow-btn-entered.png");
		rightButton.setImage(image);
	}

	public void buttonClickMedia() {
		File file = new File("res/music/button6.wav");
		Media media = new Media(file.toURI().toString());
		mediaPlayerOnClick = new MediaPlayer(media);
		mediaPlayerOnClick.setVolume(soundLevel / 10.0);
		mediaPlayerOnClick.play();
	}

	private void mediaInit() {
		File file = new File("res/music/Bustling-Ancient-City.mp3");
		Media media = new Media(file.toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setVolume(Math.min(soundLevel / 10.0, musicLevel / 10.0));
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.setCycleCount(100);
		mediaPlayer.play();
	}

	private String getImagePath(String id, String suffix) {
		return "image/welcome/" + id + "-" + suffix + ".png";
	}

	public void onLeftButton() {
		if (characterNumber == 0) {
			characterNumber = 2;
			characterImage.setImage(characters.get(characterNumber).getImage());
			getCharacter(characterNumber);

		} else {
			characterImage.setImage(characters.get(--characterNumber).getImage());
			getCharacter(characterNumber);

		}
	}

	public void onRightButton() {
		if (characterNumber == 2) {
			characterNumber = 0;
			getCharacter(characterNumber);
			characterImage.setImage(characters.get(characterNumber).getImage());
		} else {
			characterImage.setImage(characters.get(++characterNumber).getImage());
			getCharacter(characterNumber);
		}

	}

	public void getCharacter(int number) {
		charName.setImage(new Image(
				getImagePath(characters.get(characterNumber).getCharacter().getType().toString().toLowerCase(),
						"name")));
		charDescription.setImage(new Image(
				getImagePath(characters.get(characterNumber).getCharacter().getType().toString().toLowerCase(),
						"description")));
	}

	public AnchorPane getWelcomePane() {
		return welcomePane;
	}

	private void minimizeButtonInit() {
		this.minimizeView.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				minimizeView.setImage(new Image("/image/window/line.png"));
			}
		});

		this.minimizeView.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				minimizeView.setImage(new Image("/image/window/line-entered.png"));
			}
		});

		minimizeView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				levelController.getStage().setIconified(true);

			}
		});
	}

	private void closeButtonInit() {
		this.crossView.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				crossView.setImage(new Image("/image/window/cross-entered.png"));
			}
		});

		this.crossView.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				crossView.setImage(new Image("/image/window/cross.png"));
			}
		});

		crossView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.exit(0);
			}
		});
	}

	private void playButtonInit() {
		this.playButton.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				playButton.setImage(new Image("/image/welcome/play-btn.png"));
			}
		});

		this.playButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				playButton.setImage(new Image("/image/welcome/play-btn-entered.png"));
				buttonClickMedia();
			}
		});

		playButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				onPlay();
			}
		});
	}

	public void clickOnGear() {
		settingsController.open();
	}

	public AbstractLevel getLevel() {
		return level;
	}
}
