package com.noreastergames.corporaterun.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

public class Fighter extends Entity {
	
	private int hp = 10;
	private int defense = 2;
	private int power = 2;

	public Fighter(Rectangle rectangle, Texture texture, GridPoint2 gridPoint) {
		super(rectangle, texture, gridPoint);
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}
}
