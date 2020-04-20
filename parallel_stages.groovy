#!groovy

pipeline {
    agent {
        node {
            label 'master'
			customWorkspace "workspace/${env.BUILD_TAG}"
        }
    }

    options {
        timestamps()
    }

    stages {
        stage('Check for Signed-Off Commits') {
            steps {
                sh ' echo This is Check for Signed-Off Commits stage  '
            }
        }

        stage('Run Lint') {
            steps {
                sh ' echo Run Lint stage'
            }
        }
 
		
		stage("CreateRepo") {
			steps {
				parallel (
					"Direct Model Build" : {
						script
							{
								sh ' echo Direct Model Build'
							}
					},
					"Fabric proxy model Build" : {
						script
							{
								sh ' echo Direct Model Build'
							}
					}
				)
			}
		}

        stage('Run Avalon Tests') {
            steps {
                sh 'echo Run Avalon Tests stage'
            }
        }

        stage('Create git archive') {
            steps {
                sh 'echo Create git archive'
            }
        }

    }

    post {
        success {
            echo ' Success '
        }
        aborted {
            echo ' Aborted '
        }
        failure {
            echo ' Failure '
        }
    }
}
