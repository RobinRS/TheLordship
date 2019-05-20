package de.robinschleser.the12lords.funnystuff;

public class FunInitializer {

    private DiscordIntegration discord;

    //TODO: CREATE OBS INTEGRATION
    private ObsIntegration obs;

    public FunInitializer() {
        this.discord = new DiscordIntegration();
    }

    public DiscordIntegration getDiscord() {
        return discord;
    }
}
