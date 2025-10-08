package name.remal.gradle_plugins.template;

import lombok.RequiredArgsConstructor;
import name.remal.gradle_plugins.toolkit.testkit.functional.GradleProject;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
class TemplatePluginFunctionalTest {

    final GradleProject project;

    @Test
    void helpTaskWorks() {
        project.getBuildFile().applyPlugin("name.remal.template-plugin");
        project.assertBuildSuccessfully("help");
    }

}
