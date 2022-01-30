pipeline {
  agent {
    docker {
     image 'postman/newman'
     args '--entrypoint='
    }
  }
  stages {
    stage('Test API') {
      steps {
        sh 'newman --version'
        sh 'newman run https://www.getpostman.com/collections/658854deae123990fbae'
      }
    }
  }
  post {
    success {
      echo 'The Pipeline success.'
    }
    failure {
      script {
        echo 'The Pipeline failed.'
      }
    }
  }
}
