/*
 * Copyright (C) 2015 Aeranythe Echosong
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package world;

import java.util.List;

/**
 *
 * @author Aeranythe Echosong
 */
public class PlayerAI extends CreatureAI {

    private List<String> messages;

    public PlayerAI(Creature creature, List<String> messages) {
        super(creature);
        this.messages = messages;
    }

    @Override
    public void onEnter(int x, int y, Tile tile) {
        if (tile.isGround()) {
            creature.setX(x);
            creature.setY(y);
        } else if (tile.isDiggable()) {
            creature.dig(x, y);
        }
    }

    @Override
    public void onNotify(String message) {
        this.messages.add(message);
    }

    @Override
    public void onUpdate() {
    }

    @Override
    public void attack(Creature another) {
        int damage = Math.max(0, this.creature.attackValue() - another.defenseValue());
        damage = (int) (Math.random() * damage) + 1;

        another.modifyHP(-damage);

        this.creature.notify("You attack the '%s' for %d damage.", another.glyph(), damage);
        another.notify("The '%s' attacks you for %d damage.", this.creature.glyph(), damage);

    }
}
