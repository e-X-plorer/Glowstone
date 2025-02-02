package net.glowstone.entity.projectile;

import net.glowstone.EventFactory;
import net.glowstone.entity.objects.GlowExperienceOrb;
import net.glowstone.net.message.play.entity.SpawnObjectMessage;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.ThrownExpBottle;
import org.bukkit.event.entity.ExpBottleEvent;

import java.util.concurrent.ThreadLocalRandom;

public class GlowThrownExpBottle extends GlowProjectile implements ThrownExpBottle {

    public GlowThrownExpBottle(Location location) {
        super(location);
    }

    @Override
    public void collide(Block block) {
        spawnOrb();
    }

    @Override
    public void collide(LivingEntity entity) {
        spawnOrb();
    }

    private void spawnOrb() {
        int xp = ThreadLocalRandom.current().nextInt(9) + 3;
        ExpBottleEvent event = EventFactory.getInstance()
                .callEvent(new ExpBottleEvent(this, xp));
        xp = event.getExperience();
        ExperienceOrb orb = (ExperienceOrb) world.spawnEntity(location, EntityType.EXPERIENCE_ORB);
        orb.setExperience(xp);
        if (orb instanceof GlowExperienceOrb) {
            ((GlowExperienceOrb) orb).setFromBottle(true);
        }
        remove();
    }

    @Override
    protected int getObjectId() {
        return SpawnObjectMessage.EXPERIENCE_BOTTLE;
    }
}
