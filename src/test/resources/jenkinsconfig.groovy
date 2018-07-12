node {

    git url: 'https://github.com/bhaskarkoley/zoopluscucumber.git', branch: 'master'

    // set up Java:

    env.JAVA_HOME="/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.161-0.b14.el7_4.x86_64/jre"
    env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
    // set up Maven:

    env.M2_HOME  = "/usr/share/maven"
    // execute tests and produce reports:

    sh "/usr/share/maven/bin/mvn -B -e -q -X clean verify -s mvn-settings.xml -Dwebdriver.remote.url=http://localhost:4444/wd/hub  -Dwebdriver.chrome.driver=$JENKINS_HOME/tools/chromedriver -Dwebdriver.remote.driver=chrome -Dwebdriver.base.url=https://www.wolf-of-wilderness.com -Dlang=DE"
    // publish the Serenity report

    publishHTML(target: [
            reportName : 'Serenity',
            reportDir:   'target/site/serenity',
            reportFiles: 'index.html',
            keepAll:     true,
            alwaysLinkToLastBuild: true,
            allowMissing: false
    ])
}