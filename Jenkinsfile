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
		        bat "${mavenHome}/bin/mvn spotbugs:spotbugs"
		        bat "${mavenHome}/bin/mvn pmd:pmd"
		    }
		}
		stage('Test'){
			steps{
				bat "${mavenHome}/bin/mvn test"
			}
		}
		// stage('Deploy') {
		// 	steps {
		// 	    	bat "${mavenHome}/bin/mvn jar:jar deploy:deploy"
		// 	}
		// }
	}
}
