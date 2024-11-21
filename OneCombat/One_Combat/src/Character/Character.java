
package Character;

import java.util.Random;

import Character.Components.CharacterAction;
import Character.Components.CharacterAiAction;
import Character.Components.CharacterAiCondition;
import Character.Components.CharacterAiHandler;
import Character.Components.CharacterAiMovement;
import Character.Components.CharacterAttack;
import Character.Components.CharacterCondition;
import Character.Components.CharacterData;
import Character.Components.CharacterEnergy;
import Character.Components.CharacterEntity;
import Character.Components.CharacterHealth;
import Character.Components.CharacterLocomotion;
import Character.Components.CharacterMovement;
import Character.Components.CharacterSound;
import Character.Components.CharacterUI;
import Character.Data.Ace;
import Character.Data.Hancock;
import Character.Data.Luffy;
import Character.Data.Nami;
import Character.Data.Robin;
import Character.Data.Sanji;
import Character.Data.Shanks;
import Character.Data.Usopp;
import Character.Data.Vivi;
import Character.Data.Zoro;
import Character.SpecialAttack.AceSpecialAttack;
import Character.SpecialAttack.HancockSpecialAttack;
import Character.SpecialAttack.LuffySpecialAttack;
import Character.SpecialAttack.NamiSpecialAttack;
import Character.SpecialAttack.RobinSpecialAttack;
import Character.SpecialAttack.SanjiSpecialAttack;
import Character.SpecialAttack.ShanksSpecialAttack;
import Character.SpecialAttack.UsoppSpecialAttack;
import Character.SpecialAttack.ViviSpecialAttack;
import Character.SpecialAttack.ZoroSpecialAttack;
import EntityComponent.EntityHandler;
import system.enumeration.Layer;
import system.enumeration.SortingLayer;
import system.object.component.animation.Animator;
import system.object.component.renderer.SpriteRenderer;
import system.object.component.rigidbody.Rigidbody;
import system.object.gameobject.GameObject;

public class Character extends GameObject {

	private float width = 0.32f;
	private float height = 0.66f;
	private float scale = 2;
	private CharacterData characterData;

//	public Character(float x) {
//		super();
//		sortingLayer = SortingLayer.Character;
//		characterData = new Zoro();
//		width *= scale;
//		height *= scale;
//		transform.setScale(scale);
//		renderer = new SpriteRenderer(this, transform);
//		rb = Rigidbody.CreateBoxBody(1f, width, height, 1f, false, 0f, Layer.Character, this, transform);
//		animator = new Animator(this, transform, "Zoro", (SpriteRenderer) renderer);
//		components.add(new CharacterLocomotion(this, transform));
//		// components.add(new CharacterInput(this, transform));
//		components.add(new CharacterMovement(this, transform, characterData));
//		components.add(new CharacterAttack(this, transform, characterData));
//		components.add(new CharacterHealth(this, transform, characterData));
//		components.add(new CharacterEnergy(this, transform, characterData));
//		components.add(new ZoroSpecialAttack(this, transform, characterData));
//		components.add(new CharacterEntity(this, transform, "Joueur"));
//		components.add(new CharacterCondition(this, transform));
//		components.add(new CharacterAction(this, transform));
//		components.add(new EntityHandler(this, transform));
//		components.add(new CharacterSound(this, transform, "Zoro"));
//		components.add(new CharacterAiMovement(this, transform));
//		components.add(new CharacterUI(this, transform, "Zoro"));
//	}
//
//	public Character(int k1, int k2, int k3) {
//		super();
//		sortingLayer = SortingLayer.Character;
//		width *= scale;
//		height *= scale;
//		characterData = new Luffy();
//		transform.setScale(scale);
//		renderer = new SpriteRenderer(this, transform);
//		rb = Rigidbody.CreateBoxBody(1f, width, height, 1f, false, 0f, Layer.Character, this, transform);
//		animator = new Animator(this, transform, "Luffy", (SpriteRenderer) renderer);
//		components.add(new CharacterLocomotion(this, transform));
//		components.add(new CharacterInput(this, transform, k1, k2, k3));
//		components.add(new CharacterMovement(this, transform, characterData));
//		components.add(new CharacterAttack(this, transform, characterData));
//		components.add(new CharacterHealth(this, transform, characterData));
//		components.add(new CharacterEnergy(this, transform, characterData));
//		components.add(new LuffySpecialAttack(this, transform, characterData));
//		components.add(new CharacterEntity(this, transform, "Joueur"));
//		components.add(new CharacterCondition(this, transform));
//		components.add(new CharacterAction(this, transform));
//		components.add(new EntityHandler(this, transform));
//		components.add(new CharacterSound(this, transform, "Luffy"));
//		components.add(new CharacterUI(this, transform, "Luffy"));
//	}

	public Character(String characterName, boolean isAi, String automateName) {
		super();
		sortingLayer = SortingLayer.Character;
		width *= scale;
		height *= scale;
		transform.setScale(scale);
		switch (characterName) {
		case "Luffy":
			characterData = new Luffy();
			components.add(new LuffySpecialAttack(this, transform, characterData));
			break;
		case "Ace":
			characterData = new Ace();
			components.add(new AceSpecialAttack(this, transform, characterData));
			break;
		case "Zoro":
			characterData = new Zoro();
			components.add(new ZoroSpecialAttack(this, transform, characterData));
			break;
		case "Sanji":
			characterData = new Sanji();
			components.add(new SanjiSpecialAttack(this, transform, characterData));
			break;
		case "Robin":
			characterData = new Robin();
			components.add(new RobinSpecialAttack(this, transform, characterData));
			break;
		case "Nami":
			characterData = new Nami();
			components.add(new NamiSpecialAttack(this, transform, characterData));
			break;
		case "Vivi":
			characterData = new Vivi();
			components.add(new ViviSpecialAttack(this, transform, characterData));
			break;
		case "Hancock":
			characterData = new Hancock();
			components.add(new HancockSpecialAttack(this, transform, characterData));
			break;
		case "Usopp":
			characterData = new Usopp();
			components.add(new UsoppSpecialAttack(this, transform, characterData));
			break;
		case "Shanks":
			characterData = new Shanks();
			components.add(new ShanksSpecialAttack(this, transform, characterData));
			break;
		default:
			characterData = new Luffy();
			components.add(new LuffySpecialAttack(this, transform, characterData));
		}
		renderer = new SpriteRenderer(this, transform);
		rb = Rigidbody.CreateBoxBody(1f, width, height, 1f, false, 0f, Layer.Character, this, transform);
		animator = new Animator(this, transform, characterName, (SpriteRenderer) renderer);
		components.add(new CharacterLocomotion(this, transform));
		components.add(new CharacterMovement(this, transform, this.characterData));
		components.add(new CharacterAttack(this, transform, this.characterData));
		components.add(new CharacterHealth(this, transform, this.characterData));
		components.add(new CharacterEnergy(this, transform, this.characterData));
		components.add(new EntityHandler(this, transform));
		components.add(new CharacterSound(this, transform, characterName));
		components.add(new CharacterUI(this, transform, characterName));
		components.add(new CharacterEntity(this, transform, automateName));

		if (!isAi) {
			components.add(new CharacterCondition(this, transform));
			components.add(new CharacterAction(this, transform));
			//components.add(new CharacterInput(this, transform));
		} else {
			components.add(new CharacterAiMovement(this, transform));
			components.add(new CharacterAiCondition(this, transform));
			components.add(new CharacterAiAction(this, transform));
			components.add(new CharacterAiHandler(this, transform));
		}
	}

	public static Character createPlayer(String characterName, String automate) {
		return new Character(characterName, false, automate);
	}

	public static Character createRandomCharacterAi(String automate) {
		String[] characters = { "Luffy", "Zoro", "Ace", "Sanji", "Robin", "Nami" };
		Random r = new Random();
		int i = r.nextInt(6);
		return new Character(characters[i], true, automate);
	}
}