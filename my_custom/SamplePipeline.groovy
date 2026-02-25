pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source...'
                echo 'Branch: main'
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
