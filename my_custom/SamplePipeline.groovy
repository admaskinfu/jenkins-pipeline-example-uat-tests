pipeline {
    agent any

    parameters {
        string(name: 'MY_STRING', defaultValue: '', description: 'A string parameter')
        booleanParam(name: 'MY_BOOL', defaultValue: false, description: 'A boolean parameter')
        string(name: 'branch', defaultValue: 'main', description: 'Branch to check out')
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source...'
                echo "Branch to check out: ${params.branch}"
                echo "String parameter: ${params.MY_STRING}"
                echo "Boolean parameter: ${params.MY_BOOL}"
                checkout scm: [$class: 'GitSCM', branches: [[name: params.branch]], userRemoteConfigs: scm.userRemoteConfigs]
            }
        }

        stage('Run Sample Script') {
            steps {
                echo 'Running sample script from this branch...'
                sh 'bash my_custom/sample-script.sh'
            }
        }

        stage('Build') {
            steps {
                echo 'Running build...'
                echo 'Build completed successfully.'
            }
        }

        stage('Test') {
            parallel {
                stage('Unit Tests') {
                    steps {
                        echo 'Running unit tests...'
                        echo 'Unit tests passed.'
                    }
                }
                stage('Lint') {
                    steps {
                        echo 'Running lint...'
                        echo 'Lint passed.'
                    }
                }
                stage('Security Scan') {
                    steps {
                        echo 'Running security scan...'
                        echo 'Security scan completed.'
                    }
                }
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging artifact...'
                echo 'Package ready.'
            }
        }

        stage('Deploy') {
            parallel {
                stage('Deploy to Staging') {
                    steps {
                        echo 'Deploying to staging...'
                        echo 'Staging deploy done.'
                    }
                }
                stage('Deploy to QA') {
                    steps {
                        echo 'Deploying to QA...'
                        echo 'QA deploy done.'
                    }
                }
            }
        }

        stage('Notify') {
            steps {
                echo 'Sending notifications...'
                echo 'Pipeline complete.'
            }
        }
    }
}
