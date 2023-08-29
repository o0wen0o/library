pipeline {
	agent any
	
	environment {
		mavenHome = tool 'jenkins-maven-3.8.5'
	}
	
	tools {
		jdk 'jdk-11.0.11'
	}
	
	stages {
		stage('Build'){
			steps {
				bat "${mavenHome}/bin/mvn clean install -DskipTests"
			}
		}
		
		stage('Static Code Analysis') {
		    steps {
		        // Run the static code analysis plugins
		        bat "${mavenHome}/bin/mvn checkstyle:checkstyle"
		        // bat "${mavenHome}/bin/mvn spotbugs:spotbugs"
		        bat "${mavenHome}/bin/mvn pmd:pmd"
	             }
		}

		stage('Test and Code Coverage') {
	            steps {
	                // Run tests and generate code coverage data with JaCoCo
	                bat "${mavenHome}/bin/mvn clean test jacoco:report"

	                // Archive the JaCoCo code coverage report
                    archiveArtifacts(artifacts: '**/target/site/jacoco/index.html', allowEmptyArchive: true)
	            }
	    }

		stage('Publish Reports') {
		    steps {
// 		        // Publish Checkstyle report
// 		        publishHTML(target: [
// 		            allowMissing: false,
// 		            alwaysLinkToLastBuild: true,
// 		            keepAll: true,
// 		            reportDir: 'target/site/',
// 		            reportFiles: 'checkstyle-result.html, pmd.html, jacoco.html',
// 		            reportName: 'Checkstyle Report',
// 		        ])
		
		        // // Publish SpotBugs report
		        // publishHTML(target: [
		        //     allowMissing: false,
		        //     alwaysLinkToLastBuild: true,
		        //     keepAll: true,
		        //     reportDir: 'target/site',
		        //     reportFiles: 'spotbugsXml.xml',
		        //     reportName: 'SpotBugs Report',
		        // ])
		
		 //        // Publish PMD report
		 //        publishHTML(target: [
		 //            allowMissing: false,
		 //            alwaysLinkToLastBuild: true,
		 //            keepAll: true,
		 //            reportDir: 'target/site/pmd',
		 //            reportFiles: 'pmd.html',
		 //            reportName: 'PMD Report',
		 //        ])

			// Publish JaCoCo code coverage report
		        publishHTML(target: [
		            allowMissing: false,
		            alwaysLinkToLastBuild: true,
		            keepAll: true,
		            reportDir: 'target/site/jacoco',
		            reportFiles: 'index.html',
		            reportName: 'JaCoCo Code Coverage Report',
		        ])
		    }
		}
	}
}
