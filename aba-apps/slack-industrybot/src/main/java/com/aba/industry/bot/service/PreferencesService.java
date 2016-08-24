package com.aba.industry.bot.service;

import com.aba.industry.bot.commands.PreferencesCommands;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;

/**
 * Created by mm66053 on 8/24/2016.
 */
public interface PreferencesService {
    void processCommand ( PreferencesCommands command, SlackMessagePosted event );
}
