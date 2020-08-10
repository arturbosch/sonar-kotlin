package io.gitlab.arturbosch.detekt.sonar.rules

import io.gitlab.arturbosch.detekt.api.Severity

internal val severityTranslations: Map<Severity, String> = mapOf(
    Severity.CodeSmell to org.sonar.api.rule.Severity.MAJOR,
    Severity.Defect to org.sonar.api.rule.Severity.CRITICAL,
    Severity.Maintainability to org.sonar.api.rule.Severity.MAJOR,
    Severity.Minor to org.sonar.api.rule.Severity.MINOR,
    Severity.Security to org.sonar.api.rule.Severity.BLOCKER,
    Severity.Style to org.sonar.api.rule.Severity.INFO,
    Severity.Warning to org.sonar.api.rule.Severity.INFO,
    Severity.Performance to org.sonar.api.rule.Severity.CRITICAL
)
