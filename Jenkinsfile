pipeline {
	agent any
	
	environment {
		mavenHome = tool 'jenkins-maven-3.8.5'
	}
	
	tools {
		jdk 'jdk-11.0.11'
	}
	
	stages {
		stage('Build') {
		  	steps {
				bat "${mavenHome}/bin/mvn clean install -DskipTests"
			}
		}
		
		stage('Static Code Analysis') {
		    steps {
			// Run the static code analysis plugins
			// Checkstyle
			bat "${mavenHome}/bin/mvn checkstyle:checkstyle"
			
			// Publish Checkstyle report
		        publishHTML(target: [
		            allowMissing: false,
		            alwaysLinkToLastBuild: true,
		            keepAll: true,
		            reportDir: 'target/site/',
		            reportFiles: 'checkstyle.html',
		            reportName: 'Checkstyle Report',
		        ])

			// PMD
			bat "${mavenHome}/bin/mvn pmd:pmd"
			
			// Publish PMD report
		        publishHTML(target: [
		            allowMissing: false,
		            alwaysLinkToLastBuild: true,
		            keepAll: true,
		            reportDir: 'target/site/',
		            reportFiles: 'pmd.html',
		            reportName: 'PMD Report',
		        ])
		     }
		}

		stage('Test and Code Coverage') {
	            steps {
	                // Run tests and generate code coverage data with JaCoCo
	                bat "${mavenHome}/bin/mvn clean test jacoco:report"

	                // Archive the JaCoCo code coverage report
                    	archiveArtifacts(artifacts: '**/target/site/jacoco/index.html', allowEmptyArchive: true)

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
