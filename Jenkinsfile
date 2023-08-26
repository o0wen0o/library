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
		stage('Test'){
			steps{
				bat "${mavenHome}/bin/mvn test"
			}
		}

		stage('Publish Reports') {
		    steps {
		        // Publish Checkstyle report
		        publishHTML(target: [
		            allowMissing: false,
		            alwaysLinkToLastBuild: true,
		            keepAll: true,
		            reportDir: 'target/site',
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
		            reportDir: 'target/site',
		            reportFiles: 'pmd.xml',
		            reportName: 'PMD Report',
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
