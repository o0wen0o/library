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
			    
	                // Archive the Checkstyle code coverage report
	                archiveArtifacts(artifacts: '**/target/site/checkstyle/index.html', allowEmptyArchive: true)
			    
	                // Archive the PMD code coverage report
	                archiveArtifacts(artifacts: '**/target/site/pmd/index.html', allowEmptyArchive: true)
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
		        // Publish Checkstyle report
		        publishHTML(target: [
		            allowMissing: false,
		            alwaysLinkToLastBuild: true,
		            keepAll: true,
		            reportDir: 'target/site/checkstyle',
		            reportFiles: 'checkstyle-result.xml',
		            reportName: 'Checkstyle Report',
		        ])
		
		        // // Publish SpotBugs report
		        // publishHTML(target: [
		        //     allowMissing: false,
		        //     alwaysLinkToLastBuild: true,
		        //     keepAll: true,
		        //     reportDir: 'target/site',
		        //     reportFiles: 'spotbugsXml.xml',
		        //     reportName: 'SpotBugs Report',
		        // ])
		
		        // Publish PMD report
		        publishHTML(target: [
		            allowMissing: false,
		            alwaysLinkToLastBuild: true,
		            keepAll: true,
		            reportDir: 'target/site/pmd',
		            reportFiles: 'pmd.xml',
		            reportName: 'PMD Report',
		        ])

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
		
		// stage('Deploy') {
		// 	steps {
		// 	    	bat "${mavenHome}/bin/mvn jar:jar deploy:deploy"
		// 	}
		// }
	}
}
