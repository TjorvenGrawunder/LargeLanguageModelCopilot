package org.example.codellamacopilot.settings;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Objects;

public final class CopilotSettingsConfigurable implements Configurable {

    private CopilotSettingsComponent copilotSettingsComponent;


    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "Code Llama Copilot Settings";
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return copilotSettingsComponent.getPreferredFocusedComponent();
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        copilotSettingsComponent = new CopilotSettingsComponent();
        return copilotSettingsComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        CopilotSettingsState settings = CopilotSettingsState.getInstance();
        boolean modified = !copilotSettingsComponent.getCompletionAPITokenText().equals(settings.apiToken);
        modified |= !copilotSettingsComponent.getChatAPITokenText().equals(settings.chatApiToken);
        modified |= !Objects.equals(copilotSettingsComponent.getSelectedCompletionModel(), settings.usedModel);
        modified |= !Objects.equals(copilotSettingsComponent.getSelectedChatModel(), settings.usedChatModel);
        modified |= copilotSettingsComponent.getUseCompletion() != settings.useCompletion;
        modified |= !copilotSettingsComponent.getChatGPTSpecificSettings().equals(settings.chatGPTSpecificSettings);
        modified |= !copilotSettingsComponent.getPerplexityAISpecificSettings().equals(settings.perplexityAISpecificSettings);
        modified |= !copilotSettingsComponent.getHuggingFaceSpecificSettings().equals(settings.huggingFaceSpecificSettings);
        return modified;
    }

    @Override
    public void apply() {
        CopilotSettingsState settings = CopilotSettingsState.getInstance();
        settings.apiToken = copilotSettingsComponent.getCompletionAPITokenText();
        settings.chatApiToken = copilotSettingsComponent.getChatAPITokenText();
        settings.usedModel = copilotSettingsComponent.getSelectedCompletionModel();
        settings.usedChatModel = copilotSettingsComponent.getSelectedChatModel();
        settings.useCompletion = copilotSettingsComponent.getUseCompletion();
        settings.chatGPTSpecificSettings = copilotSettingsComponent.getChatGPTSpecificSettings();
        settings.perplexityAISpecificSettings = copilotSettingsComponent.getPerplexityAISpecificSettings();
        settings.huggingFaceSpecificSettings = copilotSettingsComponent.getHuggingFaceSpecificSettings();
    }

    @Override
    public void reset() {
        CopilotSettingsState settings = CopilotSettingsState.getInstance();
        //copilotSettingsComponent.setCompletionAPITokenText(settings.apiToken);
        //copilotSettingsComponent.setChatAPITokenText(settings.chatApiToken);
        copilotSettingsComponent.setSelectedModel(settings.usedModel);
        copilotSettingsComponent.setSelectedChatModel(settings.usedChatModel);
        copilotSettingsComponent.setUseCompletion(settings.useCompletion);
        copilotSettingsComponent.setChatGPTSpecificSettings(settings.chatGPTSpecificSettings);
        copilotSettingsComponent.setPerplexityAISpecificSettings(settings.perplexityAISpecificSettings);
        copilotSettingsComponent.setHuggingFaceSpecificSettings(settings.huggingFaceSpecificSettings);
    }

    @Override
    public void disposeUIResources() {
        copilotSettingsComponent = null;
    }
}
