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
				sh "${mavenHome}/bin/mvn clean install -DskipTests"
			}
		}
		stage('Test'){
			steps{
				sh "${mavenHome}/bin/mvn test"
			}
		}
		stage('Deploy') {
			steps {
			    	sh "${mavenHome}/bin/mvn jar:jar deploy:deploy"
			}
		}
	}
}
