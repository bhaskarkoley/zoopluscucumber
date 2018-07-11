## Automated web tests using Serenity, Cucumber and Maven, Selenium Grid, Parallel Testing

A simple example of some BDD-style automated acceptance criteria 

Run the tests like this:

```
mvn clean verify
```

By default, the tests run with Chrome, so you will need this installed. Otherwise, if you prefer Firefox, modify the serenity.properties file or run the tests like this:
```
mvn clean verify -Dwebdriver.driver=firefox
```

The reports will be generated in `target/site/serenity`.

1.) cucumber Test:

The solution is designed based on the popular opensource Framework called Serenity BDD Framework (http://thucydides.info/docs/serenity-staging/) .
The Feature file is written under:
features/category/facet_filter.feature

The step definitions are available under:
cucumber-webtests/src/test/java/net/thucydides/showcase/cucumber/steps

The Page objects are defined in:
cucumber-webtests/src/test/java/net/thucydides/showcase/cucumber/pages

- run against multiple shops:
	I would pass the shop as a maven parameter using : 
```
		-Dwebdriver.base.url=https://www.wolf-of-wilderness.com
```
		In the current project the properties are defined in the serenities.properties file.
		In Jenkins we will call the project multiple times with different base.url.
		
- select multiple languages:
	The Locale will be passed as a maven parameter using:
```
		-Dlang=DE
 ```
		The project uses the module resourcebundle to read the key/value pairs of the language locale.
		Once we have all the labels then we can substitute them during the call. The implementation part is missing currently.
		for eg: 
			in File labels_nl_NL.properties the 
				key "Lamm" will fetch value "Lam"
			
- test 15 different category:
	The feature uses a scenario outline to test 15 different examples. As seen below i have tried not to repeat the code and expanded the test by using Examples/Scenario outline.
	For brevity i haven't included all examples here:

```
  Scenario Outline: Facet filter enabled
    Given I have a facet filter "<facet>" enabled for category "<category>" using link "<link>"
    Then facet filter should be enabled
    Then the top header highlighting is correct
  Examples:
    | facet    | category       | link                                                                      |
    | Lamm     | Nassfutter     | shop/de/nassfutter/c/CZ533500?q=%3Arelevance%3Aflavour%3ALamm&text=       |
    | Hase     | Probierpakete  | shop/de/probierpakete/c/CZ533594?q=%3Arelevance%3Aflavour%3AHase&text=    |
```

	For asserting the "top header highlighting" and "Facet filter" selection, 
	- i have used the webelementfacade to findAll li and then find child "input=checkbox" with the label "Lamm" (for eg) and then assert if its selected
	
	- How to do you prefer to choose as a test execution input parameter?
	the Testdata is driven by EXAMPLES defined in the feature file. At the moment i am using facet, category and link as the input criterion. The base url and language are defined globally for each run.
	
	- What do you prefer to choose as a step parameter?
	Yes i will use the examples as suggested above.
	
	Why?
	To try not to repeat the code and tests are driven by Testdata
	
2.) Selenium grid:

	- To launch selenium grid i installed locally the selenium standalone server
	and using a startgrid.sh i start the grid
```  
		HUB_URL=http://localhost:4444/grid/register
		CHROME_DRIVER_LOC=/home/osboxes/selenium/chromedriver
		java -jar selenium-server-standalone-3.13.0.jar -role hub &
		java -jar selenium-server-standalone-3.13.0.jar -role node -hub $HUB_URL -port 5556 &
		java -jar selenium-server-standalone-3.13.0.jar -role node -hub $HUB_URL -port 5557 &
```

	- to run the scenarios parallely i updated the failsage plugin in the pom.xml. The solution is based on https://johnfergusonsmart.com/running-parallel-tests-serenity-bdd-cucumber/
```  
            <plugin>
                <artifactId>maven-failsafe-plugin</artifactId>
                <version>2.18</version>
                <configuration>
                    <includes>
                        <include>**/cucumber/junit/**/*.java</include>
                        <include>**/cucumber/*.java</include>
                    </includes>
                    <parallel>classes</parallel>
                    <threadCount>2</threadCount>
                    <perCoreThreadCount>true</perCoreThreadCount>
                    <argLine>-Xmx1024m -XX:MaxPermSize=256m</argLine>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>integration-test</goal>
                            <goal>verify</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
	```
		This will run your feature runner classes in parallel. 
		Next, we need to configure the cucumber-jvm-parallel-plugin to generate these runners. The configuration will look something like this:

```
           <plugin>
                <groupId>com.github.temyers</groupId>
                <artifactId>cucumber-jvm-parallel-plugin</artifactId>
                <version>4.2.0</version>
                <executions>
                    <execution>
                        <id>generateRunners</id>
                        <phase>generate-test-sources</phase>
                        <goals>
                            <goal>generateRunners</goal>
                        </goals>
                        <configuration>
                            <!-- Mandatory -->
                            <!-- List of package names to scan for glue code. -->
                            <glue>
                                <package>net.thucydides.showcase.cucumber.steps</package>
                            </glue>
                            <parallelScheme>SCENARIO</parallelScheme>
                            <customVmTemplate>src/test/resources/cucumber-serenity-runner.vm</customVmTemplate>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
```
		A custom template to use to generate the test runners called cucumber-serenity-runner.vm is created and is placed under src/test/resources directory. 
		
		- to run the cucumber tests i will simply invoke:
```    
		mvn verify -Dwebdriver.remote.url=http://localhost:4444/wd/hub -Dwebdriver.remote.driver=chrome -Dwebdriver.remote.os=LINUX -Dwebdriver.base.url=https://www.wolf-of-wilderness.com -Dlang=DE
```
		The chromedriver has to be defined and also in the serenity.properties file.
		
3.) For running the tests in Jenkins, i installed the following:
			- Jenkins itself
			- selenium grid	
			- Chromedriver plugin
		
		Configured the Selenium grid to have 1 hub and 2 nodes.
		the following jenkinsfile will then run the tests in Jenkins

```
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
		
```		

Questions:
- How would you improve your solution further?
		- Configure the test to use several different browser and device combinations. see Browsercombos.json
		- Configure the test to run in both "local" or "jenkins" mode by using a marker file "local.json"
		- Since the menu changes with the window size, adapt the menu item search based on the browser size / window

- Which improvements idea would you implement first.
	- I will implement first the menu adaptation feature based on the browser
				


