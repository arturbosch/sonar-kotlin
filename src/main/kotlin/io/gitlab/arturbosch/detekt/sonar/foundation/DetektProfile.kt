package io.gitlab.arturbosch.detekt.sonar.foundation

import io.gitlab.arturbosch.detekt.sonar.rules.DetektRuleKey
import io.gitlab.arturbosch.detekt.sonar.rules.ruleKeys
import io.gitlab.arturbosch.detekt.sonar.rules.severityTranslations
import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition

typealias RuleContext = BuiltInQualityProfilesDefinition.Context

class DetektProfile : BuiltInQualityProfilesDefinition {

    override fun define(context: RuleContext) {
        registerProfile(context, DETEKT_WAY, ruleKeys.filter { it.active }, isDefault = true)
        registerProfile(context, DETEKT_FAIL_FAST, ruleKeys, isDefault = false)
    }

    private fun registerProfile(
        context: RuleContext,
        name: String,
        rules: List<DetektRuleKey>,
        isDefault: Boolean
    ) {
        val profile = context.createBuiltInQualityProfile(name, LANGUAGE_KEY)
        profile.isDefault = isDefault

        for (ruleKey in rules) {
            val severity = severityTranslations[ruleKey.issue.severity]
                ?: error("Unexpected severity '${ruleKey.issue.severity}'")
            profile.activateRule(ruleKey.repository(), ruleKey.rule())
                .overrideSeverity(severity)
        }

        profile.done()
    }
}
