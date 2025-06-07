package com.github.torverntheog.trsingularity.main.uniques;

import com.github.manasmods.tensura.ability.skill.Skill;

// This is a class to create a Unique skill, you should always extend Skill for it.
public class UniqueSkill extends Skill {
    // This is where u define what type of skill it is, a unique, ultimate or other.
    public UniqueSkill() {
        super(SkillType.UNIQUE);
    }
}
