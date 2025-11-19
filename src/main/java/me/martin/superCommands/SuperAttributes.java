package me.martin.superCommands;

public class SuperAttributes {
    private boolean immortal;
    private boolean invulnerable;
    private boolean noHunger;

    public SuperAttributes() {
        this.immortal = false;
        this.invulnerable = false;
        this.noHunger = false;
    }

    public boolean isGodmode() {
        return immortal && invulnerable && noHunger;
    }

    public void setGodmode(boolean godmode) {
        this.immortal = godmode;
        this.invulnerable = godmode;
        this.noHunger = godmode;
    }

    public boolean isImmortal() {
        return immortal;
    }

    public void setImmortal(boolean immortal) {
        this.immortal = immortal;
    }

    public boolean isInvulnerable() {
        return invulnerable;
    }

    public void setInvulnerable(boolean invulnerable) {
        this.invulnerable = invulnerable;
    }

    public boolean isNoHunger() {
        return noHunger;
    }

    public void setNoHunger(boolean noHunger) {
        this.noHunger = noHunger;
    }
}
