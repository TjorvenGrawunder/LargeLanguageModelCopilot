package org.example.codellamacopilot.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.intellij.util.xmlb.annotations.OptionTag;
import org.example.codellamacopilot.llamaconnection.RequestFormat;
import org.example.codellamacopilot.persistentconverter.RequestFormatConverter;
import org.jetbrains.annotations.NotNull;


@State(
        name = "org.example.codellamacopilot.settings.CopilotSettingsState",
        storages = @Storage("CodeLlamaCopilotSettings.xml")
)
public final class CopilotSettingsState implements PersistentStateComponent<CopilotSettingsState> {

    @OptionTag(converter = RequestFormatConverter.class)
    public RequestFormat usedModel;
    public String apiToken;
    public boolean useCompletion;

    public static CopilotSettingsState getInstance() {
        return ApplicationManager.getApplication().getService(CopilotSettingsState.class);
    }
    @Override
    public CopilotSettingsState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull CopilotSettingsState state) {
        XmlSerializerUtil.copyBean(state, this);
    }
}
