package build

import static groovy.transform.TypeCheckingMode.SKIP

import groovy.transform.CompileStatic
import groovy.xml.XmlNodePrinter
import groovy.xml.XmlParser
import org.gradle.api.plugins.quality.CheckstyleExtension

class IdeaCheckstyleConfigUpdater {

    @CompileStatic(SKIP)
    static void updateSettings(File configFile, CheckstyleExtension checkstyle) {
        String toolVersion = checkstyle.toolVersion
        if (toolVersion == null || toolVersion.isBlank()) return

        Node rootNode = new XmlParser(false, false).parse(configFile)
        Node component = rootNode.component.find()
        if (component?.attribute('name') != 'CheckStyle-IDEA') return

        boolean isChanged = false
        component.option?.map?.entry?.forEach { Node entry ->
            if (entry.attribute('key') == 'checkstyle-version'
                && entry.attribute('value') != toolVersion
            ) {
                entry.attributes()['value'] = toolVersion
                isChanged = true
            }
        }

        if (isChanged) {
            StringWriter stringWriter = new StringWriter()
            XmlNodePrinter xmlNodePrinter = new XmlNodePrinter(new PrintWriter(stringWriter), '  ')
            xmlNodePrinter.print(rootNode)

            String content = stringWriter.toString().trim()
            configFile.setText(content, 'UTF-8')
        }
    }

}
