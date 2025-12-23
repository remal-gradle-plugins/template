package name.remal.gradle_plugins.template;

import lombok.RequiredArgsConstructor;
import name.remal.gradle_plugins.toolkit.testkit.functional.GradleProject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
class TemplatePluginFunctionalTest {

    final GradleProject project;

    @BeforeEach
    void beforeEach() {
        project.forBuildFile(build -> {
            build.applyPlugin("name.remal.template-plugin");
        });
    }


    @Test
    void helpTaskWorks() {
        project.assertBuildSuccessfully("help");
    }

}
