pipeline {
	agent any
	environment {
		mavenHome = tool 'jenkins-maven-3.8.5'
		SONAR_TOKEN = credentials('automation')
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
